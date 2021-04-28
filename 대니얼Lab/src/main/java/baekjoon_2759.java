import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_2759 {
    static int[] arr;
    static int[] dp; //dp[i]를 i번째를 밟았을때 i번째까지 최고 점수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n + 10];
        dp = new int[n + 10];

        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        dp[3] = Math.max(arr[1] + arr[3], arr[2] + arr[3]);
        System.out.println(solve(n));
    }

    private static int solve(int curPos) {
        if (dp[curPos] == 0) {
            dp[curPos] = Math.max(solve(curPos - 3) + arr[curPos-1], solve(curPos - 2)) + arr[curPos];
        }
        return dp[curPos];
    }
}
