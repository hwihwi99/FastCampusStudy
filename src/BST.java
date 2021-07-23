/*
* 트리
* Node와 Branch를 이용해서 사이클을 이루지 않도록 구성한 데이터 구조
*
* 트리는 언제 사용?
* 이진 트리 구조 형태를 많이 쓰고, 탐색, 검색 알고리즘 구현을 위해 많이 사용된다.
*
* 트리 관련 용어
* 노드 : 데이터를 저장하는 공간 , 데이터와
* 연결된 다른 노드에 대한 branch 정보도 포함한다. (왼쪽쪽오른쪽 노드)
* leaf 노드, terminal 노드 : 자식이 없는 노드
* 부모노드 , 자식노드 -> 이건 알지!
*
*
* 이진 트리 : 노드의 최대 링크가 2개
*
* 이진 탐색 트리 :
* 루트 노드를 기준으로
* 왼쪽 : 작은 값, 오른쪽 : 큰 값
* -> 이건 들어오는 키 값에 따라서 효율성이 많이 떨어진다.. -> 그래서 이거 개선한게 레드 블랙 트리리
*/

public class BST {

    public Node rootNode;

    public class Node{
        Node left;
        Node right;
        int value;
        public Node(int data){
            this.value = data;
            this.left = null;
            this.right = null;
        }
    }

    public void insertNode(int data){
        // case 1 )트리가 비어있을 때
        if(rootNode == null){
            this.rootNode = new Node(data);
        }
        // case 2) 노드가 하나이상 들어가 있을 때
        else{
            Node currentNode = rootNode;
            while (true){
                //case 2-1) : 현재 노드의 왼쪽에 노드가 들어가야 할 때
                if(data<currentNode.value){
                    // 현재 노드의 왼쪽이 있다면 다음 노드로 이동 후 다시 검사
                    if(currentNode.left != null){
                        currentNode = currentNode.left;
                    }
                    // 왼쪽 노드가 없다면? => 왼쪽에 생성
                    else{
                        currentNode.left = new Node(data);
                        break;
                    }
                }
                //case 2-2) : 현재 노드의 오른쪽에 들어가야 할 떄
                else if(data>currentNode.value){
                    // 현재 노드의 오른쪽이 있다면 다음 노드로 이동 후 다시 검사
                    if(currentNode.right != null){
                        currentNode = currentNode.right;
                    }
                    // 오른쪽 노드가 없다면? => 오른쪽에 생성
                    else {
                        currentNode.right = new Node(data);
                        break;
                    }
                }
                // case 3 ) 현재 노드와 값이 같을 때는 그냥 값 바꿔치기 -> 사실 같은 역할,, 해줄필요 없어
                else{
                    currentNode.value = data;
                }
            }
        }
    }

    // 해당 데이터 찾기
    public Node search(int data){
        //case 1) Node가 하나도 없을 때
        if(rootNode == null){
            return null;
        }
        //case 2) Node가 하나 이상 있을 때
        Node currentNode = rootNode;
        while (currentNode != null){
            // 만약 같다면 값을 찾았다 -> 성공
            if(currentNode.value == data){
                return currentNode;
            }
            // 만약 작으면 왼쪽으로!
            else if(data<currentNode.value){
                currentNode = currentNode.left;
            }
            // 크다면 오룬쪽으로!
            else if(data > currentNode.value){
                currentNode = currentNode.right;
            }
        }
        return null;
    }

    // 특정 데이터의 노드 삭제하기
    // 1. 리프노드라면 ? 그 부모 노드의 next를 끊어버려
    // 2. 삭제할 노드의 자식이 한개라면?
    // 3. 삭제할 노드의 자식이 두개라면?
    // 구현 방식!
    // 삭제할 노드의 오른쪽 자식중 가장 작은 값을 삭제할 노드의 부모 노드가 가리키도록 한다.
    public Node deleteNode (int data){
        return null;
    }


    public static void main(String[] args) {
        BST tree = new BST();
        tree.insertNode(5);
        tree.insertNode(3);
        tree.insertNode(2);
        tree.insertNode(7);
        tree.insertNode(6);
        System.out.println(tree.search(9));
        System.out.println(tree.search(7).value);
    }
}
