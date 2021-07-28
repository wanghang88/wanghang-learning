package com.wanghang.code.JDK.datastructure;


import java.util.HashMap;
import java.util.Map;

/**

 HashMap的使用原理参考博文:
 大体介绍了下HashMap的(put,get ,remove等方法)
 https://blog.csdn.net/qq_26323323/article/details/86219905

 只要介绍和HashMap的原理,关于put方法讲解得比较详细
 https://mp.weixin.qq.com/s/3SK251gv5w_Hnxy1oJPzhw


 HashMap的实现原理参考博文：
 https://www.cnblogs.com/jajian/p/13965678.html   （码辣架构）
 bugstack虫洞栈：
 https://bugstack.cn/interview/2020/08/13/%E9%9D%A2%E7%BB%8F%E6%89%8B%E5%86%8C-%E7%AC%AC4%E7%AF%87-HashMap%E6%95%B0%E6%8D%AE%E6%8F%92%E5%85%A5-%E6%9F%A5%E6%89%BE-%E5%88%A0%E9%99%A4-%E9%81%8D%E5%8E%86-%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90.html
 苦味代码:初始化，数据寻址-hash方法，数据存储-put方法,扩容-resize方法，只要理解了这四个点的原理和调用时机，也就理解了整个HashMap的设计
 https://mp.weixin.qq.com/s/UOr9BOWrv67d8l1VQxqUkA

 b)JDK1.7HashMap的原理分析:
 https://blog.csdn.net/mu_wind/article/details/94450556



 关于实现java8的HashMap
 1,哈希表：
 哈希表这种数据结构，本质上也是数组，但是它与传统数组不同的是，它的下标是通过对数据进行一个函数处理之后，然后得到一个下标值，再在相应的位置存放该数据
 对数据的添加和查询操作都是基于函数处理之后获取下标，这样（在没有哈希冲突的情况下）它的操作时间复杂度都在O(1)
 哈希冲突:对key进行哈希函数处理的时候,当计算的两个key的值是一样的时候， 就会造成冲突。
 解决哈希冲突的方法：
                开发定址法，开发定址法的原理是遇到冲突的时候查找顺着原来哈希地址查找下一个空闲地址然后插入，但是也有一个问题就是如果空间不足，那他无法处理冲突也无法插入数据，因此需要装填因子(插入数据/空间)<=1。
                链地址法，链地址法可以，链地址法的原理时如果遇到冲突，他就会在原地址新建一个空间，然后以链表结点的形式插入到该空间。
 哈希表的性能:
            由于哈希表高效的特性，查找或者插入的情况在大多数情况下可以达到O(1)，时间主要花在计算hash上;
            当然也有最坏的情况就是hash值全都映射到同一个地址上，这样哈希表就会退化成链表，查找的时间复杂度变成O(n)。


 2,HashMap的三个函数
       public HashMap(), 采用默认的容量16和默认的加载因子
       public HashMap(int initialCapacity，float loadFactor), 采用自定义的容量和自定义的负载因子
       public HashMap(int initialCapacity),  采用自定义的容量
       这其中tableSizeFor(int cap), 利用的思想是:如果一个二进制数低位全是1，那么这个数+1则肯定是一个2的幂次方数。

 3,HashMap的put:
     3.1)在put一个k-v时，首先调用hash()方法来计算key的hashcode,并且还是用了扰动函数来降低哈希冲突。
         static final int hash(Object key) {
         int h;
         return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
         }
         扰动函数的处理:是将key.hashCode()得到的哈希码,然后将哈希吗和哈希吗右移16位的异或运算得到的值返回。

     3.2)在计算出hash之后之后，调用putVal方法进行key-value的存储操作.在putVal方法中首先需要判断table是否被初始化了（因为hashmap是延迟初始化的，并不会在创建对象的时候初始化table),
            如果table还没有初始化，则通过resize方法进行扩容。
            if ((tab = table) == null || (n = tab.length) == 0)
                 n = (tab = resize()).length;

     3.3)通过(n-1)&hash计算出当前key所在的bucket下标，如果当前table中当前下标中还没有存储数据，则创建一个链表节点直接将当前k-v存储在该下标的位置。   // TODO: 脚下标是怎么算的？(n-1)&hash

                if ((p = tab[i = (n - 1) & hash]) == null)
                    tab[i] = newNode(hash, key, value, null);

     3.4)如果table下标处已经存在数据，则首先判断当前key是否和下标处存储的key完全相等，如果相等则直接替换value，并将原有value返回，否则继续遍历链表或者存储到红黑树。

     3.5)当前下标处的节点是树节点，则直接存储到红黑树中:
            else if (p instanceof TreeNode)
              e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);

     3.6)如果不是红黑树，则遍历链表，如果在遍历链表的过程中，找到相等的key，则替换value，如果没有相等的key，就将节点存储到链表尾部（jdk8中采用的是尾插法），
              并检查当前链表中的节点树是否超过了阈值8，如果超过了8，则通过调用treeifyBin方法将链表转化为红黑树。
                    for (int binCount = 0; ; ++binCount) {
                       if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                       break;
                    }
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) break;
                    p = e;
                 }
      3.7)将数据存储完成之后，需要判断当前hashmap的大小是否超过扩容阈值Cap*load_fact,如果大于阈值，则调用resize()方法进行扩容。
                 f (++size > threshold)
                     resize();

   4:HashMap的扩容:
           HashMap在扩容后的容量为原容量的2倍，起基本机制是创建一个2倍容量的table，
           然后将数据转存到新的散列表中，并返回新的散列表。和jdk1.7中不同的是，jdk1.8中多转存进行了优化，可以不再需要重新计算bucket下标.
           如果一个key hash和原容量oldCap按位与运算结果为0，则扩容前的bucket下标和扩容后的bucket下标相等，否则扩容后的bucket下标是原下标加上oldCap。
           扩容的原理,
 */
public class HashMapDemo {

    // 初始化容量，默认为16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    // 数组最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;

    // 负载因子，这个是在扩容的时候使用
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // 存储的节点数量
    transient int size;

    // 扩展后的数组长度
    int threshold;


    // 负载因子，可主动指定，建议直接使用默认值0.75
    final float loadFactor=0;



    public static void main(String[] args) {
        HashMap hashMap=new HashMap(17,0.65f);
        hashMap.put("a",110);

        hashMap.put("b",2);
        hashMap.put("c",3);
        hashMap.put("a",4);



        int tableSizeForNum = tableSizeFor(9);
        System.out.println("tableSizeForNum:"+tableSizeForNum);

        System.out.println("hashmap:"+hashMap.size());
    }

    
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

}
