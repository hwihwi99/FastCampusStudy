/*
 * 선택정렬
 * 가장 작은 값을 맨 앞에 두고, 그 다음 작은 값을 두번째에 두고,,를
 * 반복하며 정렬을 수행하는 것
 *
 * 선택 정렬 로직
 * 1. 주어진 데이터 중 최소값을 찾음.
 * 2. 해당 최소값을 데이터 맨 앞에 위치한 값과 교체
 * 3. 맨 앞의 위치를 뺸 나머지 데이터를 동일한 방법으로 반복
 *
 * 선택 정렬 분석
 * 반복문이 두개 -> O(n^2)
 * 더 자세하게 한다면 n번 -> n-1번 -> n-2번 -> ... -> 1번
 * --> n(n-1) / 2
 * */

import java.util.ArrayList;
import java.util.Collections;

public class SelectionSort {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = 0;i<10;i++){
            arrayList.add((int)(Math.random()*100));
        }
        System.out.println(arrayList);
        int min;
        for(int i = 0; i<arrayList.size();i++){
            min = i; // 최솟값 인덱스
            for(int j = i+1; j<arrayList.size();j++){
                if(arrayList.get(j) < arrayList.get(min)){
                    min = j;
                }
            }
            Collections.swap(arrayList,min,i);
        }
        System.out.println(arrayList);
    }
}
