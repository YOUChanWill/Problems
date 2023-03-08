package DataStructures;

public class SearchBST {

// 左子树所有节点的元素值均小于根的元素值；
// 右子树所有节点的元素值均大于根的元素值。

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val == root.val) {
            return root;
        }
        return searchBST(val < root.val ? root.left : root.right, val);
    }


    public TreeNode searchBST01(TreeNode root, int val) {
        while (root != null) {
            if (val == root.val) {
                return root;
            }
            root = val < root.val ? root.left : root.right;
        }
        return null;
    }

}
