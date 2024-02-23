/*
 * CSC 445 Assignment 3
 * Drew Rakers
 * Resources Used: OpenAI
 */

import java.util.Arrays;

public class Assignment3 {

    //Dynamic Algorithm
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

    //Greedy Algorithm
    public static int coinChangeGreedy(int[] coinValues, int change) {
        Arrays.sort(coinValues);
        int numCoins = 0;

        for (int i = coinValues.length - 1; i >= 0; i--) {
            while (change >= coinValues[i]) {
                change -= coinValues[i];
                numCoins++;
            }
        }

        return change == 0 ? numCoins : -1;
    }

    //Main
    public static void main(String[] args) {
        int[] coins = {1, 3, 4};

        System.out.println("Change\tDP\tGreedy");
        for (int i = 1; i <= 55; i++) {
            System.out.print(i + "\t");
            System.out.print(coinChangeDP(coins, i) + "\t");
            System.out.println(coinChangeGreedy(coins, i));
        }
    }
}
