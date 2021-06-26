import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class baekjoon_2910 {

    static int N, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);
        inputs = br.readLine().split(" ");
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        ArrayList<Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : o1 == o2 ? 0 : -1;
            }
        }));

        for (Entry<Integer, Integer> keyVal : list) {
            for (int i = 0; i < keyVal.getValue(); i++) {
                System.out.print(keyVal.getKey() + " ");
            }
        }
    }
}
