package ProgramLearn;

public class SumOddLengthSubarrays {

    public static int sumOddLengthSubarrays(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int countL = i;//下标为 i 的位置左边有几个数字
            int countR = arr.length - i - 1;//下标为 i 的位置右边有几个数字

            //开始构造长度为奇数的子数组

            //情况一: 位置 i 左边选奇数个数, 右边选奇数个数.
            //为什么这样选呢 ? 因为: 奇数 + 1 + 奇数 = 奇数
            int oddL = (countL + 1) >> 1;//在位置 i 的左边可以选多少种奇数
            int oddR = (countR + 1) >> 1;//在位置 i 的右边可以选多少种奇数
            //位置 i 左右两边都选奇数的话一共有多少种可选情况:
            //假设左边可选有 n 种, 右边可选有 m 种, 那么总情况一共有: n * m, 其实就是多少种组合嘛
            int oddAll = oddL * oddR;

            //情况二: 位置 i 左边选偶数个数, 右边选偶数个数.
            //为什么这样选呢 ? 因为: 偶数 + 1 + 偶数 = 奇数
            int evenL = (countL >> 1) + 1;//在位置 i 的左边可以选多少种偶数
            int evenR = (countR >> 1) + 1;//在位置 i 的右边可以选多少种偶数
            //位置 i 左右两边都选偶数的话一共有多少种可选情况:
            //假设左边可选有 n 种, 右边可选有 m 种, 那么总情况一共有: n * m, 其实就是多少种组合嘛
            int evenAll = evenL * evenR;

            ans += arr[i] * (oddAll + evenAll);
        }
        return ans;
    }
}
