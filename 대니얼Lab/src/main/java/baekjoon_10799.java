import java.io.BufferedReader;
import java.io.InputStreamReader;


public class baekjoon_10799 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String brackets = br.readLine();
        int ans = 0;
        int stack = 0;
        // 앞에 '('가 오고 ')'가 오면 자르는 것이고 ')' 다음 ')'는 막대기의 끝이다.
        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(') {
                if (brackets.charAt(i + 1) == '(') {
                    stack += 1;
                } else {
                    continue;
                }
            } else {
                if (brackets.charAt(i - 1) == '(') {
                    ans += stack;
                } else {
                    ans += 1;
                    stack -= 1;
                }
            }
        }
        System.out.println(ans);
    }
}
