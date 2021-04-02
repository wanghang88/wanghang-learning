package com.wanghang.code.datastructure;

import com.wanghang.code.datastructure.tree.JDKLinkedHashMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


/**
 *注名：
 *   源码中一些变量定义,如果定义了一个节点 p,pl（p left）为 p 的左节点,
 *                                    pr（p right）为 p 的右节点,
 *                                    pp（p parent）为 p 的父节点,
 *                                    ph（p hash）为 p 的 hash 值,
 *                                    pk（p key）为 p 的 key 值,
 *                                    kc（key class）为 key 的类等等.
 *
 * 源码中对红黑树进行查找时会用到这两条规则：
 *                             1)如果目标节点的 hash 值小于 p 节点的 hash 值，则向 p 节点的左边遍历；否则向 p 节点的右边遍历,
 *                             2)如果目标节点的 key 值小于 p 节点的 key 值，则向 p 节点的左边遍历；否则向 p 节点的右边遍历.
 *                                  这两条规则是利用了红黑树的特性(左节点 < 根节点 < 右节点).
 *
 * 源码中进行红黑树的查找时:会用 dir（direction）来表示向左还是向右查找,
 *                      dir 存储的值是目标节点的 hash/key 与 p 节点的 hash/key 的比较结果.
 *
 *
 *1)JDK1.8Hashmap:
 *              JDK 1.8 对 HashMap 进行了比较大的优化，底层实现由之前的 “数组+链表” 改为 “数组+链表+红黑树”
 *
 *
 *
 *
 *
 *
 *
 */
public class JDK18HashMap {
    HashMap<String,Object> hashMap=new HashMap();


    // 默认容量16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;


    // 最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;


    // 默认负载因子0.75
    static final float DEFAULT_LOAD_FACTOR = 0.75f;


    // 链表节点转换红黑树节点的阈值, 9个节点转
    static final int TREEIFY_THRESHOLD = 8;


    // 红黑树节点转换链表节点的阈值, 6个节点转
    static final int UNTREEIFY_THRESHOLD = 6;


    // 转红黑树时, table的最小长度
    static final int MIN_TREEIFY_CAPACITY = 64;








    //Node节点：
     public static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        JDK18HashMap.Node<K,V> next;
        Node(int hash, K key, V value, JDK18HashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value;}
        public final String toString() { return key + "=" + value;}

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }





}
