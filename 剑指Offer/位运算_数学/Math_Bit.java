package 位运算_数学;

import java.util.Arrays;
import java.util.HashSet;

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
            n &= n - 1; // 把 n 的二进制位中的最低位的 1 变为 0
            ret++;
        }
        return ret;
    }


    /**剑指 Offer 65. 不用加减乘除做加法

     写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。*/
    public int add(int a, int b) {
        while (b != 0) { // 进位为0时跳出
            int c = (a & b) << 1; // 进位
            a ^= b; // 没有进位
            b = c; // 进位
        }
        return a;
    }


    /**剑指 Offer 56 - I. 数组中数字出现的次数

     一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
     要求时间复杂度是O(n)，空间复杂度是O(1)。*/
    public int[] singleNumbers(int[] nums) {
        //因为相同的数字异或为0，任何数字与0异或结果是其本身。
        //所以遍历异或整个数组最后得到的结果就是两个只出现一次的数字异或的结果：即 z = x ^ y
        int z = 0;
        for(int i : nums) z ^= i;
        //我们根据异或的性质可以知道：z中至少有一位是1，否则x与y就是相等的。
        //我们通过一个辅助变量m来保存z中哪一位为1.（可能有多个位都为1，我们找到最低位的1即可）。
        //举个例子：z = 10 ^ 2 = 1010 ^ 0010 = 1000,第四位为1.
        //我们将m初始化为1，如果（z & m）的结果等于0说明z的最低为是0
        //我们每次将m左移一位然后跟z做与操作，直到结果不为0.
        //此时m应该等于1000，同z一样，第四位为1.
        int m = 1;
        while((z & m) == 0) m <<= 1;
        //我们遍历数组，将每个数跟m进行与操作，结果为0的作为一组，结果不为0的作为一组
        //例如对于数组：[1,2,10,4,1,4,3,3]，我们把每个数字跟1000做与操作，可以分为下面两组：
        //nums1存放结果为0的: [1, 2, 4, 1, 4, 3, 3]
        //nums2存放结果不为0的: [10] (碰巧nums2中只有一个10，如果原数组中的数字再大一些就不会这样了)
        //此时我们发现问题已经退化为数组中有一个数字只出现了一次
        //分别对nums1和nums2遍历异或就能得到我们预期的x和y
        int x = 0, y = 0;
        for(int i : nums) {
            //这里我们是通过if...else将nums分为了两组，一边遍历一遍异或。
            //跟我们创建俩数组nums1和nums2原理是一样的。
            if((i & m) == 0) x ^= i;
            else y ^= i;
        }
        return new int[]{x, y};
    }


    /**剑指 Offer 56 - II. 数组中数字出现的次数 II

     在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。*/
    public int singleNumber(int[] nums) {
        if(nums.length==0) return -1;//输入数组长度不符合要求，返回-1;
        int[] bitSum = new int[32];//java int类型有32位，其中首位为符号位
        int res=0;
        for(int num:nums){
            int bitMask=1;//需要在这里初始化，不能和res一起初始化
            for(int i=31;i>=0;i--){//bitSum[0]为符号位
                //这里同样可以通过num的无符号右移>>>来实现，否则带符号右移(>>)左侧会补符号位，对于负数会出错。
                //但是不推荐这样做，最好不要修改原数组nums的数据
                if((num&bitMask)!=0) bitSum[i]++;//这里判断条件也可以写为(num&bitMask)==bitMask,而不是==1
                bitMask=bitMask<<1;//左移没有无符号、带符号的区别，都是在右侧补0
            }
        }
        for(int i=0;i<32;i++){//这种做法使得本算法同样适用于负数的情况
            res=res<<1;
            res+=bitSum[i]%3;//这两步顺序不能变，否则最后一步会多左移一次
        }
        return res;
    }

    public int singleNumber1(int[] nums) {

    }


}
