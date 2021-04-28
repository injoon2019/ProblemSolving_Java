import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class baekjoon_9012 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            String brackets = br.readLine();
            judgeBrackets(brackets);
        }
    }

    private static void judgeBrackets(String brackets) {
        int stack = 0;
        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(') {
                stack += 1;
            } else {
                if (stack ==0) {
                    System.out.println("NO");
                    return;
                } else {
                    stack -= 1;
                }
            }
        }

        if (stack > 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
        return;
    }
}
