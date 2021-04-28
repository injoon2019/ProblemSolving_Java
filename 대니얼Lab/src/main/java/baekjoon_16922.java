import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class baekjoon_16922 {

    static int[] romans = new int[]{1, 5, 10, 50};
    static Set<Integer> ansSet = new HashSet<>();
    static int[] ansArr = new int[]{0, 4,
        10,
        20,
        35,
        56,
        83,
        116,
        155,
        198,
        244,
        292,
        341,
        390,
        439,
        488,
        537,
        586,
        635,
        684,
        733
    };
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        bw.write(String.valueOf(ansArr[N]));
        bw.flush();
        bw.close();
    }
}

