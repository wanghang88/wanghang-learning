package com.wanghang.code.datastructure.list;

/**
 *单向链表的操作
 * 参考:
 * https://blog.csdn.net/lw1124052197/article/details/108859915
 *
 *
 *
 *
 */
public class SinglyLinkedList<T> {

    private Node head;    		//头结点
    private int size;			//链表元素个数

    public SinglyLinkedList(){
        this.head = null;
        this.size = 0;
    }


    /**
     * 1：往链表的头部插入元素:
     * @param t
     */
    public void addFirst(T t) {
        //构建一个没有指针域的节点
        Node node = new Node(t);
        node.next = this.head;
        this.head = node;
        this.size++;
    }


    /**
     * 2:在节点的中间插入:
     * @param index
     * @param t
     */
    public void add(int index,T t) {
        //2.1 当index值小于零或者大于链表长度时，抛出异常
        if (index <0 || index > this.size){
            throw new IllegalArgumentException("index is error");
        }
        //2.2 当index为零的时候向链表头部插入一个值
        if (index == 0){
            this.addFirst(t);
        }
        //2.3 获取当前链表的头元素（第一个值）
        Node preNode = this.head;
        //找到要插入节点的前一个节点
        for(int i=0;i<index-1;i++) {
            preNode = preNode.next;
        }
        Node cruNode = new Node(t);
        //要插入的节点的下一个节点指向preNode节点的下一个节点
        cruNode.next = preNode.next;
        //preNode的下一个节点指向要插入节点node
        preNode.next = cruNode;
        this.size++;
    }


    /**
     * 3:判断节点中是否有元素:
     * @param t
     * @return
     */
    public boolean contains(T t){
        Node cur = this.head;
        while(cur != null){
            if(cur.t.equals(t)){
                return true;
            }
            else {
                cur = cur.next;
            }
        }
        return false;
    }


    /**
     * 4:删除节点中的元素:
     * @param t
     */
    public void remove(T t) {
        if(this.head ==null) {
            System.out.println("链表中无数据，不可进行删除！！");
        }else {
            //如果要删除的元素跟链表头部的元素相同，就删除头部（把头元素的指向的元素设为头元素）
            if(head.t.equals(t)) {
                head = head.next;
                this.size--;
            }else {
                //获取当前节点为头部节点
                Node cur = this.head;
                //一直循环遍历链表到最后
                while(cur.next != null) {
                    //当前元素的下一个元素为要删除的值时，那就把当前元素下一个元素的下一个元素 设为 当前元素的下一个元素
                    if(cur.next.t.equals(t)) {
                        cur.next = cur.next.next;
                    }else {
                        cur = cur.next;
                    }
                }
            }
        }
    }



    static class Node <T> {
        //数据域
        public T t;
        //指针域
        public Node next;

        public Node(T t, Node next) {
            this.t = t;
            this.next = next;
        }

        public Node(T t) {
            this(t, null);
        }
    }





    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList=new SinglyLinkedList();


        //1:测试往链表的头部插入元素
        singlyLinkedList.addFirstTest(singlyLinkedList);


        //2:往链表的中间插入元素：
        singlyLinkedList.addTest(singlyLinkedList);



        //3:查询链表中是否有某个元素：
        singlyLinkedList.containsTest(singlyLinkedList);


        //4:删除单向链表的元素测试：
        singlyLinkedList.removeTest(singlyLinkedList);











    }





    private void addFirstTest( SinglyLinkedList singlyLinkedList) {
        String[] strArray = new String[6];
        strArray[0] = "one";
        strArray[1] = "two";
        strArray[2] = "three";
        strArray[3] = "four";
        strArray[4] = "five";
        strArray[5] = "six";
        for(String str : strArray) {
            singlyLinkedList.addFirst(str);
            System.out.println(singlyLinkedList);
        }
    }


    private void addTest(SinglyLinkedList singlyLinkedList) {
        String[] strArray = new String[6];
        strArray[0] = "one";
        strArray[1] = "two";
        strArray[2] = "three";
        strArray[3] = "four";
        strArray[4] = "five";
        strArray[5] = "six";
        for(String str : strArray) {
            singlyLinkedList.addFirst(str);
            System.out.println(singlyLinkedList);
        }

        singlyLinkedList.add(3, "23");
        System.out.println(singlyLinkedList);
        singlyLinkedList.add(3, "45");
        System.out.println(singlyLinkedList);
    }

    private void containsTest(SinglyLinkedList singlyLinkedList) {
        String[] strArray = new String[6];
        strArray[0] = "one";
        strArray[1] = "two";
        strArray[2] = "three";
        strArray[3] = "four";
        strArray[4] = "five";
        strArray[5] = "six";
        for(String str : strArray) {
            singlyLinkedList.addFirst(str);
            System.out.println(singlyLinkedList);
        }

        singlyLinkedList.add(3, "23");
        System.out.println(singlyLinkedList);
        singlyLinkedList.add(3, "45");
        System.out.println(singlyLinkedList);
        System.out.println(singlyLinkedList.contains("four"));
        System.out.println(singlyLinkedList.contains("ten"));
    }

    private void removeTest(SinglyLinkedList singlyLinkedList) {
        String[] strArray = new String[6];
        strArray[0] = "one";
        strArray[1] = "two";
        strArray[2] = "three";
        strArray[3] = "four";
        strArray[4] = "five";
        strArray[5] = "six";
        for(String str : strArray) {
            singlyLinkedList.addFirst(str);
            System.out.println(singlyLinkedList);
        }

        singlyLinkedList.add(3, "23");
        System.out.println(singlyLinkedList);
        singlyLinkedList.add(3, "45");
        System.out.println(singlyLinkedList);
        System.out.println(singlyLinkedList.contains("four"));
        System.out.println(singlyLinkedList.contains("ten"));

        singlyLinkedList.remove("45");
        System.out.println(singlyLinkedList);
    }
}
