import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class baekjoon_12978 {

    static int n;
    static List<Integer>[] graph;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        dp = new int[n + 1][2];

        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            String[] inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        System.out.println(Math.min(dfs(1, 0, -1), dfs(1, 1, -1)));


    }

    private static int dfs(int root, int turnOn, int prevNode) {
        if (dp[root][turnOn] != Integer.MAX_VALUE) {
            return dp[root][turnOn];
        }
        int count = 0;
        if (turnOn == 1) { //자기 자신 포함하면 한개 추가
            count += 1;
        }

        for (int childNode : graph[root]) {
            if (childNode != prevNode) {
                if (turnOn == 1) { //자기 자신이 켜져 있다면
                    count += Math.min(dfs(childNode, 1, root), dfs(childNode, 0, root));
                } else {
                    count += dfs(childNode, 1, root);
                }
            }
        }
        dp[root][turnOn] = count;
        return count;
    }
}
