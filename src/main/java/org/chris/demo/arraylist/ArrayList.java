/*
 * Copyright (c) 2005-2018 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package org.chris.demo.arraylist;

import lombok.Data;

/**
 * 数组List
 *
 * add:             O(n)
 * delete:          O(n)
 * find:            O(n)
 * getByIndex:      O(1)
 *
 * @author caizq
 * @date 2019/2/11
 * @since v1.0.0
 */
@Data
public class ArrayList<T> {
    private Object[] dataArrays;
    private int size;
    
    public ArrayList(int length){
        dataArrays = new Object[length];
    }
    
    public void add(T data){
        expandIfNecessary();
        dataArrays[size++]=data;
    }

    private void expandIfNecessary() {
        if (size+1>dataArrays.length) {
            //扩容
            Object[] copy = new Object[dataArrays.length<<1];
            System.arraycopy(dataArrays,0,copy,0,size);
            dataArrays=copy;
        }
    }

    public void printAll(){
        for (Object dataArray : dataArrays) {
            System.out.println(dataArray);
        }
    }

    public void remove(int index){
        if (index>size||index<0) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(dataArrays, index+1, dataArrays, index, size-index-1);
        dataArrays[--size]=null;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList(1);
        arrayList.add(1);
        arrayList.add(2);
        //arrayList.add(3);
        //arrayList.add(4);
        //arrayList.add(5);
        //arrayList.add(6);
        //arrayList.add(7);
        //arrayList.add(8);
        //arrayList.add(9);

        arrayList.printAll();
        arrayList.remove(0);
        System.out.println("#########分割线1##########");
        arrayList.printAll();
        System.out.println("#########分割线2##########");
        System.out.println(arrayList.getSize());
    }
}
