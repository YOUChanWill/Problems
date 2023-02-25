package Day;

public class numberOfSubarrays {
    public static void main(String[] args) {

        int[] ints = {1, 1, 2, 1, 1};
        numberOfSubarrays number = new numberOfSubarrays();
        System.out.println(number.numberOfSubarrays(ints, 3));
    }


    public int numberOfSubarrays(int[] nums, int k) {
        int r=0,l=0,m=0,n=nums.length;
        int cnt=0,ans=0;
        while(r<n){
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
