package Day;

public class numberOfSubarrays {
    public static void main(String[] args) {

        int[] ints = {1, 1, 2, 1, 1};
        numberOfSubarrays number = new numberOfSubarrays();
        System.out.println(number.numberOfSubarrays(ints, 3));
    }

//    给你一个整数数组 nums 和一个整数 k。如果某个连续子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
//    请返回这个数组中 「优美子数组」 的数目。

    public int numberOfSubarrays(int[] nums, int k) {
        int r = 0,l = 0,m = 0,n = nums.length;// 滑动窗口
        int cnt = 0,ans = 0;
        while(r < n){
            if((nums[r++]&1) == 1)++cnt;
            while(cnt>k){
                if((nums[l++]&1) == 1)--cnt;
                m=l;
            }
            while(m<r && !((nums[m]&1) == 1))++m;
            if(cnt==k)ans+=m-l+1;
        }
        return ans;
    }
}
