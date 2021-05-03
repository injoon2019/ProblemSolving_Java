import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class baekjoon_2169 {

    static int N;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int i, j, n = sc.nextInt(), m = sc.nextInt();
        int[][] map = new int[n + 1][m + 1];
        int d[][] = new int[n + 1][m + 1];
        int tmp[][] = new int[2][m + 2];

        for (i = 1; i <= n; i++) {
            for (j = 1; j <= m; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        sc.close();

        d[1][1] = map[1][1];
        for (i = 2; i <= m; i++) {
            d[1][i] = d[1][i - 1] + map[1][i];
        }

        for (i = 2; i <= n; i++) {
            tmp[0][0] = d[i-1][1];
            for (j = 1; j <= m; j++) {
                tmp[0][j] = Math.max(tmp[0][j - 1], d[i - 1][j]) + map[i][j];
            }
            tmp[1][m + 1] = d[i - 1][m];
            for (j = m; j >= 1; j--) {
                tmp[1][j] = Math.max(tmp[1][j + 1], d[i - 1][j]) + map[i][j];
            }

            for (j = 1; j <= m; j++) {
                d[i][j] = Math.max(tmp[0][j], tmp[1][j]);
            }
        }
        System.out.println(d[n][m]);
    }
}
