
package org.chris.demo.linklist;

import lombok.Data;

import java.util.LinkedList;

/**
 * 单向链表
 *
 * add:             O(1)
 * delete:不含查询   O(1)
 * delete:含查询     O(n)
 * find:            O(n)
 * getByIndex:      O(n)
 *
 * @author caizq
 * @date 2019/2/11
 * @since v1.0.0
 */
public class SinglyLinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public SinglyLinkedList() {
        this.first = this.last = null;
    }

    /**
     * 尾插法
     * @param data
     */
    public void add(T data) {
        size++;
        if (this.first==null) {
            this.first = this.last = new Node(data);
            return;
        }
        Node newNode = new Node(data);
        this.last.next=newNode;
        this.last = newNode;
    }

    public void printAll() {
        for (Node p = first; p != null; p = p.next) {
            if (p.data != null) {
                System.out.println(p.data);
            }
        }
    }

    public T getFirst(){
        return first.data;
    }

    public void removeFirst() {
        //Node tmp = this.first;

        this.first = this.first.next;
        size--;
    }


    private class Node<T> {
        private T data;
        private Node next;

        /*public Node(T data) {
            this.data = data;
            this.next = null;
        }*/
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.printAll();
        singlyLinkedList.add(1);
        singlyLinkedList.add(2);
        singlyLinkedList.add(3);
        singlyLinkedList.printAll();
        System.out.println(singlyLinkedList.getSize());
    }

    public int getSize() {
        return size;
    }
}
