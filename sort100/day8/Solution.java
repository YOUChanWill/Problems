package day8;

import java.util.*;

public class Solution {

    /**矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。
     * 例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。

     给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。**/
    public int[][] diagonalSort(int[][] mat) {
        // 行数
        int m = mat.length;
        // 列数
        int n = mat[0].length;
        // 主对角线的条数
        int dLen = m + n - 1;

        // 每一条对角线都创建一个动态数组
        ArrayList<Integer>[] diagonal = new ArrayList[dLen];
        for (int i = 0; i < dLen; i++) {
            diagonal[i] = new ArrayList<>(m);
        }

        // 遍历原始矩阵，把原始矩阵中的元素放进对应的动态数组中
        // 主对角线上元素的特点是：纵坐标 - 横坐标 = 定值
        // 加上偏移 m - 1 是为了能够放进数组中
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diagonal[j - i + (m - 1)].add(mat[i][j]);
            }
        }

        // 对每一个对角线上的动态数组分别进行升序排序
        for (int i = 0; i < dLen; i++) {
            Collections.sort(diagonal[i]);
        }

        int[][] res = new int[m][n];

        // 对角线数组上还未取出的元素的下标，初始化的时候均为 0
        int[] next = new int[dLen];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 对角线的坐标
                int index = j - i + (m - 1);
                // 记录结果
                res[i][j] = diagonal[index].get(next[index]);
                // 维护 next 数组的值
                next[index]++;
            }
        }
        return res;
    }

    public int[][] diagonalSort01(int[][] matrix) {

        int numRows = matrix.length;
        int numCols = matrix[0].length;
        int[][] sortedMatrix = new int[numRows][numCols];
        int[] diagonal = new int[Math.max(numRows, numCols)];
        for(int i = 1 - numCols; i < numRows; i++) {
            int startRow;
            int startCol;
            if(i < 0) {
                startRow = 0;
                startCol = -i;
            } else {
                startRow = i;
                startCol = 0;
            }
            int len = 0;
            for(int row = startRow, col = startCol; row < numRows && col < numCols; row++, col++) {
                diagonal[len++] = matrix[row][col];
            }
            Arrays.sort(diagonal, 0, len);
            for(int j = 0, row = startRow, col = startCol; j < len; j++, row++, col++) {
                sortedMatrix[row][col] = diagonal[j];
            }
        }

        return sortedMatrix;
    }


    public int[][] diagonalSort02(int[][] mat) {
        int[][] res = new int[mat.length][mat[0].length];
        if (mat.length < 1 || mat[0].length < 1) {
            return res;
        }

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();

        for (int i = 0; i < mat.length; i++ ) {
            for (int j = 0; j < mat[0].length; j++) {
                map.putIfAbsent(i-j, new PriorityQueue<>());
                map.get(i-j).add(mat[i][j]);
            }
        }

        for (int i = 0; i < mat.length; i++ ) {
            for (int j = 0; j < mat[0].length; j++) {
                res[i][j] = map.get(i-j).poll();
            }
        }

        return res;
    }


    /**Alice 管理着一家公司，并租用大楼的部分楼层作为办公空间。Alice 决定将一些楼层作为 特殊楼层 ，仅用于放松。

     给你两个整数 bottom 和 top ，表示 Alice 租用了从 bottom 到 top（含 bottom 和 top 在内）的所有楼层。
     另给你一个整数数组 special ，其中 special[i] 表示  Alice 指定用于放松的特殊楼层。

     返回不含特殊楼层的 最大 连续楼层数。**/
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = 0, n = special.length;
        for (int i = 1; i < special.length; i++) {
            max = Math.max(special[i] - special[i - 1] - 1,max);
        }
        return Math.max(max,Math.max(top - special[special.length - 1], special[0] - bottom));
    }

    public int maxConsecutive01(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = special[0] - bottom;
        for (int i = 1; i < special.length; i++) {
            int floor = special[i] - special[i - 1] - 1;
            if (floor > max) {
                max = floor;
            }
        }
        int last = special[special.length - 1];
        if (top - last > max) {
            max = top - last;
        }
        return max;
    }


    /**给你一个包含若干 互不相同 整数的数组 nums ，你需要执行以下操作 直到数组为空 ：

     如果数组中第一个元素是当前数组中的 最小值 ，则删除它。
     否则，将第一个元素移动到数组的 末尾 。
     请你返回需要多少个操作使 nums 为空。**/
    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length;
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; ++i)
            id[i] = i;
        Arrays.sort(id, (i, j) -> nums[i] - nums[j]);

        long ans = n; // 先把 n 计入答案
        for (int k = 1; k < n; ++k)
            if (id[k] < id[k - 1]) // 必须多走一整圈
                ans += n - k; // 减去前面删除的元素个数
        return ans;
    }

    public long countOperationsToEmptyArray01(int[] nums) {
        Map<Integer, Integer> pos = new HashMap<>();
        long n = nums.length, res = n;
        for (int i = 0; i < n; ++i)
            pos.put(nums[i], i);
        Arrays.sort(nums);
        for (int i = 1; i < n; ++i)
            if (pos.get(nums[i]) < pos.get(nums[i - 1]))
                res += n - i;
        return res;
    }

    /**给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。**/
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        if (n > 1440) return 0;
        // 当天最多只有 60∗24=144060 * 24 = 144060∗24=1440 个不同的时间点（跨天的话则是双倍）
        int[] cnt = new int[1440 * 2 + 10];
        for (String s: timePoints ) {
            String[] ss = s.split(":");
            int h = Integer.parseInt(ss[0]),m = Integer.parseInt(ss[1]);
            cnt[60 * h + m]++;
            cnt[60 * h + m + 1440]++;
        }
        // 找到间隔最小两个时间点
        int ans = 1440, last = -1;
        for (int i = 0; i <= 1440 * 2 && ans != 0; i++) {
            if (cnt[i] == 0) continue;
            if (cnt[i] > 1) ans = 0;
            else if (last != -1) ans = Math.min(ans,i - last);
            last = i;
        }
        return ans;
    }

    public int findMinDifference01(List<String> timePoints) {
        if(timePoints.size()>1440) return 0;

        boolean[] times=new boolean[1440];
        for(String time:timePoints){
            String[] s1=time.split(":");
            int i=Integer.parseInt(s1[0])*60+Integer.parseInt(s1[1]);
            if(times[i]){
                return 0;
            }
            times[i]=true;
        }

        int minDiff=times.length-1;
        int first=Integer.MAX_VALUE;
        int last=Integer.MIN_VALUE;
        int prev=-1;
        for(int i=0;i<times.length;i++){
            if(times[i]){
                if(prev!=-1){
                    minDiff=Math.min(minDiff,i-prev);
                }
                prev=i;
                first=Math.min(i,first);
                last=Math.max(i,last);
            }
        }
        minDiff=Math.min(minDiff,first+1440-last);
        return minDiff;
    }

    /**给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。请你选出两个下标 i 和 j（i != j），且 nums[i] 的数位和 与  nums[j] 的数位和相等。

     请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值 。**/
    public int maximumSum(int[] nums) {
         /*
        预处理位数和+HashMap统计+贪心
        用长度为2的数组记录两个最大值
         */
        int n = nums.length;
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i], sum = 0;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            // arr[0]最大值，arr[1]次大值
            int[] arr = map.getOrDefault(sum, new int[2]);
            if (nums[i] >= arr[0]) {
                arr[1] = arr[0];
                arr[0] = nums[i];
            } else if (nums[i] > arr[1]) {
                arr[1] = nums[i];
            }
            map.put(sum, arr);
        }
        int res = -1;
        for (int key : map.keySet()) {
            int[] arr = map.get(key);
            // 跳过数位和只有一个的情况
            if (arr[1] == 0) continue;
            res = Math.max(res, arr[0] + arr[1]);
        }
        return res;
    }
    public int maximumSum01(int[] nums) {
        int[] num1 = new int[82];
        int[] num2 = new int[82];
        for(int num : nums){
            int s = 0;
            int x = num;
            while(x > 0){
                s += x %10;
                x /= 10;
            }
            if(num >= num1[s]){
                num2[s] = num1[s];
                num1[s] = num;
            }else if(num > num2[s]){
                num2[s] = num;
            }
        }
        int result = -1;
        for(int i = 0; i < num1.length ; i++){
            if(num2[i] != 0){
                result = Math.max(result,(num1[i]+num2[i]));
            }
        }
        return result;
    }



    /**有 n 名工人。 给定两个数组 quality 和 wage ，其中，quality[i] 表示第 i 名工人的工作质量，其最低期望工资为 wage[i] 。

     现在我们想雇佣 k 名工人组成一个工资组。在雇佣 一组 k 名工人时，我们必须按照下述规则向他们支付工资：

     对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
     工资组中的每名工人至少应当得到他们的最低期望工资。
     给定整数 k ，返回 组成满足上述条件的付费群体所需的最小金额 。在实际答案的 10-5 以内的答案将被接受。。**/
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] indexArr = new Integer[n];
        for(int i=0; i<n; i++){
            indexArr[i] = i;
        }
        //定义 工资比质量，一份质量给一份工资，故同事都被拔高到最高待遇的同事同等待遇。排序下标顺序由低至高，先找每份质量工质要求最低的员工。
        Arrays.sort(indexArr, (a, b) -> {
            return quality[b]*wage[a] - quality[a]*wage[b];
        });

        double totalQuality = 0;
        //由于我们按质量总和给钱，故维护pq降序，大的在前，大堆顶，即使淘汰高质量员工
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        //先塞进k-1个低要求的员工。
        for(int i=0; i<k-1; i++){
            int idx = indexArr[i];
            totalQuality += quality[idx];
            pq.offer(quality[idx]);
        }
        //再来找每一个要求最高的宝藏员工，由于拿到比例后，我们按质量给钱,需要及时踢出高质量员工降低成本
        double ans = Double.MAX_VALUE;
        for(int i=k-1; i<n; i++){
            //高质量员工下标
            int idx = indexArr[i];
            //新高质量员工带来的工资质量比例
            double bili = (double)wage[idx]/quality[idx];
            //招进来当前的高质量员工，此时组内正好K个
            totalQuality += quality[idx];
            pq.offer(quality[idx]);
            //即时更新开销，找最小
            double nowCost = bili*totalQuality;
            ans = Math.min(ans, nowCost);

            //踢出高质量员工降低成本，为下一次招新做准备
            totalQuality -= pq.poll();
        }
        return ans;
    }

    public static class Employee {
        double rubbish;
        int quality;

        public Employee(int wage, int quality) {
            this.rubbish = (double) wage / (double) quality;
            this.quality = quality;

        }
    }


    public double mincostToHireWorkers01(int[] quality, int[] wage, int k) {
        //1.获得总工人数
        int num = quality.length;
        //2.创建工人对象数组
        Employee[] employees = new Employee[num];
        for (int i = 0; i < num; i++) {
            employees[i] = new Employee(wage[i], quality[i]);
        }
        //3.按照垃圾比，从小到大排序
        Arrays.sort(employees, (a, b) -> a.rubbish <= b.rubbish ? -1 : 1);
        //4.创建大根堆（作为门槛）
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);

        double ans = Double.MAX_VALUE;
        for (int i = 0,sum = 0; i < num; i++) {
            int curQuality = employees[i].quality;
            //5.如果堆没有满
            if (heap.size() < k) {
                sum += curQuality;
                heap.add(curQuality);
                if (heap.size() == k) {
                    ans = Math.min(ans, sum * employees[i].rubbish);
                }
            } else {//堆满了
                if (heap.peek() > curQuality) {
                    sum += curQuality - heap.poll();
                    heap.add(curQuality);
                    ans = Math.min(ans, sum * employees[i].rubbish);
                }
            }
        }
        return ans;
    }

    public double mincostToHireWorkers02(int[] quality, int[] wage, int k) {
        int len = wage.length;
        Integer[] indexs = new Integer[len];
        for (int i = 0; i < len; i ++){
            indexs[i] = i;
        }
        Arrays.sort(indexs,(a, b) -> wage[a] * quality[b] - wage[b] * quality[a]);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        double ans = 1e9;
        double totalQ = 0.0;
        for (int i = 0; i < k-1; i ++){
            totalQ += quality[indexs[i]];
            pq.offer(quality[indexs[i]]);
        }
        for (int i = k -1; i < len; i ++){
            int index = indexs[i];
            totalQ += quality[index];
            pq.offer(quality[index]);
            double data = ((double)wage[index])/quality[index] * totalQ;
            ans = Math.min(data, ans);
            totalQ -= pq.poll();
        }
        return ans;
    }

    /**堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高 hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。

     输入使用数组[wi, di, hi]表示每个箱子。**/
    public int pileBox(int[][] box) {
        Arrays.sort(box,(a,b)->a[0]-b[0]);
        int[] dp = new int[box.length];
        int res = 0;
        for (int i = 0; i < box.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2])
                    dp[i] = Math.max(dp[i],dp[j]);
            }
            dp[i] += box[i][2];
            res = Math.max(dp[i],res);
        }
        return res;
    }

    public int pileBox01(int[][] box) {
        int len = box.length;
        Arrays.sort(box,(a,b) -> {
            if(a[0] != b[0]){
                return a[0] - b[0];
            }
            if(a[1] != b[1]){
                return b[1] - a[1];
            }
            if(a[2] != b[2]){
                return b[2] - a[2];
            }
            return 0;
        });

        int[] dp = new int[box.length];
        dp[0] = box[0][2];
        int res = dp[0];
        // int max = 0;
        for (int i= 1;i<box.length;i++){
            dp[i] = box[i][2];
            int depth = box[i][1];
            int height = box[i][2];
            for (int j= 0;j<i;j++){
                if (box[j][1] < depth && box[j][2] < height){
                    dp[i] = Math.max(dp[j] + height,dp[i]);
                }
            }
            res = Math.max(dp[i],res);
        }
        return res;
    }




}
