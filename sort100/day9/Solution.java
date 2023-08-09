package day9;

import java.util.*;

public class Solution {

    /**给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。

     您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。**/
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, max = 0;
        if (n < 2) return 0;
        for (int i = 1; i < n; i++) {
            max = Math.max(max,(nums[i] - nums[i - 1]));
        }
        return max;
    }

    // 使用基数排序
    public int maximumGap01(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        long exp = 1;
        int[] buf = new int[n];
        int maxVal = Arrays.stream(nums).max().getAsInt();

        while (maxVal >= exp) {
            int[] cnt = new int[10];
            for (int i = 0; i < n; i++) {
                int digit = (nums[i] / (int) exp) % 10;
                cnt[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                cnt[i] += cnt[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int digit = (nums[i] / (int) exp) % 10;
                buf[cnt[digit] - 1] = nums[i];
                cnt[digit]--;
            }
            System.arraycopy(buf, 0, nums, 0, n);
            exp *= 10;
        }

        int ret = 0;
        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, nums[i] - nums[i - 1]);
        }
        return ret;
    }

    // 使用桶排序
    public int maximumGap02(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int d = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketSize = (maxVal - minVal) / d + 1;

        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; ++i) {
            Arrays.fill(bucket[i], -1); // 存储 (桶内最小值，桶内最大值) 对， (-1, -1) 表示该桶是空的
        }
        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - minVal) / d;
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = bucket[idx][1] = nums[i];
            } else {
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }

        int ret = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }
            if (prev != -1) {
                ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
            }
            prev = i;
        }
        return ret;
    }


    /**给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。**/
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int x :
                nums) {
            map.put(x,map.getOrDefault(x,0) + 1);
        }
        for (int x : nums) {
            if (map.get(x) > (n / 3)) {
                ans.add(x);
                map.put(x,0);
            }
        }
        return ans;
    }
    // 使用摩尔投票法
    public List<Integer> majorityElement01(int[] nums) {
        int element1 = 0;
        int element2 = 0;
        int vote1 = 0;
        int vote2 = 0;

        for (int num : nums) {
            if (vote1 > 0 && num == element1) { //如果该元素为第一个元素，则计数加1
                vote1++;
            } else if (vote2 > 0 && num == element2) { //如果该元素为第二个元素，则计数加1
                vote2++;
            } else if (vote1 == 0) { // 选择第一个元素
                element1 = num;
                vote1++;
            } else if (vote2 == 0) { // 选择第二个元素
                element2 = num;
                vote2++;
            } else { //如果三个元素均不相同，则相互抵消1次
                vote1--;
                vote2--;
            }
        }

        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                cnt1++;
            }
            if (vote2 > 0 && num == element2) {
                cnt2++;
            }
        }
        // 检测元素出现的次数是否满足要求
        List<Integer> ans = new ArrayList<>();
        if (vote1 > 0 && cnt1 > nums.length / 3) {
            ans.add(element1);
        }
        if (vote2 > 0 && cnt2 > nums.length / 3) {
            ans.add(element2);
        }

        return ans;
    }


    /**以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。

     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。**/
    public int[][] merge(int[][] intervals) {
        int n=intervals.length;
        if(n==0){
            return new int[0][2];
        }
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] interval1,int[] interval2){
                return interval1[0]-interval2[0];
            }
        });
        List<int[]> merged =new ArrayList<>();
        for(int i=0;i<n;i++){
            int L=intervals[i][0],R=intervals[i][1];
            if(merged.size()==0||merged.get(merged.size()-1)[1]<L){
                merged.add(new int[]{L,R});
            }else{
                merged.get(merged.size()-1)[1]=Math.max(merged.get(merged.size()-1)[1],R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public int[][] merge01(int[][] intervals) {
        int max = -1;
        int min = Integer.MAX_VALUE;
        for(int[] i : intervals){
            max = Math.max(max, i[1]);
            min = Math.min(min, i[0]);
        }
        int len = max - min + 1;
        int[] pos = new int[len];
        for (int[] tuple : intervals) {
            int index = tuple[0] - min;
            if (pos[index] > 0) {
                if(pos[index] < tuple[1] - min)
                    pos[index] = tuple[1] - min;
            } else {
                pos[index] = tuple[1]-min;
            }
        }
        int start = 0;
        int end = pos[0];
        ArrayList<int[]> result = new ArrayList<>(intervals.length >> 1);
        for (int i = 1; i < len; i++) {
            if (pos[i] != 0) {
                if (i <= end) {
                    end = Math.max(end,pos[i]);
                } else {
                    result.add(new int[]{start+min,end+min});
                    start = i;
                    end = pos[i];
                }
            }
        }
        result.add(new int[]{start+min,end+min});
        return result.toArray(new int[][]{});
    }





}
