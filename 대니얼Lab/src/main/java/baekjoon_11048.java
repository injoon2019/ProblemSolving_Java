import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_11048 {

    static int N, M;
    static int[][] graph, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        graph = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i - 1 >= 0 && j - 1 >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + graph[i][j], dp[i][j]);
                }

                if (i - 1 >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j] + graph[i][j], dp[i][j]);
                }
                if (j - 1 >= 0) {
                    dp[i][j] = Math.max(dp[i][j-1] + graph[i][j], dp[i][j]);
                }
                dp[i][j] = Math.max(dp[i][j], graph[i][j]);
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}
