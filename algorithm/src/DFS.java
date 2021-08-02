/*
* 깊이 우선 탐색 (depth first search)
* 정점의 자식들을 먼저 탐색하는 경우
* -> 한 노드의 자식을 타고 끝까지 순회한 후, 다시 돌아와서 다른 형제들의 자식을 타고 내려가며 순회한다.
*
* 그래프는 일단 인접 리스트로 표현을 하자.
*
* BFS는 둘다 큐였지!
*
* DFS는
 needVisit 스택 : 방문이 필요한 노드들을 스택으로 관리하자.
* visited 큐 : 방문한 노드들을 큐로 관리하자.
*
* 시간 복잡도
* 노드의 수 : V, 간선의 수 : E
* 시간 복잡도 => O(V+E)
* */

import java.util.*;

public class DFS {
    public static Queue<String> dfs(HashMap<String,ArrayList<String>> graph, String startNode){
        Stack<String> needVisited = new Stack<>();
        Queue<String> visited = new LinkedList<>();

        // 일단 처음 방문할 노드를 넣는다.
        needVisited.push(startNode);
        while (!needVisited.isEmpty()){
            String currentNode = needVisited.pop();

            if(!visited.contains(currentNode)){
                visited.offer(currentNode);
                needVisited.addAll(graph.get(currentNode));
            }
        }
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

        Queue<String> ans = dfs(graph,"A");
        for(String str : ans){
            System.out.print(str+" ");
        }
    }
}
