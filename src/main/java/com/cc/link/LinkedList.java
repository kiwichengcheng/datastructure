package com.cc.link;

public class LinkedList<E> {

    private class Node{
        E e;
        Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public void addFirst(E e){
        /*Node node = new Node(e);
        node.next = head;
        head = node;*/

        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public void add(int index, E e){
        if(index<0|| index>size){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        Node prev = dummyHead;
        for (int i =0 ; i < index ; i++){
            prev = prev.next;
        }
        prev.next = new Node(e,prev.next);

        size++;
    }

    public E get(int index){
        if(index<0|| index>=size){
            throw new IllegalArgumentException("get failed. Require index >= 0 and index < size.");
        }

        Node cur = dummyHead.next;

        for(int i = 0 ; i <index ; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public void set(int index, E e){
        if(index<0|| index>=size){
            throw new IllegalArgumentException("get failed. Require index >= 0 and index < size.");
        }

        Node cur = dummyHead.next;

        for(int i = 0 ; i <index ; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e){

        Node cur = dummyHead.next;

        while (cur!=null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    public E removeElement(int index){

        if(index<0 || index >= size){
            throw new IllegalArgumentException("remove failed. Require index >= 0 and index < size-1.");
        }

        Node prev = dummyHead;

        for(int i = 0 ; i < index ; i++){
            prev = prev.next;
        }
        Node ret = prev.next;
        prev.next = ret.next;
        ret.next = null;
        size--;
        return ret.e;

    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return removeElement(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return removeElement(size - 1);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        Node cur = dummyHead.next;

        while (cur!=null){
            stringBuilder.append(cur.e +"->");
            cur = cur.next;
        }
        stringBuilder.append("null");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);
        linkedList.removeElement(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
