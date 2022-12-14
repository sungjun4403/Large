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

    public void FlaskCrawlGitCommit(String gitID, Integer commits) throws IOException {
        URL url = new URL("http://127.0.0.1:5000/checkIfUpdated/"+ gitID + "/" + commits);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");

        int responseCode = conn.getResponseCode();
        List<String> result = getResponse(conn, responseCode);

        conn.disconnect();
    }

}
