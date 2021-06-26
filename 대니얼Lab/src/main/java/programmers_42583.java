import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class programmers_42583 {

    public static void main(String[] args) throws IOException {
        System.out.println(solution(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(solution(100, 100, new int[]{10}));
        System.out.println(solution(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}));

    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int truck : truck_weights) {
            q.add(truck);
        }
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }

        int curBridgeSum = 0;
        while (!q.isEmpty() || curBridgeSum != 0) {
            answer++;
            int poll = bridge.poll();
            if (poll != 0) { //차가 나온다면
                curBridgeSum -= poll;
            }
            int newTruck = 0;
            if (!q.isEmpty() && (curBridgeSum + q.peek() <= weight)) {
                newTruck = q.poll();
                curBridgeSum += newTruck;
            }
            bridge.add(newTruck);
        }
        return answer;
    }
}
