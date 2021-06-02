import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class baekjoon_5397 {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int k = 0; k < N; k++) {
            Stack<Character> stack1 = new Stack<>();
            Stack<Character> stack2 = new Stack<>();
            String input = br.readLine();

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '<') {
                    if (!stack1.isEmpty()) {
                        stack2.add(stack1.pop());
                    }
                } else if (input.charAt(i) == '>') {
                    if (!stack2.isEmpty()) {
                        stack1.add(stack2.pop());
                    }
                } else if (input.charAt(i) == '-') {
                    if (!stack1.isEmpty()) {
                        stack1.pop();
                    }
                } else {
                    stack1.add(input.charAt(i));
                }
            }

            StringBuffer answer = new StringBuffer();
            StringBuffer string1 = new StringBuffer();
            while (!stack1.isEmpty()) {
                string1.append(stack1.pop());
            }
            answer = string1.reverse();
            string1 = new StringBuffer();
            while (!stack2.isEmpty()) {
                string1.append(stack2.pop());
            }
            answer.append(string1);
            System.out.println(answer);
        }
    }
}

