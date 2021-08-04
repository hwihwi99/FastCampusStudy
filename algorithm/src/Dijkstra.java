/*
* 최단 경로 문제
* 두 노드를 잇는 가장 짧은 경로를 찾는 문제
* 가중치 그래프에서 간선의 가중치 합이 최소가 되도록 하는 경로 찾기
*
* 최단 경로 문제 종류
* 1. 단일 출발 최단 경로 문제
*      특정 노드에서 출발해서 그래프 내의 모든 다른 노드에 도착하는 가장 짧은 경로를 찾는 문제
* 2. 단일 도착 최단 경로 문제
*      모든 노드들로 부터 출방해서 특정 노드로 도착하는 가장 짧은 경로 찾기
* 3. 단일 쌍 최단 경로 문제
*      주어진 두 노드간에 최단 경로를 찾는 문제
* 4. 전체 쌍 최단 경로 문제
*      그래프 내의 모든 노드 쌍 사이에 대한 최단 경로를 찾는 문제
*
* 최단 경로 알고리즘 - 다익스트라 알고리즘
* 1번. 단일 출발 최단 경로 문제에 맞는 알고리즘
*
* 다익스트라 알고리즘 로직
* 우선순위 큐는 최소힙을 이용해서 가장 짧은 거리를 가진 노드 정보를 먼저 꺼낸다.
* (step1)
* 첫 정점을 기준으로 거리에 대한 정보를 배열로 만든다.
* 초기 첫 정점은 0 나머지는 inf로 무한대의 값을 넣는다.
* 우선순위 큐에 (첫 정점, 0)을 먼저 넣는다.
*
* (step2)
* 우선순위 큘에서 첫 정점이 꺼내진다.
* 그 정점에서 인접한 노드들에 대한 각각의 가중치 값을 계산하고
* 현재 배열에 저장되어 있는 첫 정점에서 각 정점까지의 거리를 비교한다.
* 값이 더 작은 노드의 경로가 발견된다면 업데이트하고 그 업데이트 한 값도 우선순위 쿵[ 넣는다.
*
* (step3)
* 우선순위 큐에서 꺼낼 노드가 없을 때까지 반복한다.
*
* 시간 복잡도 _ 다익스트라
* 과정 1 : 각 노드마다 인접한 간선들을 모두 검사하는 과정
*   각 노드는 최대 한번씩 방문하기 때문에, 간선은 최대 한번씩 검사하게 된다.
*   각 노드마다 인접한 간선들을 모두 검사하는 과정 O(E)
*
* 과정 2 : 우선순위 큐에 노드/거리 정보를 넣고 삭제하는 과정
*   우선순위 큐에 최대 E개의 노드가 추가될 수 있고 우선순위 큐를 유지하는데는 O(logE)만큼의 시간이 필요하다.
*   즉 O(ElogE)
*
* 과정 1 + 과정 2 = O(E) + O(ElogE) => O(ElogE)
*
*
* */
import java.util.*;

public class Dijkstra {

    public static class Edge implements Comparable<Edge>{
        private int distance;
        private String vertex;

        public Edge(int distance, String vertex){
            this.distance = distance;
            this.vertex = vertex;
        }

        public String toString(){
            return "vertex : "+this.vertex+", distance : "+this.distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance-o.distance; // 최소 힙 구하기
            //return o.distance-this.distance; 최대 힙 구하기
        }
    }


    public static HashMap<String,Integer> dijkstra(HashMap<String,ArrayList<Edge>> graph, String start){

        Edge edgeNode,adjacentNode;
        int currentDistance, weight;
        String currentNode,adjacent;
        ArrayList<Edge> nodeList;

        HashMap<String,Integer> distances = new HashMap<>(); // 짧은 거리 정보가 있는 배열을 만드는 중
        for(String key : graph.keySet()){
            distances.put(key,Integer.MAX_VALUE); // 가장 최댓값으로 넣어보기
        }
        distances.put(start,0);

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(distances.get(start),start));

        while (!priorityQueue.isEmpty()){
            edgeNode = priorityQueue.poll();
            currentDistance = edgeNode.distance;
            currentNode = edgeNode.vertex;

            // 만약 우선순위의 큐 값이 이미 최솟값보다 더 크다면? -> 무시하고 다음으로!
            if(currentDistance > distances.get(currentNode)){
                continue;
            }

            nodeList = graph.get(currentNode);
            for(int i = 0; i<nodeList.size();i++){
                adjacentNode = nodeList.get(i);
                adjacent = adjacentNode.vertex;
                weight = adjacentNode.distance;

                int tempDistance = currentDistance+weight;
                if(tempDistance < distances.get(adjacent)){
                    distances.put(adjacent,tempDistance);
                    priorityQueue.add(new Edge(tempDistance,adjacent));
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        // 우선순위 큐를 사용할 수 있는 클래스!(최소 힙, 최대 힙)
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        HashMap<String, ArrayList<Edge>> graph = new HashMap<>();
        graph.put("A",new ArrayList<Edge>(Arrays.asList(new Edge(8,"B"),new Edge(1,"C"),new Edge(2,"D"))));
        graph.put("B",new ArrayList<Edge>());
        graph.put("C",new ArrayList<Edge>(Arrays.asList(new Edge(5,"B"),new Edge(2,"D"))));
        graph.put("D",new ArrayList<Edge>(Arrays.asList(new Edge(3,"E"),new Edge(5,"F"))));
        graph.put("E",new ArrayList<Edge>(Arrays.asList(new Edge(1,"F"))));
        graph.put("F",new ArrayList<Edge>(Arrays.asList(new Edge(5,"A"))));

        System.out.println(dijkstra(graph,"A"));
        //dijkstra(graph,"A");
    }
}
