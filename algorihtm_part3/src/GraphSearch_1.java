/**
 * 그래프 : 간선 + 정점
 *
 * (무방향/방향) + 가중치
 *
 * 그래프 용어
 * degree : 정점x의 차수, 정점 x에 연결된 간선의 수 *
 * 무방향 그래프의 모든 정점의 차수의 합 == 총 간선의 수 * 2
 * 방향 그래프 : 진입 차수 + 진츨 차수
 *
 * 그래프를 코드로 나타내는 방법
 * 1. 인접 행렬
 * ( 2차원 행렬 : (1,5)의 값이 1 이다? 1과 5는 연결되어 있다. (2,3)의 값이 0이다? 2와 3번은 연결이 안되어있다.)
 * 만약 가중치 그래프라면 1대신 가중치 값을!
 * -> 문제점 ) 필요없는 공간이 너무 많이 소요된다.
 *
 * 2. 인접 리스트
 * 특정 정점에서 갈 수 있는 정점들만 리스트로 표현한다.
 * 인접 리스트들은 ArrayList< <ArrayList<Integer> >adj
 * O(E) 만큼의 공간이 필요하다.
 *
 * 예로 양방향성 그래프에서 A에서 B로 이동가능? 가중치 얼마?이러면
 * A와 B중 차수가 작은 정점의 인접 리스트를 탐색하면서 확인하자!!
 *
 * 그래프 문제에서 정점, 간선이 의미하는 것을 파악할 필요가 있다@
 * 수학에서 x를 무엇으로 정할지 고르는 것 처럼!
 *
 * **** 그래프에서 탐색을 할 수 있는 알고리즘!! ****
 * DFS 깊이 우선 탐색
 *       큐로 visited, 스택으로 needvisit를 표현 (이건 경로까지 다 체크할 필요가 있을 때)
 *
 *       한 공간을 boolean 배열로 만들어서 거쳐간 곳은 true로 만들고 그 인접한 곳들을 탐색하는 방법
 *       (단순히 그 공간을 들렸는지 안들렸는지를 체크하는 용)
 *       -> 인접리스트 방식이 시간복잡도 면에서 더 유용하다
 *
 * BFS 너비 우선 탐색
 *      큐로 visited, needvisit를 표현
 *
 *      한 곳에서 갈 수 있는 곳을 큐에 넣기!
 *      큐를 만든 이유
 *      -> 방문이 가능한 정점들을 찾아서 큐에 해당 정점을 넣는다.
 *      즉, 큐는 아직 방문하지 않은 정점들을 다 넣는다.
 *
 *      *주의*
 *      방문한 순간 그 정점을 true값으로 바꿔주어야합니다!
 *
 *      백준 1260번
 *      인접 행렬
 *
 *      인접 리스트
 *      문제에서 크기가 작은 순으로 먼저 방문한다고 했으므로 인접 정점들을 정렬해두면 좋겠다!
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GraphSearch_1 {
    // 1260을 인접 행렬로 정렬하는 방식!
    static int N,M,V;
    static int [][] adj; // 인접 행렬
    static boolean[] visit; // 방문 유무
    static StringBuilder sb = new StringBuilder();

    public static void bfs(int start){
        Queue<Integer> needvisit = new LinkedList<>();

        needvisit.add(start);
        visit[start] = true;
        while (!needvisit.isEmpty()){
            int x = needvisit.poll();
            sb.append(x).append(' ');
            for(int y = 1;y<=N;y++){
                if(adj[x][y] == 0) continue;
                if(visit[y] == true) continue;
                needvisit.add(y);
                visit[y] = true;
            }
        }
    }

    public static void dfs(int x){
        visit[x] = true; // x를 방문할 수 있어!
        sb.append(x).append(' ');
        for(int y = 1; y<=N;y++){
            if(adj[x][y] == 0) continue; // 인접한 정점이 아니면 패스

            if(visit[y]) continue; // 이미 방문한 정점이면 패스

            dfs(y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        adj = new int [N+1][N+1];

        for(int i = 0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            adj[first][second] = 1;
            adj[second][first] = 1;
        }
        visit = new boolean[N+1];

        // 깊이 우선 탐색
        dfs(V);
        sb.append('\n');
        // 한번 탐색이 완료되었으므로 다시 false로 바꿔서 방문 가능하다고 표시를 해주자.
        for(int i = 1; i<=N;i++)
            visit[i] = false;

        bfs(V);
        System.out.println(sb.toString());
    }

}
