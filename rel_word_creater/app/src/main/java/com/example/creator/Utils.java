package com.example.creator;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.HashMap;

public class Utils {
    public static void jsonProcess(String jsonInput, HashMap<String, String> wordMap) {
        try {
            // 解析JSON输入
            JSONObject jsonObj = new JSONObject(jsonInput);

            // 提取相关单词（rel_word 部分）
            JSONObject relWord = jsonObj.getJSONObject("rel_word");
            JSONArray rels = relWord.getJSONArray("rels");

            // 遍历相关单词并将它们添加到HashMap中
            for (int i = 0; i < rels.length(); i++) {
            JSONObject rel = rels.getJSONObject(i).getJSONObject("rel");
            JSONArray words = rel.getJSONArray("words");
            String pos = rel.getString("pos"); // 获取词性
            for (int j = 0; j < words.length(); j++) {
                JSONObject wordObj = words.getJSONObject(j);
                String word = wordObj.getString("word");
                String definition = pos + " " + wordObj.getString("tran").trim();
                System.out.println("Word: " + word);
                System.out.println("Definition: " + definition);
                    // 将释义作为键，单词作为值添加到HashMap
                    wordMap.put(definition, word);
                }
            }
        } catch (Exception err) {
           // System.err.println("An error occurred: " + err.getMessage());
        }
    }

    public static void main(String input) {
        //String jsonInputExample = "{\"rel_word\": {\"rels\": [{\"rel\": {\"words\": [{\"word\": \"example\", \"tran\": \"示例\"}]}}]}}";
        HashMap<String, String> wordMapExample = new HashMap<>();
        jsonProcess(input, wordMapExample);

        // 打印HashMap以验证结果
        for (String definition : wordMapExample.keySet()) {
            System.out.println("Definition: " + definition + ", Word: " + wordMapExample.get(definition));
        }
    }
}