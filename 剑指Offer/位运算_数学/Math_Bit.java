package 位运算_数学;

import java.util.Arrays;

public class Math_Bit {


    /**剑指 Offer 15. 二进制中1的个数

     编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。

     提示：
     请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，
     并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。

     在 Java 中，编译器使用 二进制补码 记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。*/
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    public int hammingWeight1(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }


    /**剑指 Offer 65. 不用加减乘除做加法

     写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。*/
    public int add(int a, int b) {

    }


    /**剑指 Offer 56 - I. 数组中数字出现的次数

     一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
     要求时间复杂度是O(n)，空间复杂度是O(1)。*/
    public int[] singleNumbers(int[] nums) {

    }


    /**剑指 Offer 56 - II. 数组中数字出现的次数 II

     在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。*/
    public int singleNumber(int[] nums) {

    }



}
