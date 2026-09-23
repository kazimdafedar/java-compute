package trees;

import java.util.HashMap;
import java.util.Map;

/**
 * File 04 — Q326: Construct Binary Tree from Preorder + Inorder
 */
class ConstructBinaryTree {

    private int preIndex;
    private int[] preorder;
    private Map<Integer, Integer> inorderIndex = new HashMap<>();

    TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.preIndex = 0;
        inorderIndex.clear();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }
        return build(0, inorder.length - 1);
    }

    private TreeNode build(int left, int right) {
        if (left > right) {
            return null;
        }
        int rootValue = preorder[preIndex++];
        TreeNode root = new TreeNode(rootValue);
        int mid = inorderIndex.get(rootValue);
        root.left = build(left, mid - 1);
        root.right = build(mid + 1, right);
        return root;
    }

    void main() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        IO.println("Level order: " + LevelOrderTraversal.levelOrder(root));
        IO.println("Inorder:     " + InorderTraversal.inorderRecursive(root));
    }
}
