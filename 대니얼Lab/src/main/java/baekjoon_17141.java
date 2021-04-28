import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class baekjoon_17141 {

    static int N, M;
    static int[][] graph;
    static List<int[]> virusPositions = new ArrayList<>();
    static boolean[] backTrackingVisited;
    static int ans = Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        graph = new int[N][N];

        int emptySpace = 0;
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(input[j]);
                if (graph[i][j] == 2) {
                    virusPositions.add(new int[]{i, j});
                }
            }
        }

        backTrackingVisited = new boolean[virusPositions.size()];

        for (int m = 0; m < virusPositions.size(); m++) {
            backTracking(m, 0, M, new ArrayList<>());
        }
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);
    }


    private static void backTracking(int curPos, int curCount, int maxCount, ArrayList<Integer> path) {
        if (curPos >= virusPositions.size() && curCount < maxCount) {
            return;
        }

        if (curCount == maxCount) {
            int time = simulate(path);
            ans = Math.min(ans, time);
            return;
        }

//        backTracking(curPos + 1, curCount + 1, maxCount, addPath);
//        backTracking(curPos + 1, curCount , maxCount, newPath);
        for (int m = curPos; m < virusPositions.size(); m++) {

            if (!backTrackingVisited[m]) {
                backTrackingVisited[m] = true;
                ArrayList<Integer> addPath = (ArrayList<Integer>) path.clone();
                addPath.add(curPos);
                backTracking(m+1, curCount+1, M, addPath);
                backTrackingVisited[m] = false;
            }
        }
    }

    private static int simulate(ArrayList<Integer> path) {
        int[][] bfsVisited = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bfsVisited[i][j] = -1;
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int virusIndex : path) {
            int y = virusPositions.get(virusIndex)[0];
            int x = virusPositions.get(virusIndex)[1];
            queue.add(new int[]{y, x, 0});
            bfsVisited[y][x] = 3; //바이러스는 3으로 표현하자
        }

        int maxTime = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int y = poll[0];
            int x = poll[1];
            int time = poll[2];
            maxTime = Math.max(time, maxTime);
            bfsVisited[y][x] = time;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (0 <= ny && ny < N && 0 <= nx && nx < N) {
                    if (graph[ny][nx] != 1 && bfsVisited[ny][nx] == -1) {
                        bfsVisited[ny][nx] = 3;
                        queue.add(new int[]{ny, nx, time+1});
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (bfsVisited[i][j] == -1 && (graph[i][j] == 0 || graph[i][j] == 2)) {
                    maxTime = Integer.MAX_VALUE;
                }
            }
        }
        return maxTime;
    }
}
