package trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * File 04 — Q329: Kth Smallest in BST
 */
class KthSmallestInBST {

    static int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (--k == 0) {
                return current.val;
            }
            current = current.right;
        }
        return -1;
    }

    void main() {
        TreeNode root = TreeUtils.build(5, 3, 6, 2, 4, null, null, 1);
        IO.println(kthSmallest(root, 3));   // 3
        IO.println(kthSmallest(root, 1));   // 1
    }
}
