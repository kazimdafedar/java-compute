package trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * File 04 — Q96: BFS Level-Order & DFS Traversals
 */
class LevelOrderTraversal {

    static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    static List<Integer> preorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    static void preorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    static List<Integer> postorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    static void postorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.val);
    }

    void main() {
        TreeNode root = TreeUtils.build(3, 9, 20, null, null, 15, 7);
        IO.println("Level order: " + levelOrder(root));   // [[3], [9, 20], [15, 7]]
        IO.println("Preorder:    " + preorder(root));     // [3, 9, 20, 15, 7]
        IO.println("Postorder:   " + postorder(root));   // [9, 15, 7, 20, 3]
    }
}
