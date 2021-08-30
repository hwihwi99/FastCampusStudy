import java.util.ArrayList;
import java.util.Collections;

public class Heap_ver2 {
    private static ArrayList<Integer> heapArray = null;

    // 루트노드를 하나 가져오면서 만든다!
    public Heap_ver2(int data) {
        heapArray = new ArrayList<>();
        heapArray.add(null);
        heapArray.add(data);
    }

    public int insert(int data){
        // 힙이 비었거나 만들어져있지 않을때는?
        if(heapArray == null) {
            heapArray = new ArrayList<>();
            heapArray.add(null);
            heapArray.add(data);
            return data;
        }
        heapArray.add(data);
        int index = heapArray.size()-1;
        while(index > 1){
            if(heapArray.get(index) > heapArray.get(index/2)){
                Collections.swap(heapArray,index,index/2);
                index /=2;
            }
            else{
                break;
            }
        }
        return data;
    }

    public int pop(){
        if(heapArray==null)
            return -1;
        else if (heapArray.size() == 2){
            return heapArray.remove(1);
        }
        else{
            int result = heapArray.get(1);
            heapArray.set(1,heapArray.remove(heapArray.size()-1));
            int index = 1;
            int left_child = 0, right_child = 0;
            while(index < heapArray.size()){
                left_child = index * 2;
                right_child = index * 2 + 1;
                // 왼쪽 자식 노드도 없을 때 -> 즉 모든 자식 노드가 없을 때
                if(left_child >= heapArray.size()){
                    break;
                }
                // 왼쪽 자식 노드만 있을 때 -> 하나의 자식만 있을 때
                else if(right_child>=heapArray.size()){
                    if(heapArray.get(left_child)>heapArray.get(index)){
                        Collections.swap(heapArray,left_child,index);
                        index = left_child;
                    }
                    else{
                        break;
                    }
                }
                // 둘 다 있을 때
                else{
                    if(heapArray.get(left_child)>heapArray.get(right_child)){
                        if(heapArray.get(left_child)>heapArray.get(index)){
                            Collections.swap(heapArray,left_child,index);
                            index = left_child;
                        }else{
                            break;
                        }
                    }else{
                        if(heapArray.get(right_child)>heapArray.get(index)){
                            Collections.swap(heapArray,right_child,index);
                            index = right_child;
                        }else {
                            break;
                        }
                    }
                }
            }
            return result;
       }
    }

    public static void main(String[] args) {
        Heap_ver2 maxHeap = new Heap_ver2(9);
        maxHeap.insert(2);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(6);
        maxHeap.insert(8);
        maxHeap.insert(80);
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.heapArray);
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.heapArray);
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.heapArray);
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.heapArray);
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.heapArray);
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.heapArray);
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.heapArray);
    }
}
