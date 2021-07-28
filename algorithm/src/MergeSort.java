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
*
* 분석
* 시간복잡도는 O(nlogn)
* 한 레벨마다 노드는 모두 n개가 있지
* 그리고 트리의 높이는 logn이니깐
* 최종적인 시간 복잡도는 nlogn이다.
* */

import java.util.ArrayList;

public class MergeSort {

    // 재귀함수를 이용해서 mergeSplit 함수를 만들어야 한다. -> 들어오는 배열을 쪼개주는 역할!
    // 1. 만약 배열 갯수가 한개라면 해당 값 리턴
    // 2. 그렇지 않다면 배열을 두개로 나눈다.
    // leftArr, rightArr로 나누고
    // merge(leftArr, rightArr)를 반환핟다.

    public static ArrayList<Integer> mergeSplit (ArrayList<Integer> arr){
        if(arr.size()<=1){
            return arr;
        }
        int middle = arr.size()/2;

        // 재귀용법으로 계속 배열을 쪼개준다.
        ArrayList<Integer> left = null;
        left = mergeSplit(new ArrayList<>(arr.subList(0,middle)));
        ArrayList<Integer> right = null;
        right = mergeSplit(new ArrayList<>(arr.subList(middle, arr.size())));

        return merge(left,right);
    }

    // merge 함수를 만들어서 두 배열을 합치면서 순서있게 나열한다.

    public static ArrayList<Integer> merge (ArrayList<Integer> left,ArrayList<Integer> right){
        ArrayList<Integer> temp = new ArrayList<>();
        int leftPoint = 0;
        int rightPoint = 0;

        //case 1) left, right 둘 다 있을 때
        while (left.size() > leftPoint && right.size() > rightPoint){
            if(left.get(leftPoint) <= right.get(rightPoint)){
                temp.add(left.get(leftPoint));
                leftPoint++;
            }
            else{
                temp.add(right.get(rightPoint));
                rightPoint++;
            }
        }
        // 이게 끝나면 왼쪽 혹은 오른쪽 베열이 한개는 다 처리가 된 것 이다.

        //case 2) right만 없을 때 -> 남은 left를 뒤에 넣어준다.
        while(leftPoint < left.size()){
            temp.add(left.get(leftPoint));
            leftPoint++;
        }

        //case 3) left만 없을 때 -> 남은 right를 뒤에 넣어준다.
        while(rightPoint < right.size()) {
            temp.add(right.get(rightPoint));
            rightPoint++;
        }
        return temp;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testArr = new ArrayList<>();
        for(int i = 0;i<20;i++){
            testArr.add((int)(Math.random()*100));
        }
        System.out.println(testArr);
        MergeSort ms = new MergeSort();
        System.out.println(ms.mergeSplit(testArr));
    }

}
