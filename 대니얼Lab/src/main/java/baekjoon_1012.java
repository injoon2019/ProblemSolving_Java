import java.io.BufferedReader;
import java.io.InputStreamReader;


public class baekjoon_1012 {
    static int C, R, K;
    static int[][] graph;
    static int[][] visited;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String[] inputs = br.readLine().split(" ");
            C = Integer.parseInt(inputs[0]);
            R = Integer.parseInt(inputs[1]);
            K = Integer.parseInt(inputs[2]);

            graph = new int[R][C];
            visited = new int[R][C];

            for (int k = 0; k < K; k++) {
                inputs = br.readLine().split(" ");
                int x = Integer.parseInt(inputs[0]);
                int y = Integer.parseInt(inputs[1]);
                graph[y][x] = 1;
            }

            int count = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (graph[i][j] == 1 && visited[i][j] == 0) {
                        count += 1;
                        dfs(i, j);
                    }
                }
            }
            System.out.println(count);
        }
    }

    private static void dfs(int y, int x) {
        visited[y][x] = 1;
        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];
            if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                if (graph[ny][nx] == 1 && visited[ny][nx] == 0) {
                    dfs(ny, nx);
                }
            }
        }
    }
}
