/*
* 이진 탐색
* 탐색할 자료를 둘로 나누어 해당 데이터가 있을 만한 곳을 탐색하는 방법
* 단, 이진탐색 전에는 데이터가 정렬되어 있어야 한다.
*
* 배열의 중간 값과 찾고자 하는 데이터를 확인하고
* 이 값이 작으면 왼쪽 배열의 중간, 크면 오른쪽 배열의 중간을 찾는다.
*
* 분할 정복 알고리즘과 이진 탐색
* 분할 정복 알고리즘
*  분할 : 문제를 하나 또는 둘 이상으로 나눈다.
*  정복 : 나눠진 문제가 충분히 작고, 해결이 가능하다면 해결하고 그렇지 않으면 다시 나눈다.
*
* 이진 탐색
*  분할 : 리스트를 두개의 서브 리스트로 나눈다.
*  정복 : 검색할 숫자 < 중간값 -> 왼쪽 서브 리스트를 검색
*        검색할 숫자 > 중간값 -> 오른쪽 서브 리스트를 검색
*
* 성능
* n개의 리스트를 매번 2로 나눠 1이 될 때까지 비교연산을 k회 진행
* n * 1/2 * 1/2 * ... = 1
* n * (1/2)^k = 1
* n = log 2(밑) 2^k(진수)
* log 2 n = k
* 즉 빅오표기법 시간 복잡도는 O(logn)이 된다.
* */
import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch {
    public static int binarySearch(ArrayList<Integer> list, int data){
        int lo = 0;
        int hi = list.size()-1;
        int mid;

        while(lo<=hi){
            mid = lo + hi /2;
            if(list.get(mid) == data){
                return mid;
            }
            else if(list.get(mid)<data){
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<10;i++){
            list.add((int)(Math.random()*100));
        }
        int data = list.get(8);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        System.out.println(binarySearch(list,data));
    }
}
