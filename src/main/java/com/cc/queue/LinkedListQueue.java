package com.cc.queue;

import com.cc.link.LinkedList;

public class LinkedListQueue<E> implements Queue<E> {

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
    private Node tail;

    public LinkedListQueue(){
        dummyHead = new Node();
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if(tail == null){
            tail = new Node(e);
            dummyHead.next = tail;
        }else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty, dequeue failed");
        }
        Node ret = dummyHead.next;
        dummyHead.next = dummyHead.next.next;
        ret.next = null;
        if(dummyHead.next == null){
            tail = null;
        }
        size --;
        return ret.e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("queue is empty, dequeue failed");
        }
        return dummyHead.next.e;
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

    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        System.out.println(queue);
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }

        for(int i = 0 ; i < 7 ; i ++){
            queue.dequeue();
            System.out.println(queue);
        }
    }
}
