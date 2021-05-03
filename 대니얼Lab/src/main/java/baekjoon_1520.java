import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1520 {

    static int R, C;
    static int[][] graph, dp;
    static int[] dx = new int[]{-1, 0, 0, 1};
    static int[] dy = new int[]{0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);
        graph = new int[R][C];
        dp = new int[R][C];

        for (int i = 0; i < R; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                graph[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dp[i][j] = -1;
            }
        }
        //dp[i][j] = (i,j)에서 출발하여 (R-1, C-1)까지 가는 방법의 수
        System.out.println(dfs(0, 0));

    }

    private static int dfs(int y, int x) {
        if (y == R - 1 && x == C - 1) {
            return 1;
        }

        int ans = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                if (graph[ny][nx] < graph[y][x]) {
                    if (dp[ny][nx] >= 0) {
                        ans += dp[ny][nx];
                    } else {
                        ans += dfs(ny, nx);
                    }
                }
            }
        }
        dp[y][x] = ans;
        return ans;
    }
}
