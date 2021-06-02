import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;


public class baekjoon_14716 {

    static int N, M;
    static int[][] graph, visited;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        graph = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < inputs.length; j++) {
                graph[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 1 && visited[i][j] == 0) {
                    count += 1;
                    dfs(i, j);
                }
            }
        }
        System.out.println(count);

    }

    private static void dfs(int y, int x) {
        visited[y][x] = 1;
        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (0 <= ny && ny < N && 0 <= nx && nx< M) {
                if (graph[ny][nx] == 1 && visited[ny][nx] == 0) {
                    dfs(ny, nx);
                }
            }
        }
    }
}

