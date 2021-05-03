import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class baekjoon_18428 {

    static int N;
    static char[][] graph;
    static List<int[]> emptySpaces = new ArrayList<>();
    static List<int[]> teachers = new ArrayList<>();
    static List<int[]> students = new ArrayList<>();
    static Set<int[]> foundStudentsSets = new HashSet<>();
    static int[] dx = new int[]{-1, 0, 0, 1};
    static int[] dy = new int[]{0, -1, 1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];

        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = inputs[j].charAt(0);
                if (graph[i][j] == 'X') {
                    emptySpaces.add(new int[]{i, j});
                } else if (graph[i][j] == 'T') {
                    teachers.add(new int[]{i, j});
                } else if (graph[i][j] == 'S') {
                    students.add(new int[]{i, j});
                }
            }
        }

        hide();
        System.out.println("NO");
    }

    private static void hide() {
        backTracking(0, 0, 3, new ArrayList<>());
    }

    private static void backTracking(int curPos, int curCount, int maxCount, ArrayList<Integer> objectIndexes) {
        if (curPos == emptySpaces.size() && curCount < maxCount) {
            return;
        }

        if (curCount == maxCount) {
            if (simulateSuccess(objectIndexes)) { //선생님들이 다 찾지 못한 경우
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }

        ArrayList<Integer> addList = (ArrayList<Integer>) objectIndexes.clone();
        ArrayList<Integer> justList = (ArrayList<Integer>) objectIndexes.clone();
        addList.add(curPos);

        backTracking(curPos+1, curCount+1, maxCount, addList);
        backTracking(curPos+1, curCount, maxCount, justList);
    }

    private static boolean simulateSuccess(ArrayList<Integer> objectIndexes) {
        foundStudentsSets = new HashSet<>();
        for (int index : objectIndexes) { //장애물 설치
            int[] objectCoordinates = emptySpaces.get(index);
            int y = objectCoordinates[0];
            int x = objectCoordinates[1];
            graph[y][x] = '#'; //장애물
        }

        for (int[] teacherCoordinates : teachers) {
            int y = teacherCoordinates[0];
            int x = teacherCoordinates[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                while (0 <= ny && ny < N && 0 <= nx && nx < N) {
                    if (graph[ny][nx] == '#') {
                        break;
                    } else if (graph[ny][nx] == 'S') {
                        foundStudentsSets.add(new int[]{ny, nx});
                    }
                    ny = ny + dy[i];
                    nx = nx + dx[i];
                }
            }
        }

        if (foundStudentsSets.size() != 0) { //한명이라도 찾으면 학생은 실패다
            for (int index : objectIndexes) { //장애물 원상 복구
                int[] objectCoordinates = emptySpaces.get(index);
                int y = objectCoordinates[0];
                int x = objectCoordinates[1];
                graph[y][x] = 'X'; //빈공간
            }
            return false;
        }

        return true;
    }
}
