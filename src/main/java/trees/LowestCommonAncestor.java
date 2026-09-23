package trees;

/**
 * File 04 — Q97: Lowest Common Ancestor (Binary Tree)
 */
class LowestCommonAncestor {

    static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    void main() {
        TreeNode root = TreeUtils.build(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode p = TreeUtils.find(root, 5);
        TreeNode q = TreeUtils.find(root, 1);
        IO.println(lca(root, p, q).val);   // 3

        p = TreeUtils.find(root, 5);
        q = TreeUtils.find(root, 4);
        IO.println(lca(root, p, q).val);   // 5
    }
}
