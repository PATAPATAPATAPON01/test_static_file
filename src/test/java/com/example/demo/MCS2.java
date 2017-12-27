package com.example.demo;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by PataPon on 2017/11/7.
 */
public class MCS2 {

    AtomicReferenceFieldUpdater<MCS2, Node> updater = AtomicReferenceFieldUpdater.newUpdater(MCS2.class, Node.class, "queue");

    private Node currentNode = new Node();
    private static Node queue;

    public void lock() {

        Node preNode = updater.getAndSet(this, currentNode);
        if (preNode != null) {
            preNode.next = currentNode;
        }
        while (currentNode.locked) { //当前节点的锁属性上自旋

        }
    }

    public void unlock() {

        if (updater.get(this) == currentNode) { //只要是拥有锁才能去释放


        }

    }


    class Node {
        Node next;
        boolean locked = true;
    }
}
