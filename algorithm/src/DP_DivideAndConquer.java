/*
* 동적 계획법과 분할 정복 -> 전략
*
* 동적 계획법 (Dynamic Programming, DP) : 상향식 접근법
* 입력 크기가 작은 부분 문제들을 해결한 후, 해당 부분 문제의 해를 이용해서
* 보다 큰 크기의 부분 문제를 해결한다. 예) 피보나치 수열
* 요약) 작은 문제 우선 해결 -> 큰 문제 해결 성공 (Memoization 기법)
* Memoization : 프로그램 실행 시, 이전 값을 저장하여 다시 계산하지 않도록해서 전제 실행 속도를 빠르게 하는 것
*
* 분할 정복 (DivideAndConquer) : 하향식 접근법
* 문제를 나눌 수 없을 때까지 나누어서 각각 풀면서 다시 합병하여 문제의 답을 얻는 알고리즘
* 하양식 접근법으로 상위 해답을 구하기 위해 아래로 내려가면서 하위릐 해답을 구하는 방식
*
* 공통점
* 큰 문제를 잘게 쪼개서 가장 작은 단위로 분할
*
* 차이점
* 동적 계획법은
* 부분 문제는 중복되어, 상위 문제 해결시 재활용 된다.
* 분할 정복은
* 부분 문제는 서로 중복되지 않는다.
*   */

// 이번시간에는 동적 계획법과 재귀 함수의 차이를 보기 위해서!
public class DP_DivideAndConquer {
    // 재귀 용법 버전
    public static int Fibonacci1(int n){
        if(n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return Fibonacci1(n-2)+Fibonacci1(n-1);

    }

    // 동적 계획법 버전
    public static int Fibonacci2(int n){
        int []arr = new int[n+1];
        arr[0] = 0;
        arr[1] = 1;
        for(int i = 2; i< arr.length;i++){
            arr[i] = arr[i-1]+arr[i-2];
        }
        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(Fibonacci1(10));
        System.out.println(Fibonacci2(10));
    }
}
