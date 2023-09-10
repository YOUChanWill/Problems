package 分治算法;

import DataStructures.HasCycle;

import java.util.HashMap;
import java.util.Stack;

public class Solution {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**剑指 Offer 07. 重建二叉树

     输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。

     假设输入的前序遍历和中序遍历的结果中都不含重复的数字。*/
    int[] preorder;
    HashMap<Integer,Integer> hashMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i],i);
        }
        return build(0,0, preorder.length - 1);
    }

    private TreeNode build(int root, int left, int right){
        if (left > right) return null;
        TreeNode node = new TreeNode(preorder[root]);
        int i = hashMap.get(preorder[root]);
        node.left = build(root + 1, left, i - 1);
        node.right = build(root + i - left + 1,i + 1 ,right);
        return node;
    }


    /**剑指 Offer 16. 数值的整数次方

     实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。*/
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        long b = n;
        double res = 1.0;
        if (b < 0){
            x = 1 / x;
            b = -b;
        }
        while (b > 0){
            if ((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }


    /**剑指 Offer 33. 二叉搜索树的后序遍历序列

     输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
     如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。*/
    public boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i]){
                root = stack.pop();
            }
            stack.add(postorder[i]);
        }
        return true;
    }

    public boolean verifyPostorder1(int[] postorder) {
        return helper(postorder, 0, postorder.length - 1);
    }

    boolean helper(int[] postorder, int left, int right) {
        //如果left==right，就一个节点不需要判断了，如果left>right说明没有节点，
        //也不用再看了,否则就要继续往下判断
        if (left >= right)
            return true;
        //因为数组中最后一个值postorder[right]是根节点，这里从左往右找出第一个比
        //根节点大的值，他后面的都是根节点的右子节点（包含当前值，不包含最后一个值，
        //因为最后一个是根节点），他前面的都是根节点的左子节点
        int mid = left;
        int root = postorder[right];
        while (postorder[mid] < root)
            mid++;
        int temp = mid;
        //因为postorder[mid]前面的值都是比根节点root小的，
        //我们还需要确定postorder[mid]后面的值都要比根节点root大，
        //如果后面有比根节点小的直接返回false
        while (temp < right) {
            if (postorder[temp++] < root)
                return false;
        }
        //然后对左右子节点进行递归调用
        return helper(postorder, left, mid - 1) && helper(postorder, mid, right - 1);
    }



    /**剑指 Offer 17. 打印从1到最大的n位数

     输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。*/
    public int[] printNumbers(int n) {
        int lengh = 1;
        for (int i = 0; i < n; i++) {
            lengh *= 10;
        }
        int[] ans = new int[lengh - 1];
        for (int i = 0; i < lengh - 1; i++) {
            ans[i] = i + 1;
        }
        return ans;
    }



    int[] res;
    int nine = 0, count = 0, start, n;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public int[] printNumbers1(int n) {
        this.n = n;
        res = new int[(int)Math.pow(10, n) - 1];
        num = new char[n];
        start = n - 1;
        dfs(0);
        return res;
    }
    void dfs(int x) {
        if(x == n) {
            String s = String.valueOf(num).substring(start);
            if(!s.equals("0")) res[count++] = Integer.parseInt(s);
            if(n - start == nine) start--;
            return;
        }
        for(char i : loop) {
            if(i == '9') nine++;
            num[x] = i;
            dfs(x + 1);
        }
        nine--;
    }



    /**剑指 Offer 51. 数组中的逆序对

     在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。*/
    public int reversePairs(int[] nums) {

    }

}
