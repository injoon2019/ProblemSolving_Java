import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class baekjoon_14889 {

    static int[][] arr;
    static int N;
    static int arrSum;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        for (int i = 1; i <N+1; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < inputs.length; j++) {
                arr[i][j+1] = Integer.parseInt(inputs[j]);
            }
        }
        arrSum = 0;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                arrSum += arr[i][j];
            }
        }
        solve(1, "", "");
        System.out.println(ans);
    }

    private static void solve(int curPerson, String startTeam, String linkTeam) {
        if (startTeam.length() == N/2 || linkTeam.length() == N/2) {
            if (linkTeam.length() == N/2) { // 만약 링크팀이 다 찼다면 나머지는 자동 스타트팀으로 채워준다.
                for (int i = curPerson; i <= N; i++) {
                    startTeam += Integer.toString(i);
                }
            } else if (startTeam.length() == N/2) {
                for (int i = curPerson; i <= N; i++) {
                    linkTeam += Integer.toString(i);
                }
            }

            int startSum = 0;
            int linkSum = 0;
            for (int i = 0; i < startTeam.length(); i++) {
                for (int j = 0; j < startTeam.length(); j++) {
                    startSum += arr[Character.getNumericValue(startTeam.charAt(i))][Character.getNumericValue(startTeam.charAt(j))];
                }
            }

            for (int i = 0; i < linkTeam.length(); i++) {
                for (int j = 0; j < linkTeam.length(); j++) {
                    linkSum += arr[Character.getNumericValue(linkTeam.charAt(i))][Character.getNumericValue(linkTeam.charAt(j))];
                }
            }
            ans = Math.min(ans, Math.abs(startSum - linkSum));
            return;
        }

        //curPerson이 스타트 팀에 들어가는 경우
        solve(curPerson + 1, startTeam + Integer.toString(curPerson), linkTeam);

        //curPerson이 링크 팀에 들어가는 경우
        solve(curPerson + 1, startTeam, linkTeam + Integer.toString(curPerson));
    }
}
