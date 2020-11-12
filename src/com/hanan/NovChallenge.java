package com.hanan;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NovChallenge {
    public static void main(String[] aa) {
        //System.out.println(new NovChallenge().getDecimalValue(new ListNode(4)));
//        ListNode listNode = new ListNode();
//        listNode = listNode.push(4, listNode);
//        listNode = listNode.push(2, listNode);
//        listNode = listNode.push(7, listNode);
//        listNode = listNode.push(8, listNode);
//        listNode = listNode.push(1, listNode);
//        listNode = listNode.push(3, listNode);
//        listNode = listNode.push(6, listNode);
//        listNode = listNode.push(5, listNode);
//        new NovChallenge().insertionSortList(listNode);
        //System.out.println(new NovChallenge().maxPower("abbcccddddeeeeedcba"));
        // System.out.println(new NovChallenge().findMaxConsecutiveOnes(new int[]{1}));
        //System.out.println(new NovChallenge().findTilt(new TreeNode(5)));
        // System.out.println(new NovChallenge().maxAncestorDiff(new TreeNode(5)));
        System.out.println(new NovChallenge().flipAndInvertImage(new int[][]{{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}}));

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = listToStack(l1);
        Stack<Integer> s2 = listToStack(l2);

        int carray = 0;
        ListNode head = null;
        while (!s1.isEmpty() || !s2.isEmpty() || carray != 0) {

            int sum = carray;
            if (!s1.isEmpty()) sum += s1.pop();
            if (!s2.isEmpty()) sum += s2.pop();
            ListNode newNode = new ListNode(sum % 10);

            newNode.next = head;
            head = newNode;
            carray = sum / 10;
        }
        return head;
    }

    Stack<Integer> listToStack(ListNode l) {
        Stack<Integer> stack = new Stack();
        while (l != null) {
            stack.push(l.val);
            l = l.next;
        }
        return stack;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = p;
        return p;
    }

    public ListNode addTwoNumbersI(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);

        ListNode p = l1, q = l2, curr = dummyHead;

        int carry = 0;

        while (p != null || q != null) {

            int x = (p != null) ? p.val : 0;

            int y = (q != null) ? q.val : 0;

            int sum = carry + x + y;

            carry = sum / 10;

            curr.next = new ListNode(sum % 10);

            curr = curr.next;

            if (p != null) p = p.next;

            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;

    }

    public int[][] flipAndInvertImage(int[][] A) {
        int C = A[0].length;
        for (int[] row : A)
            for (int i = 0; i < (C + 1) / 2; ++i) {
                int tmp = row[i] ^ 1;
                row[i] = row[C - 1 - i] ^ 1;
                row[C - 1 - i] = tmp;
            }

        return A;
    }

    int result = 0;

    public void helper(TreeNode node, int max, int min) {
        if (node == null)
            return;
        result = Math.max(result, Math.max(Math.abs(node.val - min), Math.abs(node.val - max)));


        min = Math.min(min, node.val);
        max = Math.max(max, node.val);
        helper(node.left, max, min);
        helper(node.left, max, min);

    }

    public int maxAncestorDiff(TreeNode root) {
        helper(root, root.val, root.val);

        return result;
    }


    int totalTilt = 0;

    int valueSum(TreeNode node) {
        if (node == null)
            return 0;

        int leftTilt = valueSum(node.left);
        int rightTilt = valueSum(node.right);
        int tilt = Math.abs(leftTilt - rightTilt);
        totalTilt += tilt;
        return node.val + leftTilt + rightTilt;
    }

    public int findTilt(TreeNode root) {
        this.totalTilt = 0;

        valueSum(root);
        return totalTilt;
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();


        return result;
    }

    public int findMaxConsecutiveOnes(int[] nums) {

        int count = 0;
        int max = 0;

        for (int i : nums) {
            if (i == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }

        return max;
    }

    public int maxPower1(String s) {
        int count = 0;
        int maxCount = 0;
        char previous = ' ';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == previous) {
                // if same as previous one, increase the count
                count++;
            } else {
                // else, reset the count
                count = 1;
                previous = c;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    public int maxPower(String s) {
        int result = 0, i = 0, l = 0;

        for (; i < s.length(); i++) {

            if (s.charAt(i) != s.charAt(l)) {
                result = Math.max(result, i - l);
                l = i;
            }

        }
        return Math.max(result, i - l);
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