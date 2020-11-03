package com.hanan;

public class NovChallenge {
    public static void main(String[] aa) {
        //System.out.println(new NovChallenge().getDecimalValue(new ListNode(4)));
        ListNode listNode = new ListNode();
        listNode = listNode.push(4, listNode);
        listNode = listNode.push(2, listNode);
        listNode = listNode.push(7, listNode);
        listNode = listNode.push(8, listNode);
        listNode = listNode.push(1, listNode);
        listNode = listNode.push(3, listNode);
        listNode = listNode.push(6, listNode);
        listNode = listNode.push(5, listNode);
        new NovChallenge().insertionSortList(listNode);


    }

    public ListNode insertionSortList(ListNode head) {
        ListNode pseudoHead = new ListNode();
        ListNode curr = head, prevNode, nextNode;

        while (curr != null) {
            // At each iteration, we insert an element into the resulting list.
            prevNode = pseudoHead;
            nextNode = pseudoHead.next;

            // find the position to insert the current node
            while (nextNode != null) {
                if (curr.val < nextNode.val)
                    break;
                prevNode = nextNode;
                nextNode = nextNode.next;
            }
            ListNode nextIter = curr.next;
            // insert the current node to the new list
            curr.next = nextNode;
            prevNode.next = curr;

            // moving on to the next iteration
            curr = nextIter;
        }

        return pseudoHead.next;
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