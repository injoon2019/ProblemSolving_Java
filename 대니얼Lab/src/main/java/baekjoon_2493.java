import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class baekjoon_2493 {

    static int N;
    static int[] arr;
    static int[] ansArr;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ansArr = new int[N];

        String[] inputs = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        for (int i = N - 1; i >= 0; i--) {

            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                ansArr[stack.pop()] = i+1;
            }
            stack.add(i);
        }
        for (int num : ansArr) {
            bw.write(num + " ");
        }
        bw.flush();
        bw.close();
    }
}

