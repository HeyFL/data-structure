package org.chris.demo.dueue;

import lombok.Data;
import org.chris.demo.linklist.SinglyLinkedList;

/**
 * 队列
 * @author caizq
 * @date 2019/2/11
 * @since v1.0.0
 */
@Data
public class Dueue<T> extends SinglyLinkedList<T> {
    public T pop(){
        if (getSize()==0) {
            throw new IndexOutOfBoundsException();
        }
        T last = getFirst();
        removeFirst();
        return last;
    }


    public static void main(String[] args) {
        Dueue<Integer> Dueue = new Dueue();
        Dueue.printAll();
        Dueue.add(1);
        Dueue.add(2);
        Dueue.add(3);
        System.out.println(Dueue.pop());
        System.out.println(Dueue.pop());
        System.out.println(Dueue.pop());
        Dueue.add(4);
        System.out.println(Dueue.pop());
        System.out.println(Dueue.pop());
    }
}
