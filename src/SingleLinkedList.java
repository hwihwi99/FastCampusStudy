/*
* Array리스트
* 미리 최대의 자료를 알고 있어야 했다.
* 바로바로 주소가 옆주소여야 한다.
*
* 링크드리스트
* 노드 : 하나의 데이터로 취급 (데이터 + 다음 데이터 주소(포인터))
* 다음 데이터 주소를 의미하는 것은 포인터라고도 한다.
*
* 장점
* 미리 데이터 공간을 할당하지 않아도 된다.
*
* 단점
* 노드에는 다음 데이터 주소를 아는 공간이 또 필요하다.
* 인덱스가 없어서 앞에서부터 원하는 곳까지 찾아야한다. -> 접근속도가 느리다.
* 중간 데이터 삭제 및 삽입 시, 앞뒤 데이터의 연결을 재구성해야 한다.
* */

import javax.annotation.processing.SupportedSourceVersion;

public class SingleLinkedList<T> {
    public Node<T> head = null;

    class Node<T>{
        T data;
        Node<T> next = null;
        public Node(T data){
            this.data = data;
        }
    }

    //마지막 부분에 노드 추가하는 메서드
    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if(head == null){
            head = newNode;
        }else{
            Node t = head;
            while(t.next != null){
                t = t.next;
            }
            t.next = newNode;
        }
    }

    public Node<T> search (T isData){
        if(head == null){
            return null;
        }
        Node <T> node = head;
        while(node!=null){
            if(node.data == isData){
                return node;
            }
            node = node.next;
        }
        return null;
    }
    // 리스트 중간에 노드 삽입
    // data를 isData라는 값을 가진 노드 뒤에다가 넣어줘!
    public void addNodeInside(T data, T isData){
        Node<T> searchNode = this.search(isData);
        if(searchNode == null){
            add(data);
        }else{
            Node<T> newNode = new Node<>(data);
            newNode.next = searchNode.next;
            searchNode.next = newNode;
        }
    }

    // 노드 삭제하고 성공 실패 유무 확인
    public boolean delNode(T isData){
        if(head == null)
            return false;
        Node<T> node = this.head;

        //만약 첫번째 노드를 삭제해야한다면?
        if(node.data == isData){
            head = head.next;
            return true;
        }else{
            while(node.next != null){
                if(node.next.data == isData){
                    Node<T> tempNode = node.next;
                    node.next = tempNode.next;
                    tempNode.next = null;
                    return true;
                }
                node=node.next;
            }
            return false;
        }
    }

    public void printAll(){
        if(head!=null){
            Node<T> t = head;
            System.out.println(t.data);
            while(t.next != null){
                t = t.next;
                System.out.println(t.data);
            }
        }
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);
        //1을 가진 노드 뒤에 5 값을 삽입
        singleLinkedList.addNodeInside(5,1);
        //20을 가진 노드 뒤에 7 값을 삽입 -> 이러면 없는 노드잖아요? 그래서 맨뒤로 갑니다.
        singleLinkedList.addNodeInside(7,20);
        singleLinkedList.printAll();
        System.out.println(singleLinkedList.delNode(7));
        singleLinkedList.printAll();
        System.out.println(singleLinkedList.delNode(1));
        singleLinkedList.printAll();
    }



}
