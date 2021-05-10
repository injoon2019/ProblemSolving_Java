import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class baekjoon_14938 {

    static int n, m, r;
    static List<int[]>[] graph;
    static int[] items;
    static int max_item = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]); //지역의 개수
        m = Integer.parseInt(inputs[1]); //수색 범위
        r = Integer.parseInt(inputs[2]); //길의 개수

        graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        items = new int[n+1];

        inputs = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            items[i+1] = Integer.parseInt(inputs[i]);
        }

        for (int i = 0; i < r; i++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            int c = Integer.parseInt(inputs[2]);
            graph[a].add(new int[]{c, b});
            graph[b].add(new int[]{c, a});
        }
        for (int i = 1; i < n + 1; i++) {
            dijkstra(i);
        }
        System.out.println(max_item);
    }

    private static void dijkstra(int startNode) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] < o2[0] ? -1 : o1[0] == o2[0] ? 0 : 1;
            }
        });
        pq.add(new int[]{0, startNode});

        while (!pq.isEmpty()) {
            int[] polls = pq.poll();
            int cost = polls[0];
            int node = polls[1];

            for (int[] adjNodeCost : graph[node]) {
                int adjCost = adjNodeCost[0];
                int adjNode = adjNodeCost[1];

                if (adjCost + cost < distances[adjNode]) {
                    distances[adjNode] = adjCost + cost;
                    pq.add(new int[]{cost + adjCost, adjNode});
                }
            }
        }

        int item_count = 0;
        for (int i = 1; i < n + 1; i++) {
            if (distances[i] <= m) {
                item_count += items[i];
            }
        }
//        System.out.println("startNode: " + startNode);
//        for (int i = 1; i < n + 1; i++) {
//            System.out.print(distances[i] + " ");
//        }
//        System.out.println();
//        System.out.println(item_count);
        max_item = Math.max(item_count, max_item);

    }
}

