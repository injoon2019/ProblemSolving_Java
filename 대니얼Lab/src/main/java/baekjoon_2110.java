import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class baekjoon_2110 {

    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]); // 집의 개수
        C = Integer.parseInt(inputs[1]); // 공유기의 개수
        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int left = 0;
        int right = houses[N-1] - houses[0]; //처음 최대거리는 첫집에서 마지막 집 사이 거리다.

        int ans = 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (countHouse(mid) >= C) { //최대한 멀리 설치해야 하니 mid 거리를 올려 본다.
                ans = Math.max(ans, mid);
                left = mid + 1;
            } else if (countHouse(mid) < C) { // 만약 mid 거리로 필요한 개수보다 적게 설치되면 간격을 줄여야 한다.
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }

    private static int countHouse(int distance) {
        int count = 1; // 첫집에는 무조건 설치
        int curPos = houses[0];

        for (int i = 1; i < N; i++) {
            if (curPos + distance > houses[i]) {
                continue;
            }
            count += 1;
            curPos = houses[i];
        }
        return count;
    }
}

