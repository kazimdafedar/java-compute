package trees;

/**
 * File 04 — Q330: Lowest Common Ancestor (BST)
 */
class LowestCommonAncestorBST {

    static TreeNode lcaBST(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    void main() {
        TreeNode root = TreeUtils.build(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
        TreeNode p = TreeUtils.find(root, 2);
        TreeNode q = TreeUtils.find(root, 8);
        IO.println(lcaBST(root, p, q).val);   // 6

        p = TreeUtils.find(root, 2);
        q = TreeUtils.find(root, 4);
        IO.println(lcaBST(root, p, q).val);   // 2
    }
}
