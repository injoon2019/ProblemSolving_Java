import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_12865 {

    static int N, K;
    static int[] weights;
    static int[] values;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        K = Integer.parseInt(inputs[1]);

        //dp[i][j]는 i번째 물건을 고려했을 때, 무게 K에서 최대 행복
        // dp[i][j] = Max {dp[i-1][j] , dp[i][j-weights[i]] + values[i]}
        // i번쨰 물건을 안쓰고 그전까지 같은 무게에서 최대 행복 또는
        // i번쨰 물건을 쓰는대신 i번쨰 물건의 무게만큼 줄어든 것의 최대 행복
        dp = new int[N][K + 1];
        weights = new int[N];
        values = new int[N];
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            weights[i] = Integer.parseInt(inputs[0]);
            values[i] = Integer.parseInt(inputs[1]);
        }

        for (int i = 0; i < N; i++) {
            for (int j = K; j >= 0 ; j--) {
                if (i - 1 >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                if (j - weights[i] >= 0) {
                    if (i - 1 >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j - weights[i]] + values[i]);
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - weights[i]] + values[i]);
                }
            }
        }

        System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());

    }
}
