import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class baekjoon_2798 {

    static int[] arr;
    static int answer = Integer.MAX_VALUE;
    static int M;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        arr = new int[N];
        inputs = br.readLine().split(" ");
        for (int i = 0; i < inputs.length; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        backTracking(0, 0, 0);

        System.out.println(answer);
    }

    private static void backTracking(int curIndex, int curSum, int count) {
        if ((curIndex >= N && count < 3) || curSum > M) {
            return;
        }

        if (count == 3) {
            if (Math.abs(answer - M) > Math.abs(curSum - M)) {
                answer = curSum;
            }
            return;
        }

        backTracking(curIndex+1, curSum + arr[curIndex], count + 1);
        backTracking(curIndex+1, curSum, count);
    }
}
