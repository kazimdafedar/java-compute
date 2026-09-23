package trees;

/**
 * File 04 — Q328: BST Insert, Search, Delete
 */
class BSTOperations {

    static TreeNode search(TreeNode root, int key) {
        while (root != null && root.val != key) {
            root = key < root.val ? root.left : root.right;
        }
        return root;
    }

    static TreeNode insert(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        if (key < root.val) {
            root.left = insert(root.left, key);
        } else if (key > root.val) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    static TreeNode delete(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = delete(root.left, key);
        } else if (key > root.val) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            root.val = successor.val;
            root.right = delete(root.right, successor.val);
        }
        return root;
    }

    void main() {
        TreeNode root = null;
        for (int value : new int[]{5, 3, 7, 2, 4}) {
            root = insert(root, value);
        }
        IO.println("Search 4: " + (search(root, 4) != null));   // true
        IO.println("Inorder:  " + InorderTraversal.inorderRecursive(root));

        root = delete(root, 3);
        IO.println("After delete 3: " + InorderTraversal.inorderRecursive(root));   // [2, 4, 5, 7]
    }
}
