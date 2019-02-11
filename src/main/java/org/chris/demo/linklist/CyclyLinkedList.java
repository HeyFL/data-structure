
package org.chris.demo.linklist;

import lombok.Data;

/**
 * 双向链表
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
@Data
public class CyclyLinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public CyclyLinkedList() {
        this.first = this.last = null;
    }

    public void add(T data) {
        size++;
        if (this.first == null) {
            this.first = this.last = new Node(data);
            this.first.pre = this.first.next = this.first;
            return;
        }
        Node newNode = new Node(data);
        this.last.next = newNode;
        newNode.next = this.first;
        newNode.pre = this.last;
        this.last = newNode;
        this.first.pre = newNode;
    }

    private class Node<T> {
        private Node pre;
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
        }
    }

    public void printAll() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(get(i));
        }
    }

    public Object get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        int count = 0;
        for (Node p = first; p != null; p = p.next, count++) {
            if (count == index) {
                return p.data;
            }
        }
        return null;
    }

    public void printAllInReverse() {
        for (int i = this.size-1; i >= 0; i--) {
            System.out.println("printAllInReverse:" + get(i));
        }
    }


    public static void main(String[] args) {
        CyclyLinkedList<Integer> DoublyLinkedList = new CyclyLinkedList();
        DoublyLinkedList.printAll();
        DoublyLinkedList.add(1);
        DoublyLinkedList.add(2);
        DoublyLinkedList.add(3);
        //正序打印
        DoublyLinkedList.printAll();
        //逆序打印
        DoublyLinkedList.printAllInReverse();
        //输出大小
        System.out.println(DoublyLinkedList.getSize());
    }
}
