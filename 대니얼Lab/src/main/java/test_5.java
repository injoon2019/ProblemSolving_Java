import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class test_5 {

    public static double solution(int[] arr) {
        int tmpSum = Arrays.stream(arr).sum();
        System.out.println(tmpSum);
        return Arrays.stream(arr).sum() / arr.length;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3, 4};
        System.out.println(solution(test));
    }
}

