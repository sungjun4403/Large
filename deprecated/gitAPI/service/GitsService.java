package com.project.large.deprecated.gitAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GitsService { //deprecated



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
        com.project.large.gitAPI.dto.AnnotationCreate annotationCreate = com.project.large.gitAPI.dto.AnnotationCreate.builder()
                .gitID(annotationToSave.get(0))
                .annotation(annotationToSave.get(1))
                .filepath(annotationToSave.get(2))
                .annotationStartsAt(annotationToSave.get(3))
                .annotationEndsAt(annotationToSave.get(4))
                .build();

        com.project.large.gitAPI.entity.Annotation annotation = com.project.large.gitAPI.entity.Annotation.builder()
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

                    List<String> toSave = createAnnotationInfo(ca.get(0), codeLine, gitIDnFilepath.get(0), gitIDnFilepath.get(1), annotationStartsAt, annotationEndsAt);
                    saveAnnotation(toSave);
                }
            }
            else if (ca.size() == 2) {

            }
        }

        return "";
    }

    public List<String> createAnnotationInfo(String annotationUsed, String codeLine, String gitID, String filepath, String annotationStartsAt, String annotationEndsAt) { //List contains [gitID, annotation, filepath, annotationStartsAt, annotationEndsAt]
        List<String> result = new ArrayList<String>();
        String[] annotationSplit = codeLine.split(annotationUsed);

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


    public void findAnnotationEasy(List<String> getCode, String codeLanguage, String path) {
        ArrayList<ArrayList<String>> codeAnnotation = AnnotationData.get(codeLanguage); // [["'''", "'''"], ["#"]]
        List<String> gitIDnFilepath = detachGitIDnFilepath(path); // ["sungjun4403", "sungjun4403/O_baksa_go/main/OBSpjct/OBSapp/views.py"]

        String wholeCode = getCode.toString();
        String guess = "#";
        ArrayList<Integer> unfilteredIndex = new ArrayList<>();

        for (int index = wholeCode.indexOf(guess);
             index >= 0;
             index = wholeCode.indexOf(guess, index + 1))
        {
            unfilteredIndex.add(index);
        }

        filterIfAnnotation(getCode, unfilteredIndex);

//            @Override
//            public void accept(String codeLine) {
//        getCode.forEach(new Consumer<String>() {
//                findAnnotationInLine(getCode, codeLine, codeAnnotation, gitIDnFilepath);
//            }
//        });
    }

    private List<Integer> filterIfAnnotation(List<String> getCode, ArrayList<Integer> unfilteredIndex) {


        return null;
    }