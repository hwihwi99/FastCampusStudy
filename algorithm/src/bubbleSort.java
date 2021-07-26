/*
* 정렬
* 어떤 데이터들을 순서대로 나열하는 것
*
* 버블 정렬
* 서로 인접한 두 원소를 검사하여 정렬하는 알고리즘
* -> 인접한 두개의 레코드를 비교하여 크기가 순서대로 되어 있지 않으면 서로 교환한다.
*
* 1회전을 수행하고 나면, 가장 큰 자료가 맨 뒤로 이동하므로
* 2회전에서는 맨 끝에 있는 자료는 정렬에서 제외하고,
* 3회전때는 끝에서 두번쨰 자료까지 정렬에서 제외한다..
* 이렇게 정렬을 1회전 수행할 때마다 정렬에서 제외되는 데이터가 하나씩 늘어난다.
*
* 장점
* 구현이 간단하다
*
* 단점
* 하나의 요소가 가장 왼쪽에서 오른쪽으로 이동하여야 한다면 배열에서 모든 다른 요소들과 교환되어야 한다.
*
* --> 자료의 교환이 이동보다 더 복잡해서 버블정렬은 단순하지만 거의 쓰이지 않는다.
*
* 시간 복잡도
* 반복문이 2개 이다. O(n^2)
*
* 최악의 경우
* n + (n-1) + (n-2) + (n-3) + ... + 1 == n(n-1)/2 이다.
*
* */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class bubbleSort {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();

        for(int i = 0; i<10;i++){
            array.add((int)(Math.random()*100));
        }

        System.out.println(array);

        for(int i = 0; i< array.size()-1;i++){
            // 한번의 루틴속에서 교환이 한번도 안일어났다면
            // 이미 정렬된 것 이므로 혹시 다 돌고도 fasle이면 그냥 끝내주면 된다.
            boolean swap = false;

            for(int j = 0;j< array.size()-1-i;j++){
                if(array.get(j)>array.get(j+1)){
                    Collections.swap(array,j,j+1);
                    swap = true;
                }
            }

            if(swap == false)
                break;
        }

        System.out.println(array);
    }
}
