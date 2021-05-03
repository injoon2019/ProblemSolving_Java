import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class baekjoon_2638 {

    static int N, M;
    static int[][] graph;
    static int[] dx = new int[]{-1, 0, 0, 1};
    static int[] dy = new int[]{0, -1, 1, 0};
    static boolean[][] visited;
    static int[][] outerAirGraph;
    static List<int[]> tmpAir = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        graph = new int[N][M];
        visited = new boolean[N][M];
        outerAirGraph = new int[N][M];

        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        int time = 0;
        while (stillCheese()) {
            meltSimulate();
            time += 1;
        }
        System.out.println(time);
    }

    private static void meltSimulate() {
        visited = new boolean[N][M];
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 0 && !visited[i][j] && isOuterAir(i, j)) {
                    outerAirBFS(i, j);
//                    paint_graph(outerAirGraph);
                    bfs(i, j);
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    private static void outerAirBFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] outerAirVisited = new boolean[N][M];
        queue.add(new int[]{i, j});
        outerAirVisited[i][j] = true;
        outerAirGraph[i][j] = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int y = poll[0];
            int x = poll[1];
            outerAirGraph[y][x] = 1;
            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (0 <= ny && ny < N && 0 <= nx && nx < M) {
                    if (!outerAirVisited[ny][nx] && graph[ny][nx] == 0) {
                        outerAirVisited[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }

    private static boolean isOuterAir(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] tmpVisited = new boolean[N][M];
        queue.add(new int[]{i, j});
        tmpVisited[i][j] = true;
        while (!queue.isEmpty()) {

            int[] poll = queue.poll();
            int y = poll[0];
            int x = poll[1];

            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (0 > ny || ny >= N || 0 > nx || nx >= M) {
                    return true;
                }
                if (!tmpVisited[ny][nx] && graph[ny][nx] == 0) {
                    tmpVisited[ny][nx] = true;
                    queue.add(new int[]{ny, nx});
                }
            }
        }
        return false;
    }

    private static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited = new boolean[N][M];
        outerAirGraph[i][j] = 1;
        visited[i][j] = true;

        while (!queue.isEmpty()) {

            int[] poll = queue.poll();
            int y = poll[0];
            int x = poll[1];

            for (int k = 0; k < 4; k++) {
                int ny = y + dy[k];
                int nx = x + dx[k];
                if (0 <= ny && ny < N && 0 <= nx && nx < M) {
                    if (!visited[ny][nx]) {
                        if (graph[ny][nx] == 0) { //공기
                            visited[ny][nx] = true;
                            queue.add(new int[]{ny, nx});
                        } else { //치즈면 녹일 수 있는 치즈인지 판단한다
                            if (meltable(ny, nx)) {
                                visited[ny][nx] = true;
                                tmpAir.add(new int[]{ny, nx});
                            } else {
                                visited[ny][nx] = true;
                            }
                        }
                    }
                }
            }
        }
        for (int[] airPos : tmpAir) {
            graph[airPos[0]][airPos[1]] = 0;
            outerAirGraph[airPos[0]][airPos[1]] = 1;
        }
//        paint_graph(graph);
    }

    private static boolean meltable(int y, int x) {
        int airFace = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (0 <= ny && ny < N && 0 <= nx && nx < M) {
                if (graph[ny][nx] == 0 && outerAirGraph[ny][nx] == 1) {
                    airFace += 1;
                }
            }
        }
        return airFace >= 2;
    }

    private static boolean stillCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void paint_graph(int[][] graph) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
