package trees;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * File 04 — Q99: Serialize / Deserialize Binary Tree
 */
class SerializeDeserializeTree {

    static String serialize(TreeNode root) {
        if (root == null) {
            return "#,";
        }
        return root.val + "," + serialize(root.left) + serialize(root.right);
    }

    static TreeNode deserialize(String data) {
        Deque<String> queue = new ArrayDeque<>(Arrays.asList(data.split(",")));
        return build(queue);
    }

    static TreeNode build(Deque<String> queue) {
        String value = queue.poll();
        if ("#".equals(value)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = build(queue);
        node.right = build(queue);
        return node;
    }

    void main() {
        TreeNode root = TreeUtils.build(1, 2, 3, null, null, 4, 5);
        String data = serialize(root);
        IO.println("Serialized: " + data);
        TreeNode restored = deserialize(data);
        IO.println("Inorder restored: " + InorderTraversal.inorderRecursive(restored));   // [2, 1, 4, 3, 5]
    }
}
