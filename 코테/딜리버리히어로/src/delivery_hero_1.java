import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class delivery_hero_1 {

    public static void main(String[] args) {
        String S = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker";
        String S2 = "John Doe; Peter Benjamin Parker; Mary Jane Watsonab; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker";
        String C = "Example";
        System.out.println(solution(S, C));
        System.out.println(solution(S2, C));
    }

    private static String solution(String S, String C) {
        String[] names = S.toLowerCase().split(";");
        String answer = "";
        C = C.toLowerCase();
        Map<String, Integer> nameMap = new HashMap<>();
        for (String name : names) {
            name = name.trim();
            String[] splittedNames = name.split(" ");
            String tmp = "";
            String emailHeadFirst = "";
            String emailHeadSecond = "";
            if (splittedNames.length >= 3) { // middle name 있을 경우
                emailHeadFirst = splittedNames[0];
                emailHeadSecond = splittedNames[2];
            } else { //middle name 없을 경우
                emailHeadFirst = splittedNames[0];
                emailHeadSecond = splittedNames[1];
            }
            emailHeadSecond = emailHeadSecond.replaceAll("-", ""); //하이픈 제거
            emailHeadSecond = emailHeadSecond.substring(0, Math.min(emailHeadSecond.length(), 8));
            tmp += emailHeadFirst;
            tmp += ".";
            tmp += emailHeadSecond;
            if (nameMap.containsKey(tmp)) {
                nameMap.put(tmp, nameMap.get(tmp) + 1);
                tmp += nameMap.get(tmp);
            } else {
                nameMap.put(tmp, 1);
            }
            tmp += "@" + C + ".com; ";
            answer += tmp;
        }
        answer = answer.substring(0, answer.length() - 2); //마지막 " ;"제거
        return answer;
    }
}
