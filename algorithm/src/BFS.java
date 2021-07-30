/*
* 그래프 탐색 알고리즘
* BFS, DFS가 있다.
*
* 너비 우선 탐색 (breadth-First Search)
* 정점과 같은 레벨에 있는 노드들을 먼저 탐색하는 방식
*
* 1. 그래프를 자료구조로 만드는 방법
* Java Collection Framework에서 제공하는 HaspMap, Arraylist를 이용해서 그래프를 표현할 수 있다.
* 인접 리스트를 사용한다.
* cf> HashMap은 키와 값을 저장하는 자료구조로, 내부에서 함수로 키에 대한 값을 빠르게 검색 가능
*
* 2. 2개의 큐를 이용해서 visited(방문함) needvisit(방문이 필요함)에 해당하는 노드들을 저장하자.
*
* 시작 노드를 우선 결정한다.
* 그리고 visited에 들어가면 관렴된 인접 리스트들을 needvisit 큐에 저장한다.
*
* 시간 복잡도
* 노드의 수 : V, 간선의 수 : E
* 시간 복잡도 => O(V+E)
*/

import java.util.*;

public class BFS {
    public static Queue<String> bfs(HashMap<String,ArrayList<String>> graph,String startNode){

        Queue<String> visited = new LinkedList<>();
        Queue<String> needVisit = new LinkedList<>();

        // 탐색의 시작 위치를 알려주어야한다.
        needVisit.offer(startNode);

        // 만약 방문해야되는 큐가 비지 않았다면
        while(needVisit.size()>0){
            //방문이 필요한 노드의 맨 앞의 값을 일단 가져온다.
            String node = needVisit.poll();

            // 만약 이미 방문한 큐에 해당 노드가 들어있지 않다면
            if(!visited.contains(node)){
                // 노드를 방문한다.
                visited.offer(node);
                // 노드를 방문하면 node의 인접 리스트들을 needvisit 큐에 넣어준다.
                needVisit.addAll(graph.get(node));
            }
        }
        // 이걸 리턴하면 최종적으로 방문한 순서가 리턴이 될 것이다.
        return visited;

    }
    public static void main(String[] args) {

        // 각 KEY 값에 대해서 인접한 노드들을 arraylist에 모두 저장한다.
        HashMap<String, ArrayList<String>> graph = new HashMap<>();
        graph.put("A",new ArrayList<String>(Arrays.asList("B","C")));
        graph.put("B",new ArrayList<String>(Arrays.asList("A","D")));
        graph.put("C",new ArrayList<String>(Arrays.asList("A","G","H","I")));
        graph.put("D",new ArrayList<String>(Arrays.asList("B","E","F")));
        graph.put("E",new ArrayList<String>(Arrays.asList("D")));
        graph.put("F",new ArrayList<String>(Arrays.asList("D")));
        graph.put("G",new ArrayList<String>(Arrays.asList("C")));
        graph.put("H",new ArrayList<String>(Arrays.asList("C")));
        graph.put("I",new ArrayList<String>(Arrays.asList("C","J")));
        graph.put("J",new ArrayList<String>(Arrays.asList("I")));

        Queue<String> bfsans = new LinkedList<>();
        bfsans = bfs(graph,"A");
        System.out.println(bfsans.size());
        while(!bfsans.isEmpty()){
            System.out.println(bfsans.poll());
        }

    }


}
