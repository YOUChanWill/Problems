package 搜索与回溯算法;

import java.util.*;

public class Backtracking {

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


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    /**剑指 Offer 32 - I. 从上到下打印二叉树

     从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。*/
    public int[] levelOrder1(TreeNode root) {
        if (root == null) return new int[0];
        Deque<TreeNode> deque = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        deque.add(root);
        while (!deque.isEmpty()){
            TreeNode node = deque.pollFirst();
            list.add(node.val);
            if (node.left != null) deque.addLast(node.left);
            if (node.right != null) deque.addLast(node.right);
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
//        int arr[] = list.stream().mapToInt(Integer::intValue).toArray();
        return ans;
    }


    /**剑指 Offer 32 - II. 从上到下打印二叉树 II

     从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。*/
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null) deque.addLast(root);
        while (!deque.isEmpty()){
            List<Integer> list = new ArrayList<>();
            for (int i = deque.size(); i > 0; i--) {
                TreeNode node = deque.pollFirst();
                list.add(node.val);
                if (node.left != null) deque.offerLast(node.left);
                if (node.right != null) deque.offerLast(node.right);
            }
            ans.add(list);
        }
        return ans;
    }

    // 深度搜索，递归
    List<List<Integer>> node = new ArrayList();

    public List<List<Integer>> levelOrder(TreeNode root) {
        lei(root, 0);
        return node;
    }

    public void lei(TreeNode root, int k){
        if(root != null){
            if(node.size()<=k) {
                node.add(new ArrayList());
            }
            node.get(k).add(root.val);
            lei(root.left, k+1);
            lei(root.right,k+1);
        }
    }


    /**剑指 Offer 32 - III. 从上到下打印二叉树 III

     请实现一个函数按照之字形顺序打印二叉树，
     即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。*/
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null) deque.offerLast(root);
        while (!deque.isEmpty()){
            List<Integer> list = new ArrayList<>();
            for (int i = deque.size(); i > 0; i--) {
                TreeNode node = deque.pollFirst();
                list.add(node.val);
                if (node.left != null) deque.offerLast(node.left);
                if (node.right != null) deque.offerLast(node.right);
            }
            if (ans.size() % 2 == 1) Collections.reverse(list);
            ans.add(list);
        }
        return ans;
    }

    public List<List<Integer>> levelOrder301(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null) deque.offerLast(root);
        while (!deque.isEmpty()){
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = deque.size(); i > 0; i--) {
                TreeNode node = deque.pollFirst();
                if (ans.size() % 2 == 0) list.addLast(node.val);
                else list.addFirst(node.val);
                if (node.left != null) deque.offerLast(node.left);
                if (node.right != null) deque.offerLast(node.right);
            }
            ans.add(list);
        }
        return ans;
    }


    /**剑指 Offer 26. 树的子结构

     输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

     B是A的子结构， 即 A中有出现和B相同的结构和节点值。*/
    public boolean isSubStructure(TreeNode A, TreeNode B) {

    }



    /**剑指 Offer 27. 二叉树的镜像

     请完成一个函数，输入一个二叉树，该函数输出它的镜像。*/
    public TreeNode mirrorTree(TreeNode root) {

    }


    /**剑指 Offer 28. 对称的二叉树

     请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。*/
    public boolean isSymmetric(TreeNode root) {

    }



    /**剑指 Offer 12. 矩阵中的路径

     给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

     单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     同一个单元格内的字母不允许被重复使用。*/
    public boolean exist(char[][] board, String word) {

    }



    /**剑指 Offer 13. 机器人的运动范围

     地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
     它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
     请问该机器人能够到达多少个格子？*/
    public int movingCount(int m, int n, int k) {

    }


    /**剑指 Offer 34. 二叉树中和为某一值的路径

     给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

     叶子节点 是指没有子节点的节点。*/
    public List<List<Integer>> pathSum(TreeNode root, int target) {

    }



    /**剑指 Offer 36. 二叉搜索树与双向链表

     输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。*/
    public Node treeToDoublyList(Node root) {

    }



    /**剑指 Offer 54. 二叉搜索树的第k大节点

     给定一棵二叉搜索树，请找出其中第 k 大的节点的值。*/
    public int kthLargest(TreeNode root, int k) {

    }


    /**剑指 Offer 55 - I. 二叉树的深度

     输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。*/
    public int maxDepth(TreeNode root) {

    }



    /**剑指 Offer 55 - II. 平衡二叉树

     输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。*/
    public boolean isBalanced(TreeNode root) {

    }









}
