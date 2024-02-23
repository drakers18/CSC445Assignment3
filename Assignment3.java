import java.util.Arrays;

public class Assignment3 {

    public static int coinChangeDP(int[] coinValues, int change) {
        int[] dp = new int[change + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= change; i++) {
            for (int coin : coinValues) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        if (dp[change] == Integer.MAX_VALUE) {
            return -1;
        } else {
            return dp[change];
        }
    }

    public static int coinChangeGreedy(int[] coinValues, int change) {
        Arrays.sort(coinValues);
        int numCoins = 0;
        int index = coinValues.length - 1;

        while (change > 0 && index >= 0) {
            if (coinValues[index] <= change) {
                int count = change / coinValues[index];
                numCoins += count;
                change -= count * coinValues[index];
            }
            index--;
        }

        return change == 0 ? numCoins : -1;
    }

    public static void main(String[] args) {
        int[] coins = {50, 25, 10, 5, 1};

        System.out.println("Change\tDP\tGreedy");
        for (int i = 1; i <= 55; i++) {
            System.out.print(i + "\t");
            System.out.print(coinChangeDP(coins, i) + "\t");
            System.out.println(coinChangeGreedy(coins, i));
        }
    }
}
