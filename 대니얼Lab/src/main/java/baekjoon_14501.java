import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class baekjoon_14501 {

    static int N;
    static int[] tArr;
    static int[] pArr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        tArr = new int[N];
        pArr = new int[N];
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            tArr[i] = Integer.parseInt(inputs[0]);
            pArr[i] = Integer.parseInt(inputs[1]);
        }
        solve();
        System.out.println(answer);
    }

    private static void solve() {
        recursive(0, 0);
    }

    private static void recursive(int curDay, int curMoney) {
        answer = Math.max(answer, curMoney);
        if (curDay >= N) {
            return;
        }
        //이 날 일을 하는 경우
        if (curDay + tArr[curDay] <= N) {
            recursive(curDay + tArr[curDay], curMoney + pArr[curDay]);
        }
        //이 날 일을 안하는 경우
        if (curDay + 1 <= N) {
            recursive(curDay + 1, curMoney);
        }
    }
}
