/*
* 힙
* 데이터에서 최대값과 최소값을 빠르게 찾기 위해 고안된 완전 이진 트리
 *
 * 완전 이진 트리
 * 노드를 삽입할 떄 최하단 왼쪽 노드부터 차례대로 삽입하는 트리
 *
 * * 힙을 사용하는 이유
 * 1. 배열로 최대, 최소를 찾으면 모든것을 다 탐색해야해서 O(n)이 걸림
 * 2. 힙은 O(log n)
 * 3. 우선순위 큐와 같이 최대값과 최솟값을 찾아야하는 자료구조 및 알고리즘 구현에 사용된다.
 *
 * 힙 구조
 * 완전 이진 트리 형태를 갖는다.
 * - Max Heap : 최대값을 구하기 위한 구조
 *      각 노드의 값은 해당 노드의 자식노드가 가진 값보다 크거나 같다.
 * - min Heap : 최소소을 구하기 위한 구조
 *      각 노드의 값은 해당 노드의 자식노드가 가진 값보다 작거나 같다.
 *
 * 힙 동작
 * 1. 데이터 삽입하기
 * -> 항상 첫 노드 삽입은 마지막 왼쪽 노드부터 계속 채워서
 * -> max heap, min heap 에 조건이 위배된다면 부모노드와 나를 바꿔서 저장한다.
 *
 * 2. 데이터를 삭제할 때
 * -> 특정 값을 삭제한다기 보다는 가장 큰 값, 혹은 가장 작은 값 ( 트리의 root)을 삭제한다.
 * 루트노드 삭제하고 그 자리에 최하위단에 가장 오른쪽 노드와 바꿔치기를 한다.
 *  바꿔치기를 하고나선 다시 자리를 올바르게 바꿔준다.
 *
 * 힙 VS BST
 * 공통점
 * ->모두 이진 트리이다.
 *
 * 차이점
 * -> BST는 값의 크기가 왼쪽 자식 < 부모 < 오른쪽 자식 순이다. 탐색을 위한 구조이다.
 * -> 힙은 BST의 조건이 없다. 최대 / 최소값 검색을 위한 구조 중 하나이다.
 *
 * 힙과 배열
 * 일반적인 힙 구현시, 배열로 사용
 * 배열 인덱스는 0번부터 시작을 하지만 root노드를 1번 인덱스라고 지정하면 구현이 더 수월하다.
 * 부모노드 인덷스 번호 = 자식노드 / 2
 * 부모노드를 인덱스를 알 때(K라고 하자) 자식노드의 인덱스는 왼쪽이 2K 오른쪽이 2K+1
 *
 */

import java.util.ArrayList;
import java.util.Collections;

public class MaxHeap {

    private ArrayList<Integer> heapArray = null;

    public MaxHeap(int data){
        heapArray = new ArrayList<>();

        heapArray.add(0); // 0번째 인덱스는 비워주기 때문에에
        heapArray.add(data);
    }

    // 현재 내 노드가 움직여야 되는지 아닌지지
   public boolean moved_up(int inserted_idx){
        //내가지금 루트 노드라면 더 움직일 필요가 없어요
        if(inserted_idx <= 1){
            return true;
        }
        int parent_idx = inserted_idx / 2;
        if(heapArray.get(inserted_idx)>heapArray.get(parent_idx)){
            return true;
        }else{
            return false;
        }
    }

    public void insert(int data){

        // 힙 배열이 비어있다면 -> 배열을 새로 만들어 주고 새로운 데이터를 삽입한다.
        if(heapArray == null){
            heapArray = new ArrayList<>();
            heapArray.add(null);
            heapArray.add(data);
        }
        else{
            int insert_idx, parent_idx; // 현재 삽입한 데이터의 인덱스와 그 부모 인덱스

            // 그게 아니라면 배열 뒤에다가 삽입한다.
            heapArray.add(data);
            insert_idx = heapArray.size() - 1; // 현재 인덱스 == 배열의 마지막 인덱스

            // Collectins.swap(배열,인덱스1,인덱스2)을 사용해서 배열의 인덱스 1, 2 를 바꿔준다
            while (moved_up(insert_idx)){
                parent_idx = insert_idx/2;
                Collections.swap(heapArray,insert_idx,parent_idx);
                insert_idx = parent_idx;
            }
        }
    }


    // 바꿀필요가 있으면 true, 없으면 fasle
    public boolean moved_down(int pop_idx){
        int left_child_pop_idx , right_child_pop_idx;

        left_child_pop_idx = pop_idx * 2;
        right_child_pop_idx = pop_idx * 2 + 1;

        //case 1) 왼쪽 자식 노드도 없을 때 ( 자식 노드가 하나도 없을 때)
        // 현재 배열 사이즈보다 크면 아직 데이터가 안들어온것
        if(left_child_pop_idx >= heapArray.size()){
            return false;
        }
        // case 2) 오른쪽 자식 노드만 없을 때
        else if(right_child_pop_idx >= heapArray.size()){
            if(heapArray.get(pop_idx) < heapArray.get(left_child_pop_idx)){
                return true;
            }else
                return false;
        }
        // case 3) 왼쪽/ 오른쪽 자식이 모두 있을 때
        else{
            // 왼쪽과 오른쪽을 먼저 비교
            // 왼쪽이 더 크다면
            if(heapArray.get(left_child_pop_idx) > heapArray.get(right_child_pop_idx)){
                if(heapArray.get(pop_idx) < heapArray.get(left_child_pop_idx)){
                    return true;
                }
                else{
                    return false;
                }
            }
            else if (heapArray.get(left_child_pop_idx) < heapArray.get(right_child_pop_idx)){
                if(heapArray.get(pop_idx) < heapArray.get(right_child_pop_idx))
                    return true;
                else
                    return false;
            }
        }
        return false;
    }
    // 루 노드를 제거하자!
    public int pop(){
        // 만약 힙이 비어있다면 return -1
        if(heapArray == null){
            return -1;
        }
        // 그렇지 않다면 root를 빼고 그 자리에 마지막에 있는 데이터로 바꿔치기한다.
        int returnData = heapArray.get(1);
        // 루트 노드에 마지막값을 넣어주고
        heapArray.set(1,heapArray.get(heapArray.size()-1));
        // 마지막 값은 삭제한다.
        heapArray.remove(heapArray.size()-1);
        int pop_idx = 1;
        int left_child_pop_idx, right_child_pop_idx;

        while(moved_down(pop_idx)){
            left_child_pop_idx = pop_idx * 2;
            right_child_pop_idx = pop_idx * 2 + 1;

            // 오른쪽 자식만 없을 때
            if(right_child_pop_idx > heapArray.size()){
                if(heapArray.get(pop_idx) < heapArray.get(left_child_pop_idx)){
                    Collections.swap(heapArray,pop_idx,left_child_pop_idx);
                    pop_idx = left_child_pop_idx;
                }
            }
            // 왼쪽/ 오른쪽 자식 모두 있을 때
            else {
                // 왼쪽과 오른쪽을 먼저 비교
                // 왼쪽이 더 크다면
                if(heapArray.get(left_child_pop_idx) > heapArray.get(right_child_pop_idx)){
                    if(heapArray.get(pop_idx) < heapArray.get(left_child_pop_idx)){
                        Collections.swap(heapArray,pop_idx, left_child_pop_idx);
                        pop_idx = left_child_pop_idx;
                    }
                }
                else if (heapArray.get(left_child_pop_idx) < heapArray.get(right_child_pop_idx)){
                    if(heapArray.get(pop_idx) < heapArray.get(right_child_pop_idx)){
                        Collections.swap(heapArray,pop_idx,right_child_pop_idx);
                        pop_idx = right_child_pop_idx;
                    }
                }
            }
        }
        return returnData;
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(1);
        maxHeap.insert(2);
        maxHeap.insert(3);
        maxHeap.insert(4);
        maxHeap.insert(5);

        System.out.println(maxHeap.heapArray);
    }
}
