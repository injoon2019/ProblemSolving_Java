import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class baekjoon_21312 {

    static int A, B, C;
    static ArrayList<Integer> cocktails = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        A = Integer.parseInt(inputs[0]);
        B = Integer.parseInt(inputs[1]);
        C = Integer.parseInt(inputs[2]);

        cocktails.add(A);
        cocktails.add(B);
        cocktails.add(C);
        cocktails.add(A * B);
        cocktails.add(B * C);
        cocktails.add(A * C);
        cocktails.add(A * B * C);

        cocktails = (ArrayList<Integer>) cocktails.stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 왼작마오
                if (o1 % 2 == 1 && o2 % 2 == 0) {
                    return 1;
                } else if (o1 % 2 == 0 && o2 % 2 == 1) {
                    return -1;
                } else if (o1 % 2 == 0 && o2 % 2 == 0) {
                    return o1 < o2 ? -1 : o1 == o2 ? 0 : 1;
                } else {
                    return o1 < o2 ? -1 : o1 == o2 ? 0 : 1;
                }
            }
        }).collect(Collectors.toList());
        System.out.println(cocktails.get(cocktails.size() - 1));
    }
}
