import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class baekjoon_11726 {

    static int MOD = 10007;
    static long[] dp = new long[1001];
    static int[] dp2 = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        System.out.println(solve(n));
    }

    private static Long solve(Long n) {
        if (n == 1) {
            return 1L;
        } else if (n == 2) {
            return 2L;
        }
        if (dp[(int) (n-1)] == 0){
            dp[(int) (n - 1)] = solve(n - 1);
        }
        if (dp[(int) (n - 2)] == 0) {
            dp[(int) (n - 2)] = solve(n - 2);
        }

        return (dp[(int) (n-1)]  % MOD + dp[(int) (n-2)]  % MOD) % MOD;
    }
}
