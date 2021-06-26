import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class baekjoon_15806 {

    static int N, M, K, t;
    static ArrayList<int[]> moldPositions = new ArrayList<>();
    static ArrayList<int[]> inspectPositions = new ArrayList<>();
    static int[][] graph;
    static int[] dy = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dx = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]); // 방의 크기
        M = Integer.parseInt(inputs[1]); // 곰팡이의 개수
        K = Integer.parseInt(inputs[2]); // 청소 검사 시스템이 검사하는 방바닥 좌표 개수
        t = Integer.parseInt(inputs[3]); // 남은 일수
        graph = new int[N][N];

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            moldPositions.add(new int[]{Integer.parseInt(inputs[0]) - 1, Integer.parseInt(inputs[1]) - 1});
            graph[Integer.parseInt(inputs[0]) - 1][Integer.parseInt(inputs[1]) - 1] = 1;
        }

        for (int i = 0; i < K; i++) {
            inputs = br.readLine().split(" ");
            inspectPositions.add(new int[]{Integer.parseInt(inputs[0]) - 1, Integer.parseInt(inputs[1]) - 1});
            graph[Integer.parseInt(inputs[0]) - 1][Integer.parseInt(inputs[1]) - 1] = 3;
        }

//        printGraph(graph);
        System.out.println();
        simulateMold();
        System.out.println(inspectRoom());
//        printGraph(graph);

    }

    private static void printGraph(int[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static String inspectRoom() {
        for (int[] inspectPosition : inspectPositions) {
            if (graph[inspectPosition[0]][inspectPosition[1]] == 1) {
                return "YES";
            }
        }
        return "NO";
    }

    private static void simulateMold() {
        Queue<int[]> queue = new LinkedList<>();
        for (int[] position : moldPositions) {
            queue.add(new int[]{0, position[0], position[1]});
        }

        for (int i = 1; i < t + 1; i++) {
            ArrayList<int[]> tmpList = new ArrayList<>();
            while (!queue.isEmpty() && queue.peek()[0] == i - 1) {
                tmpList.add(queue.poll());
            }

            for (int[] poll : tmpList) {
                int y = poll[1];
                int x = poll[2];
                graph[y][x] = 0;
            }

            for (int[] poll : tmpList) {
                int day = poll[0];
                int y = poll[1];
                int x = poll[2];
                for (int k = 0; k < 8; k++) {
                    int ny = y + dy[k];
                    int nx = x + dx[k];
                    if (0 <= ny && ny < N && 0 <= nx && nx < N) {
                        graph[ny][nx] = 1;
                        queue.add(new int[]{i, ny, nx});
                    }
                }
            }
        }
    }

}
