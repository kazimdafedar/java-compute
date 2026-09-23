package trees;

/**
 * File 04 — Q322: Path Sum
 */
class PathSum {

    static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        int remaining = targetSum - root.val;
        return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining);
    }

    void main() {
        TreeNode root = TreeUtils.build(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        IO.println(hasPathSum(root, 22));   // true  (5->4->11->2)
        IO.println(hasPathSum(root, 26));   // false
    }
}
