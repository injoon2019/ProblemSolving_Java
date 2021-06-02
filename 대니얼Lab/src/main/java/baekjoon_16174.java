import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class baekjoon_16174 {

    static int N;
    static int[][] graph, visited;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(inputs[j]);
            }
        }
        System.out.println(bfs(0, 0));


    }

    private static String bfs(int i, int j) {
        visited[i][j] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int y = poll[0];
            int x = poll[1];
            if (graph[y][x] == -1) {
                return "HaruHaru";
            }

            for (int k = 0; k < 2; k++) {
                int ny = y + (dy[k] * graph[y][x]);
                int nx = x + (dx[k] * graph[y][x]);
                if (0 <= ny && ny < N && 0 <= nx && nx < N) {
                    if (visited[ny][nx] == 0) {
                        visited[ny][nx] = 1;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
        return "Hing";
    }
}

