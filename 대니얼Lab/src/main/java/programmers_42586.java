import java.io.IOException;
import java.util.*;

public class programmers_42586 {

    static boolean[] primeNumbers = new boolean[5000];

    public static void main(String[] args) throws IOException {
        System.out.println(solution(new int[]{93, 30, 55}, new int[]{1, 30, 5}));
        System.out.println(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1}));

    }

    private static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ansList = new ArrayList<>();

        Queue<Integer> progressesQ = new LinkedList<>();
        Queue<Integer> speedsQ = new LinkedList();
        for (int i = 0; i < progresses.length; i++) {
            progressesQ.add(progresses[i]);
            speedsQ.add(speeds[i]);
        }

        while (!progressesQ.isEmpty()) {
            int count = 0;
            while (!progressesQ.isEmpty() && (progressesQ.peek() == 100 || progressesQ.peek() > 100)) {

                count += 1;
                progressesQ.poll();
                speedsQ.poll();
            }
            if (count != 0) {
                ansList.add(count);
            }
            while (!progressesQ.isEmpty() && progressesQ.peek() < 100) {
                int queueSize = progressesQ.size();
                for (int i = 0; i < queueSize; i++) {
                    int tmpSpeed = speedsQ.poll();
                    int tmpProgress = progressesQ.poll();
                    progressesQ.add(tmpProgress + tmpSpeed);
                    speedsQ.add(tmpSpeed);
                }
            }
        }
        return ansList.stream().mapToInt(i -> i).toArray();
    }

}
