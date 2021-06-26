import java.io.IOException;
import java.util.Arrays;

public class programmers_12903 {

    static boolean[] primeNumbers = new boolean[5000];

    public static void main(String[] args) throws IOException {
        System.out.println(solution("abcde"));
        System.out.println(solution("qwer"));

    }

    public static String solution(String s) {
        if (s.length() % 2 == 1) {
            return Character.toString(s.charAt(s.length() / 2));
        } else {
            return s.substring(s.length() / 2 - 1, s.length() / 2 + 1);
        }
    }
}
