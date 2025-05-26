package com.example.creator;

import com.example.creator.Utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.stream.Collectors;

public class HttpsUtils {
    private HttpURLConnection conn;
    private BufferedReader reader;
    private String jsonResponse;

    public void sendOnce(String word,HashMap<String, String> wordMap) {
        word = word.toLowerCase();
        connect(word);
        send(wordMap);
        disconnect();
    }

    public void send(HashMap<String, String> wordMap) {
        // String params = langParam + "&" + "imgBase=base64," + base64Param;
        try {
            // conn.getOutputStream().write(params.getBytes());
            // conn.getOutputStream().flush();
            // System.out.println("don1");

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            jsonResponse = reader.lines().collect(Collectors.joining());
            // System.out.println("原始" + jsonResponse);
            Utils.jsonProcess(jsonResponse,wordMap);
            // System.out.println(result);

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void connect(String word) {
        try {
            // 创建 URL 对象
            URL url = new URL("https://dict.youdao.com/jsonapi?q=" + word
                    + "&dicts=%7B%22count%22%3A99%2C%22dicts%22%3A%5B%5B%22rel_word%22%5D%5D%7D");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // System.out.println("don0");

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void disconnect() {
        // 关闭连接
        try {
            reader.close();
        } catch (Exception err) {

        }
        conn.disconnect();
    }
}
