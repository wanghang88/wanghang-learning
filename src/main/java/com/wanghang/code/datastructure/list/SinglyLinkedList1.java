package com.wanghang.code.datastructure.list;


/**
 *单向链表:
 *       https://blog.csdn.net/yoonerloop/article/details/81489665
 *
 */
public class SinglyLinkedList1<T> {

    //头结点
    private Node head;
    //尾节点
    private Node tail;
    //长度
    private int length;


    /**
     * 往链表的尾部插入:
     * @param data
     * @return
     */
    public Node insert(T data) {
        //新节点
        Node node = new Node(data);
        if (isEmpty()) {
            //首次插入
            head = node;
            tail = node;
        } else {
            //非首次插入
            tail.next = node;
            tail = node;
        }
        length++;
        return node;
    }

    /**
     * 往链表的尾部插入：
     * @param data
     * @return
     */
    public Node insertHead(T data) {
        Node node = new Node(data);
        Node lastNode;
        lastNode = head;
        head = node;
        head.next = lastNode;
        length++;
        return node;
    }

    /**
     * 往链表的指定位置插入元素：
     * @param data
     * @param position
     * @return
     */
    public Node insert(T data, int position) {
        if (position < 0) {
            throw new IndexOutOfBoundsException();
        }
        //新节点
        Node node = new Node(data);
        if (position == 0) {
            //插入头部
            insertHead(data);
        } else if (isEmpty() || position >= length()) {
            //插入尾部
            insert(data);
        } else {
            //插入中间
            //node上一个元素
            Node current = head;
            //node的下一个元素
            Node nextNode = current.next;
            for (int i = 1; i < length(); i++) {
                if (i == position) {
                    //上一个元素指向node
                    current.next = node;
                    //node下一个元素指向原始current的下一个
                    node.next = nextNode;
                    break;
                } else {
                    //更新下一个节点
                    current = current.next;
                    nextNode = current.next;
                }
            }
            length++;
        }
        return node;
    }


    /**
     * 根据传入的元素,删除链表的指定节点;
     * @param data
     * @return
     */
    public T delete(T data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return null;
        }
        if (head.data.equals(data)) {
            //头结点
            deleteHead();
            return data;
        } else if (tail.data.equals(data)) {
            deleteTail();
            return data;
        } else {
            //删除中间的
            //上一个元素
            Node lastNode = null;
            //当前元素
            Node currentNode = head;
            //下一个元素
            Node nextNode = currentNode.next;
            while (currentNode != null) {
                if (currentNode.data.equals(data)) {
                    lastNode.next = nextNode;
                    length--;
                    return data;
                }
                if (nextNode == null) {
                    return null;
                } else {
                    lastNode = currentNode;
                    currentNode = nextNode;
                    nextNode = nextNode.next;
                }
            }
        }
        return null;
    }


    /**
     * 删除尾节点：
     */
    public Node deleteTail() {
        Node deleteNode = tail;
        if (isEmpty()) {
            //没有元素。删除失败，抛出异常
            throw new RuntimeException("please insert element first");
        }
        if (length == 1) {
            //只有一个元素，头尾元素相同，直接删除
            head = null;
            tail = null;
        } else {
            //含义多个元素
            Node lastNode = head;
            //lastNode.next不会空指针就执行完毕，以为tail存在
            while (lastNode.next != tail) {
                lastNode = lastNode.next;
            }
            tail = lastNode;
            tail.next = null;
        }
        length--;
        return deleteNode;
    }


    /**
     * 删除头节点
     * @return
     */
    public Node deleteHead(){
      return null;
    }


    /**
     * 指定位置删除:
     * @param position
     * @return
     */
    public Node delete(int position) {
        if (isEmpty()) {
            //没有元素。删除失败，抛出异常
            throw new RuntimeException("please insert element first");
        }
        if (position < 0 || position > length() - 1) {
            //下标越界
            throw new IndexOutOfBoundsException();
        }
        if (position == 0) {
            //删除头部
            return deleteHead();
        } else if (position == length() - 1) {
            //删除尾部
            return deleteTail();
        } else {
            //删除中间，至少有3个元素
            //上一个元素
            Node lastNode = head;
            //当前元素
            Node currentNode = lastNode.next;
            //下一个元素
            Node nextNode = currentNode.next;
            for (int i = 1; i < length(); i++) {
                if (i == position) {
                    //上一个元素指向node
                    lastNode.next = nextNode;
                    break;
                } else {
                    //更新下一个节点
                    lastNode = currentNode;
                    currentNode = nextNode;
                    nextNode = nextNode.next;
                }
            }
            length--;
            return currentNode;
        }
    }



    /**
     * 查看节点是否为空
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * 查看链表长度
     */
    public int length() {
        return length;
    }


    private static class Node <T> {
        //数据域
        public T data;
        //指针域
        public Node next;

        public Node(T t,Node next) {
            this.data = t;
            this.next = next;
        }
        public Node(T t) {
            this(t, null);
        }
    }
}
