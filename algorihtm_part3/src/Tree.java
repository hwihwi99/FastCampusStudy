/**
 * 트리 (그래프의 특수한 형태)
 * 아래 조건 중 2개 이상 만족해야 함
 *
 * 1. 모두가 연결되어 있는 그래프
 * 2. 사이클이 존재하지 않는 그래프
 * 3. 정점의 갯수 = 간선의 갯수 + 1
 *
 * 트리의 용어
 * Node : 각 정점들
 * Root : 근간이 되는 최조상노드
 * Depth : 깊이.. 루트노드가 0부터 1씩 증가 (1부터 시작해도 무방)
 * Parent, child, Ancestor(조상, 자신과 root 사이의 관계), Sibling
 * LeafNode : 종단노드 , 자식이 없는 노드
 *
 * 트리 문제의 키워드!
 * 사이클이 없고, 모든 정점 사이에는 이들을 잇는 경로가 있다.
 * 마을과 마을사이를 잇는 길은 (마을갯수 - 1)만큼이 있다 // 3번 조건
 *
 * 트리는 인접 리스트를 이용해서 표현합니다.
 *
 * 11725번 문제
 * 인접 리스트와 dfs 혹은 bfs 방법을 사용햐서 풀면 될 것 같아요
 * 트리는 dfs를 사용해서 푸는 것이 더 좋다.
 *
 * 1991 6539 15900 20364 3584 1240 9489
 *
 * 1068 단말노드의 갯수
 * **중요**
 *
 * 1. 정점 x 가 지워진다.
 *      해당 정점과 연결된 자식노드들은 삭제를 한다.
 * 2. 트리의 단말 노드의 갯수
 *      1) 탐색 버전
 *      dfs, bfs를 사용해서 자식이 더 없는지 확인
 *
 *      2) 서브트리를 이용해서
 *      큰 문제와 작은 문제로 나눈다.
 *      큰 문제 : 트리 안에 있는 단말 노드의 갯수
 *      작은 문제 : 루트의 자식 노드들의 서브트리안에 있는 단말 노드의 갯수
 *
 *      각 서브트리들이 단말 노드 갯수를 카운트해서 알려준다.
 *
 *      leaf 배열을 만들어서
 *      leaf[x] => x를 루트로 하는 서브트리에 있는 단말 노드의 갯수
 *      단말 노드의 경우 leaf[x] = 1
 *
 *      둘을 합해서 dfs로 호출을 하고 그 서브 트리들이 다 합해주면서 답을 다 더해줍니다!
 *
 *
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Tree {
    static HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
    static int[] parents;

    public static void dfs(int x, int parent){ // 특정 노드와 그 노드의 부모를 입력
        parents = new int[hashMap.size()+1];
        for(int y : hashMap.get(x)){
            if(y == parent) continue; // 만약 내 부모와 같다면 무시해
            parents[y] = x;
            dfs(y,x);
        }
    }
    public static void pro(){
        dfs(1,-1);
        for(int i = 2; i<= parents.length;i++){
            System.out.println(parents[i]);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int num = Integer.parseInt(br.readLine());
        for(int i = 0; i<num;i++){
            hashMap.put(i+1,new ArrayList<>());
        }

        for(int i = 0; i<num;i++){
            st = new StringTokenizer(br.readLine()," ");
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            hashMap.get(first).add(second);
            hashMap.get(second).add(first);
        }
        pro();
    }
}
