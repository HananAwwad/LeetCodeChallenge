package com.hanan.june;

public class JuneSolution {

    public static void main(String[] aa) {
        int[] a = new int[]{4, 2, 7, 1, 3, 6, 9};
        for (int i = 0; i < a.length; i++) {

        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.right = left;
        right.left = right;
        return root;
    }


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

