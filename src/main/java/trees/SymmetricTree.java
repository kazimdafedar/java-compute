package trees;

/**
 * File 04 — Q327: Symmetric Tree
 */
class SymmetricTree {

    static boolean isSymmetric(TreeNode root) {
        return root == null || mirror(root.left, root.right);
    }

    static boolean mirror(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        return left.val == right.val
            && mirror(left.left, right.right)
            && mirror(left.right, right.left);
    }

    void main() {
        IO.println(isSymmetric(TreeUtils.build(1, 2, 2, 3, 4, 4, 3)));   // true
        IO.println(isSymmetric(TreeUtils.build(1, 2, 2, null, 3, null, 3)));   // false
    }
}
