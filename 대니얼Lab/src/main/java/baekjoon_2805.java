import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class baekjoon_2805 {

    static int N, M;
    static int[] trees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        trees = new int[N];
        inputs = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(inputs[i]);
        }

        int left = 0;
        int right = 1000000000;
        int check = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            long cuttedTree = cutTree(mid);
            if (cuttedTree >= M) {
                check = mid;
                left = mid + 1;
                continue;
            }

            if (cuttedTree < M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(check);
    }

    private static long cutTree(int mid) {
        long treeSum = 0;
        for (int i = 0; i < N; i++) {
            if (trees[i] >= mid) {
                treeSum += trees[i] - mid;
            }
        }
        return treeSum;
    }
}

