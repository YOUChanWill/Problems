package DataStructures;

public class MaxProfit {

    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice){
                minprice = prices[i]; // 记录最低价格
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;  // 当前价格减去最低价格获取的利润最大
            }
        }
        return maxprofit;
    }
}
