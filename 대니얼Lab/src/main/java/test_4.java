import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class test_4 {

    static int[] arr = new int[]{1, 3, 7, 9, 11, 13, 15, 17};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = arr.length;
        int difference = (arr[arr.length - 1] - arr[0]) / n;

        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            // left에서 mid까지 스텝 개수 == mid - left
            // mid에서 right까지 스텝 개수 == right - left
            if (arr[left] + difference * (mid - left) != arr[mid]) { //왼쪽에 빈칸이 있다
                right = mid - 1;
            } else { //오른쪽에 빈칸이 있다
                left = mid + 1;
            }
        }
        System.out.println(Math.min(left, right));
        System.out.println(arr[0] + difference * (Math.min(left, right) - 0));
        System.out.println("left = " + left);
        System.out.println("right = " + right);
    }
}

