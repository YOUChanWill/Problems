package DataStructures;

import java.util.HashSet;
import java.util.Map;

public class containsDuplicate {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4};
        containsDuplicate duplicate = new containsDuplicate();
        System.out.println(duplicate.containsDuplicate(ints));

    }

//    给你一个整数数组 nums 。如果任一值在数组中出现至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。

    public boolean containsDuplicate(int[] nums) {
        HashSet hashSet = new HashSet();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            hashSet.add(nums[i]);
            count++;
        }
        if (hashSet.size() != count) {
            return true;
        } else return false;
    }

    public boolean containsDuplicate01(int[] nums) {
        HashSet hashSet = new HashSet();
        for (int x : nums) {
            if (!hashSet.add(x)) {
                return true;
            }
        }
        return false;
    }
}
