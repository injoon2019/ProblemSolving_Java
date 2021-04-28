import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;


public class baekjoon_6603 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        while (true) {
            String[] inputs = input.split(" ");
            arr = new int[Integer.parseInt(inputs[0])];
            N = Integer.parseInt(inputs[0]);
            for (int i = 1; i < inputs.length; i++) {
                arr[i - 1] = Integer.parseInt(inputs[i]);
            }

            solve(0, 0, "");
            input = br.readLine();
            if (input.equals("0")) {
                break;
            }
            System.out.println();
        }
    }

    private static void solve(int curPos, int curCount, String curString) {
        if (curPos == N && curCount < 6) {
            return;
        }
        if (curCount == 6) {
            System.out.println(curString.substring(1, curString.length()));
            return;
        }
        solve(curPos + 1, curCount + 1, curString + " " + Integer.toString(arr[curPos]));
        solve(curPos + 1, curCount, curString);
    }
}

