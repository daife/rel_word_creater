package com.example.creator;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.creator.databinding.ActivityMainBinding;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	
	  private ActivityMainBinding binding;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		    binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
		    setSupportActionBar(binding.toolbar);

		    binding.fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
                   // collectwords();
                    //TextProcessor.main(txtToString("/storage/emulated/0/AAB/规则.txt"));
         saveAsFileWriter(TextProcessor.main(txtToString("/storage/emulated/0/AAB/规则.txt")),"/storage/emulated/0/AAB/生成.txt");
                    }
//
        }
        );
        

        }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
    
        private static void saveAsFileWriter(String content, String filePath) {
        FileWriter fwriter = null;
        try {
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(filePath/*, true*/);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private static String txtToString(String filePath){
        String fileAsString = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            fileAsString = sb.toString();
            System.out.println(fileAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileAsString;
            //System.out.println(fileAsString);
    }
    
    

    public static String generateJavaProgram(String inputText) {
        StringBuilder javaCode = new StringBuilder();
        String[] lines = inputText.split("\n");
        int caseNumber = 1;

        javaCode.append("public class StringSwitchExample {\n");
        javaCode.append("    public static void main(String[] args) {\n");
        javaCode.append("        String inputWord = \"ture\"; // Replace with your input word\n");
        javaCode.append("        String processedWord = processWord(inputWord);\n");
        javaCode.append("        System.out.println(\"Processed word: \" + processedWord);\n");
        javaCode.append("    }\n\n");

        javaCode.append("    public static String processWord(String word) {\n");
        javaCode.append("        switch (word) {\n");

        for (String line : lines) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                String suffix = parts[0];
                String replacement = parts[1];
                javaCode.append("            case ").append(caseNumber).append(":\n");
                if (suffix.isEmpty()) {
                    javaCode.append("                // Directly add \"").append(replacement).append("\"\n");
                } else {
                    javaCode.append("                if (word.endsWith(\"").append(suffix).append("\")) {\n");
                    javaCode.append("                    // Replace \"").append(suffix).append("\" with \"").append(replacement).append("\"\n");
                    javaCode.append("                    word = word.substring(0, word.length() - ").append(suffix.length()).append(") + \"").append(replacement).append("\";\n");
                    javaCode.append("                }\n");
                }
                javaCode.append("                break;\n");
                caseNumber++;
            }
        }

        javaCode.append("            default:\n");
        javaCode.append("                // Handle other cases\n");
        javaCode.append("                break;\n");
        javaCode.append("        }\n");
        javaCode.append("        return word;\n");
        javaCode.append("    }\n");
        javaCode.append("}\n");

        return javaCode.toString();
    }
        public static void collectwords() {
        String roots = "entrance, apply, mystery, transmission, commit";
        // 将单词字符串拆分为单词数组
        String[] wordArray = roots.split(", ");

        // 遍历单词数组并执行相关操作
        for (String word : wordArray) {
            new Thread(){
                @Override
                public void run() {

            
            HashMap<String, String> wordMap = new HashMap<>();
        HttpsUtils aa = new HttpsUtils();
            aa.sendOnce(word,wordMap);
            List<String> derivativesValues = new ArrayList<>();
        for (String value : wordMap.values()) {
            derivativesValues.add(value);
        }

        // 遍历派生词的值并执行 mypossess 方法
        for (String derivative : derivativesValues) {
                HttpsUtils bb = new HttpsUtils();
                bb.sendOnce(derivative,wordMap);
        }
                    WordDatabaseBuilder.main(word,wordMap);
                    }}.start();
            
    }
            
            //System.out.println("Processing word: " + word);
        }
    }
