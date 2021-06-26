import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class programmers_42576 {

    static boolean[] primeNumbers = new boolean[5000];

    public static void main(String[] args) throws IOException {
        System.out.println(solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));

    }

    private static String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        for (String runner : participant) {
            if (map.containsKey(runner)) {
                map.put(runner, map.get(runner) + 1);
            } else {
                map.put(runner, 1);
            }
        }

        for (String compRunner : completion) {
            if (map.containsKey(compRunner)) {
                if (map.get(compRunner) == 1) {
                    map.remove(compRunner);
                } else {
                    map.put(compRunner, map.get(compRunner) - 1);
                }
            }
        }

        Set<String> strings = map.keySet();
        return (String) strings.toArray()[0];
    }

}
