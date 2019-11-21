package study;

import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

/**
 * @author zyf
 */
public class Palindrome {

    private static SingleLinked addToLinked(String text) {
        SingleLinked<String> singleLinked = new SingleLinked<>();
        char[] chars = text.toCharArray();
        for (char aChar : chars) {
            singleLinked.add(aChar + "");
        }
        return singleLinked;
    }

    /**
     * 快慢指针法判断是否回文数
     *
     * @param text
     * @return
     */
    public static boolean isPalindrome(String text) {
        if (StringUtils.isBlank(text)) {
            return false;
        }
        SingleLinked linked = addToLinked(text);
        System.out.println(linked.toString());
        if (linked.getFirst() == null || linked.getFirst().next == null) {
            return true;
        }
        if (linked.size() == 2) {
            return linked.getFirst().getValue().equals(linked.getFirst().next.getValue());
        }
        Stack<String> stack = new Stack<>();
        SingleLinked.Node slow = linked.getFirst();
        SingleLinked.Node fast = linked.getFirst();
        stack.push(slow.getValue() + "");
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            stack.push(slow.getValue() + "");
        }
        System.out.println(linked.toString());
        SingleLinked.Node temp = slow;
        if (linked.size() % 2 == 0) {
            while (temp.next != null) {
                if (!temp.next.getValue().equals(stack.pop())) {
                    return false;
                }
                temp = temp.next;
            }
        } else {
            while (temp != null) {
                if (!temp.getValue().equals(stack.pop())) {
                    return false;
                }
                temp = temp.next;
            }
        }
        System.out.println(linked.toString());
        return true;
    }

    public static void main(String[] args) {
        boolean palindrome = Palindrome.isPalindrome("1234432");
        System.out.println(palindrome);
    }
}
