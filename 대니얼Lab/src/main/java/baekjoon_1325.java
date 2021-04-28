import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class baekjoon_1325 {
    static int N, M;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        arr = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);

            graph.get(a).add(b);
        }

        for (int i = 0; i < N + 1; i++) {
            visited = new boolean[N+1];
            dfs(i);
        }
        int maxVal = Arrays.stream(arr).max().getAsInt();
        for (int i = 1; i < N + 1; i++) {
            if (arr[i] == maxVal) {
                bw.write(i + " ");
            }
        }
        bw.flush();
        bw.close();
    }

    private static void dfs(int curPos) {
        visited[curPos] = true;
        arr[curPos]++;
        for (int adjNode : graph.get(curPos)) {
            if (!visited[adjNode]) {
                dfs(adjNode);
            }
        }
    }
}

