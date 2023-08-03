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
        StringBuilder sb = new StringBuilder();
        sb.append(num);

        char[] arr = sb.toString().toCharArray();

        // 每次找到的数字若为偶数，则向后遍历，寻找更大的偶数，若存在，则交换位置，否则不做处理
        // 每次找到的数字若为奇数，则向后遍历，寻找更大的奇数，若存在，则交换位置，否则不做处理
        for (int i = 0; i < sb.length(); i++) {
            char n = arr[i];
            for (int j = i + 1; j < sb.length(); j++) {
                if ((n - '0') % 2 == 1 && (arr[j] - '0') % 2 == 1 && arr[j] - '0' > n - '0') {
                    arr[i] = arr[j];
                    arr[j] = n;
                    n = arr[i];
                } else if ((n - '0') % 2 == 0 && (arr[j] - '0') % 2 == 0 && arr[j] - '0' > n - '0') {
                    arr[i] = arr[j];
                    arr[j] = n;
                    n = arr[i];
                }
            }
        }
        return Integer.parseInt(String.copyValueOf(arr));
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


    /**给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     必须在不使用库内置的 sort 函数的情况下解决这个问题。**/
    public void sortColors(int[] nums) {
        int lengh = nums.length , n0 = 0, n1 = 0;
        for (int i = 0; i < lengh; i++) {
            int num = nums[i];
            nums[i] = 2;
            if (num < 2) nums[n1++] = 1;
            if (num < 1) nums[n0++] = 0;
        }
    }

    public void sortColors01(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        int zero = 0;
        int two = len;
        int i = 0;
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }


    /**给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。

     运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：

     名次第 1 的运动员获金牌 "Gold Medal" 。
     名次第 2 的运动员获银牌 "Silver Medal" 。
     名次第 3 的运动员获铜牌 "Bronze Medal" 。
     从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
     使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。**/
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] ans = new String[n];
        int[] clone = score.clone();
        Arrays.sort(clone);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0 ; i--) {
            map.put(clone[i],n - 1 - i);
        }
        String[] ss = new String[]{"Gold Medal","Silver Medal","Bronze Medal"};
        for (int i = 0; i < n; i++) {
            int rank = map.get(score[i]);
            ans[i] = rank < 3 ? ss[rank] : String.valueOf(rank + 1);
        }
        return ans;
    }

    /**商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。

     注意：Tony 可以按任意顺序购买雪糕。

     给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。

     你必须使用计数排序解决此问题。
     **/
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        for (int i = 0; i < costs.length; i++) {
            coins -= costs[i];
            if (0 <= coins) count++;
            else break;
        }
        return count;
    }

    public int maxIceCream01(int[] costs, int coins) {
        int[] freq = new int[100001];
        // 记录数组 costs 中的每个元素出现的次数
        for (int cost : costs) {
            freq[cost]++;
        }
        int count = 0;
        for (int i = 1; i <= 100000; i++) {
            //如果该下标不超过剩余的硬币数
            if (coins >= i) {
                // 则根据下标值和该下标处的元素值计算价格为该下标的雪糕的可以购买的最大数量
                int curCount = Math.min(freq[i], coins / i);
                count += curCount;
                // 将硬币数减去购买当前雪糕的花费
                coins -= i * curCount;
            } else {
                break;
            }
        }
        return count;
    }

    /**有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：

     每一轮中，你将会选出 任意 3 堆硬币（不一定连续）。
     Alice 将会取走硬币数量最多的那一堆。
     你将会取走硬币数量第二多的那一堆。
     Bob 将会取走最后一堆。
     重复这个过程，直到没有更多硬币。
     给你一个整数数组 piles ，其中 piles[i] 是第 i 堆中硬币的数目。

     返回你可以获得的最大硬币数目。**/
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int lengh = piles.length;
        int rounds = lengh / 3, coins = 0, index = lengh - 2;
        for (int i = 0; i < rounds; i++) {
            coins += piles[index];
            index -= 2;
        }
        return coins;
    }


    /**给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。

     商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。

     返回礼盒的 最大 甜蜜度。**/
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int left = 0, right = price[price.length - 1] - price[0];
        while (left < right){
            int mid = (left + right + 1) / 2;
            if (check(price,k,mid)) left = mid;
            else right = mid - 1;
        }
        return left;
    }


    private boolean check(int[] price, int k, int tastiness){
        int prev = Integer.MIN_VALUE / 2;
        int cnt = 0;
        for (int p : price) {
            if (p - prev >= tastiness){
                cnt++;
                prev = p;
            }
        }
        return cnt >= k;
    }

    private int[] price;
    public int maximumTastiness01(int[] price, int k) {
        this.price = price;
        Arrays.sort(price);
        int l = 0, r  = (price[price.length - 1] - price[0]) / (k - 1) + 1;
        while (l <= r){//找第一个小于k
            int mid = l+(r-l)/2;
            if (f(mid) >=k) l = mid+1;
            else r=mid-1;
        }
        return r;
    }
    //甜蜜度至少为t时，最多能选k种，随着t的增大，k减小
    private int f(int t){
        int res = 1,pre = price[0];
        for (int p: price){
            if (p >=pre+t) {res++;pre = p;}
        }
        return res;
    }



    /**给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。**/
    public int[] topKFrequent(int[] nums, int k) {

    }




    /**在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
     例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
     **/
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] ans = nums.clone();
        Arrays.sort(nums);
        for (int i = 0 ,j = n - 1; i != j ; i++,j--) {
            ans[i] = nums[j];
        }
    }


    /**给定正整数 n ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
     如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。**/
    public boolean reorderedPowerOf2(int n) {

    }


    /**输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。**/
    public String minNumber(int[] nums) {

    }

}
