import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_2193 {

    static int N;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //dp[i][x] = i 자리의 이친수 중 x로 끝나는 이친수 개수
        dp = new long[90+1][2];
        dp[1][0] = 0; //
        dp[1][1] = 1; //1
        dp[2][0] = 1; // 10
        dp[2][1] = 0;
        dp[3][1] = 1; // 101
        dp[3][0] = 1; // 100

        for (int i = 4; i <= N; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i - 1][0];
        }

        System.out.println(dp[N][0] + dp[N][1]);
    }
}
