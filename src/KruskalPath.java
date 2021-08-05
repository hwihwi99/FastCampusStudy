/**
 * 최소 신장 트리 (Spanning tree)
 * 신장 트리란 => 전체 노드를 연결하되 사이클이 없어야한다.
 *
 * 최소 신장 트리란 (최단 경로, 길찾기에서 주로 사용)
 * => 가중치가 있는 (무방향)그래프에서 전체 노드를 연결하고, 사이클이 없을 때 가중치 합이 최소일 때
 *
 * 최소 신장 트리 알고리즘
 * 1. 크루스칼 알고리즘
 * 2. 프림 알고리즘즘
 *
 * 크루스칼 알고리즘 (탐욕 알고리즘을 기초로 한다)
 * 1. 모든 정점을 연결이 안된 독립적인 집합으로 만든다.
 * 2. 모든 간선을 비용을 기준으로 정렬하고, 비용이 작은 간선부터 양 끝 두 정점을 비교한다.(탐욕 알고리즘의 아이디어)
 * 3. 두 정점의 최상위 정점을 확인하고 다르면 연결 (즉, 만약 이걸 연결해서 사이클이 생기면 연결하지 않음)
 *
 * 자, 그렇다면 간선을 이을 때, 사이클이 생기는지 안생기는지 판단을 해야한다.
 * -> 유니온 - 파인드 알고리즘 사용!!!
 *
 * 유니온 - 파인드 알고리즘
 * disjoint set(서로 중복되지 않은 부분 집합으로)을 유지하기 위해서
 *
 * 1. 초기화 : n개의 원소가 개별 집합으로 초기화한다.
 * 2. Union : 두 개별 집합을 하나의 집합으로 합친다. -> 두 트리를 하나의 트리로 만든다.
 * 3. Find : 두 노드가 같은 그래프로 속하는지 파악 -> 두 노드의 루트노드가 같으지 다른지 판단
 *
 * UF 알고리즘을 하다보면 편향되서 시간복잡도가 커지는 경우가 생긴다. 이때 해결방법 2가지가 있다.
 * union-by-rank -> 트리의 높이를 작게 유지한다!
 * 각 트리의 높이(rank)를 기억해서 높이가 작은 트리를 높이가 큰 트리에다가 붙인다.
 * 만약 높이가 같다면 둘 중 하나의 트리의 높이를 높이고 나머지 하나를 거기다가 합친다.
 *
 * path compression
 * find를 통해서 루트노드로 가기까지 지나간 노드들을 루트노드에 연결해서 사용한다.
 *
 * 시간복잡도
 * 1. 초기화하기 -> 반복문, 노드의 갯수만큼 반복한다. O(노드 갯수)
 * 2. 정렬하기 -> 퀵, 병합 정렬을 사용한다면 간선이 E이기 때문에 O(ElogE)
 * 3. union을 이용해서 정점 합치기 -> 간선수 만큼 돌림 O(E)
 * UF알고리즘은 O(1)이라서
 *
 * 최종적으로 (큰거빼고 다 없어져!)
 * O(V) + O(ElogE) + O(E) => O(ElogE)
*/
import java.util.*;
public class KruskalPath {

    // 내 노드의 부모 노드를 리턴하는 해쉬 맵을 만들자.
    public static HashMap<String, String> parent = new HashMap<>();

    // 내 노드의 랭크 정보를 관리하기 위해서서
    public static HashMap<String, Integer> rank = new HashMap<>();

    // Edge에 대한 클래스
    public static class Edge implements Comparable<Edge>{
        int weight;
        String nodeV;
        String nodeU;

        Edge(int weight, String nodeV, String nodeU){
            this.weight = weight;
            this.nodeV = nodeV;
            this.nodeU = nodeU;
        }

        public String toString(){
            return "("+this.weight+", "+this.nodeV+", "+this.nodeU+")";
        }

        @Override
        public int compareTo(Edge edge){
            return this.weight - edge.weight;
        }
    }

    public static String find(String node){
        // pathCompression 기법 -> 내 부모 노드가 루트노드가 아니면
        // 루트노드를 찾아서 거기에 연결하는 기법
        if(parent.get(node)!=node){
            parent.put(node,find(parent.get(node)));
        }
        return parent.get(node);
    }

    public static void union(String nodeV, String nodeU){
        String root1 = find(nodeV);
        String root2 = find(nodeU);

        //union-by-rank 기법

        //root1의 높이가 더 크므로 root2를 root1에 연결해준다.
        if(rank.get(root1)>rank.get(root2)){
            parent.put(root2,root1);
        }
        //root2의 높이가 더 크거나 같으므로 root1을 root2에 연결해준다.
        else {
            parent.put(root1, root2);
            // 만약 높이가 같다면
            // 루트 1의 부모를 루트 2에 연결시켰기 때문에 루트 2의 높이를 키워준다.
            if(rank.get(root1)==rank.get(root2)){
                rank.put(root2,rank.get(root2)+1);
            }
        }
    }

    // 초기화를 위한 함수
    public static void makeSet(String node){
        parent.put(node,node); // 루트 노드로 만들고
        rank.put(node,0); // 랭크를 0으로 만든다.
    }

    public static ArrayList<Edge> kruskal(ArrayList<String> vertices, ArrayList<Edge> edges){
        ArrayList<Edge> mst = new ArrayList<>();
        Edge currentEdge;

        // 초기화 메소드 호출
       for(int i = 0;i< vertices.size();i++){
            makeSet((vertices.get(i)));
        }

       // 간선의 가중치를 무게로 정렬한다.
       Collections.sort(edges);

       // 정렬된 edge들을 하나씩 순회해서 가져온다.
       for(int i = 0; i< edges.size();i++){
           currentEdge = edges.get(i);
           // 부모노드가 다르다면 싸이클이 없으므로 합쳐라!
            if(find(currentEdge.nodeV) != find(currentEdge.nodeU)){
                union(currentEdge.nodeV,currentEdge.nodeU);
                mst.add(currentEdge); // 해당 간선 정보를 mst에 추가한다.
            }
       }
       return mst;
    }

    public static void main(String[] args) {

        ArrayList<String> vertices = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G"));

        ArrayList<Edge> edges = new ArrayList<>();
        edges.add(new Edge(7,"A","B"));
        edges.add(new Edge(5,"A","D"));
        edges.add(new Edge(7,"B","A"));
        edges.add(new Edge(9,"B","D"));
        edges.add(new Edge(8,"B","C"));
        edges.add(new Edge(7,"B","E"));
        edges.add(new Edge(8,"C","D"));
        edges.add(new Edge(5,"C","E"));
        edges.add(new Edge(5,"D","A"));
        edges.add(new Edge(9,"D","B"));
        edges.add(new Edge(7,"D","E"));
        edges.add(new Edge(6,"D","F"));
        edges.add(new Edge(7,"E","B"));
        edges.add(new Edge(5,"E","C"));
        edges.add(new Edge(7,"E","D"));
        edges.add(new Edge(8,"E","F"));
        edges.add(new Edge(9,"E","G"));
        edges.add(new Edge(6,"F","D"));
        edges.add(new Edge(8,"F","E"));
        edges.add(new Edge(11,"F","G"));
        edges.add(new Edge(11,"G","F"));
        edges.add(new Edge(9,"G","E"));

        System.out.println(vertices);
        System.out.println(edges);
        System.out.println();
        System.out.println(kruskal(vertices,edges));
        }
}
