import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class baekjoon_2644 {
    static int N, M;
    static int A, B;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        A = Integer.parseInt(inputs[0]);
        B = Integer.parseInt(inputs[1]);
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int x = Integer.parseInt(inputs[0]);
            int y = Integer.parseInt(inputs[1]);
            graph[x].add(y);
            graph[y].add(x);
        }

        dfs(A, 0);
        System.out.println("-1");

    }

    private static void dfs(int curPos, int count) {
        if (curPos == B) {
            System.out.println(count);
            System.exit(0);
        }
        for (int adjNode : graph[curPos]) {
            if (!visited[adjNode]) {
                visited[adjNode] = true;
                dfs(adjNode, count + 1);
            }
        }
    }
}

