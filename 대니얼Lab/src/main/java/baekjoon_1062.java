import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


public class baekjoon_1062 {
    static int N, K;
    static int maxAns = 0;
    static boolean[] alphaArr = new boolean[26];
    static String[] testWords;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        K = Integer.parseInt(inputs[1]);
        if (K < 5) {
            System.out.println(0);
            return;
        }
        testWords = new String[N];
        for (int n = 0; n < N; n++) {
            testWords[n] = br.readLine();
        }
        // a n t i c 는 무조건 읽을 수 있어야한다 anta tica는 필수기 때문에
        alphaArr[(int)'a' - (int)'a'] = true;
        alphaArr[(int)'n' - (int)'a'] = true;
        alphaArr[(int)'t' - (int)'a'] = true;
        alphaArr[(int)'i' - (int)'a'] = true;
        alphaArr[(int)'c' - (int)'a'] = true;

        backTracking(0, 0, K-5);
        System.out.println(maxAns);
    }

    private static void backTracking(int curPos, int curCount, int maxCount) {

        if (curPos >= 26 && curCount < maxCount) {
            return;
        }
        if (curCount == maxCount) {
            int readableCount = 0;
            for (int n = 0; n < N; n++) {
                if (checkReadable(testWords[n])) {
                    readableCount++;
                }
            }
            maxAns = Math.max(readableCount, maxAns);
            return;
        }

        if (alphaArr[curPos] == false) {
            alphaArr[curPos] = true;
            backTracking(curPos+1, curCount+1, maxCount);
            alphaArr[curPos] = false;
        }
        backTracking(curPos+1, curCount, maxCount);
    }

    private static boolean checkReadable(String testWord) {
        for (int i = 4; i < testWord.length() - 4; i++) {
            if (!alphaArr[(int) testWord.charAt(i) - (int) 'a']) {
                return false;
            }
        }
        return true;
    }
}

