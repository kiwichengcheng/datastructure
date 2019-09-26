package com.cc.redblacktree;

import com.cc.FileOperation;
import com.cc.map.BSTMap;
import com.cc.map.Map;

import java.util.ArrayList;

public class RBTree<K extends Comparable<K>, V> implements Map<K,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }

    }
    private Node root;
    private int size;

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public RBTree(){
        root = null;
        size = 0;
    }

    private boolean isRed(Node node){
        if(node == null){
            return BLACK;
        }
        return node.color;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    //对应2-3树中添加的过程
    //    37:black
    //添加42
    //结果应为一个三节点
    //    37|42
    private Node leftRotate(Node node){
        Node x = node.right;
        //左旋转
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node){
        Node x= node.left;
        //右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //颜色翻转
    private void flipColor(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node balance(Node node){
        //红黑树性质维护
        if(isRed(node.right) && !isRed(node.left)){//左旋转
            node = leftRotate(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){//右旋转
            node = rightRotate(node);
        }

        if(isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        return node;
    }

    //向以node为根的红黑树插入节点，
    //返回新树的根
    private Node add(Node node, K key, V value){
        if(node == null){
            size++;
            return new Node(key,value); //默认插入红色节点
        }
        if(key.compareTo(node.key)< 0){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key)> 0){
            node.right = add(node.right,key,value);
        }else {
            node.value = value;
        }


        //红黑树性质维护
        if(isRed(node.right) && !isRed(node.left)){//左旋转
            node = leftRotate(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){//右旋转
            node = rightRotate(node);
        }

        if(isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        return node;
    }


    private Node moveRedLeft(Node h)
    {  // Assuming that h is red and both h.left and h.left.left
        // are black, make h.left or one of its children red.
        flipColor(h);
        if (isRed(h.right.left))
        {
            h.right = rightRotate(h.right);
            h = leftRotate(h);
            flipColor(h);
        }
        return h;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root,key);
        if(node != null) {
            root = remove(root, key);
            root.color = BLACK;
            return node.value;
        }
        return null;
    }


    private Node remove(Node node, K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key)< 0){
            node.left = remove(node.left,key);
            return node;
        }else if(key.compareTo(node.key)> 0){
            node.right = remove(node.right,key);
            return node;
        }else {
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    private Node minimum(Node e){
        if(e.left == null){
            return e;
        }else {
            return minimum(e.left);
        }
    }

    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key)!=null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node == null? null:node.value;
    }

    private Node getNode(Node node, K key){
        if(node == null){
            return null;
        }

        if(key.compareTo(node.key) == 0){
            return node;
        }else if(key.compareTo(node.key) < 0){
            return getNode(node.left,key);
        }else {
            return getNode(node.right,key);
        }
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if(node == null){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
