import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class baekjoon_1654 {

    static int K, N;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        K = Integer.parseInt(inputs[0]); //가지고 있는 랜선의 개수
        N = Integer.parseInt(inputs[1]); //필요한 랜선의 ㄱ새ㅜ
        arr = new long[K];

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long left = 0;
        long right = Arrays.stream(arr).max().getAsLong();
        long maxLength = 0;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (mid == 0) {
                left += 1;
                continue;
            }
            if (getCount(mid) >= N) { //필요 개수 이상이면 더 길게 짤라도 된다.
                maxLength = Math.max(maxLength, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(maxLength);

    }

    private static int getCount(long length) {
        int count = 0;
        for (long wire : arr) {
            if (length != 0) {
                count += wire / length;
            }
        }
        return count;
    }
}
