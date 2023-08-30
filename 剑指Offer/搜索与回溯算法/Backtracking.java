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
        if (A == null || B == null ) return false;
        return isSameTree(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }

    private boolean isSameTree(TreeNode Anode, TreeNode Bnode) {
        if (Bnode == null) return true;
        if (Anode == null || Anode.val != Bnode.val) return false;
        return isSameTree(Anode.left,Bnode.left) && isSameTree(Anode.right,Bnode.right);
    }


    /**100. 相同的树

     给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。

     如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。*/
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (q ==  null && p == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);
    }


    /**剑指 Offer 27. 二叉树的镜像

     请完成一个函数，输入一个二叉树，该函数输出它的镜像。*/
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
        return root;
    }


    public TreeNode mirrorTree1(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
            TreeNode node1 = node.left;
            node.left = node.right;
            node.right = node1;
        }
        return root;
    }


    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) return null;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerLast(root);
        while (!deque.isEmpty()){
            TreeNode node = deque.pollFirst();
            if (node.left != null) deque.offerLast(node.left);
            if (node.right != null) deque.offerLast(node.right);
            TreeNode node1 = node.left;
            node.left = node.right;
            node.right = node1;
        }
        return root;
    }


    /**剑指 Offer 28. 对称的二叉树

     请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。*/
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSame(root.left,root.right);
    }

    public boolean isSame(TreeNode p, TreeNode q) {
        if (q ==  null && p == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSame(p.left, q.right) && isSame(p.right, q.left);
    }


    /**剑指 Offer 12. 矩阵中的路径

     给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

     单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     同一个单元格内的字母不允许被重复使用。*/
    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, chars, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] word, int i, int j, int k){
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if (k == (word.length - 1)) return true; // 递归结束
        char temp = board[i][j];
        board[i][j] = '\0';
        boolean res = dfs(board,word,i + 1, j , k + 1) || dfs(board,word,i - 1 ,j ,k + 1) ||
                dfs(board,word,i , j + 1 , k + 1) || dfs(board,word,i  ,j - 1,k + 1);
        board[i][j] = temp; // 回溯的过程
        return res;
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


    /**剑指 Offer 64. 求1+2+…+n

     求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。*/
    public int sumNums(int n) {

    }


    /**剑指 Offer 68 - I. 二叉搜索树的最近公共祖先

     给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

     百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”*/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    }


    /**剑指 Offer 68 - II. 二叉树的最近公共祖先

     给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

     百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”*/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    }


    /**剑指 Offer 37. 序列化二叉树

     请实现两个函数，分别用来序列化和反序列化二叉树。

     你需要设计一个算法来实现二叉树的序列化与反序列化。
     这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

     提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
     你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。*/
    class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {

        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

        }
    }


    /**剑指 Offer 38. 字符串的排列

     输入一个字符串，打印出该字符串中字符的所有排列。

     你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。*/
    public String[] permutation(String s) {

    }


}
