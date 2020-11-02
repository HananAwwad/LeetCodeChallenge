package com.hanan;

public class NovChallenge {
    public static void main(String[] aa) {
        System.out.println(new NovChallenge().getDecimalValue(new ListNode(4)));
    }

    public int getDecimalValue(ListNode head) {
        int num = head.val;

        while (head.next != null) {
            num = num * 2 + head.next.val;
            head = head.next;
        }
        return num;
    }

}