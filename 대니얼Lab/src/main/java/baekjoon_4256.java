import java.io.IOException;
import java.util.Scanner;

public class baekjoon_4256 {

    static int pre[];
    static int in[];

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while(t-->0) {
            int n = sc.nextInt();
            pre = new int[n+1];
            in = new int[n+1];
            for(int i=0; i<n; i++)
                pre[i] = sc.nextInt();
            for(int i=0; i<n; i++)
                in[i] = sc.nextInt();

            postorder(0, n, 0);
            System.out.println();
        }


    }

    public static void postorder(int start, int end, int root) {
        for(int i= start; i< end; i++) {
            if(in[i] == pre[root]) {
                postorder(start, i, root+1);
                postorder(i+1, end, root+i- start +1);
                System.out.print(pre[root] + " ");
            }
        }
    }
}
