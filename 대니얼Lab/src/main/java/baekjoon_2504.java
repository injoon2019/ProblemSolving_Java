import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class baekjoon_2504 {
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        System.out.println(solve(0, str.length()-1));

    }

    private static int solve(int start, int end) {
        if (str.substring(start, end+1).equals("")) {
            return 1;
        }

        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = start; i <= end; i++) {
            if (stack.size() == 1) {
                if ( (str.charAt(stack.peek()) == '(' && str.charAt(i) == ')')) {
                    ans += 2 * solve(stack.pop()+1, i-1);
                    continue;
                } else if (str.charAt(stack.peek()) == '[' && str.charAt(i) == ']') {
                    ans += 3 * solve(stack.pop()+1, i-1);
                    continue;
                }
            }
            if (str.charAt(i) == '(' || str.charAt(i) == '[') {
                stack.add(i);
            } else if (stack.size() > 0 && (str.charAt(stack.peek()) == '(' && str.charAt(i) == ')'
                || str.charAt(stack.peek()) == '[' && str.charAt(i) == ']')) {
                stack.pop();
            } else {
                System.out.println(0);
                System.exit(0);
            }

        }
        if (stack.size() > 0) {
            System.out.println(0);
            System.exit(0);
        }
        return ans;
    }
}
