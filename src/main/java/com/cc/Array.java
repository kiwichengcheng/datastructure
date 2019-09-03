package com.cc;

public class Array<E> {

    E[] data;

    int size;

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array(){
        this(10);
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public void add(int index, E e){

        if(index<0 || index> size){
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        if(size == getCapacity()){
            resize(2*data.length);
        }

        for(int i = size -1 ; i >= index ; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size ++;
     }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    public E getFirist(){
        return get(0);
    }

    public E getLast(){
        return get(size -1);
    }

     public E remove(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("remove failed. Require index >= 0 and index <= size.");
        }

        E ret = data[index];
        for(int i = index; i < size -1 ; i++){
            data[i] = data[i+1];
        }

        size --;
        data[size] = null;


        if(size == getCapacity()/4 && data.length/2 !=0){
            resize(data.length/2);
        }


        return ret;
     }


    public void resize(int capacity){
        E[] newdata = (E[])new Object[capacity];

        for(int i = 0 ; i < size ; i++){
            newdata[i] = data[i];
        }

        data = newdata;
    }

    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }

    public int find(E e){

        for (int i = 0 ; i < size ; i ++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    public E get(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
