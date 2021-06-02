package com.hanan.common;

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode push(int new_data, ListNode head) {
        /* 1 & 2: Allocate the Node &
                  Put in the data*/
        ListNode new_node = new ListNode(new_data);

        /* 3. Make next of new Node as head */
        new_node.next = head;

        /* 4. Move the head to point to new Node */
        head = new_node;
        return head;
    }

    public void printList(ListNode head) {
        ListNode tnode = head;
        while (tnode != null) {
            System.out.print(tnode.val + "->");
            tnode = tnode.next;
        }
        System.out.println("NULL");
    }


}