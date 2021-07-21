import javax.sound.midi.SysexMessage;

/*
* 더블 링크드 리스트
* (== 이중 연결 리스트)
* 장점
* 양방향으로 연결되어  노드 탐색이 양쪽으로 다 가능하다.
* */
class Node<T>{
    T data;
    Node<T> prev = null;
    Node<T> next = null;
    public Node(T data){
        this.data = data;
    }
}
public class DoublyLinkedList<T> {
    public Node<T> head = null;
    public Node<T> tail = null;

    // 맨 뒤에 값 추가
    public void addNode(T data){
        Node<T> newNode = new Node<>(data);

        if(this.head == null){
            head = newNode;
            tail = head;
        }else{
            Node<T> node = head;
            while(node.next != null){
                node = node.next;
            }
            node.next = newNode;
            newNode.prev = node;
            tail = newNode;
        }
    }

    // tail부터 특정 값이 있는 노드 찾기
    public Node<T> searchTail(T isData){
        if(tail == null)
            return null;
        Node<T> temp = tail;
        while(temp.prev != null){
            if(temp.data == isData){
                return temp;
            }
            temp = temp.prev;
        }
        return null;
    }

    // head부터 특정 값이 있는 노드 찾기
    public Node<T> searchHead(T isData){
        if(head==null)
            return null;
        Node<T> temp = head;
        while(temp != null){
            if(temp.data == isData){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // 임의의 노드 앞에 노드를 추가하는 메서드
    // isData를 갖는 노드에 앞에 새로운 노드 삽입
    public void addInside (T data, T isData){

        //헤드가 비어있을 때
        if(head == null){
            this.head = new Node<>(data);
            this.tail = this.head;
        }
        // 찾고자하는 데이터 노드가 head일 때 -> 헤드는 새로운 노드로 바뀌게 된다.
        else if (this.head.data == isData){
            Node<T> newHead = new Node<>(data);
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        //그 외 찾고자하는 데이터가 헤더에 있지 않을때..그 뒤에 있을 때
        else{
            Node<T> newNode = new Node<>(data);
            Node<T> findNode = searchHead(isData);
            if(findNode == null){
                addNode(data);
            }
            else{
                newNode.next = findNode;
                newNode.prev = findNode.prev;
                findNode.prev.next = newNode;
                findNode.prev = newNode;
            }
        }
    }

    public void printAll(){
        if(head != null){
            Node<T> temp = head;
            System.out.println(temp.data);
            while (temp.next!=null){
                temp = temp.next;
                System.out.println(temp.data);
            }
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addNode(2);
        doublyLinkedList.addNode(4);
        doublyLinkedList.addNode(6);
        doublyLinkedList.addNode(8);
        doublyLinkedList.addNode(9);
        doublyLinkedList.addNode(23);
        doublyLinkedList.addInside(45,23);
        doublyLinkedList.printAll();

    }
}
