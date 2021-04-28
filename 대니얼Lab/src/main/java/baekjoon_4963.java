import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class baekjoon_4963 {
    static int C, R;
    static int[][] graph;
    static int[][] visited;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rowCol = br.readLine().split(" ");
        C = Integer.parseInt(rowCol[0]);
        R = Integer.parseInt(rowCol[1]);

        while (!(C == 0 && R == 0)) {
            graph = new int[R][C];
            visited = new int[R][C];

            for (int i = 0; i < R; i++) {
                String[] inputs = br.readLine().split(" ");
                for (int j = 0; j < C; j++) {
                    graph[i][j] = Integer.parseInt(inputs[j]);
                }
            }
            int count = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (graph[i][j] == 1 && visited[i][j] == 0){
                        visited[i][j] = 1;
                        count += 1;
                        dfs(i, j);
                    }
                }
            }
            System.out.println(count);

            rowCol = br.readLine().split(" ");
            C = Integer.parseInt(rowCol[0]);
            R = Integer.parseInt(rowCol[1]);
        }
    }

    private static void dfs(int y, int x) {
        visited[y][x] = 1;
        for (int k = 0; k < 8; k++) {
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
