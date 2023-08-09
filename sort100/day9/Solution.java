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


    /**给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。

     返回 已排序的字符串 。如果有多个答案，返回其中任何一个。**/
    public String frequencySort(String s) {
        int[][] cnts = new int[128][2];
        char[] cs = s.toCharArray();
        for (int i = 0; i < 128; i++) cnts[i][0] = i;
        for (char c : cs) cnts[c][1]++;
        Arrays.sort(cnts, (a, b)->{
            return a[1] != b[1] ? b[1] - a[1] : a[0] - b[0];
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            char c = (char)cnts[i][0];
            int k = cnts[i][1];
            while (k-- > 0) sb.append(c);
        }
        return sb.toString();
    }

    /**要求选手从 N 张卡牌中选出 cnt 张卡牌，
     * 若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。 给定数组 cards 和 cnt，
     * 其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0**/
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int len = cards.length;
        int sum = 0, count = 0, a1 = 0, a2 = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (count == cnt) {
                break;
            }
            // 最大的奇数
            if (cards[i] % 2 != 0) {
                a1 = cards[i];
            }
            // 最大的偶数
            if (cards[i] % 2 == 0) {
                a2 = cards[i];
            }
            sum += cards[i];
            count++;
        }
        // 偶数直接返回
        if (sum % 2 == 0) {
            return sum;
        }
        // 不是偶数接着取
        int a3 = 0, a4 = 0;
        for (int i = len - 1 - cnt; i >= 0; i--) {
            if (a3 != 0 && a4 != 0) {
                break;
            }
            // 下一个奇数
            if (a3 == 0 && cards[i] % 2 != 0) {
                a3 = cards[i];
            }
            // 下一个偶数
            if (a4 == 0 && cards[i] % 2 == 0) {
                a4 = cards[i];
            }
        }
        if (cnt == 1 && a4 == 0) {
            return 0;
        }
        if (cnt == 1) {
            return a4;
        }
        if (a3 == 0 || a4 == 0) {
            if (a3 != 0) {
                return a3 + sum - a2;
            }
            if (a4 != 0) {
                return a4 + sum - a1;
            } else {
                return 0;
            }
        }
        //01 010
        if (a2 == 0){
            return a4 + sum - a1;
        }
        return Math.max(a3 + sum - a2, a4 + sum - a1);
    }

    public int maxmiumScore01(int[] cards, int cnt) {
        // 排序后，逆序遍历求前1、2、3、4、5.。。个的奇数或者偶数的和放入到odd和even中
        Arrays.sort(cards);
        int len = cards.length;
        List<Integer> odd = new ArrayList<>(), even = new ArrayList<>();
        odd.add(0); even.add(0);
        for(int i=len-1;i>=0;i--){
            if(cards[i]%2 == 1) odd.add(odd.get(odd.size()-1)+cards[i]);
            else even.add(even.get(even.size()-1)+cards[i]);
        }
        // 枚举k从0开始，每次+2
        int k = 0;
        int ans = 0;
        // 所以odd选择的必须是偶数，偶数的下表是0，2，4，6.。。。。，所以k=0，k+=2；
        // 如果k=2，可能会出现全是偶数的情况，如果cnt=1，这是返回even，不会进入循环，and=0，错误，不理解可以忽略这一步
        // 所以再while循环中，只需要满足k和cnt-k分别不越界，并且相加是偶数就可以把最大值记录下来，直到不满足循环退出
        // 返回计算结果
        while(k<=cnt){
            if(k<odd.size()&&(cnt-k)<even.size()&&(odd.get(k)+even.get(cnt-k))%2==0)
                ans = Math.max(ans, odd.get(k)+even.get(cnt-k));
            k+=2;
        }
        return ans;
    }

    public int maxmiumScore02(int[] cards, int cnt) {
        int max = 1000;

        int[] count = new int[max +1];
        for(int x:cards)
            ++count[x];

        int result = 0;
        int lastOdd = max + 1;
        int lastEven = max + 1;

        for(int x = max; x > 0; --x){
            if(count[x] == 0) continue;
            int min = Math.min(cnt, count[x]);
            result += min * x;
            cnt -= min;
            count[x] -= min;
            if((x & 1) == 0)
                lastEven = x;
            else
                lastOdd = x;
            if(cnt == 0)
                break;
        }

        if((result & 1) == 0) return result;

        int last = Math.min(lastEven, lastOdd);

        int delta1 = Integer.MIN_VALUE;
        if(count[last] > 0 && lastEven <= max && lastOdd <= max)
            delta1 = - Math.abs(lastOdd - lastEven);

        int delta2 = Integer.MIN_VALUE;
        for(int x = last - 1; x > 0; x -= 2){
            if(count[x] > 0){
                delta2 = x - last;
                break;
            }
        }

        int delta = Math.max(delta1, delta2);

        return delta == Integer.MIN_VALUE? 0: result + delta;
    }

    /**给定一个字符串数组 strs ，将 变位词 组合在一起。 可以按任意顺序返回结果列表。

     注意：若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。**/
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            ArrayList<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }


    /**给你一个整数数组 nums 和一个整数 k 。你可以将 nums 划分成一个或多个 子序列 ，使 nums 中的每个元素都 恰好 出现在一个子序列中。

     在满足每个子序列中最大值和最小值之间的差值最多为 k 的前提下，返回需要划分的 最少 子序列数目。

     子序列 本质是一个序列，可以通过删除另一个序列中的某些元素（或者不删除）但不改变剩下元素的顺序得到。**/
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1, n = nums.length, min = nums[0];
        if (n == 1 || n == 0) return 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] - min > k){
                ans++;
                min = nums[i];
            }
        }
        return ans;
    }

    public int partitionArray01(int[] nums, int k) {

        int min = 100001, max = 0;
        for(int x:nums){
            if(x < min) min = x;
            if(x > max) max = x;
        }
        int n = max - min + 1;

        if(n < k)
            return 1;
        int[] count = new int[n];
        for(int x:nums)
            ++count[x - min];

        int result = 0;
        int index = 0;
        while(index < n)
        {
            if(count[index] > 0){
                ++result;
                index += k +1 ;
            }else{
                ++index;
            }

        }
        return result;
    }

}
