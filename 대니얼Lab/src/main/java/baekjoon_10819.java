import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class baekjoon_10819 {

    static boolean[] check;
    static int[] arr;
    static int ans = Integer.MIN_VALUE;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        check = new boolean[N];

        String[] strInputs = br.readLine().split(" ");
        for (int i = 0; i < strInputs.length; i++) {
            arr[i] = Integer.parseInt(strInputs[i]);
        }
        solve(0, 0, Integer.MIN_VALUE);
        System.out.println(ans);
    }

    private static void solve(int count, int curSum, int prevValue) {
        if(count == N) {
            ans = Math.max(ans, curSum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!check[i]) {
                check[i] = true;
                if (prevValue == Integer.MIN_VALUE) {
                    solve(count+1, curSum, arr[i]);
                } else {
                    solve(count+1, curSum + Math.abs(prevValue - arr[i]), arr[i]);
                }
                check[i] = false;
            }
        }
    }
}
