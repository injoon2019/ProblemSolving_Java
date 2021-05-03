import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class baekjoon_15681 {

    static int N, R, Q;
    static List<Integer>[] graph;
    static int[] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        R = Integer.parseInt(inputs[1]);
        Q = Integer.parseInt(inputs[2]);

        graph = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        dp = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            inputs = br.readLine().split(" ");
            int A = Integer.parseInt(inputs[0]);
            int B = Integer.parseInt(inputs[1]);
            graph[A].add(B);
            graph[B].add(A);
        }

        dfs(R); //루트
        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            System.out.println(dp[q]);
        }


    }
    //dp[root]는 root를 포함한 서브트리에 속한 정점의 개수
    private static int dfs(int root) {
        if (dp[root] != 0) {
            return dp[root];
        }
        visited[root] = true;

        int count = 1; //root 본인 한 칸

        for (int adj : graph[root]) {
            if (!visited[adj]) {
                count += dfs(adj);
            }
        }
        dp[root] = count;
        return count;
    }
}
