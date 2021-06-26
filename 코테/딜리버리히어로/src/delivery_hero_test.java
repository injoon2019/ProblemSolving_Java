public class delivery_hero_test {

    public static void main(String[] args) {
        int[] A = new int[] {1, 3, 6, 4, 1, 2};
        System.out.println(solution(A));
    }

    private static int solution(int[] A) {
        int max_num = Integer.MIN_VALUE;
        for (int num : A) {
            if (num > max_num) {
                max_num = num;
            }
        }
        if (max_num > 0) {
            return max_num + 1;
        } else {
            return 1;
        }
    }
}
