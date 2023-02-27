//package Algorithms;
//
//public class FirstBadVersion {
//    public static void main(String[] args) {
//
//    }
//
//
//// 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
//// 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
//// 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
//
//    public int firstBadVersion(int n) {
//        int first = 1,end = n;
//        while (first < end) { // 循环直至区间左右端点相同
//            int mid = first + (end - first) / 2; // 防止计算时溢出
//            if (isBadVersion(mid)) {
//                end = mid; // 答案在区间 [left, mid] 中
//            } else {
//                first = mid + 1; // 答案在区间 [mid+1, right] 中
//            }
//        }
//        // 此时有 left == right，区间缩为一个点，即为答案
//        return first;
//    }
//}
//
