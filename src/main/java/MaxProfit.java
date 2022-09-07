public class MaxProfit {

    public static int getProfit(int [] prices){
        int maxProfit=0;
        int minSoFar = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] < minSoFar){
                minSoFar = prices[i];
            }
            int localProfit = prices[i] - minSoFar;
            if(localProfit > maxProfit){
                maxProfit = localProfit;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        //int [] prices = {1, 4, 5, 2, 4};
        int [] prices = {5,4,1,2,4,2,8};
        System.out.println(getProfit(prices));
    }
}
