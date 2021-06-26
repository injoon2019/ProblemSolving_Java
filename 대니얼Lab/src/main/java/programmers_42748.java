import java.io.IOException;
import java.util.*;

public class programmers_42748 {

    static boolean[] primeNumbers = new boolean[5000];

    public static void main(String[] args) throws IOException {
        System.out.println(solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}}));

    }

    public static int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int[] command : commands) {
            int start = command[0] - 1;
            int end = command[1];
            int kth = command[2] - 1;

            int[] tmpArr = Arrays.copyOfRange(array, start, end);
            Arrays.sort(tmpArr);
            ans.add(tmpArr[kth]);
        }
        int[] ansArr = ans.stream().mapToInt(i -> i).toArray();
        return ansArr;
    }
}
