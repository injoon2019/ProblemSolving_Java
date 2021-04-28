import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class baekjoon_11729 {

    static List<List<Integer>> list = new ArrayList<>();
    static int callCount = 0;
    static List pathList = new ArrayList();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        List stack0 = new ArrayList();
        List stack1 = new ArrayList();
        List stack2 = new ArrayList();
        for (int i = N; i > 0; i--) {
            stack0.add(i);
        }
        list.add(stack0);
        list.add(stack1);
        list.add(stack2);

        solve(0, 1, 2, N);
        System.out.println(callCount);
        for (Object path : pathList) {
            bw.write(String.join(" ", (List) path)+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void solve(int start, int stopover, int destination, int count) {
        if (count == 1) {
            move(start, destination);
            return;
        }

        solve(start, destination, stopover, count-1); // 제일 밑의 원판을 목표지점으로 옮기기 위해 위에 것들을 stopover에 잠시 놔둔다.
        move(start, destination);
        solve(stopover, start, destination, count - 1);
    }

    private static void move(int start, int destination) {
        callCount += 1;
        List startDest = new ArrayList();
        startDest.add(Integer.toString(start + 1));
        startDest.add(Integer.toString(destination + 1));
        pathList.add(startDest);

        list.get(destination).add(list.get(start).remove(list.get(start).size()-1));
    }
}
