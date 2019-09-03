package com.cc.set;

import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        FileOperation.readFile("pride-and-prejudice.txt",words1);
        System.out.println("Total words: "+words1.size());
        BSTSet<String> bstSet = new BSTSet<>();
        for (String word: words1)
            bstSet.add(word);

        System.out.println(bstSet.getSize());

        TreeSet set = new TreeSet();
    }



}
