package ProgramLearn;

public class SumOfLeftLeaves {
    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        help(root);
        return sum;
    }

    public void help(TreeNode root){
        if (root == null){
            return;
        }
        if (root.left != null && root.left.left == null && root.left.right == null){
            sum += root.left.val;
        }
        help(root.left);
        help(root.right);
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
    TreeNode() {}
     TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
       this.left = left;
        this.right = right;
    }
}
