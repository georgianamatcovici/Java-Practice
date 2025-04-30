package org.example;

import org.graph4j.Digraph;
import org.graph4j.GraphBuilder;
import org.graph4j.traversal.DFSIterator;
import org.graph4j.traversal.SearchNode;

import javax.swing.tree.TreeNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dictionary {
   private Digraph<TrieNode, Character> myTrie = GraphBuilder.empty().buildDigraph();
   private  DFSIterator trieIterator;
   private List<String> wordsStartingWithPrefix;
    Dictionary()
    {
        myTrie.addLabeledVertex(0, new TrieNode("", true));
        try {
            FileReader fileReader = new FileReader("words.txt");
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim();
                if (!word.isEmpty()) {
                    addWord(word.toLowerCase());
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }



    }



    public TrieNode findWord(String word) {
        int v = 0;

        for (char c : word.toCharArray()) {
            int u = findChild(myTrie, v, c);
            if (u == -1) {
                return null;
            }
            v = u;
        }
        TrieNode node = myTrie.getVertexLabel(v);
        return node;
    }

    public void displayNodes()
    { int count=0;
        trieIterator=new DFSIterator(myTrie);
        while (trieIterator.hasNext()) {
            SearchNode currentNode=trieIterator.next();
            TrieNode currentTrieNode=myTrie.getVertexLabel(currentNode.vertex());
            System.out.println(currentTrieNode.text());
          count++;
          //  System.out.println(currentNode.vertex());
        }
        System.out.println(count);


    }



    public void findPrefix(String prefixWord)
    {
        wordsStartingWithPrefix=new ArrayList<>();
        int deepestNode=searchPrefix(prefixWord);
        if(deepestNode!=-1)
      //  System.out.println(deepestNode.text());
        dfs(deepestNode, prefixWord);


    }

    private int findChild(Digraph g, int v, Character c) {
        for (var it = g.neighborIterator(v); it.hasNext();) {
            int u = it.next();
            if (c.equals(it.getEdgeLabel())) {
                return u;
            }
        }
        return -1;
    }
    private int addWord(String word) {
        int v = 0;
        StringBuilder prefix = new StringBuilder();

        for (char c : word.toCharArray()) {
            prefix.append(c);
            int u = findChild(myTrie, v, c);
            if (u == -1) {
                boolean isWord = prefix.length() == word.length();
                TrieNode newNode = new TrieNode(prefix.toString(), isWord);
                u = myTrie.addLabeledVertex(newNode);
                myTrie.addLabeledEdge(v, u, c);
            } else {
                TrieNode existing = myTrie.getVertexLabel(u);
                if (prefix.length() == word.length() && !existing.isWord()) {
                    myTrie.setVertexLabel(u, new TrieNode(existing.text(), true));
                }
            }
            v = u;
        }

        return v;
    }

    private void dfs(int currentStartNode, String prefix) {

        trieIterator=new DFSIterator(myTrie, currentStartNode);
        while (trieIterator.hasNext()) {
            SearchNode currentNode=trieIterator.next();
            TrieNode currentTrieNode=myTrie.getVertexLabel(currentNode.vertex());
            if (currentTrieNode.isWord() && currentTrieNode.text().startsWith(prefix)) {

                System.out.println(currentTrieNode.text());
            }

        }

    }

    private int searchPrefix(String word)
    {

        int v = 0;

        for (char c : word.toCharArray()) {
            int u = findChild(myTrie, v, c);
            if (u == -1) {
                return -1;
            }
            v = u;
        }
        return v;


    }





}
