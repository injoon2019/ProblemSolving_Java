import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class baekjoon_16562 {

    static int n, m, k;
    static int[] costs;

    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]); //학생 수
        m = Integer.parseInt(inputs[1]); //친구 관계 수수
        k = Integer.parseInt(inputs[2]); //가지고 있는 돈
        costs = new int[n + 1];
        parents = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            parents[i] = i;
        }
        inputs = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            costs[i + 1] = Integer.parseInt(inputs[i]);
        }

        for (int i = 0; i < m; i++) {
            inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            union(a, b);
        }

        HashMap<Integer, Integer> groupCost = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            if (!groupCost.containsKey(parents[i])) {
                groupCost.put(parents[i], costs[i]);
            } else {
                if (groupCost.get(parents[i]) > costs[i]) { //기존보다 싼 친구라면 갱신
                    groupCost.put(parents[i], costs[i]);
                }
            }
        }

        int total_money = 0;
        for (int money : groupCost.values()) {
            total_money += money;
        }

        if (total_money <= k) {
            System.out.println(total_money);
        } else {
            System.out.println("Oh no");
        }

    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }

        if (a < b) {
            for (int i = 1; i <= n; i++) {
                if (i != b && parents[i] == b) {
                    parents[i] = a;
                }
            }
            parents[b] = a; //궁긍증: 만약 그렇다면 b의 부하들은 여전히 b를 대장이라고 생각하지 않나?
        } else {
            for (int i = 1; i <= n; i++) {
                if (i != a && parents[i] == a) {
                    parents[i] = b;
                }
            }
            parents[a] = b;
        }
    }

    private static int find(int a) {
        if (a == parents[a]) {
            return a;
        }
        parents[a] = find(parents[a]);
        return parents[a];
    }

}

