package day3;

import java.util.*;

public class Solution {

    /**现有一台饮水机，可以制备冷水、温水和热水。每秒钟，可以装满 2 杯 不同 类型的水或者 1 杯任意类型的水。
     给你一个下标从 0 开始、长度为 3 的整数数组 amount ，
     其中 amount[0]、amount[1] 和 amount[2] 分别表示需要装满冷水、温水和热水的杯子数量。返回装满所有杯子所需的 最少 秒数。
     **/
    // 中和小的和小于等于大时，答案就是 大。此时大的尽量陪中小一起灌水
    // 中和小的和大于大时，答案是 大 + 多余部分的一半（要向上取整）。此时大的陪中小灌完水以后，中小自己互相陪着灌水
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[2] > amount[1] + amount[0]) return amount[2];
        return (amount[0] + amount[1] + amount[2] + 1) / 2;
    }


    /**从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
     * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
     **/
    public static boolean isStraight(int[] nums) {
        HashSet<Integer> set = new HashSet<>(); // 用于判断是否有重复的
        int max = -1 ,min = 14;
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i]) && nums[i] != 0) return false;
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min && nums[i] != 0) {
                min = nums[i];
            }
        }
        return max - min < 5;
    }


    /**给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     初始化 A 和 B 的元素数量分别为 m 和 n。**/
    public void merge(int[] A, int m, int[] B, int n) {
        for (int i = 0; i != n; ++i) {
            A[m + i] = B[i];
        }
        Arrays.sort(A);
    }

    // 使用双指针
    public void merge01(int[] A, int m, int[] B, int n) {
        int pa = m - 1, pb = n - 1;
        int tail = m + n - 1;
        int cur = 0;
        while (pa >= 0 || pb >= 0){
            if (pa == -1) cur = B[pb--];
            else if (pb == -1) {
                cur = A[pa--];
            } else if (A[pa] > B[pb]) {
                cur = A[pa--];
            }else cur = B[pb--];
            A[tail--] = cur;
        }
    }


    /**数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？**/
    public int missingNumber(int[] nums) {
        int lengh = nums.length;
        int sum = (1 + lengh) * lengh / 2;
        int cur = 0;
        for (int x :
                nums) {
            cur += x;
        }
        return sum - cur;
    }

    //利用异或的特性，res = res ^ x ^ x。对同一个值异或两次，那么结果等于它本身
    public int missingNumber01(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            res ^= i;
            res ^= nums[i];
        }
        res ^= nums.length;

        return res;
    }

    public int missingNumber02(int[] nums) {
        int[] arr = new int[nums.length+1];
        for(int i = 0;i < nums.length;i++){
            arr[nums[i]]++;
        }
        for(int i = 0;i < arr.length;i++){
            if(arr[i] == 0){
                return i;
            }
        }
        return -1;
    }


    /**给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。

     注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。**/
    public boolean isAnagram(String s, String t) {
        int m = s.length(), n = t.length();
        if (m != n) return false;
        if (s.equals(t)) return false;
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (char a :
                s.toCharArray()) {
            cnt1[a - 'a']++;
        }
        for (char a :
                t.toCharArray()) {
            cnt2[a - 'a']++;
        }
        return Arrays.equals(cnt1,cnt2);
    }

    /**给你一个正整数 num 。你可以交换 num 中 奇偶性 相同的任意两位数字（即，都是奇数或者偶数）。
     返回交换 任意 次之后 num 的 最大 可能值。**/
    public int largestInteger(int num) {

    }


    /**给你 n 个二维平面上的点 points ，其中 points[i] = [xi, yi] ，请你返回两点之间内部不包含任何点的 最宽垂直区域 的宽度。
     垂直区域 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 最宽垂直区域 为宽度最大的一个垂直区域。
     请注意，垂直区域 边上 的点 不在 区域内。**/
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points,(a,b)->a[0]-b[0]);
        int max = 0;
        for (int i = 1; i < points.length; i++) {
            max = Math.max(points[i][0] - points[i - 1][0],max);
        }
        return max;
    }


    /**一个数对 (a,b) 的 数对和 等于 a + b 。最大数对和 是一个数对数组中最大的 数对和 。

     比方说，如果我们有数对 (1,5) ，(2,3) 和 (4,4)，最大数对和 为 max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8 。
     给你一个长度为 偶数 n 的数组 nums ，请你将 nums 中的元素分成 n / 2 个数对，使得：

     nums 中每个元素 恰好 在 一个 数对中，且
     最大数对和 的值 最小 。
     请你在最优数对划分的方案下，返回最小的 最大数对和 。**/
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[0] + nums[n - 1];
        for (int i = 0,j = n - 1; i < j; i++,j--) {
            ans = Math.max(ans,nums[i] + nums[j]);
        }
        return ans;
    }



    /**牌组中的每张卡牌都对应有一个唯一的整数。你可以按你想要的顺序对这套卡片进行排序。

     最初，这些卡牌在牌组里是正面朝下的（即，未显示状态）。

     现在，重复执行以下步骤，直到显示所有卡牌为止：

     从牌组顶部抽一张牌，显示它，然后将其从牌组中移出。
     如果牌组中仍有牌，则将下一张处于牌组顶部的牌放在牌组的底部。
     如果仍有未显示的牌，那么返回步骤 1。否则，停止行动。
     返回能以递增顺序显示卡牌的牌组顺序。
     答案中的第一张牌被认为处于牌堆顶部。**/
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Deque<Integer> index = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            index.add(i);
        }
        int[] ans = new int[n];
        Arrays.sort(deck);
        for (int x :
                deck) {
            ans[index.pollFirst()] = x;
            if (!index.isEmpty()) index.add(index.pollFirst());
        }
        return ans;
    }

    public int[] deckRevealedIncreasing01(int[] deck) {
        if (deck == null || deck.length < 1) {
            return deck;
        }
        Arrays.sort(deck);// 得到升序排列的数组

        Queue<Integer> queue = new LinkedList<>();
        for (int i = deck.length - 1;i >= 0;i--) {// 倒着遍历，便是按降序访问
            queue.add(deck[i]);// 选最大值插入队列
            if (i == 0) {// 数组中所有元素均在队列中，退出过程
                break;
            }

            queue.add(queue.poll());// 将队头元素插入到队尾中
        }
        for (int i = deck.length - 1;i >= 0;i--) {
            deck[i] = queue.poll();// 倒回去，得到answer
        }
        return deck;
    }





    public static void main(String[] args) {
        int[] list = new int[]{0,3,4,13,9};
        isStraight(list);
    }



}
