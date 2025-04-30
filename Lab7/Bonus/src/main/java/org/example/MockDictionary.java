package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockDictionary  {
    List<String> words=new ArrayList<String>();

    public MockDictionary() {
        try {
            FileReader fileReader = new FileReader("words.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim();
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public int findWord(String word) {

        if(!words.contains(word.toLowerCase()) )
            return -1;
        return 1;
    }

    public List<String> findWordsWithPrefix(String prefix) {
        return words.parallelStream()
                .filter(word -> word.startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

}
