package trees;

/**
 * File 04 — Q320: Invert Binary Tree
 */
class InvertBinaryTree {

    static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    void main() {
        TreeNode root = TreeUtils.build(4, 2, 7, 1, 3, 6, 9);
        IO.println("Before: " + LevelOrderTraversal.levelOrder(root));
        invertTree(root);
        IO.println("After:  " + LevelOrderTraversal.levelOrder(root));
    }
}
