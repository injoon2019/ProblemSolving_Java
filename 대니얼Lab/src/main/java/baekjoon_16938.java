import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class baekjoon_16938 {
    static int N, L, R, X;
    static int[] arr;
    static int ans;
    static Set<String> ansSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        L = Integer.parseInt(inputs[1]);
        R = Integer.parseInt(inputs[2]);
        X = Integer.parseInt(inputs[3]);
        arr = new int[N];
        inputs = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        solve(0, 0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE, "");
        System.out.println(ansSet.size());
    }

    private static void solve(int curPos, int curSum, int curCount, int minValue, int maxValue, String choice) {
        if (curCount >= 2) {
            if (L <= curSum && curSum <= R && Math.abs(maxValue - minValue) >= X) {
                ans++;
                ansSet.add(choice);
            }
        }

        if (curPos >= N) {
            return;
        }

        solve(curPos + 1, curSum + arr[curPos], curCount + 1, Math.min(minValue, arr[curPos]), Math.max(maxValue, arr[curPos]), choice + " " +Integer.toString(curPos));
        solve(curPos+1, curSum, curCount, minValue, maxValue, choice);
    }
}

