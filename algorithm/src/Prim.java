/**
 * 최소 신장 트리 알고리즘 (둘 다 탐욕 알고리즘을 기반으로!)
 * 1. 크루스칼 알고리즘
 *      전체 간선들을 가중치로 정렬해서 가장 작은 값부터 싸이클이 안생기도록 연결해나가는 방식
 * 2. 프림 알고리즘
 *      시작 정점을 선택하고, 정점에 인접한 간선 중 최소 가중치 간선을 선택하면서 최적의 솔루션을 찾자
 *
 * 이번시간은 프림 알고리즘에 대해서!
 *
 * 구현 방식
 * 1. 임의의 시작 정점 선택, 연결된 노드 집합에 삽입
 * 2. 선택된 정점에 연결된 간선들을 간선 리스트에 삽입
 * 3. 최소 가중치를 가지는 간선부터 추출하기 (최소 힙을 사용해서!)
 * 4. 추출한 간선은 간선 리스트에서 삭제
 * 5. 3~4 반복하기
 *
 * 최소 가중치를 이용하자 -> 최소 힙을 사용하자.
 * hashMap에 특정 키 존재 여부를 확인 -> containsKey() 함수 사용
 *
 * 0. 모든 간선 정보를 저장 (adjacentEdges)
 * 1. 임의의 정점을 선택, 연결된 노드 집합에 삽입 (connectedNodes)
 * 2. 선택된 정점에 연결된 간선들을 간선 리스트 (candidateEdgeList)에 삽입
 * 3. candidateEdgeList에서 최소 가중치를 가지는 간선부터 추출
 *      해당 간선의 인접 정점이 connectedNodes에 들어있다면
 *          -> 패쓰 (사이클을 막기 위해서)
 *      해당 간선의 인접 정점이 connectedNodes에 없다면
 *          -> 해당 간선을 선택하고 그 간선의 정보를 mst에 삽입
 *              인접 정점의 간선들 중, connectedNodes에 없는 노드와 연결된 간선들만 간선 리스트(candidate)에 삽입
 * 4. 선택된 간선은 간선 리스트(candidate)에서 제거
 * 5. 간선 리스트가 빌때까지 3~4번 반복
 *
 * 시간 복잡도
 * 최악의 경우 모든 간선을 다 돌고 최소 힙 구조를 사용하므로 O(ElogE)
 * * */

import java.util.*;

public class Prim {

    // 간선에 대한 클래스
    public static class Edge implements Comparable<Edge>{
        public int weight;
        public String node1;
        public String node2;

        Edge(int weight, String node1, String node2){
            this.weight = weight;
            this.node1 = node1;
            this.node2 = node2;
        }

        public String toString(){
            return "("+this.weight+", "+this.node1+", "+this.node2+")";
        }

        public int compareTo(Edge e){
            return this.weight - e.weight;
        }
    }

    public static ArrayList<Edge> primeFunction(String startNode, ArrayList<Edge> edges){
        ArrayList<Edge> mst = new ArrayList<>(); // 최소 신장 트리 정보 저장

        ArrayList<String> connectedNodes = new ArrayList<>(); // 연결된 정점들의 집합
        // 간선에 연결된 노드들을 표현하자. 각 정점마다 연결되어 있는 간선에 대한 정보가 필요
        // 각 노드와 그 노드에 연결된 간선들을 Arraylist에 저장한다.
        HashMap<String, ArrayList<Edge>> adjacentEdges = new HashMap<>();

        Edge currentEdge;

        // 알고있는 정보를 초기화하자. -> 일단 모든 노드들을 다 가져왔다.
        for(int index = 0; index < edges.size();index++){
            // 각각의 간선 정보를 하나씩 빼오는 중
            currentEdge = edges.get(index);

            if(!adjacentEdges.containsKey(currentEdge.node1)){
                adjacentEdges.put(currentEdge.node1,new ArrayList<>());
            }

            if(!adjacentEdges.containsKey(currentEdge.node2)){
                adjacentEdges.put(currentEdge.node2,new ArrayList<>());
            }
        }

        // 현재 노드의 간선 정보
        ArrayList<Edge> currentEdgeList;

        for(int index=0; index <edges.size();index++){
            currentEdge = edges.get(index);
            currentEdgeList = adjacentEdges.get(currentEdge.node1);
            currentEdgeList.add(new Edge(currentEdge.weight, currentEdge.node1, currentEdge.node2));
            currentEdgeList = adjacentEdges.get(currentEdge.node2);
            currentEdgeList.add(new Edge(currentEdge.weight, currentEdge.node2, currentEdge.node1));
        }

        // 일단 시작 노드를 연결한다.
        connectedNodes.add(startNode);
        ArrayList<Edge> candidateEdgeList; // 후보 간선 리스트
        // 해단 노드에 연결된 간선들이 있다면 그것에 대한 리스트가, 없다면 빈 리스트가 온다.
        candidateEdgeList = adjacentEdges.getOrDefault(startNode,new ArrayList<Edge>());

        // 여기에는 candidate에 간선에서 가중치가 작은 순으로 넣을 수 있다.
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        for(int index = 0; index<candidateEdgeList.size();index++){
            priorityQueue.add(candidateEdgeList.get(index));
        }

        Edge poppedEdge, adjacentNode;
        ArrayList<Edge> adjacentEdgesNodes;
        while(!priorityQueue.isEmpty()){
            poppedEdge = priorityQueue.poll();
            if(!connectedNodes.contains(poppedEdge.node2)){
                // 해당 edge를 mst에 추가
                connectedNodes.add(poppedEdge.node2);
                mst.add(new Edge(poppedEdge.weight, poppedEdge.node1, poppedEdge.node2));

                adjacentEdgesNodes = adjacentEdges.getOrDefault(poppedEdge.node2, new ArrayList<Edge>());
                for(int index =0; index< adjacentEdgesNodes.size();index++){
                    adjacentNode = adjacentEdgesNodes.get(index);
                    if(!connectedNodes.contains(adjacentNode.node2)){
                        priorityQueue.add(adjacentNode);
                    }
                }

            }
        }

        return mst;
    }

    public static void main(String[] args) {
        // 그래프에 대한 정보를 ArrayList로!
        ArrayList<Edge> myedges = new ArrayList<>();
        myedges.add(new Edge(7,"A","B"));
        myedges.add(new Edge(5,"A","D"));
        myedges.add(new Edge(7,"B","A"));
        myedges.add(new Edge(9,"B","D"));
        myedges.add(new Edge(8,"B","C"));
        myedges.add(new Edge(7,"B","E"));
        myedges.add(new Edge(8,"C","D"));
        myedges.add(new Edge(5,"C","E"));
        myedges.add(new Edge(5,"D","A"));
        myedges.add(new Edge(9,"D","B"));
        myedges.add(new Edge(7,"D","E"));
        myedges.add(new Edge(6,"D","F"));
        myedges.add(new Edge(7,"E","B"));
        myedges.add(new Edge(5,"E","C"));
        myedges.add(new Edge(7,"E","D"));
        myedges.add(new Edge(8,"E","F"));
        myedges.add(new Edge(9,"E","G"));
        myedges.add(new Edge(6,"F","D"));
        myedges.add(new Edge(8,"F","E"));
        myedges.add(new Edge(11,"F","G"));
        myedges.add(new Edge(11,"G","F"));
        myedges.add(new Edge(9,"G","E"));

        System.out.println(primeFunction("A",myedges));


    }
}
