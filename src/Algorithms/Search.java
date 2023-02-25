package Algorithms;

public class Search {
    public static void main(String[] args) {
        int[] ints = {-1,0,3,5,9,12};
        Search search = new Search();
        System.out.println(search.search(ints, 9));
    }

//    给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
//    写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = (left + right) / 2;

        while (left<=right) { //确保程序不会重复查询，不会越界
            if (target > nums[mid]) {
                //如果查询的值比中间值大，则往右边区域找，就把最小索引改为中间索引右移一位
                left = mid + 1;
            } else if (target < nums[mid]) {
                //如果查询的值比中间值小，则往左边区域找，就把最大索引改为中间索引左移一位
                right = mid - 1;
            } else {
                //剩余的情况就是查询到了结果，那么就直接返回索引。
                return mid;
            }
            mid = (left + right) / 2;
        }
        //没有查询到，则返回-1
        return -1;
    }


}
