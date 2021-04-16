package com.wanghang.code.algorithm;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 *参考博文：
 *      https://mp.weixin.qq.com/s/oQrxkzNvQNwB0rc0Vg1vQg
 *2)不过相比较LinkedHashMap还是更容易理解,如果复写removeEldestEntry会更简单
 *
 */
public class LRU {
    public static void main(String[] args) {
        //1:使用LinkedHashMap获取最后一个Entry:
        Map<Integer,Integer> map=new LinkedHashMap<>();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,1);
        map.put(5,1);
        map.put(6,1);
        map.put(7,1);
        map.put(8,1);
        System.out.println("map:"+map);

        //1:最老的数据,也是Map第一次插入的数据：1=1
        Map.Entry<Integer, Integer> oldestEntry = map.entrySet().iterator().next();
        Integer oldestKey = oldestEntry.getKey();
        System.out.println("oldestEntry:"+oldestEntry);
        System.out.println("oldestKey:"+oldestKey);

        //2:最新的数据,就是最后一次插入Map的数据：8=1
        Map.Entry<Integer, Integer> lastEntry=null;
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            lastEntry= iterator.next();
        }
        Integer lastKey = lastEntry.getKey();
        System.out.println("lastEntry:"+lastEntry);
        System.out.println("lastKey:"+lastKey);


        LRU lRU=new LRU();
        lRU.lruCache1();

        lRU.lruCache2();
    }

    private void lruCache2() {
        LRUCache2 lruCache2 = new LRUCache2(10);
        for (int i = 0; i < 10; i++) {
            lruCache2.put(i,i);
        }
        System.out.println("lruCache2:"+ JSONObject.toJSONString(lruCache2));
        lruCache2.put(10,200);
        System.out.println("full after lruCache2:"+JSONObject.toJSONString(lruCache2));
    }


    //测试基于LinkedHashMap实现LRU算法(LinkedHashMap,最新的数据在链表的尾部,之前的老数据在链表的头,淘汰的话是保留最新的数据，删除老数据)
    private void lruCache1() {
        LRUCache1 lruCache = new LRUCache1(10);
        for (int i = 0; i < 10; i++) {
            lruCache.map.put(i,i);
        }

        System.out.println("lruCache1.map:"+lruCache.map);
        lruCache.put(10,200);
        System.out.println("full after lruCache1.map:"+lruCache.map);
    }


    //1:采用LinkedHashMap实现LRU
    static class LRUCache1{
        //缓存容量的大小
        int capacity;
        Map<Integer,Integer> map;

        public LRUCache1(int capacity){
            this.capacity = capacity;
            map = new LinkedHashMap<>();
        }

        public int get(int key){
            //如果没有找到
            if (!map.containsKey(key)){
                return -1;
            }
            //找到了就刷新数据(删除原来的数据,新加入数据)
            Integer value = map.remove(key);
            map.put(key,value);
            return value;
        }

        public void put(int key,int value){
            if (map.containsKey(key)){
                map.remove(key);
                map.put(key,value);
                return;
            }
            map.put(key,value);
            //超出capacity，删除最久没用的即第一个,或者可以复写removeEldestEntry方法
            if (map.size() > capacity){
                Map.Entry<Integer, Integer> oldEstEntry = map.entrySet().iterator().next();
                map.remove(map.remove(oldEstEntry.getKey()));
            }
        }
    }


    //2:双链表+hashmap
    @Data
    @ToString
    static class LRUCache2{
        private int capacity;

        private Map<Integer,Node>map;
        private Node head;
        private Node tail;

        public LRUCache2(int capacity){
            this.capacity = capacity;
            map = new HashMap<>();
            head = new Node(-1,-1);
            tail = new Node(-1,-1);
            head.next = tail;
            tail.pre = head;
        }

        //获取元素:
        public int get(int key){
            if (!map.containsKey(key)){
                return -1;
            }
            Node node = map.get(key);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            return node.val;
        }

        //添加元素:
        public void put(int key,int value){
            if (get(key)!=-1){
                map.get(key).val = value;
                return;
            }
            Node node = new Node(key,value);
            map.put(key,node);

            moveToTail(node);

            if (map.size() > capacity){
                map.remove(head.next.key);
                head.next = head.next.next;
                head.next.pre = head;
            }
        }

        //把节点移动到尾巴
        private void moveToTail(Node node) {
            node.pre = tail.pre;
            tail.pre = node;
            node.pre.next = node;
            node.next = tail;
        }
    }



    //双向链表:
    static class Node{
        int key;
        int val;
        Node pre;
        Node next;

        public Node(int key,int val){
            this.key = key;
            this.val = val;
            pre = null;
            next = null;
        }
    }
}
