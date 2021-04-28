import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class baekjoon_2309 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] people = new int[9];
        for (int i = 0; i < 9; i++) {
            int height = Integer.parseInt(br.readLine());
            people[i] = height;
        }

        boolean[] visited = new boolean[9];
        List<Integer> list = new ArrayList<>();
        backTracking(people, visited, list);
    }

    private static void backTracking(int[] people, boolean[] visited, List<Integer> list) {
        if (list.size() == 7) {
            if (list.stream().mapToInt(Integer::intValue).sum() == 100) {
                Collections.sort(list);
                for (int height : list) {
                    System.out.println(height);
                }
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(people[i]);
                backTracking(people, visited, list);
                list.remove((Integer)people[i]);
                visited[i] = false;
            }
        }
    }
}
