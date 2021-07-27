/*
* 병합 정렬
* 재귀 용법을 활용한 정렬 알고리즘
* 1. 리스트를 절반으로 잘라 비슷한 크기의 두 부분 리스트로 나눔
* 2. 각 부분 리스트를 재귀적으로 합병 정렬을 이용해 정렬한다.
* 3. 두 부분 리스트를 다시 하나의 정렬된 리스트로 합병한다.
*
* 병합 정렬은
* 1. Split 단계
* 정렬되지 않은 배열을 끝까지 분리한다.
* 2. Merge 단계
* 분리한 데이터를 단계별로 합친다.
* */

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    // 일단 배열을 반으로 쪼개는 과정을 함수로 표현하는 중
    public void split(ArrayList<Integer> arrayList){
        // 배열의 크기가 1보다 작다면 그냥 끝내
        if(arrayList.size()<=1){
            return;
        }

        int medium = arrayList.size()/2;
        ArrayList<Integer> left;
        ArrayList<Integer> right;
        left = new ArrayList<Integer>(arrayList.subList(0,medium));
        right = new ArrayList<>(arrayList.subList(medium,arrayList.size()));
    }
}
