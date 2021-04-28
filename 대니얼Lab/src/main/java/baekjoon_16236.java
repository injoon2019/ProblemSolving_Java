import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class baekjoon_16236 {

    static int sharkSize = 2;
    static int sharkEat = 0;
    static int[] sharkPos = new int[2];
    static int N;
    static int[][] graph;
    static int time = 0;
    static boolean[][] visited;
    static int[] dx = new int[]{0, -1, 1, 0};
    static int[] dy = new int[]{-1, 0, 0, 1};
    static PriorityQueue<FishInfo> fishInfo = new PriorityQueue<>();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];


        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(inputs[j]);
                if (graph[i][j] == 9) {
                    sharkPos[0] = i;
                    sharkPos[1] = j;
                }
            }
        }

        while (true) {
            visited = new boolean[N][N];
            fishInfo = lookForFish(sharkPos[0], sharkPos[1]); //잡아 먹은 위치로 갱신
            if (fishInfo.size() == 0) {
                break;
            }
            FishInfo fish = fishInfo.poll();
            //크기 갱신
            sharkEat += 1;
            if (sharkEat == sharkSize) {
                sharkSize += 1;
                sharkEat = 0;
            }

            //기존 상어 지점 없애기
            graph[sharkPos[0]][sharkPos[1]] = 0;
            //먹힌 물고기 위치로 상어 이동
            graph[fish.y][fish.x] = 9;
            sharkPos[0] = fish.y;
            sharkPos[1] = fish.x;
            //시간 갱신
            time += fish.distance;
        }
        System.out.println(time);
    }

    private static PriorityQueue<FishInfo> lookForFish(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX, 0});
        fishInfo = new PriorityQueue<>();
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] elements = queue.poll();
            int y = elements[0];
            int x = elements[1];
            int count = elements[2];

            if (graph[y][x] != 9 && graph[y][x] != 0) {
                if (graph[y][x] < sharkSize) {
                    fishInfo.add(new FishInfo(count, y, x));
                }
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (0 <= ny && ny < N && 0 <= nx && nx < N) {
                    if (!visited[ny][nx] && graph[ny][nx] <= sharkSize) {
                        visited[ny][nx] = true;
                        queue.add(new int[]{ny, nx, count + 1});
                    }
                }
            }
        }
        return fishInfo;
    }


    public static class FishInfo implements Comparable<FishInfo> {
        int distance;
        int y;
        int x;

        FishInfo(int distance, int y, int x) {
            this.distance = distance;
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(FishInfo o) {
            if (this.distance > o.distance) { //y 에 대해 오름차순
                return 1;
            } else if (this.distance == o.distance) {
                if (this.y > o.y) {
                    return 1;
                } else if (this.y == o.y) {
                    if (this.x > o.x) {
                        return 1;
                    }
                }
            }
            return -1;
        }
    }
}

