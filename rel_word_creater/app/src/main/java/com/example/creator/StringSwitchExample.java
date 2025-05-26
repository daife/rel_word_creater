package com.example.creator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StringSwitchExample {
    public static void main(String[] args) {
        String inputWord = "ment"; // Replace with your desired input word
        String processedWord = processWord(inputWord);
        System.out.println("Processed word: " + processedWord);
    }

    public static String processWord(String word) {
        // Read rules from the file
        Map<String, String> suffixRules = readRulesFromFile("规则列表.txt");

        // Apply the rules
        for (String suffix : suffixRules.keySet()) {
            if (word.endsWith(suffix)) {
                word = word.substring(0, word.length() - suffix.length()) + suffixRules.get(suffix);
                break; // Apply only one rule
            }
        }

        return word;
    }

    public static Map<String, String> readRulesFromFile(String filename) {
        Map<String, String> rules = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String suffix = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    if (suffix == null) {
                        suffix = line;
                    } else {
                        rules.put(suffix, line);
                        suffix = null;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading rules from file: " + e.getMessage());
        }
        return rules;
    }
}
