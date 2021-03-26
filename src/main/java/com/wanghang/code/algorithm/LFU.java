package com.wanghang.code.algorithm;

import lombok.Data;

import java.util.*;

/**
 *1)LRU:内存管理算法,
 * Least Recently Used,最近最少使用策略，判断最近被使用的时间，距离目前最远的数据优先被淘汰,它也是redis采用的淘汰算法之一，redis还有一个缓存策略叫做LFU
 *参考：
 *   https://mp.weixin.qq.com/s/_JAA84X-p_N9zU3e42FAIQ
 *
 *2)LFU:内存管理算法,
 * Least Frequently Used,最不经常使用策略，在一段时间内，数据被使用频次最少的，优先被淘汰(最少使用LFU).
 *
 *
 *3)LRU和LFU的区别以及LFU的缺点:
 * LRU主要体现在对元素的使用时间上,
 * 而LFU主要体现在对元素的使用频次上,缺陷：短期的时间内，对某些缓存的访问频次很高,这些缓存会立刻晋升为热点数据，而保证不会淘汰,而之前的数据啃呢个会被删除
 *
 *4)LRU的实现思路：
 * Node主要包含了key和value，因为LFU的主要实现思想是比较访问的次数，如果在次数相同的情况下需要比较节点的时间，
 *                        越早放入的越快被淘汰，因此我们需要在Node节点上加入time和count的属性,分别用来记录节点的访问的时间和访问次数
 *
 */
public class LFU<K,V> {
    /*总容量*/
    private int capacity;

    /*所有的node节点*/
    private Map<K, Node> caches;

    /*构造函数*/
    public LFU(int size) { this.capacity = size;
        caches = new LinkedHashMap<K,Node>(size);
    }


    /**
     * 添加元素：
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        Node node = caches.get(key); //如果新元素
        if (node == null) { //如果超过元素容纳量
            if (caches.size() >= capacity) { //移除count计数最小的那个key
                K leastKey = removeLeastCount();
                caches.remove(leastKey);
            } //创建新节点
            node = new Node(key,value,System.nanoTime(),1);
            caches.put(key, node);
        }else { //已经存在的元素覆盖旧值
            node.value = value;
            node.setCount(node.getCount()+1);
            node.setTime(System.nanoTime());
        }
        sort();
    }

    /**
     * 根据key获取元素：
     * @param key
     * @return
     */
    public V get(K key){
        Node node = caches.get(key); if (node!=null){
            node.setCount(node.getCount()+1);
            node.setTime(System.nanoTime());
            sort(); return (V)node.value;
        } return null;
    }


   //排序
    private void sort() {
        List<Map.Entry<K,Node>> list = new ArrayList<>(caches.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K,Node>>() {
            @Override
            public int compare(Map.Entry<K, Node> o1, Map.Entry<K,Node> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        caches.clear();
        for (Map.Entry<K,Node> kNodeEntry:list) {
            caches.put(kNodeEntry.getKey(),kNodeEntry.getValue());
        }
    }


   //移除统计数或者时间比较最小的那个
    private K removeLeastCount() {
        Collection<Node> values = caches.values();
        Node min = Collections.min(values); return (K)min.getKey();
    }



    @Data
    static class Node implements Comparable<Node>{
       //key
       Object key;
       //value
       Object value;
       //访问时间
       long time;
       //访问次数
       int count;

       public Node(Object key,Object value,long time,int count){
           this.key = key;
           this.value = value;
           this.time = time;
           this.count = count;
       }

       @Override
       public int compareTo(Node o) {
           int compare = Integer.compare(this.count,o.count);
           if(compare==0){
               return Long.compare(this.time,o.time);
           }
           return compare;
       }
   }


    public static void main(String[] args) {
        LFU<Integer,String> lruCache = new LFU<>(5);
        lruCache.put(1, "A");
        lruCache.put(2, "B");
        lruCache.put(3, "C");
        lruCache.put(4, "D");
        lruCache.put(5, "E");
        lruCache.put(6, "F");
        lruCache.get(2);
        lruCache.get(2);
        lruCache.get(3);
        lruCache.get(6);

        //重新put节点3
        lruCache.put(3,"C");
        final Map<Integer, Node> caches = (Map<Integer,Node>) lruCache.caches;

        for (Map.Entry<Integer,Node> nodeEntry : caches.entrySet()) {
            System.out.println(nodeEntry.getValue().value);
        }
    }
}
