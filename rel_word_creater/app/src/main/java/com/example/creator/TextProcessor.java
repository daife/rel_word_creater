package com.example.creator;

import java.util.Scanner;

public class TextProcessor {

    public static String main(String inputText) {
        //Scanner scanner = new Scanner(System.in);
        //String inputText = scanner.nextLine();
        String result = "";
        String[] textSegments = inputText.split("\n\n");

         for (int i = 0; i < textSegments.length; i++) {
            String segment = textSegments[i];
            String[] rules = segment.split("\n");
            StringBuilder codeSnippet = new StringBuilder();

            for (int j = 0; j < rules.length; j++) {
                String rule = rules[j];
                String[] parts = rule.split(" ");
                String condition = parts[0];
                String replacement = parts[1];

                if (j == rules.length - 1) {
                    // Last rule, treat as else
                    codeSnippet.append("else {\n");
                } else {
                    codeSnippet.append("if (word.endsWith(\"").append(condition).append("\")) {\n");
                }

                codeSnippet.append("    word = word.substring(0, word.length() - ")
                            .append(condition.length()).append(") + \"").append(replacement).append("\";\n")
                            .append("} else ");

                if (j == rules.length - 1) {
                    codeSnippet.append("{\n    // This is the last else\n}\n");
                }
            }
            System.out.println("case " + (i + 1) + ":");
            System.out.println(codeSnippet.toString());
            System.out.println("    break;");
            result =result+"case " + (i + 1) + ":"+"\n"+codeSnippet.toString()+"\n"+"    break;"+"\n";
        }
        return result;
    }
}


