package com.cc.tree;
import java.util.TreeMap;

public class Trie {

    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    //添加单次非递归写法
    public void add(String word){
        Node cur = root;
        for (int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    //添加单次递归写法
    public void addR(String word){
        addR(word,root);
    }

    private void addR(String word, Node node){
        if(word.length() == 0){
            if(!node.isWord){
                node.isWord = true;
                size ++;
            }
            return;
        }

        if(node.next.get(word.charAt(0)) == null){
            node.next.put(word.charAt(0),new Node());
        }
        Node cur = node.next.get(word.charAt(0));
        addR(word.substring(1),cur);

    }

    public boolean contains(String word){
        Node cur = root;
        for (int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i = 0 ; i < prefix.length() ; i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    //简单的模式匹配 a..b，.表示一个字符
    public boolean match(String pattern){
        return match(pattern, root);
    }

    private boolean match(String pattern, Node node){
        if(pattern.length() == 0){
            return node.isWord;
        }

        char c = pattern.charAt(0);
        if(c != '.'){
            if(node.next.get(c) == null){
                return false;
            }
            return match(pattern.substring(1),node.next.get(c));
        }else{
            for(char nextChar: node.next.keySet()){
                if(match(pattern.substring(1), node.next.get(nextChar))){
                    return true;
                }
            }
            return false;
        }
    }


    public static void main(String[] args) {
        Trie trie1 = new Trie();
        trie1.add("Hel");
        trie1.add("Hon");
        System.out.println(trie1.contains("Hon"));
        System.out.println(trie1.isPrefix("Ho"));

        System.out.println(trie1.match("..l"));
    }
}
