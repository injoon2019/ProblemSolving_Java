import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class baekjoon_1717 {

    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);
        parent = new int[n+1];

        for (int i = 0; i < n+1; i++) { //처음에는 자기 자신을 가리킨다.
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            inputs = br.readLine().split(" ");
            int command = Integer.parseInt(inputs[0]);
            int a = Integer.parseInt(inputs[1]);
            int b = Integer.parseInt(inputs[2]);

            if (command == 0) { //합집합 연산
                union(a, b);
            } else if (command == 1) { //확인 연산
                if (find(a) == find(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static void union(int a, int b) {
        a = find(a);    //부모를 찾는다
        b = find(b);

        if (a == b) { //이미 같은 집합 소속이라면
            return;
        }
        parent[b] = a;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        parent[a] = find(parent[a]);
        return parent[a];
    }
}
