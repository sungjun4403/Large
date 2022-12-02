package com.project.large.gitAPI.service;

import com.project.large.gitAPI.dto.AnnotationCreate;
import com.project.large.gitAPI.entity.Annotation;
import com.project.large.gitAPI.repository.AnnotationRepository;
import com.project.large.member.dto.MemberCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


@Service
@RequiredArgsConstructor
public class GitService {
    private final AnnotationRepository annotationRepository;

    ArrayList<ArrayList<String>> HTML5 = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> CSS = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> Javascript = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> Python = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> Java = new ArrayList<ArrayList<String>>();

    HashMap<String, ArrayList<ArrayList<String>>> AnnotationData = new HashMap<String, ArrayList<ArrayList<String>>>(5);
    HashMap<String, ArrayList<String>> CodeDetector = new HashMap<String, ArrayList<String>>(5);

    @PostConstruct
    public void init() {
        HTML5.add(new ArrayList<String>(Arrays.asList("<!--", "-->")));
        CSS.add(new ArrayList<String>(Arrays.asList("/*", "*/")));
        Javascript.add(new ArrayList<String>(Arrays.asList("//")));
        Python.add(new ArrayList<String>(Arrays.asList("'''", "'''")));
        Python.add(new ArrayList<String>(Arrays.asList("#")));
        Java.add(new ArrayList<String>(Arrays.asList("//")));

        AnnotationData.put("HTML5", HTML5);
        AnnotationData.put("CSS", CSS);
        AnnotationData.put("Javascript", Javascript);
        AnnotationData.put("Python", Python);
        AnnotationData.put("Java", Java);


        CodeDetector.put("HTML5", new ArrayList<String>(Arrays.asList("html")));
        CodeDetector.put("CSS", new ArrayList<String>(Arrays.asList("css")));
        CodeDetector.put("Javascript", new ArrayList<String>(Arrays.asList("js")));
        CodeDetector.put("Python", new ArrayList<String>(Arrays.asList("py", "pyc", "ipynb")));
        CodeDetector.put("Java", new ArrayList<String>(Arrays.asList("java")));
    }


    public List getSourceFile(String path) throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/" + path);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");

        int responseCode = conn.getResponseCode();
        List<String> result = getResponse(conn, responseCode);

        conn.disconnect();
        return result;
    }


    private List<String> getResponse(HttpURLConnection conn, int responseCode) throws IOException {
        List<String> sb = new ArrayList<>();
        if (responseCode == 200) {
            try (InputStream is = conn.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    sb.add(line);
                }
            }
        }
        return sb;
    }


    public String DetectCodeLanguage(String path) {
        String[] splitResult = path.split("\\.");
        String extension = splitResult[splitResult.length-1];
        ArrayList<String> result = new ArrayList<>();

        CodeDetector.forEach((new BiConsumer<String, ArrayList<String>>() {
            @Override
            public void accept(String s, ArrayList<String> strings) {
                if (strings.contains(extension)) {
                    result.add(s);
                }
            }
        }));
        return result.get(0);
    }



    public void findAnnotation(List<String> getCode, String codeLanguage, String path) {
        ArrayList<ArrayList<String>> codeAnnotation = AnnotationData.get(codeLanguage); // [["'''", "'''"], ["#"]]
        List<String> gitIDnFilepath = detachGitIDnFilepath(path); // ["sungjun4403", "sungjun4403/O_baksa_go/main/OBSpjct/OBSapp/views.py"]

        getCode.forEach(new Consumer<String>() {
            @Override
            public void accept(String codeLine) {
                findAnnotationInLine(getCode, codeLine, codeAnnotation, gitIDnFilepath);
            }
        });
    }

    public void saveAnnotation(List<String> annotationToSave) {
        AnnotationCreate annotationCreate = AnnotationCreate.builder()
                .gitID(annotationToSave.get(0))
                .annotation(annotationToSave.get(1))
                .filepath(annotationToSave.get(2))
                .annotationStartsAt(annotationToSave.get(3))
                .annotationEndsAt(annotationToSave.get(4))
                .build();

        Annotation annotation = Annotation.builder()
                .gitID(annotationCreate.getGitID())
                .annotation(annotationCreate.getAnnotation())
                .filepath(annotationCreate.getFilepath())
                .annotationStartsAt(annotationCreate.getAnnotationStartsAt())
                .annotationEndsAt(annotationCreate.getAnnotationEndsAt())
                .build();

        annotationRepository.save(annotation);

    }

    public String findAnnotationInLine (List<String> getCode, String codeLine, ArrayList<ArrayList<String>> codeAnnotation, List<String> gitIDnFilepath) {
        for (ArrayList<String> ca : codeAnnotation) {
            String annotationStartsAt;
            String annotationEndsAt;

            if (ca.size() == 1) {
                if (codeLine.contains(ca.get(0))) {
                    annotationStartsAt = String.valueOf(getCode.indexOf(codeLine));
                    annotationEndsAt = annotationStartsAt;

                    List<String> toSave = createAnnotationInfo(codeLine, gitIDnFilepath.get(0), gitIDnFilepath.get(1), annotationStartsAt, annotationEndsAt);
                    saveAnnotation(toSave);
                }
            }
            else if (ca.size() == 2) {

            }
        }

        return "";
    }

    public List<String> createAnnotationInfo(String codeLine, String gitID, String filepath, String annotationStartsAt, String annotationEndsAt) { //List contains [gitID, annotation, filepath, annotationStartsAt, annotationEndsAt]
        List<String> result = new ArrayList<String>();
        String[] annotationSplit = codeLine.split("#");

        result.add(gitID);
        result.add(annotationSplit[annotationSplit.length-1].strip());
        result.add(filepath);
        result.add(annotationStartsAt);
        result.add(annotationEndsAt);

        return result;
    }

    public List<String> detachGitIDnFilepath (String path) { //path be like "sungjun4403/O_baksa_go/main/OBSpjct/OBSapp/views.py"
       String [] splitResult = path.split("/");
       List<String> result = new ArrayList<String>(2);
       result.add(splitResult[0]);
       result.add(path);

       return result;
    }


}
