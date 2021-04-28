import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_2156 {

    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n + 10];
        dp = new int[n + 10]; //dp[i] = i번째까지 최대값

        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        dp[3] = Math.max(Math.max(arr[1], arr[2]) + arr[3], dp[2]);

        System.out.println(solve(n));

    }

    private static int solve(int i) {
        if (i == 1 || i == 2 || i == 3) {
            return dp[i];
        }
        if (dp[i] == 0) {
            dp[i] = Math.max(Math.max(solve(i-3) + arr[i-1] + arr[i], solve(i-2) + arr[i]), solve(i-1));
        }
        return dp[i];
    }
}
