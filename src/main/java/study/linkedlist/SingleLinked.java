package study.linkedlist;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单向链表
 * @author zyf
 */
public class SingleLinked<T> {

    private Node head = null;
    private AtomicInteger size = new AtomicInteger(0);

    class Node<T> {
        private T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getValue() {
            return data;
        }
    }

    public void add(T data) {
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node<>(data);
        }
        size.incrementAndGet();
    }

    public int size() {
        return size.get();
    }

    public Node<T> getFirst() {
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> temp = head;
        while (temp != null) {
            sb.append(temp.data).append(",");
            temp = temp.next;
        }
        sb.append("]");
        if (sb.length() > 2) {
            return sb.substring(0, sb.length() - 2) + "]";
        }
        return sb.toString();
    }

    /**
     * 反转链表
     */
    private void reverse() {
        if (this.head == null || this.head.next == null) {
            return;
        }
        Node tempNode = null;
        Node newHead = null;
        int i = 0;
        while (i++ < size()) {
            tempNode = head;
            head = head.next;

            tempNode.next = newHead;
            newHead = tempNode;
        }
        this.head = newHead;
    }

    /**
     * 判断是否有环
     * @return 是否
     */
    private boolean isRinglike() {
        if (head == null || head.next == null) {
            return false;
        }
        Node slow = head;
        Node fast = head;
        while (true) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null || fast.next == null) {
                return false;
            }
            if (slow == fast) {
                return true;
            }
        }
    }

    /**
     * 删除倒数第N个节点
     * @param n 倒数N个
     */
    private void deleteBackwardsN(int n) {
        if (n <= 0 || n > size()) {
            return;
        }
        if (head == null) {
            return;
        }
        if (head.next == null) {
            head = null;
            size.decrementAndGet();
            return;
        }
        Node slow = head;
        Node fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (true) {
            if (fast == null || fast.next == null) {
                if (slow == head && fast == null) {
                    head = head.next;
                    size.decrementAndGet();
                    return;
                } else {
                    slow.next = slow.next.next;
                    size.decrementAndGet();
                    return;
                }
            }
            slow = slow.next;
            fast = fast.next;
        }
    }

    public static void main(String[] args) {
        SingleLinked<String> linked = new SingleLinked<>();
        linked.add("a");
        linked.add("b");
        linked.add("c");
//        linked.add("d");
//        System.out.println(linked.size());
//        System.out.println(linked.toString());
//        linked.reverse();
//        System.out.println(linked.toString());
//        System.out.println(linked.isRinglike());
        linked.deleteBackwardsN(1);



        System.out.println(linked.toString());
    }
}
