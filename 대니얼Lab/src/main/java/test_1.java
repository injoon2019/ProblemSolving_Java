import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class test_1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int l = 0;
        int r = 29;
        int check = -1;
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,  15, 15, 15, 15, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        while (l <= r) {
            int mid = (l + r) / 2;
            System.out.println(l + " " + r);
            //조건 만족 체크
            if (nums[mid] == 15) {
                check = mid;
            }
            //범위 재조정
            if (nums[mid] <= 15) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
//        return check;
    }

}

