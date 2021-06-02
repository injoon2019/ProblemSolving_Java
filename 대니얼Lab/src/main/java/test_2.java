import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class test_2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = new int[] {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
        int[] answerArr = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, answerArr);
        Arrays.stream(arr).forEach(e -> System.out.print(e + " "));
    }

    private static void mergeSort(int[] arr, int l, int r, int[] answerArr) {
        if (l == r)
            return;

        int mid = (l + r) / 2;
        mergeSort(arr, l, mid, answerArr);
        mergeSort(arr, mid + 1, r, answerArr);
        merge(arr, l, mid, r, answerArr);
    }

    private static void merge(int[] arr, int l, int mid, int r, int[] answerArr) {
        int first = l;
        int second = mid + 1;
        int index = l;

        while (first <= mid && second <= r) {
            if (arr[first] < arr[second]) {
                answerArr[index++] = arr[first++];
            } else {
                answerArr[index++] = arr[second++];
            }
        }

        while (first <= mid) {
            answerArr[index++] = arr[first++];
        }

        while (second <= r) {
            answerArr[index++] = arr[second++];
        }

        for (int i = l; i <= r; i++) {
            arr[i] = answerArr[i];
        }
    }
}

