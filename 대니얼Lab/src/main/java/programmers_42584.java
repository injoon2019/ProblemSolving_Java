import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class programmers_42584 {

    public static void main(String[] args) throws IOException {
        System.out.println(solution(new int[]{1, 2, 3, 2, 3}));
        System.out.println(solution(new int[]{1, 2, 3, 2, 3, 1}));
        System.out.println(solution(new int[]{5, 8, 6, 2, 4, 1}));

    }

    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int pop = stack.pop();
                answer[pop] = i - pop;
            }
            stack.add(i);
        }

        while (!stack.isEmpty()) {
            int pop = stack.pop();
            answer[pop] = prices.length -1 - pop;
        }
        Arrays.stream(answer).forEach(s -> System.out.print(s + " "));
        return answer;
    }
}
