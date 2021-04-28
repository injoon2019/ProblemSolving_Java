import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class baekjoon_2206 {

    static int R, C;
    static int[][] graph;
    static int[][][] visited;
    static int ans = Integer.MAX_VALUE;

    static int[] dx = new int[]{-1, 0, 0, 1};
    static int[] dy = new int[]{0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);
        graph = new int[R][C];
        visited = new int[R][C][2];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visited[i][j][0] = Integer.MAX_VALUE; // i, j에 벽을 부술 횟수를 0개 남기고 도착한 count
                visited[i][j][1] = Integer.MAX_VALUE; // i, j에 벽을 부술 횟수를 1개 남기고 도착한 count
            }
        }


        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                graph[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }

        bfs(0, 0);
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    private static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{y, x, 1, 1}); // y, x, count, 벽 부수기 가능 횟수
        visited[0][0][1] = 1;

        while(!queue.isEmpty()) {
            int[] elements = queue.poll();
            if (elements[0] == R - 1 && elements[1] == C - 1) {
                ans = Math.min(ans, elements[2]);
                return;
            }
            y = elements[0]; x = elements[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (0 <= ny && ny < R && 0 <= nx && nx < C) {
                    if (elements[3] == 1) { //깨부술 수 있을때
                        if (graph[ny][nx] == 0) { // 벽이 아닐 때
                            if (visited[ny][nx][1] > elements[2]+1) {
                                visited[ny][nx][1] = elements[2]+1;
                                queue.add(new int[]{ny, nx, elements[2]+1, elements[3]});
                            }
                        } else { //벽일 때
                            if (visited[ny][nx][0] > elements[2]+1) {
                                visited[ny][nx][0] = elements[2]+1;
                                queue.add(new int[]{ny, nx, elements[2]+1, elements[3]-1});
                            }
                        }
                    } else { //없을때
                        if (graph[ny][nx] == 0) { //벽이 아닐때만 생각하면된다.
                            if (visited[ny][nx][0] > elements[2]+1) {
                                visited[ny][nx][0] = elements[2]+1;
                                queue.add(new int[]{ny, nx, elements[2]+1, elements[3]});
                            }
                        }
                    }
                }
            }
        }
    }
}

