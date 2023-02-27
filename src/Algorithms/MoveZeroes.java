package Algorithms;

public class MoveZeroes {

    public static void main(String[] args) {
        int[] ins = {0,0,1};
        MoveZeroes zeroes = new MoveZeroes();
        zeroes.moveZeroes01(ins);
        for (int i = 0; i < ins.length; i++) {
            System.out.print(ins[i]);
        }
    }

    public void moveZeroes01(int[] nums) {
        int indexNow = 0;
        int indexNum = 0;
        int m = nums.length;

        while(indexNum<m){
            if(nums[indexNum] != 0) {
                nums[indexNow++] = nums[indexNum];
            }
            ++indexNum;
        }

        for(int i = indexNow; i < m; i++){
            nums[i] = 0;
        }
    }

    public void moveZeroes02(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

}
