package com.example.creator;

public class StringSwitchGenerator {

    public static void main(String[] args) {
        String inputText = "ment\n\ns sure\nl lure\nt ture"; // 替换为您的输入文本
        String generatedCode = generateJavaSwitch(inputText);
        System.out.println(generatedCode);
    }

    public static String generateJavaSwitch(String inputText) {
        StringBuilder javaCode = new StringBuilder();
        String[] lines = inputText.split("\n");
        int caseNumber = 1;
        String suffix = null;
        String replacement = null;

        javaCode.append("public class StringSwitchExample {\n");
        javaCode.append("    public static void main(String[] args) {\n");
        javaCode.append("        String inputWord = \"ture\"; // 替换为您的输入单词\n");
        javaCode.append("        String processedWord = processWord(inputWord);\n");
        javaCode.append("        System.out.println(\"Processed word: \" + processedWord);\n");
        javaCode.append("    }\n\n");

        javaCode.append("    public static String processWord(String word) {\n");
        javaCode.append("        switch (word) {\n");

        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                suffix = parts[0];
                replacement = parts[1];
                javaCode.append("            case ").append(caseNumber).append(":\n");
                if (suffix.isEmpty()) {
                    javaCode.append("                // 直接添加 \"").append(replacement).append("\"\n");
                    javaCode.append("                word = word + \"").append(replacement).append("\";\n");
                } else {
                    javaCode.append("                if (word.endsWith(\"").append(suffix).append("\")) {\n");
                    javaCode.append("                    // 替换 \"").append(suffix).append("\" 为 \"").append(replacement).append("\"\n");
                    javaCode.append("                    word = word.substring(0, word.length() - ").append(suffix.length()).append(") + \"").append(replacement).append("\";\n");
                    javaCode.append("                }\n");
                }
                javaCode.append("                break;\n");
                caseNumber++;
            } else {
                // 处理其他情况
                javaCode.append("            default:\n");
                javaCode.append("                // 直接添加 \"").append(replacement).append("\"\n");
                javaCode.append("                word = word + \"").append(replacement).append("\";\n");
                javaCode.append("                break;\n");
            }
        }

        javaCode.append("        }\n");
        javaCode.append("        return word;\n");
        javaCode.append("    }\n");
        javaCode.append("}\n");

        return javaCode.toString();
    }
}
