import java.io.IOException;
import java.util.*;

public class programmers_42587 {

    public static void main(String[] args) throws IOException {
        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));

    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            q.add(new int[]{priorities[i], i});
        }
        int order = 1;
        while (!q.isEmpty()) {
            int[] pollResult = q.poll();
            int qSize = q.size();
            boolean isBiggest = true;
            for (int i = 0; i < qSize; i++) {
                int[] tmpResult = q.poll();
                if (tmpResult[0] > pollResult[0]) {
                    isBiggest = false;
                }
                q.add(tmpResult);
            }
            if (isBiggest) {
                if (pollResult[1] == location) {
                    return order;
                }
                order += 1;
            } else {
                q.add(pollResult);
            }
        }
        return answer;
    }
}
