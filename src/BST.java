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

        public Node(int value) {
            this.left = null;
            this.right = null;
            this.value = value;
        }
    }

    public boolean insertNode(int data) {
        // Case 1. 노드가 하나도 없을 때
        if (this.rootNode == null) {
            Node newNode = new Node(data);
            this.rootNode = newNode;
            return true;
        }
        // Case 2. 비어있지 않다면
        else{
            // 트리 탐색을 할 첫 위치를 찾는다 (루트 노드)
            Node tempNode = rootNode;
            while (true) {
                // Case2-1 : 현재 노드의 왼쪽에 노드가 들어가야 할 때
                if (data < tempNode.value) {
                    if (tempNode.left != null) {
                        tempNode = tempNode.left;
                    }else {
                        tempNode.left = new Node(data);
                        break;
                    }
                }

                // Case2-2 : 현재 노드의 오른쪽에 노드가 들어가야 할 때
                else{
                    if (tempNode.right != null) {
                        tempNode = tempNode.right;
                    }else {
                        tempNode.right = new Node(data);
                        break;
                    }
                }
            }
            return true;
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
        while (currentNode != null) {
            if (currentNode.value == data) {
                return currentNode;
            }else if(currentNode.value < data) {
                    currentNode = currentNode.right;
            }else {
                currentNode = currentNode.left;
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
    public boolean deleteNode(int data) {
        boolean searched = false;

        Node currentParnetNode = this.rootNode;
        Node currNode = this.rootNode;

        // 코너 케이스 1 : 노드가 하나도 없을 때
        if (this.rootNode == null) {
            return false;
        }

        // 코너 케이스 2 : 노드가 단지 하나만 있고, 해당 노드가 삭제할 노드일 때
        else {
            if (this.rootNode.right == null && this.rootNode.left == null && this.rootNode.value == data) {
                this.rootNode = null;
                return true;
            }

            while (currNode != null) {
                if (currNode.value == data) {
                    searched = true;
                    break;
                }else if (currNode.value > data) {
                    currentParnetNode = currNode;
                    currNode = currNode.left;
                } else {
                    currentParnetNode = currNode;
                    currNode = currNode.right;
                }
            }

            // 삭제할 노드를 찾았을 때, 못 찾았을 때로 나뉜다.
            if (searched == false) {
                return false;
            }

            // 여기까지 실행하면 currentNode에는 삭제할 노드, currentParentNode에는 삭제할 노드의 부모노드

            // case 1 : 삭제할 노드가 리프노드인 경우
            if (currNode.left == null && currNode.right == null) {
                if(data < currentParnetNode.value) {
                    currentParnetNode.left = null;
                }else {
                    currentParnetNode.right = null;
                }
                return true;
            }
            // case 2 : 삭제할 노드에 자식이 한개인 경우
            // case 2-1 : 왼쪽에 있을 경우
            else if (currNode.left != null && currNode.right == null) {
                if(data < currentParnetNode.value) {
                    currentParnetNode.left = currNode.left;
                }else {
                    currentParnetNode.right = currNode.left;
                }
                return true;
            }
            // case 2-2 : 오른쪽에 있을 경우
            else if (currNode.left == null && currNode.right != null) {
                if(data < currentParnetNode.value) {
                    currentParnetNode.left = currNode.right;
                }else {
                    currentParnetNode.right = currNode.right;
                }
                return true;
            }

            // case 3 : 자식이 두개 다 있을 때
            else {
                // 3-1 : 삭제할 노드가 부모 노드의 왼쪽에 있을 때
                if (data < currentParnetNode.value) {
                    Node tempNode = currNode.right;
                    Node tempParentNode = currNode.right;
                    while (tempNode.left != null) {
                        tempParentNode = tempNode;
                        tempNode = tempNode.left;
                    }

                    // changeNode의 자식이 없을 때
                    if (tempNode.right == null) {
                        tempParentNode.left = null;
                    }
                    // 있을 때
                    else {
                        tempParentNode.left = tempNode.right;
                    }

                    currentParnetNode.left = tempNode;
                    tempNode.left = currNode.left;
                    tempNode.right = currNode.right;
                }
                // 3-2 : 삭제할 노드가 부모 노드의 오른쪽에 있을 때
                else {
                    Node tempNode = currNode.right;
                    Node tempParentNode = currNode.right;
                    while (tempNode.left != null) {
                        tempParentNode = tempNode;
                        tempNode = tempNode.left;
                    }

                    // changeNode의 자식이 없을 때
                    if (tempNode.right == null) {
                        tempParentNode.left = null;
                    }
                    // 있을 때
                    else {
                        tempParentNode.left = tempNode.right;
                    }

                    currentParnetNode.right = tempNode;
                    tempNode.left = currNode.left;
                    tempNode.right = currNode.right;
                }
            }
            return true;
        }
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
