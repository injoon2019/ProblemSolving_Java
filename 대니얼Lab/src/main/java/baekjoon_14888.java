import java.io.BufferedReader;
import java.io.InputStreamReader;


public class baekjoon_14888 {

    static int N;
    static int[] arr;
    static int[] operators = new int[4];
    static int minAns = Integer.MAX_VALUE;
    static int maxAns = Integer.MIN_VALUE;
    static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new int[N];

        String[] inputs = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }
        inputs = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(inputs[i]);
        }

        solve(arr[0], 1, 1);

        System.out.println(maxAns);
        System.out.println(minAns);
    }

    private static void solve(int cumVal, int count, int curPoint) {
        if (count == N) {
            maxAns = Math.max(maxAns, cumVal);
            minAns = Math.min(minAns, cumVal);
            return;
        }

        for (int j = 0; j < 4; j++) {
            if (operators[j] > 0) {
                operators[j] -= 1;
                if (j == 0) {
                    solve(cumVal + arr[curPoint], count + 1, curPoint+1);
                } else if (j == 1) {
                    solve(cumVal - arr[curPoint], count + 1, curPoint+1);
                } else if (j == 2) {
                    solve(cumVal * arr[curPoint], count + 1, curPoint+1);
                } else if (j == 3) {
                    solve(cumVal / arr[curPoint], count + 1, curPoint+1);
                }
                operators[j] += 1;
            }
        }
    }
}

