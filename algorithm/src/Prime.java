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
 * */

import java.util.*;

public class Prime {

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

        ArrayList<String> connectedNode = new ArrayList<>();
        ArrayList<Edge> mst = new ArrayList<>(); // 최소 신장 트리
        ArrayList<Edge> currentEdgeList,candidateEdgeList,adjacentEdgesNodes;
        HashMap<String,ArrayList<Edge>> adjacentEdges = new HashMap<>(); // 인접한 간선들의 모음
        Edge currentEdge;
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        // 모든 간선을 순회하면서! -> 일단 노드에 없으면 추가하자
        for(int i = 0;i<edges.size();i++){
            currentEdge = edges.get(i); // 현재의 간선
            if(!adjacentEdges.containsKey(currentEdge.node1)){
                adjacentEdges.put(currentEdge.node1,new ArrayList<Edge>());
            }
            if(!adjacentEdges.containsKey(currentEdge.node2)){
                adjacentEdges.put(currentEdge.node2,new ArrayList<Edge>());
            }
        }

        // 각각 인접해있는 노드를 리스트에 담기
        for(int i = 0; i<edges.size();i++){
            currentEdge = edges.get(i);
            currentEdgeList = adjacentEdges.get(currentEdge.node1);
            currentEdgeList.add(new Edge(currentEdge.weight, currentEdge.node1, currentEdge.node2));

            currentEdgeList = adjacentEdges.get(currentEdge.node2);
            currentEdgeList.add(new Edge(currentEdge.weight, currentEdge.node2, currentEdge.node1));

        }

        // 시작 노드
        connectedNode.add(startNode);

        // 후보 엣지 리스트들 -> 해당 노드의 인접한 간선들
        // getOrDefault : 찾는 키가 존재하면 키값을 반환하고 아니면 기본값을 반환하라
        candidateEdgeList = adjacentEdges.getOrDefault(startNode,new ArrayList<Edge>());

        for(int i = 0; i<candidateEdgeList.size();i++){
            priorityQueue.add(candidateEdgeList.get(i));
        }

        // 큐가 비어있을 때 까지
        Edge poppedEdge,adjacentEdgesNode;
        while (priorityQueue.size()>0){
            poppedEdge = priorityQueue.poll();

            if(!connectedNode.contains(poppedEdge.node2)){
                // 해당 edge를 mst에 추가
                connectedNode.add(poppedEdge.node2);
                mst.add(new Edge(poppedEdge.weight,poppedEdge.node1, poppedEdge.node2));

                adjacentEdgesNodes = adjacentEdges.getOrDefault(poppedEdge.node2,new ArrayList<Edge>());

                for(int i = 0; i<adjacentEdgesNodes.size();i++){
                    adjacentEdgesNode = adjacentEdgesNodes.get(i);
                    if(!connectedNode.contains(adjacentEdgesNode.node2)){
                        priorityQueue.add(adjacentEdgesNode);
                    }
                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {
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
