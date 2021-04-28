import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class baekjoon_2606 {

    static int N;
    static int M;
    static boolean[] visited;
    static List<Integer>[] graph;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            String[] inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1);
        System.out.println(ans-1);
    }

    private static void dfs(int curPos) {
        visited[curPos] = true;
        ans += 1;
        for (int i = 0; i < graph[curPos].size(); i++) {
            if (!visited[graph[curPos].get(i)]) {
                dfs(graph[curPos].get(i));
            }
        }
    }

}
