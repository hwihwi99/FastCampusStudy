
/**
 * 개선된 프림 알고리즘
 * edge를 기준으로 하는 것이 아닌 node를 기준으로 보자!
 *
 * 초기화
 * 정점 : key-value 구조를 만들어놓고, 특정 정점의 값을 0, 나머지는 무한대로 설정
 * 해당 키 값을 우선순위 큐 (최소힙)안에 넣음
 *
 * key값이 적은 정점을 추출 -> 해당 정점과 비교후 key값을 갱신하고 우선순위 큐에 올린다.
 *
 * --> 다익스트라와 같은 느낌으로!
 *
 * 시간복잡도
 * 최초 키 생성 : O(V)
 * while 구문과 keys.poll() : O(VlogV)
 * for문 : O(ElogV)
 * O(V + VlogV + ElogV)
 * V^2 = E가 될 수 있다.
 * 그래서 간단하게 O(ElogV)가 될 수 있다.
 *   */

import java.util.*;

public class Prim_imporved {
    public static class Path{
        // 전체 최소신장트리를 이해한다.
        public String node1;
        public String node2;
        public int weight;

        public Path(String node1, String node2, int weight){
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        public String toString(){
            return "("+this.node1+","+this.node2+","+this.weight+")";
        }
    }
    public static class Edge implements Comparable<Edge>{
        // 이번 엣지 클래스에서는 어디와 연결되어있는지와 가중치만! 누가 시작점인지는 안알려준다!
        public String node;
        public int weight;

        public Edge(String node, int weight){
            this.node = node;
            this.weight = weight;
        }

        public String toString(){
            return "("+this.node+","+this.weight+")";
        }

        @Override
        public int compareTo(Edge e){
            return this.weight-e.weight;
        }
    }
    
    public static ArrayList<Path> improvedPrime(HashMap<String, HashMap<String,Integer>> graph, String startNode){
        ArrayList<Path> mst = new ArrayList<>();
        HashMap<String,String> mstPath = new HashMap<>(); // 어느 노드에서 어느 노드로 연결되는 지에 대한 정보를 담고 있는 공간

        PriorityQueue<Edge> keys = new PriorityQueue<>();
        HashMap<String,Edge> keysObjects = new HashMap<>(); // 각각의 노드마다 연결되어 있는 간선들
        Edge edgeObject;

        // 일단 그래프의 모든 키값을 가져오자
        for(String key : graph.keySet()){
            if(key == startNode){
                edgeObject = new Edge(key,0);
                mstPath.put(key,key); // 각각의 노드가 어느 노드에 연결되어있는지!
                // 초기에는 그런게 없으니깐 나 자신이랑 연결시켜놓는다.
            }else{
                edgeObject = new Edge(key,Integer.MAX_VALUE);
                mstPath.put(key,null);// 각각의 노드가 어느 노드에 연결되어있는지!
                // 초기에는 그런게 없으니깐 null로 연결
            }
            keys.add(edgeObject); // 각각의 노드와 거리 (현재는 0또는 최댓값)
            keysObjects.put(key,edgeObject);
        }

        Edge poppedEdge,linkedEdge;
        int totalWeight=0;
        HashMap<String,Integer> linkedEdges; // 인접 간선 노드들이 된다.
        while (!keys.isEmpty()){
            poppedEdge = keys.poll();
            keysObjects.remove(poppedEdge.node);
            mst.add(new Path(mstPath.get(poppedEdge.node),poppedEdge.node,poppedEdge.weight));
            totalWeight += poppedEdge.weight;

            linkedEdges = graph.get(poppedEdge.node);

            for(String adjacent : linkedEdges.keySet()){
                if(keysObjects.containsKey(adjacent)){
                    linkedEdge = keysObjects.get(adjacent);

                    if(linkedEdges.get(adjacent)<linkedEdge.weight){
                        linkedEdge.weight = linkedEdges.get(adjacent);
                        mstPath.put(adjacent, poppedEdge.node);

                        keys.remove(linkedEdge);
                        keys.add(linkedEdge);
                    }
                }
            }

        }
        System.out.println(totalWeight);
        return mst;
    }
    
    public static void main(String[] args) {

        // A라는 키에 연결되어 있는 다른 노드와 가중치에 대한 정보를 또 다른 hashmap으로 연결하자.
        HashMap<String, HashMap<String,Integer>> mygraph = new HashMap<>();

        HashMap<String, Integer> edges;
        edges = new HashMap<>();
        edges.put("B",7);
        edges.put("D",5);
        mygraph.put("A",edges);

        edges = new HashMap<>();
        edges.put("A",7);
        edges.put("C",8);
        edges.put("D",9);
        edges.put("E",7);
        mygraph.put("B",edges);

        edges = new HashMap<>();
        edges.put("B",8);
        edges.put("E",5);
        mygraph.put("C",edges);

        edges = new HashMap<>();
        edges.put("A",5);
        edges.put("B",9);
        edges.put("E",7);
        edges.put("F",6);
        mygraph.put("D",edges);

        edges = new HashMap<>();
        edges.put("B",7);
        edges.put("C",5);
        edges.put("D",7);
        edges.put("F",8);
        edges.put("G",9);;
        mygraph.put("E",edges);

        edges = new HashMap<>();
        edges.put("D",6);
        edges.put("E",8);
        edges.put("G",11);
        mygraph.put("F",edges);

        edges = new HashMap<>();
        edges.put("E",9);
        edges.put("F",11);
        mygraph.put("G",edges);

        System.out.println(improvedPrime(mygraph,"A"));
    }
}
