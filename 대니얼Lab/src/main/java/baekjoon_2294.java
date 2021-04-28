import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_2294 {

    static int n, k;
    static int[] dp;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        k = Integer.parseInt(inputs[1]);

        dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        coins = new int[n];

        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            coins[i] = coin;
        }

        //n가지 종류로 최소의 개수로 k원을 만드는 방법은
        // Min {k - coins[0] 만드는데 드는 개수 + 1, k - coins[1] 만드는데 드는 개수 + 1 ... }
        dp[0] = 0; //0원을 만드는데 드는 최소 동전 개수는 0개이다
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j - coins[i] >= 0 && dp[j-coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
                }
            }
        }
        if (dp[k] != Integer.MAX_VALUE) {
            System.out.println(dp[k]);
        } else {
            System.out.println(-1);
        }
    }
}
