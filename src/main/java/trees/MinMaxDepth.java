package trees;

/**
 * File 04 — Q321: Maximum & Minimum Depth
 */
class MinMaxDepth {

    static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return 1 + minDepth(root.right);
        }
        if (root.right == null) {
            return 1 + minDepth(root.left);
        }
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    void main() {
        TreeNode root = TreeUtils.build(3, 9, 20, null, null, 15, 7);
        IO.println("Max depth: " + maxDepth(root));   // 3
        IO.println("Min depth: " + minDepth(root));   // 2
    }
}
