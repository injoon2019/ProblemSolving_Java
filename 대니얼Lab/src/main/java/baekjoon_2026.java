import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class baekjoon_2026 {
    static int K, N, F; // K: 소풍 보낼 명수, N: 1번부터 N번까지 학생 존재, F: 입력 줄 개수
    static boolean[] visited;
    static List<List<Integer>> graph = new ArrayList<>();;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] inputs = br.readLine().split(" ");
        K = Integer.parseInt(inputs[0]);
        N = Integer.parseInt(inputs[1]);
        F = Integer.parseInt(inputs[2]);
        visited = new boolean[N + 1];

        for (int n = 0; n < N + 1; n++) {
            graph.add(new ArrayList<>());
        }

        for (int f = 0; f < F; f++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int n = 0; n < N + 1; n++) {
            Collections.sort(graph.get(n));
        }

        solve();
    }

    private static void solve() throws IOException {

        if (graph.get(1).size() >= K-1) {
            visited[1] = true;
            ArrayList<Integer> tmpList = new ArrayList<>();
            tmpList.add(1);
            backTracking(1, 1, K, tmpList);
            visited[1] = false;
        }

        for (int i = 2; i <= N; i++) {
            if (graph.get(i).size() >= K-1) {
                visited[i] = true;
                ArrayList<Integer> tmpList2 = new ArrayList<>();
                tmpList2.add(i);
                backTracking(i, 1, K, tmpList2);
                visited[i] = false;
            }
        }
        System.out.println(-1);
    }

    private static void backTracking(int curPos, int curCount, int maxCount, ArrayList<Integer> path)
        throws IOException {
        if (curPos == N && curCount < maxCount) {
            return;
        }

        if (curCount == maxCount) {
            Collections.sort(path);
            for (int node : path) {
                System.out.println(node);
            }
            System.exit(0);
            return;
        }

        for (int adjNode : graph.get(curPos)) {
            if (!visited[adjNode]) {
                boolean allFriend = true;
                for (int past : path) {
                    if (!graph.get(past).contains(adjNode)) {
                        allFriend = false;
                    }
                }
                if (allFriend) {
                    visited[adjNode] = true;
                    ArrayList<Integer> tmpPath = (ArrayList<Integer>) path.clone();
                    tmpPath.add(adjNode);
                    backTracking(adjNode, curCount+1, maxCount, tmpPath);
                    visited[adjNode] = false;
                }
            }
        }
    }
}

