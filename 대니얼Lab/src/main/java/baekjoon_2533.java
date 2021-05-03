import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class baekjoon_2533 {

    static int N;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp; //노드가 얼리어답터 일때 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            dp[i][1] = 1; //자신이 얼리어답터면 1개는 무조건 얼리어답터다
        }
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N-1; i++) {
            String[] inputs = br.readLine().split(" ");
            int A = Integer.parseInt(inputs[0]);
            int B = Integer.parseInt(inputs[1]);
            graph[A].add(B);
            graph[B].add(A);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int curNode) {
        visited[curNode] = true;
        for (int child : graph[curNode]) {
            if (!visited[child]) {
                dfs(child);
                
                //현재 노드가 얼리어답터가 아니면 자식은 무조건 얼리어답터
                dp[curNode][0] += dp[child][1];
                //현재 노드가 얼리어답터면 자식 노드는 둘 중 최소
                dp[curNode][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}
