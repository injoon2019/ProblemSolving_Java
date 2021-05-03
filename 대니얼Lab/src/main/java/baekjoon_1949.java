import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class baekjoon_1949 {

    static int N;
    static List<Integer>[] graph;
    static int[] villages;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        villages = new int[N + 1];
        dp = new int[N + 1][2];

        String[] inputs = br.readLine().split(" ");

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < inputs.length; i++) {
            villages[i + 1] = Integer.parseInt(inputs[i]);
        }

        for (int i = 0; i < N - 1; i++) {
            inputs = br.readLine().split(" ");
            int A = Integer.parseInt(inputs[0]);
            int B = Integer.parseInt(inputs[1]);
            graph[A].add(B);
            graph[B].add(A);
        }

        System.out.println(Math.max(dfs(1, 1, -1), dfs(1, 0, -1)));
    }
    //특정노드가 켜져 있을때의 최대값을 구하고 싶다면, 그 자손들을 모두 루트노드로 보고 이 루트노드들이 꺼져있을때의 최대값을 구해 더해주면 된다.
    //특정 노드가 꺼져 있을때의 최대값을 구하고 싶다면, 그 자손들을 모두 루트노드로 보고, 각각의 루트노드가 켜져있을때와 꺼져있을때의 최대값중에 큰값을 더해주면 된다
    private static int dfs(int root, int onOff, int parent) {
        if (dp[root][onOff] != 0) {
            return dp[root][onOff];
        }

        int count = 0;
        if (onOff == 1) {
            count = villages[root];
        }

        for (int adjNode : graph[root]) {
            if (adjNode != parent) {
                if (onOff == 1) {
                    count += dfs(adjNode, 0, root);
                } else if (onOff == 0) {
                    count += Math.max(dfs(adjNode, 0, root), dfs(adjNode, 1, root));
                }
            }
        }
        dp[root][onOff] = count;
        return count;
    }
}
