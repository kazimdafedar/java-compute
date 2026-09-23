package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * File 04 — Q399: Height of Binary Tree
 */
class TreeHeight {

    static int heightRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightRecursive(root.left), heightRecursive(root.right));
    }

    static int heightBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()) {
            height++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return height;
    }

    void main() {
        TreeNode root = TreeUtils.build(3, 9, 20, null, null, 15, 7);
        IO.println("Recursive: " + heightRecursive(root));   // 3
        IO.println("BFS:       " + heightBfs(root));         // 3
    }
}
