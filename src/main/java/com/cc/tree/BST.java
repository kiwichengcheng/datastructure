package com.cc.tree;

import com.cc.ArrayStack;
import com.cc.Stack;
import com.cc.queue.LoopQueue;
import com.cc.queue.Queue;

public class BST<E extends Comparable<E>> {
    private class Node{
        E e;
        Node left;
        Node right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root,e);
    }

    private Node add(Node node, E e){
        if(node == null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)< 0){
            node.left = add(node.left,e);
        }else if(e.compareTo(node.e)> 0){
            node.right = add(node.right,e);
        }
        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    public boolean contains(Node node , E e){
        if(node == null){
            return false;
        }
        if(e.compareTo(node.e) == 0){
            return true;
        }else if(e.compareTo(node.e)< 0){
            return contains(node.left,e);
        }else {
            return contains(node.right,e);
        }
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public E floor(E e){
        if(root == null){
            return null;
        }
        Node floorNode = floor(root,e);
        if(floorNode != null)
            return floorNode.e;
        else
            return null;
    }

    private Node floor(Node node, E e){

        if(node == null){
            return null;
        }

        if(e.compareTo(node.e) == 0){
            return node;
        }else if(e.compareTo(node.e)< 0){
            if(node.left == null){
                return null;
            }
            return floor(node.left,e);
        }else {
            Node t = floor(node.right,e);
            if(t != null)
                return t;
            return node;
        }
    }


    public void preOrderNR(){
        Stack<Node> stack= new ArrayStack<Node>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left != null)
                stack.push(cur.left);
        }
    }

    public void levelOrder(){
        Queue<Node> queue = new LoopQueue<Node>();
        queue.enqueue(root);
        while(!queue.isEmpty()){
            Node cur = queue.dequeue();
            System.out.println(cur.e);
            if(cur.left!=null){
                queue.enqueue(cur.left);
            }
            if(cur.right!=null){
                queue.enqueue(cur.right);
            }
        }
    }

    public E minimum(){
        if(isEmpty()){
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
    }

    private Node minimum(Node e){
        if(e.left == null){
            return e;
        }else {
            return minimum(e.left);
        }
    }

    public E maximum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty");

        Node cur = root;
        while (cur.right!=null){
            cur = cur.right;
        }
        return cur.e;
    }

    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
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

    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.right = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){
        if(node == null){
            return null;
        }
        if(e.compareTo(node.e)< 0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e)> 0){
            node.right = remove(node.right,e);
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
            successor.left = node.left;
            successor.right = removeMin(node.right);
            node.left = node.right = null;
            return successor;
        }
    }


    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(new Integer(10));
        bst.add(new Integer(8));
        bst.add(new Integer(12));
        bst.add(new Integer(2));
        bst.add(new Integer(6));

        System.out.println(bst.minimum());
        System.out.println(bst.maximum());


        System.out.println(bst.floor(9));
    }
}
