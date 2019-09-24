package com.cc.unionfind;

public class UnionFind3 implements UF {

    private int [] parent;
    private int [] siz;

    public UnionFind3(int size){
        parent = new int[size];
        siz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            siz[i] = 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot){
            return;
        }

        if(siz[pRoot]< siz[qRoot]) {
            parent[pRoot] = qRoot;
            siz[qRoot] += siz[pRoot];
        }
        else{
            parent[qRoot] = pRoot;
            siz[pRoot] += siz[qRoot];
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p){
        if(p < 0 || p>=parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        while(p!=parent[p]){
            p = parent[p];
        }
        return p;
    }
}
