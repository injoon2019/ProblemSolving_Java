import java.util.Scanner;

public class baekjoon_1463 {

    static int[] dp = new int[1000001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        dp[1] = 0;
        System.out.println(solve(n));
    }

    private static int solve(int n) {
        if (n == 1) {
            return dp[1];
        }

        int ans = Integer.MAX_VALUE;
        if (n % 3 == 0) {
            if (dp[n/3] == 0){
                dp[n / 3] = solve(n / 3);
            }
            ans = Integer.min(ans, dp[n / 3] + 1);
        }
        if (n % 2 == 0) {
            if (dp[n/2] == 0){
                dp[n / 2] = solve(n / 2);
            }
            ans = Integer.min(ans, dp[n / 2] + 1);
        }
        if (n > 1) {
            if (dp[n - 1] == 0) {
                dp[n - 1] = solve(n - 1);
            }
            ans = Integer.min(ans, dp[n - 1] + 1);
        }
        dp[n] = ans;
        return ans;
    }
}
