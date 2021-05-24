import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class baekjoon_2613 {

    static int size, m, result;
    static int[] list;
    static int[] countList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = stoi(st.nextToken());
        m = stoi(st.nextToken()); // 그룹 수

        list = new int[size];
        countList = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            list[i] = stoi(st.nextToken());
        }

        parametric();
        System.out.println(result);
        noZeroGroup();

        Arrays.stream(countList).forEach(e -> System.out.printf(e + " "));
    }

    private static void noZeroGroup() {
        int index;
        for (int i = 0; i < m; i++) {
            if (countList[i] == 0) {
                index = i - 1;
                countList[i]++;
                while (true) {
                    if (countList[index] == 1) {
                        index--;
                        continue;
                    }
                    break;
                }
                countList[index]--;
            }
        }
    }

    private static void parametric() {
        int left = 1;
        int right = size * 100; //구슬에 적힌 숫자닌 100이하의 자연수

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    private static boolean isPossible(int mid) {
        int count = 0;
        int tempSum = 0;
        int[] tempCount = new int[m + 1];

        for (int i = 0; i < size; i++) {
            if (list[i] > mid) {
                return false;
            }
            if (tempSum + list[i] > mid) {
                tempSum = list[i];
                count++;
            } else {
                tempSum += list[i];
            }
            if (count >= m) {
                return false;
            }
            tempCount[count]++;
        }
        countList = tempCount;
        System.out.println("mid = " + mid);
        Arrays.stream(countList).forEach(s -> System.out.print(s + " "));
        System.out.println();
        return true;
    }

    private static int stoi(String input) {
        return Integer.parseInt(input);
    }

}
