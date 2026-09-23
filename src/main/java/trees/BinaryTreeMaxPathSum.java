package trees;

/**
 * File 04 — Q323: Binary Tree Maximum Path Sum
 */
class BinaryTreeMaxPathSum {

    private int best = Integer.MIN_VALUE;

    int maxPathSum(TreeNode root) {
        best = Integer.MIN_VALUE;
        gain(root);
        return best;
    }

    private int gain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, gain(node.left));
        int right = Math.max(0, gain(node.right));
        best = Math.max(best, node.val + left + right);
        return node.val + Math.max(left, right);
    }

    void main() {
        TreeNode root = TreeUtils.build(-10, 9, 20, null, null, 15, 7);
        IO.println(maxPathSum(root));   // 42  (15->20->7)
    }
}
