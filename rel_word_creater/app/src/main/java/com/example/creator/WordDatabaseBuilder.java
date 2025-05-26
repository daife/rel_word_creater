package com.example.creator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDatabaseBuilder {
    public static void main(String root,Map<String, String> wordMap) {
        // 词根
        //String root = "represent";

        // 派生词和释义
        //Map<String, String> wordMap = new HashMap<>();
//        wordMap.put("典型的，有代表性的;代议制的", "representative");
//        wordMap.put("代表的;具象派的", "representational");
//        wordMap.put("能被代表的;能上演的;能被描绘的", "representable");
//        wordMap.put("代表;表现;表示法;陈述", "representation");
//
        // 创建一个包含派生词的列表
        List<Map<String, String>> derivativesList = new ArrayList<>();
        for (String definition : wordMap.keySet()) {
            String derivative = wordMap.get(definition);
            Map<String, String> derivativeMap = new HashMap<>();
            derivativeMap.put("word", derivative);
            derivativeMap.put("definition", definition);
            derivativesList.add(derivativeMap);
        }

        // 创建一个包含词根和派生词的数据结构
        Map<String, Object> wordDatabase = new HashMap<>();
        wordDatabase.put("root", root);
        wordDatabase.put("derivatives", derivativesList);

        // 将 wordDatabase 转换为 JSON 格式并保存到文件
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(wordDatabase);
        try (FileWriter writer = new FileWriter("/storage/emulated/0/AAC/"+root+".json")) {
            writer.write(json);
            System.out.println("数据已成功生成并保存到 文件中。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
