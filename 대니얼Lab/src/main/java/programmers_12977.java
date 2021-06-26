import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class programmers_12977 {

    static boolean[] primeNumbers = new boolean[5000];

    public static void main(String[] args) throws IOException {
        System.out.println(solution(new int[]{1, 2, 3, 4}));
        System.out.println(solution(new int[]{1, 2, 7, 6, 4}));


    }

    private static int solution(int[] nums) {
        int answer = 0;
        erathos();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (primeNumbers[nums[i] + nums[j] + nums[k]]) {
                        answer += 1;
                    }
                }
            }
        }
        return answer;
    }

    private static void erathos() {
        Arrays.fill(primeNumbers, true);

        for (int i = 2; i < (int)Math.sqrt(5000) + 1; i++) {
            for (int j = i * 2; j < 5000; j += i) {
                primeNumbers[j] = false;
            }
        }
    }
}
