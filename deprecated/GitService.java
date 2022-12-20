package com.project.large.gitAPI.service;

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


}
