/*
 * 삽입 정렬
 * 자료 배열의 모든 요소를 앞에서부터 차례대로 이미 정렬된 배열 부분과 비교하여,
 * 자신의 위치를 찾아 삽입하므로써 완선하는 알고이즘이다.
 *
 * 삽입 정렬 분석
 * 반복문이 두개 -> O(n^2)
 * 최악의 경우 : n번 -> n-1번 -> n-2번 -> ... -> 1번 == n(n-1) / 2
 *
 * 완전 정렬이 되어 있는 상태라면 내부 for문은 동작하지 않아! 그래서 O(n)
 * */

import java.util.ArrayList;
import java.util.Collections;

public class InsertionSort {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for(int i = 0; i< 10; i++){
            arrayList.add((int)(Math.random()*100));
        }

        System.out.println(arrayList);
        for(int i = 0 ; i< arrayList.size()-1;i++){
            for(int j = i+1; j>0;j--){
                if(arrayList.get(j)< arrayList.get(j-1))
                    Collections.swap(arrayList,j,j-1);
                else
                    break;
            }
        }
        System.out.println(arrayList);

    }
}
