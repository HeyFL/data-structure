
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
public class DoublyLinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public DoublyLinkedList(){
        this.first=this.last = null;
    }

    public void add(T data){
        size++;
        if (this.first==null) {
            this.first=this.last = new Node(data);
            return;
        }
        Node newNode = new Node(data);
        this.last.next = newNode;
        newNode.pre=this.last;
        this.last=newNode;
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
        for (Node p = first; p != null; p = p.next) {
            if (p.data != null) {
                System.out.println(p.data);
            }
        }
    }
    public void printAllInReverse() {
        for (Node p = last; p != null; p = p.pre) {
            if (p.data != null) {
                System.out.println("printAllInReverse:"+p.data);
            }
        }
    }


    public static void main(String[] args) {
        DoublyLinkedList<Integer> DoublyLinkedList = new DoublyLinkedList();
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
