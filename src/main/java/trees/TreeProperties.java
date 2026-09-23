package trees;

/**
 * File 04 — Q98: Diameter, Balanced Check, Validate BST
 */
class TreeProperties {

    private int diameter;

    int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        heightForDiameter(root);
        return diameter;
    }

    private int heightForDiameter(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = heightForDiameter(node.left);
        int right = heightForDiameter(node.right);
        diameter = Math.max(diameter, left + right);
        return 1 + Math.max(left, right);
    }

    boolean isBalanced(TreeNode root) {
        return balancedHeight(root) >= 0;
    }

    private int balancedHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = balancedHeight(node.left);
        if (left < 0) {
            return -1;
        }
        int right = balancedHeight(node.right);
        if (right < 0 || Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode node, long low, long high) {
        if (node == null) {
            return true;
        }
        if (node.val <= low || node.val >= high) {
            return false;
        }
        return isValidBST(node.left, low, node.val)
            && isValidBST(node.right, node.val, high);
    }

    void main() {
        TreeProperties props = new TreeProperties();
        TreeNode root = TreeUtils.build(1, 2, 3, 4, 5);
        IO.println("Diameter: " + props.diameterOfBinaryTree(root));   // 3

        IO.println("Balanced: " + props.isBalanced(root));   // true
        IO.println("Balanced: " + props.isBalanced(TreeUtils.build(1, 2, null, 3)));   // false

        IO.println("Valid BST: " + props.isValidBST(TreeUtils.build(2, 1, 3)));   // true
        IO.println("Valid BST: " + props.isValidBST(TreeUtils.build(5, 1, 4, null, null, 3, 6)));   // false
    }
}
