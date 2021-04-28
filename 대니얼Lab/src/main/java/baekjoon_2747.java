import java.util.Scanner;

public class baekjoon_2747 {
    public static int[] dp = new int[50];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        System.out.println(solve(n));
    }

    private static int solve(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        if (dp[n - 1] == 0 && dp[n - 2] == 0) {
            dp[n-1] = solve(n-1);
            dp[n - 2] = solve(n - 2);
        } else if (dp[n - 2] == 0) {
            dp[n - 2] = solve(n - 2);
        } else if (dp[n - 1] == 0) {
            dp[n - 1] = solve(n - 1);
        }
        return dp[n - 1] + dp[n - 2];
    }
}
