import java.util.Scanner;

public class baekjoon_9095 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(sc.nextLine());
            System.out.println(solve(n));
        }
    }

    private static int solve(int n) {
        if (n == 0) {
            return 1;
        }
        else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        }
        return solve(n - 1) + solve(n - 2) + solve(n - 3);
    }
}
