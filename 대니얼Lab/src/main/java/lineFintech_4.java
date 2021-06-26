import java.io.IOException;

public class lineFintech_4 {

    static class Solution {
        public int solution(String inputString) {
            int answer = 0;
            int curNum = 1;
            for (int i = 0; i < inputString.length(); ) {
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
        System.out.println(new Solution().solution("123903"));
    }
}
