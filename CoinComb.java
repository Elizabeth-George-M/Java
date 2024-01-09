import java.util.Scanner;
import java.util.concurrent.*;

public class CoinComb{
    public static int findTotalCoinCombinations(int[] coins, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;

        int half = coins.length / 2;
        
        // Create two threads for parallel computation
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < half; i++) {
                for (int j = coins[i]; j <= sum; j++) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = half; i < coins.length; i++) {
                for (int j = coins[i]; j <= sum; j++) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return dp[sum];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of coins: ");
        int n = scanner.nextInt();
        int[] coins = new int[n];
        System.out.println("Enter the values of the coins:");
        for (int i = 0; i < n; i++) {
            coins[i] = scanner.nextInt();
        }

        System.out.print("Enter the target sum: ");
        int sum = scanner.nextInt();

        int totalWays = findTotalCoinCombinations(coins, sum);
        System.out.println("Total ways to make the sum using different coin combinations: " + totalWays);

        scanner.close();
    }
}