import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class baekjoon_4991 {

    static int R, C;
    static char[][] graph;
    static List<int[]> dirtyPoses = new ArrayList<>();
    static int[] cleanerPos = new int[2];
    static int[] cleanerPosBackUp = new int[2];
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static boolean[] dfsVisited;
    static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        C = Integer.parseInt(inputs[0]);
        R = Integer.parseInt(inputs[1]);
        while (!(R == 0 && C == 0)) {
            graph = new char[R][C];
            for (int i = 0; i < R; i++) {
                String input = br.readLine();
                for (int j = 0; j < input.length(); j++) {
                    graph[i][j] = input.charAt(j);
                    if (graph[i][j] == '*') {
                        dirtyPoses.add(new int[]{i, j});
                    } else if (graph[i][j] == 'o') {
                        cleanerPos[0] = i;
                        cleanerPos[1] = j;
                    }
                }
            }
            cleanerPosBackUp = cleanerPos.clone();
            if (!testBfs(cleanerPos[0], cleanerPos[1])) { //만약 어짜피 다 못치우는 경우면 미리 가지치기
                System.out.println(-1);
                continue;
            }
            dfsVisited = new boolean[dirtyPoses.size()];
            backTracking(0, 0, dirtyPoses.size(), new ArrayList<>());
            if (minTime != Integer.MAX_VALUE) {
                System.out.println(minTime);
            } else {
                System.out.println(-1);
            }
            inputs = br.readLine().split(" ");
            minTime = Integer.MAX_VALUE;
            C = Integer.parseInt(inputs[0]);
            R = Integer.parseInt(inputs[1]);
            dirtyPoses = new ArrayList<>();
        }
    }

    private static boolean testBfs(int startY, int startX) {
        int curY = cleanerPos[0];
        int curX = cleanerPos[1];
        boolean[][] tesfBfsVisited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{curY, curX, 0});
        tesfBfsVisited[curY][curX] = true;

        while (!queue.isEmpty()) {
            int[] polls = queue.poll();
            int y = polls[0];
            int x = polls[1];
            int time = polls[2];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                    if (graph[ny][nx] != 'x' && !tesfBfsVisited[ny][nx]) {
                        tesfBfsVisited[ny][nx] = true;
                        queue.add(new int[]{ny, nx, time + 1});
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!tesfBfsVisited[i][j] && graph[i][j] != 'x') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void backTracking(int curPos, int curCount, int maxCount, ArrayList<Integer> path) {
        if (curCount == maxCount) {
            int time = 0;
            System.out.println("path = " + path);
            cleanerPos = cleanerPosBackUp.clone();
            for (int index : path) {
                int[] target = dirtyPoses.get(index);
                int bfsTime = bfs(target[0], target[1]);
                if (bfsTime == Integer.MAX_VALUE) {
                    return;
                }
                time += bfsTime;
            }
            minTime = Math.min(minTime, time);
            return;
        }

        for (int i = 0; i < dirtyPoses.size(); i++) {
            if (!dfsVisited[i]) {
                dfsVisited[i] = true;
                ArrayList<Integer> addPath = (ArrayList<Integer>) path.clone();
                addPath.add(i);
                backTracking(i+1, curCount+1, maxCount, addPath);
                dfsVisited[i] = false;
            }
        }
    }

    private static int bfs(int targetY, int targetX) {
        int curY = cleanerPos[0];
        int curX = cleanerPos[1];
        boolean[][] bfsVisited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{curY, curX, 0});
        bfsVisited[curY][curX] = true;

        while (!queue.isEmpty()) {
            int[] polls = queue.poll();
            int y = polls[0];
            int x = polls[1];
            int time = polls[2];
            if (y == targetY && x == targetX) {
                cleanerPos[0] = y;
                cleanerPos[1] = x;
                return time;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                    if (graph[ny][nx] != 'x' && !bfsVisited[ny][nx]) {
                        bfsVisited[ny][nx] = true;
                        queue.add(new int[]{ny, nx, time + 1});
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
