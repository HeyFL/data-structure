package org.chris.demo.stack;

import lombok.Data;

/**
 * 栈
 * @author caizq
 * @date 2019/2/11
 * @since v1.0.0
 */
@Data
public class Stack<T> {
    private Object[] stackData;
    private int size;

    public Stack(int size){
        if (size<=0) {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    size);
        }
        stackData = new Object[size];
    }

    public void push(T data){
        expandIfNecessary();
        stackData[size++]=data;
    }
    public T pop(){
        T stackDatum = (T) stackData[size - 1];
        remove(size-1);
        return stackDatum;
    }

    public void remove(int index){
        if (index>size||index<0) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(stackData, index+1, stackData, index, size-index-1);
        stackData[--size]=null;
    }

    private void expandIfNecessary() {
        if (size+1>stackData.length) {
            //扩容
            Object[] copy = new Object[stackData.length<<1];
            System.arraycopy(stackData,0,copy,0,size);
            stackData=copy;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> objectStack = new Stack<>(1);
        objectStack.push(1);
        objectStack.push(2);
        objectStack.push(3);

        System.out.println(objectStack.pop());
        System.out.println(objectStack.pop());
        System.out.println(objectStack.pop());
        System.out.println(objectStack.pop());


    }
}
