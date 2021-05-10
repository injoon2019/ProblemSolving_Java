import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class baekjoon_15681_2 {

    static int N, R, Q;
    static List<Integer>[] graph;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]); //트리 정점의 수
        R = Integer.parseInt(inputs[1]); //루트 번호
        Q = Integer.parseInt(inputs[2]); // 쿼리 수

        graph = new ArrayList[N + 1];
        dp = new int[N + 1]; //dp[i]는 i 루트 아래 서브 쿼리의 개수
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(R, -1);

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            System.out.println(dp[query]);
        }

    }

    private static int dfs(int root, int prevNode) {
        if (dp[root] != 0) {
            return dp[root];
        }
        int count = 1; //자기 자신 포함이니 무조건 1개

        for (int childNode : graph[root]) {
            if (childNode != prevNode) {
                count += dfs(childNode, root);
            }
        }
        dp[root] = count;
        return count;
    }

}
