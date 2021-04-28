import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class baekjoon_11724 {

    static int N, M;
    static boolean[] visited;
    static List<Integer>[] graph; //리스트들을 담는 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        graph = new ArrayList[N+1];
        visited = new boolean[N + 1];

        for (int i = 0; i < graph.length; i++) { //배열 초기화 안해주면 에러 난다
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            graph[a].add(b);
            graph[b].add(a);
        }

        int ans = 0;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                ans += 1;
                dfs(i);
            }
        }
        System.out.println(ans);
    }

    private static void dfs(int start) {
        visited[start] = true;
        for (int i = 0; i < graph[start].size(); i++) {
            if (!visited[graph[start].get(i)]) {
                dfs(graph[start].get(i));
            }
        }
    }
}
