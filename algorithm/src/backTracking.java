/**
 * 백 트래킹(backTracking) _ 일종의 전략이다.
 * 제약 조건 만족 문제에서 해를 찾기 위함
 * -> 해를 찾기 위해서 후보군에 제약 조건을 계속 체크하다가 해당 후보군이 제약조선을 만족할 수 없다고
 *    판단하는 즉시 다시 처음으로 돌아가서 같은 과정을 반복하는 과정
 *
 * 고려할 수 있는 모든 케이스(상태군)를 상태공간트리를 이용해서 표현한다.
 * 그 후, 깊이 우선 탐색으로 해를 찾는다.
 *
 * 즉, 백트래킹은 트리 구조를 기반으로 ,dfs로 깊이 탐색을 진핸하면서
 * 각 루트에 대해 조건을 부합하는지 체크(Promising)
 *      if 조건에 맞지 않음:
 *          더 이상 dfs 진행하지 않고 가지치기
 *          (가지티기, pruning : 조건에 맞지 않으면 포기하고 다른 루트로 바로 돌아서서 탐색 시간을 절약한다. )
 *
 * 예시 ) NQueen 문제
 * NxN 크기의 체스판에 N개의 퀸을 서로 공격할 수 없도록 공격할 수 없도록 배치
 * 수직, 수평, 대각선으로 원하는 칸만큼 이동 가능
 *
 * 이때 대각선에 대한 계산은..?
 * 현재 후보 군의 열 - 위의 퀸 군의 열의 절대값
 * 현재 후보 군의 행 - 위의 퀸 군의 행의 절대값
 * -> 이 값이 동일하면 대각선의 위치에 있다!
 *
 * 1. 후보군을 상태 공간 트리로 만든다.
 * 위의 문제에서는 2차원 배열에서의 인덱스를 좌표로 표현한다.
 *
 * 2. prunning(가지치기), promising(체크) 계속 반복하기
 * */

import java.util.*;

public class backTracking {

    public static boolean isAvailable(ArrayList<Integer> candidate, int currentCol){
        int currentRow = candidate.size();
        for(int index = 0; index < currentRow;index++){
            if((candidate.get(index) == currentCol) || (Math.abs(candidate.get(index) - currentCol) == currentRow - index)) {
                // 수평조건 + 대각선 조건
                return false;
            }
        }
        return true;
    }


    // n:몇개의 퀸을 지정해야 하는가
    // currentRow : 현재 탐색해야하는 row가 어디인가
    // currentCandidate : 지금까지 위에서 선택된 후보들을 알려준다.
    public static void dfs(int n, int currentRow, ArrayList<Integer> currentCandidate){

        // 다 돌았다면 -> 후보군 배열을 반환한다.
        if(currentRow == n){
            System.out.println(currentCandidate);
            return;
        }

        // 순회하자!
        for(int index = 0; index < n; index++){
            // isAvailable : 조건을 판단하는 함수
            if(isAvailable(currentCandidate,index) == true) {
                currentCandidate.add(index);
                dfs(n, currentRow + 1, currentCandidate);
                currentCandidate.remove(currentCandidate.size() - 1);
            }
        }
    }
    public static void main(String[] args) {
        dfs(4,0,new ArrayList<Integer>());
    }
}
