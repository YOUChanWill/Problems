package Algorithms;

public class Rotate {

// 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[(i + k) % n] = nums[i];
        }
        System.arraycopy(ans,0,nums,0,n);// 数组拷贝，拷贝谁，开始下标，拷给谁，开始下标，数组长度
    }

//nums = "----->-->"; k =3
//result = "-->----->";
//
//reverse "----->-->" we can get "<--<-----"
//reverse "<--" we can get "--><-----"
//reverse "<-----" we can get "-->----->"
//this visualization help me figure it out :)

    public void rotate01(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    // 翻转数组，再翻转0到k的数组，再翻转剩下的数组
    public void reverse(int[] nums,int start,int end){
        while (start < end){
            int temp = nums[start];// 使用双指针进行头尾交换元素
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }




}
