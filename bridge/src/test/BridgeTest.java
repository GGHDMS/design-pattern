package test;

import impl.ArrayListImpl;
import impl.LinkedListImpl;
import list.Queue;
import list.Stack;

public class BridgeTest {

    public static void main(String[] args) {
        Stack<String> linkedListStack = new Stack<>(new LinkedListImpl<>());

        linkedListStack.push("aaa");
        linkedListStack.push("bbb");
        linkedListStack.push("ccc");

        System.out.println(linkedListStack);
        System.out.println(linkedListStack.pop());

        System.out.println("=============================");

        Stack<String> arrayStack = new Stack<>(new ArrayListImpl<>());

        arrayStack.push("aaa");
        arrayStack.push("bbb");
        arrayStack.push("ccc");

        System.out.println(arrayStack);
        System.out.println(arrayStack.pop());

        System.out.println("=============================");

        Queue<String> linkedListQueue = new Queue<>(new LinkedListImpl<>());

        linkedListQueue.enQueue("aaa");
        linkedListQueue.enQueue("bbb");
        linkedListQueue.enQueue("ccc");

        System.out.println(linkedListQueue);
        System.out.println(linkedListQueue.deQueue());

        System.out.println("=============================");


        Queue<String> arrayQueue = new Queue<>(new ArrayListImpl<>());

        arrayQueue.enQueue("aaa");
        arrayQueue.enQueue("bbb");
        arrayQueue.enQueue("ccc");

        System.out.println(arrayQueue);
        System.out.println(arrayQueue.deQueue());

    }
}
