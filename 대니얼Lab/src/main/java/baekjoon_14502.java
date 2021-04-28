import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class baekjoon_14502 {
    static int C, R, K;
    static int[][] graph;
    static int[][] visited;
    static int[] combVisited;
    static int[][] copiedMap;
    static List<int[]> emptySpaces = new ArrayList<>();
    static int maxSafe = 0;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);
        graph = new int[R][C];

        for (int i = 0; i < R; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                graph[i][j] = Integer.parseInt(inputs[j]);
                if (graph[i][j] == 0) {
                    emptySpaces.add(new int[]{i, j});
                }
            }
        }
        combVisited = new int[emptySpaces.size()];
        combination(0, 0);
        System.out.println(maxSafe);
    }

    private static void combination(int curPos, int count) {
        if (curPos >= emptySpaces.size() && count < 3) {
            return;
        }

        if (count == 3) {
            copiedMap = new int[R][C];
            visited = new int[R][C];

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    copiedMap[i][j] = graph[i][j];
                }
            }

            for (int i = 0; i < combVisited.length; i++) { //벽을 세운다
                if (combVisited[i] == 1) {
                    int y = emptySpaces.get(i)[0];
                    int x = emptySpaces.get(i)[1];
                    copiedMap[y][x] = 1;
                }
            }

            for (int i = 0; i < R; i++) { //바이러스 퍼지는 시뮬레이션
                for (int j = 0; j < C; j++) {
                    if (visited[i][j] == 0 && copiedMap[i][j] == 2) {
                        bfs(i, j);
                    }
                }
            }
            int safeCount = 0;
            for (int i = 0; i < R; i++) { //안전한 지대 세기
                for (int j = 0; j < C; j++) {
                    if (copiedMap[i][j] == 0) {
                        safeCount += 1;
                    }
                }
            }
            maxSafe = Math.max(maxSafe, safeCount);
            return;
        }

        combVisited[curPos] = 1;
        combination(curPos+1, count+1);
        combVisited[curPos] = 0;
        combination(curPos+1, count);

    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});

        while (!queue.isEmpty()) {
            int[] yx = queue.poll();
            y = yx[0];
            x = yx[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                    if (visited[ny][nx] == 0 && (copiedMap[ny][nx] == 0
                        || copiedMap[ny][nx] == 2)) {
                        copiedMap[ny][nx] = 2;
                        visited[ny][nx] = 1;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
    }

}
