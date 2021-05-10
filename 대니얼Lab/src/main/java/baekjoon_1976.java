import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon_1976 {

    static int N, M;
    static int[] parents;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(inputs[j]) == 1) {
                    union(i, j);
                }
            }
        }
        String[] inputs = br.readLine().split(" ");
        for (int i = 0; i < inputs.length - 1; i++) {
            if (find(Integer.parseInt(inputs[i])-1) == find(Integer.parseInt(inputs[i+1])-1)) {
                continue;
            } else {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");

    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    private static int find(int a) {
        if (a == parents[a]) {
            return parents[a];
        }

        parents[a] = find(parents[a]);
        return parents[a];
    }
}
