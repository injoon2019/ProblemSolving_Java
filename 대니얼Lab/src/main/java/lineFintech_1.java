import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.stream.Collectors;


public class lineFintech_1 {

    static class Solution {
        public int solution(String inputString) {
            Stack stack = new Stack();
            int answer = 0;
            int curNum = 1;
            for (int i = 0; i < inputString.length(); ) {
                System.out.println("curNum = " + curNum);
                System.out.println("i = " + i + " inputString.charAt(i) = " + inputString.charAt(i));
                for (int j = 0; j < Integer.toString(curNum).length(); j++) {
                    if (inputString.charAt(i + j) == Integer.toString(curNum).charAt(j)) {
                        i++;
                    } else {
                        i++;
                        answer++;
                    }
                }
                curNum++;
            }
            return answer;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new lineFintech_1.Solution().solution("123903"));
    }
}
