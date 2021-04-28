import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//https://bcp0109.tistory.com/30

public class baekjoon_14889_2 {
    static int stoi(String s) { return Integer.parseInt(s); }

    static int n;
    static boolean[] visited;
    static int[][] arr;
    static int min = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = stoi(br.readLine());
        visited = new boolean[n+1];
        arr = new int[n+1][n+1];

        for(int i=1; i<n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<n+1; j++) {
                arr[i][j] = stoi(st.nextToken());
            }
        }

        comb(1, 0);
        System.out.println(min);
    }

    // 모든 팀의 조합 구하기
    static void comb(int start, int depth) {
        if(depth == n/2) {
            min = Math.min(min, getAbilityDifference());
            return;
        }

        for(int i=start; i<n+1; i++) {
            if(visited[i] != true) {
                visited[i] = true;
                comb(i+1, depth+1);
                visited[i] = false;
            }
        }
    }

    // 팀의 능력치 차이를 구하기
    static int getAbilityDifference() {
        int sumStart = 0;
        int sumLink = 0;

        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
                // true 면 스타트팀
                if(visited[i] && visited[j])
                    sumStart += arr[i][j];

                // false 면 링크팀
                if(visited[i] != true && visited[j] != true)
                    sumLink += arr[i][j];
            }
        }

        return Math.abs(sumStart - sumLink);
    }
}
