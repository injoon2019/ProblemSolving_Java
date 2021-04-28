import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class baekjoon_2293 {

    static int n, k;
    static int[] dp;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        k = Integer.parseInt(inputs[1]);

        dp = new int[k+1];
        coins = new int[n];

        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }

        //n가지 종류로 k원을 만드는 방법은
        // 1) n-1가지로 k 만드는 경우의 수
        // 2) n가지 동전으로 k - coins[n] 경우의 수
        dp[0] = 1; //0원을 만드는 방법은 아무것도 안내는 방법 1가지다
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j - coins[i] >= 0) {
                    dp[j] += dp[j - coins[i]];
                }
            }
        }
        System.out.println(dp[k]);

    }
}
