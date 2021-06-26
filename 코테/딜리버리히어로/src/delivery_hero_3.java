import java.util.HashMap;
import java.util.Map;

public class delivery_hero_3 {

    public static void main(String[] args) {
        for (int i = 1; i < 1000000000; i++) {
            if (solution(i) != solution2(i)) {
                System.out.println("i has a problem " + i);
                break;
            } else {
                System.out.println(i);
            }
        }
        System.out.println(solution(55) + " 56");
        System.out.println(solution(1765) + " 1767");
        System.out.println(solution(98) + " 101");
        System.out.println(solution(44432) + " 45010");
        System.out.println(solution(3298) + " 3401");
        System.out.println(solution(4444) + " 4501");
        System.out.println(solution(9998) + " 10101");
        System.out.println(solution2(9998) + " 10101");
    }

    private static int solution(int N) {
        int ans = N + 1;
        while (!meetCondition(ans)) {
            ans = moveNum(ans);
        }
        return ans;
    }

    private static int solution2(int N) {
        int ans = N + 1;
        while (!meetCondition(ans)) {
            ans += 1;
        }
        return ans;
    }

    private static int moveNum(int ans) {
        String num = Integer.toString(ans);
        int tmp = ans;
        for (int i = 0; i < num.length() - 1; i++) {
            if (num.charAt(i) == num.charAt(i + 1)) {
                if (num.charAt(i) == '9') {
                    tmp += Math.pow(10, num.length() - 1 - i - 1);
                    break;
                } else {
                    tmp += Math.pow(10, num.length() - 1 - i - 1);
                    String tmpNumFront = Integer.toString(tmp).substring(0, i + 2);

                    String tmpNumBack = "01010101010101";
                    tmpNumBack = tmpNumBack.substring(0, Integer.toString(tmp).length() - (i + 2));
                    tmp = Integer.valueOf(tmpNumFront + tmpNumBack);
                    break;
                }
            }
        }
        return tmp;
    }

    private static boolean meetCondition(int mid) {
        String num = Integer.toString(mid);
        for (int i = 0; i < num.length() - 1; i++) {
            if (num.charAt(i) == num.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
