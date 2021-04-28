import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_1149 {

    static int[][] dp;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][3];
        dp = new int[n][3];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            String[] rgb = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(rgb[0]);
            arr[i][1] = Integer.parseInt(rgb[1]);
            arr[i][2] = Integer.parseInt(rgb[2]);
        }

        for (int j = 0; j < 3; j++) {
            dp[0][j] = arr[0][j];
        }

        for (int j = 0; j < 3; j++) {
            solve(n - 1, j);
        }

        System.out.println(Arrays.stream(dp[n-1]).min().getAsInt());
    }

    private static int solve(int i, int j) {
        if (dp[i][j] == Integer.MAX_VALUE) {
            for (int k = 0; k < 3; k++) {
                if (k == j) {
                    continue;
                }
                dp[i][j] = Math.min(dp[i][j], solve(i - 1, k) + arr[i][j]);
            }
        }
        return dp[i][j];
    }
}
