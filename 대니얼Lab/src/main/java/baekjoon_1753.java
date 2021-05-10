import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class baekjoon_1753 {

    static int V, E;
    static List<int[]>[] graph;
    static int startNode;
    static int[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        V = Integer.parseInt(inputs[0]);
        E = Integer.parseInt(inputs[1]);
        startNode = Integer.parseInt(br.readLine());
        distances = new int[V + 1];
        graph = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            inputs = br.readLine().split(" ");
            int u = Integer.parseInt(inputs[0]);
            int v = Integer.parseInt(inputs[1]);
            int w = Integer.parseInt(inputs[2]);
            graph[u].add(new int[]{v, w});
        }

        dijkstra(startNode);
        for (int i = 1; i < V + 1; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distances[i]);
            }
        }
    }

    private static void dijkstra(int startNode) {
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;
        //왼작마오
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] < o2[1] ? -1 : o1[1] == o2[1] ? 0 : 1;
            }
        });
        pq.add(new int[]{startNode, 0});

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int node = poll[0];
            int distance = poll[1];

            if (distance > distances[node]) {
                continue;
            }

            for (int[] nodeDistance : graph[node]) {
                int adjNode = nodeDistance[0];
                int adjDist = nodeDistance[1];
                if (distances[adjNode] > distance + adjDist) {
                    distances[adjNode] = distance + adjDist;
                    pq.add(new int[]{adjNode, distance + adjDist});
                }
            }
        }
    }
}
