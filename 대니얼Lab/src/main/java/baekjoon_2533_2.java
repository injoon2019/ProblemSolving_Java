import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class baekjoon_2533_2 {

    public static List<Integer>[] childs = new ArrayList[1000001];
    public static int childCount[] = new int[1000001];
    public static int cache[][] = new int[1000001][2];
    static{
        for (int i = 0; i < 1000001; i++) {
            childs[i] = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                cache[i][j] = -1;
            }
        }
    }
    public static int N;
    public static void main(String[] args){
        Scanner sca = new Scanner(System.in);
        N = sca.nextInt();
        for (int i = 0; i < N-1; i++) {
            int pa = sca.nextInt();
            int ch = sca.nextInt();
            childs[pa].add(ch);
            childs[ch].add(pa);
        }

        dfs(1, -1);
        System.out.println(Math.min(getMinEarly(1, 0, -1), getMinEarly(1, 1, -1)));
        System.out.println();
    }
    public static int dfs(int node, int parent){
        int sum = 1;
        for (int i = 0; i < childs[node].size(); i++) {
            if(childs[node].get(i) == parent)
                continue;
            sum += dfs(childs[node].get(i), node);
        }
        return childCount[node] = sum;
    }
    public static int getMinEarly(int root, int onOff, int parent){
        // 탈출조건
        if(childCount[root] == 1){
            return onOff == 1 ? 1 : 0;
        }
        if(cache[root][onOff] != -1)
            return cache[root][onOff];

        // root가 켜져 있을 때
        if(onOff == 1){
            int sum = 1;
            for (int i = 0; i < childs[root].size(); i++) {
                int child = childs[root].get(i);
                if(child == parent)
                    continue;
                sum += Math.min(getMinEarly(child, 1, root), getMinEarly(child, 0, root));
            }
            return cache[root][onOff] = sum;
        }

        // root가 꺼져 있을 때
        int sum = 0;
        for (int i = 0; i < childs[root].size(); i++) {
            int child = childs[root].get(i);
            if(child == parent)
                continue;
            sum += getMinEarly(child, 1, root);
        }
        return cache[root][onOff] = sum;
    }
}
