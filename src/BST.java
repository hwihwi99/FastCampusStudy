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
*
* 노드 삭제 과정이 좀 까다롭다..
*
* 시간 복잡도
* 트리의 높이를 h라고 한다면 O(h)
* n개의 노드를 갖는다면 O(로그 n)
*
* 단점은
* 편향된 이진 탐색 트리가 만들어 질 수 있다는 점
*
* */

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
    // 1. 리프노드라면 ?
    // 그 부모 노드의 next를 끊어버려

    // 2. 삭제할 노드의 자식이 한개라면?
    // 3. 삭제할 노드의 자식이 두개라면?
    // 삭제할 노드의 오른쪽 자식중 가장 작은 값을 삭제할 노드의 부모 노드가 가리키도록 한다.

    // data가 있는 노드를 지우자. 성공 => 참, 실패 => 거짓
    public boolean deleteNode (int data) {
        // 원하는 삭제할 노드를 찾으면 true, 아니면 false
        boolean Searched = false;

        Node currParentNode = rootNode;
        Node currNode = rootNode;

        // 일단 이런 경우부터 처리를 해주고!

        // 코너 케이스 1) Node가 하나도 없을 때
        if (rootNode == null)
            return false;
            // 코너 케이스 2) Node가 단지 하나만 있고 그 노드가 삭제할 노드일 때
        else {
            if (rootNode.value == data && rootNode.left == null && rootNode.right == null) {
                rootNode = null;
                return true;
            }
        }

        // 삭제할 노드를 찾는 중
        while (currNode != null) {
            if (currNode.value == data) {
                // 노드 찾았어!
                Searched = true;
                break;
            } else if (currNode.value > data) {
                currParentNode = currNode;
                currNode = currNode.left;
            } else if (currNode.value < data) {
                currParentNode = currNode;
                currNode = currNode.right;
            }
        }


        if (!Searched) {
            // 삭제할 노드가 없으면 false
            return false;
        }

        // 여기까지 실행되면
        // currNode에 해당 데이터를 가지고 있는 노드,
        // creeParentNode에는 해당 데이터를 가지고 있는 노드의 부모 노드

        // case 1 : 삭제할 노드가 leaf 노드일 경우
        if (currNode.right == null && currNode.left == null){
            //부모의 왼쪽 노드야?
            if(data < currParentNode.value){
                currParentNode.left = null; // 부모 왼쪽 끊어
                currNode = null; // 삭제한 노드도 비워
            }
            //부모의 오른쪽 노드야?
            else if(data > currParentNode.value){
                currParentNode.right = null; // 부모 오른쪽 끊어
                currNode = null; // 삭제한 노드도 비워
            }
        }

        // case 2 : 삭제할 노드가 child Node를 한개 가지고 있을 경우

        // case 2 - 1 : 왼쪽에 child Node가 있을 경우
        else if((currNode.right == null && currNode.left != null)){
            // 부모 노드보다 작은 값이야? => 왼쪽노드야?
            if(currParentNode.value > data ){
                currParentNode.left = currNode.left;
                currNode = null;
            }
            // 부모 노드보다 큰 값이야? => 오른쪽 노드야?
            else if(currParentNode.value < data){
                currParentNode.right = currNode.left;
                currNode = null;
            }
        }
        // case 2 - 2 : 오른쪽에 child Node가 있을 경우
        else if((currNode.right != null && currNode.left == null)){
            // 부모 노드보다 작은 값이야? => 왼쪽노드야?
            if(currParentNode.value > data ){
                currParentNode.left = currNode.right;
                currNode = null;
            }
            // 부모 노드보다 큰 값이야? => 오른쪽 노드야?
            else if(currParentNode.value < data){
                currParentNode.right = currNode.right;
                currNode = null;
            }
        }

        // case 3 : 삭제할 노드의 child 노드가 2개인 경우
        else{
            // case 3-1 : 삭제할 노드가 parent node의 왼쪽에 있을 때
            if(currParentNode.value > data){
                // 현재 노드 기준 오른쪽에서 가장 작을 값을 찾아서
                // 현제 노드와 바꿔치기 한다.
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right;

                // 가장 작은 값 찾는 중
                while(changeNode.left != null){
                    changeParentNode = changeNode;
                    changeNode = changeNode.left;
                }
                // 삭제할 노드의 오른쪽 링크에서 가장 작은 값 찾기 성공

                // case 3-1-1 : 가장 작은 값을 가진 node에게 자식 노드가 없을 때 -> 이러면 그냥 바꾸기
                if(changeNode.right == null){
                    changeParentNode.left = null;
                }
                // case 3-1-2 : 가장 작은 값을 가진 node에게 오른쪽에 childNode가 있을 때
                else{
                    changeParentNode.left = changeNode.right;
                }

                // currParnetNode의 왼쪽 child 노드에 삭제할 노드의 오른쪽 자식 중
                // 가장 작은 값을 가진 change노드 연결
                currParentNode.left = changeNode;

                // currParentNode의 왼쪽 자식 노드가 현재 change노드이다.
                changeNode.left = currNode.left;
                changeNode.right = currNode.right;

                currNode = null;
            }

            // case 3-2 : 삭제할 노드가 parent node의 오른쪽에 있을 때
            else if (currParentNode.value < data){
                // 현재 노드 기준 오른쪽에서 가장 작을 값을 찾아서
                // 현제 노드와 바꿔치기 한다.
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right;

                // 가장 작은 값 찾는 중
                while(changeNode.left != null){
                    changeParentNode = changeNode;
                    changeNode = changeNode.left;
                }
                // 삭제할 노드의 오른쪽 링크에서 가장 작은 값 찾기 성공

                // case 3-2-1 : 가장 작은 값을 가진 node에게 자식 노드가 없을 때 -> 이러면 그냥 바꾸기
                if(changeNode.right == null){
                    changeParentNode.left = null;
                }
                // case 3-2-2 : 가장 작은 값을 가진 node에게 오른쪽에 childNode가 있을 때
                else{
                    changeParentNode.left = changeNode.right;
                }

                currParentNode.right = changeNode;
                changeNode.right = currNode.right;
                changeNode.left = currNode.left;

                currNode = null;

            }
        }
         return true;
    }


    public static void main(String[] args) {
        BST tree = new BST();
        tree.insertNode(10);
        tree.insertNode(15);
        tree.insertNode(13);
        tree.insertNode(11);
        tree.insertNode(14);
        tree.insertNode(18);
        tree.insertNode(16);
        tree.insertNode(19);
        tree.insertNode(17);
        tree.insertNode(7);
        tree.insertNode(8);
        tree.insertNode(6);

        System.out.println(tree.deleteNode(15));
        System.out.println("HEAD: " + tree.rootNode.value);
        System.out.println("HEAD LEFT: " + tree.rootNode.left.value);
        System.out.println("HEAD LEFT LEFT: " + tree.rootNode.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + tree.rootNode.left.right.value);

        System.out.println("HEAD RIGHT: " + tree.rootNode.right.value);
        System.out.println("HEAD RIGHT LEFT: " + tree.rootNode.right.left.value);
        System.out.println("HEAD RIGHT RIGHT: " + tree.rootNode.right.right.value);

        System.out.println("HEAD RIGHT RIGHT LEFT: " + tree.rootNode.right.right.left.value);
        System.out.println("HEAD RIGHT RIGHT RIGHT: " + tree.rootNode.right.right.right.value);

    }
}
