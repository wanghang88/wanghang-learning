package com.wanghang.code.datastructure;

import java.util.LinkedList;

public class JDKLinkeList {

    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();

        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
    }



















    //LinkedList 双向链表的数据结构
    private static class Node<E> {
        E item;
        JDKLinkeList.Node<E> next;
        JDKLinkeList.Node<E> prev;

        Node(JDKLinkeList.Node<E> prev, E element, JDKLinkeList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
