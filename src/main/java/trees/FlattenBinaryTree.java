package trees;

/**
 * Flatten Binary Tree to Linked List — preorder, in-place, right pointers only.
 * O(n) time, O(1) extra space (Morris-style).
 */
class FlattenBinaryTree {

    static void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode pred = cur.left;
                while (pred.right != null) {
                    pred = pred.right;
                }
                pred.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    static String rightChain(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        while (root != null) {
            sb.append(root.val);
            if (root.right != null) {
                sb.append(" -> ");
            }
            root = root.right;
        }
        return sb.toString();
    }

    void main() {
        TreeNode root = TreeUtils.build(1, 2, 5, 3, 4, null, 6);
        flatten(root);
        IO.println(rightChain(root));   // 1 -> 2 -> 3 -> 4 -> 5 -> 6
    }
}
