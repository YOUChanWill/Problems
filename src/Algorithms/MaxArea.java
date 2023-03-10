package Algorithms;

public class MaxArea {
    public static void main(String[] args) {

    }
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1, ans = 0;
        while (left < right){
            int area = Math.min(height[left], height[right]) * (right - left);// 数组中的短板来决定最终的长度
            ans = Math.max(ans, area);
            if (height[left] <= height[right]) {
                ++left;
            }
            else {
                --right;
            }
        }
        return ans;
    }
}
