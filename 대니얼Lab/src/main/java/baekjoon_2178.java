import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class baekjoon_2178 {
    static int N, M;
    static int[][] graph;
    static int[][] visited;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        graph = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String lineInput = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = Character.getNumericValue(lineInput.charAt(j));
            }
        }
        bfs(0, 0);
    }

    private static void bfs(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX, 1}); // y, x, dist
        visited[startY][startX] = 1;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int y = arr[0];
            int x = arr[1];
            int dist = arr[2];

            if (y == N - 1 && x == M - 1) {
                System.out.println(dist);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (0 <= ny && ny < N && 0 <= nx && nx< M) {
                    if (graph[ny][nx] != 0 && visited[ny][nx] == 0) {
                        visited[ny][nx] = 1;
                        queue.add(new int[]{ny, nx, dist+1});
                    }
                }
            }
        }
    }
}
