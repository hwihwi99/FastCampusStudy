/*
* 퀵 정렬 ( 정렬 알고리즘의 꽃)
* 기준점(pivot)을 정한다. 주로 맨 앞에 있는 값이다.
* 기준점보다 작은 데이터는 왼쪽, 큰 데이터는 오른쪽으로 모으는 함수이다.
* 재귀함수를 이용해서 다시 위의 작업을 반복한다.
* 함수는 왼쪽 + 기준점 + 오른쪽을 리턴한다.
*
* 퀵 정렬은
* O(nlogn)
* 최악은 경우 O(n^2)이 된다.
* */

import java.util.ArrayList;

public class QuickSort {
    public ArrayList<Integer> sort(ArrayList<Integer> arr){
        if(arr.size() <= 1){
            return arr;
        }
        int pivot = arr.get(0);
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        for(int i = 1;i<arr.size();i++){
            if(arr.get(i) < pivot){
                left.add(arr.get(i));
            }else{
                right.add(arr.get(i));
            }
        }

        ArrayList<Integer> merge = new ArrayList<>();
        merge.addAll(sort(left));
        merge.add(pivot);
        merge.addAll(sort(right));

        return merge;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<>();
        for(int i = 0;i<20;i++){
            testData.add((int)(Math.random()*100));
        }
        QuickSort qs = new QuickSort();
        System.out.println(qs.sort(testData));
    }
}
