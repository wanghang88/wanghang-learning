package com.wanghang.code.datastructure.stack;


/**
 *基于数组实现的栈：
 *             https://blog.csdn.net/yoonerloop/article/details/81606518
 *
 *
 */
public class ArrayStack {

    private Object[] stack;
    //栈的大小
    private int size;
    //栈顶数据
    private Object top;


    public ArrayStack() {
        stack = new Object[5];
    }
    public ArrayStack(int size) {
        stack = new Object[size];
    }


    /**
     * 存入数据
     */
    public void push(Object data) {
        if (size == stack.length) {
            throw new RuntimeException("stack is full");
        }
        stack[size++] = data;
        top = data;
    }
    /**
     * 删除数据
     */
    public Object pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is null");
        }
        Object data = stack[size - 1];
        top = stack[--size];
        return data;
    }


    /**
     * 查看栈内元素
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("栈内元素为空");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }


    /**
     * 栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 栈是否满了;
     * @return
     */
    public boolean isFull() {
        return size() == stack.length;
    }

    /**
     * 栈的实际大小
     */
    public int size() {
        return size;
    }

    /**
     * 查看栈顶元素
     */
    public Object getTop() {
        return top;
    }


    public static void main(String[] args) {
        ArrayStack arrayStack=new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);

        Object top = arrayStack.getTop();
        System.out.println("top:"+top);

        Object pop = arrayStack.pop();
        System.out.println("pop:"+pop);


        //删除栈顶元素后,栈顶元素为：TODO,删除元素之后需不需要对数组进行移位
        Object afterTop = arrayStack.getTop();
        System.out.println("afterTop:"+afterTop);
    }
}
