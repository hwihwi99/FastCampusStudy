/**
 * 동적 프로그래밍 _ 2
 *
 * 1. 풀고 싶은 가짜 문제 정의
 * 2. 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
 * 3. 초기값은 어떻게 되는가?
 * 4. 점화식 구해내기
 * 5. 진짜 문제 정답 출력하기
 *
 * 2~4 과정에서 막힌다면 다시 1번으로 돌아가야 합니다.
 * 주로 가짜 문제는 진짜 문제와 똑같이 표현을 합니다.
 *
 * 2579번 _ 계단 오르기
 * 한번에 계단을 한개 혹은 두개씩 오를 수 있다.
 * 세 개의 계단은 모두 밟아서는 안된다.
 * 마지막 도착 계단은 반드시 밟아야한다.
 *
 * 0. 완전 탐색을 통해서 직접 하나씩 찾아보자 => 방법이 너무 많다 => DP를 사용하자!
 *
 * 1. 가짜 문제를 정의하자.
 *  i번째 계단에 도착하며 얻는 최대 점수
 *
 * 2. 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
 *  네
 *
 * 3. 초기값은 어떻게 되는가?
 *  초기값 : 쪼개지 않아도 풀 수 있는 "작은 문제"들에 대한 정답
 *  이 문제의 경우 시작점은 0점에서 부터 시작한다!
 *
 * 4. 점화식 구해내기
 *  4-1) dp[i] 계산에 필요한 탐색 경우를 공통점끼리 묶어 내기 (partitioning)
 *      dy[6]인 경우를 보자..
 *      1 1 2 2
 *      1 2 1 2
 *      1 2 2 1
 *      2 1 2 1
 *      2 2 2
 *
 *  4-2)묶어낸 부분의 정답을 dp 배열을 이용해서 빠르게 계산해보기
 *      위의 경우.. 마지막에 1칸인지 2칸인지 확인하자..
 *
 *      1 2 2 1
 *      2 1 2 1
 *      dp[i] = dp[i-1] + a[i]
 *
 *      1 1 2 2
 *      1 2 1 2
 *      2 2 2
 *      dp[i] = dp[i-2] + a[i]
 *
 *      이 방법으로 해보면... => 연속한 세 계단을 오르는지 안오르는지를 확인할 방법이 없다.
 *      모자란 정보 ) i-1번쨰 계단 직전에 i-2번째 계단도 밟았는가?
 *
 *      => 실패... 다시 1번 과정으로
 *      1. 가짜 문제 다시 정의 - 필요한 정보를 문제에 추가하기
 *          2차원 배열을 이용해서
 *          dp[i][0] : i-1번째 계단을 밟지 않고 i번쨰 계단에 도착하며 얻는 최대 점수
 *          dp[i][1] : i-1번째 계단을 밟고 i번쨰 계단에 도착하며 얻는 최대 점수
 *
 *          이 둘 중 큰 값을 진짜 dp에 넣어주면 됩니다.
 *
 *      4. 다시 점화식 구하기
 *        4-1) dp[i][0] 계산에 필요한 탐색 경우를 공통점끼리 묶어 내기 (partitioning)
 *             dp[6][0]
 *             1 2 1 2
 *
 *             1 1 2 2
 *             2 2 2
 *             여기서는 i-2번 계단을 오기 전까지 몇 칸을 뛰었는지 확인해보자!
 *             1칸 혹은 2칸!
 *
 *        4-1-1) dp[i][1] 계산에 필요한 탐색 경우를 공통점끼리 묶어 내기 (partitioning)
 *              2 1 2 1
 *              1 2 2 1
 *              i-1번쨰 계단을 꼭 밟는 경우 중에서는 i-1번째에 도착하는 모든 애들은
 *              반드시 i-3번째 계단에서 온다.(세칸 연속이 안되기 때문에..
 *
 *        4-2)묶어낸 부분의 정답을 dp 배열을 이용해서 빠르게 계산해보기
 *              dp[i][0] => dp[i-2][1] + a[i] 혹은 dp[i-2][0] + a[i]의 최댓값
 *
 *        4-2-1)묶어낸 부분의 정답을 dp 배열을 이용해서 빠르게 계산해보기
 *              dp[i][1] => dp[i-1][0] + a[i]
 *
 *        5. 진짜 문제 정답 출력하기
 *           dp[i][0]
 *           dp[i][1]
 *           여기서 최대값을 출력하면 정답입니다.
 *
 *   완전 탐색을 통해서 모든 경우를 세면 정답의 개수만큼 시간이 걸리지만
 *   dp를 사용하면 o(N) 시간이면 충분히 해결 가능하다!
 *
 * => 모자란 정보가 있다면 다이나믹배열에 그 정보를 추가해주어야 합니다.
 *
 * 1149 2156 2193 9465 1309 2688
 *
 * 계단 오르기 => backTracking(역추적)
 * 동적프로그래밍을 이해했다면 역추적을 반드시 알아야한다.
 *
 * 위 문제 continue...
 * 그래 최대값을 구하는 방법은 알았어,
 * 근데 어떤 칸들을 밟아야하지??
 * 그럼 내가 구한 정답에서 뒤로 가면서 이 루트로 왔구나!를 확인해보자!
 *
 * come이라는 배열을 또 만들어서 경로를 저장해준다.
 *  dp[i][0] => dp[i-2][1] + a[i] 혹은 dp[i-2][0] + a[i]의 최댓값
 *  come[i][0] => (i-2,1) 혹은 (i-2,0) 이거를 선택하면 됩니다.
 *
 *  dp[i][1] => dp[i-1][0] + a[i]
 *  come[i][0] => (i-1,0)
 *
 *  즉, 테이블을 채워나갈때 경로를 같이 기록한다면 실제 방법도 알 수 있다.
 *  이 방법을 역추적이라고 합니다.
 *
 *  11057
 *
 *  0. 완전 탐색으로 해보자
 *  => 길이에 맞는 오르막 수를 전부 찾기 힘들다 => dp로 접근해봐야겠다.
 *
 *  1. 가짜 문제를 정의하기
 *      (진짜 문제랑 똑같은 가짜 문제인 경우..)
 *      진짜 문제 => 길이가 n인 오르막 수의 갯수
 *
 *      가짜 문제 => dp[i] : 길이가 i인 오르막 수의 개수
 *
 *      부족한 정보는
 *          마지막 자리의 숫자가 무엇인지 알 수 없다..
 *          이걸 알아야지 올 수 있는 수의 범위가 좁혀지기 때문에
 *
 *      ==> 다시 -----------------------
 *      가짜 문제
 *      dy[i][last] => 길이가 1이고 last로 끝나는 오르막 수의 갯수
 *
 *  2. 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
 *      dy[N][0]~dy[N][9] 까지의 모든 수를 다 더하면 답이 된다!
 *
 *  3. 초기값 설정
 *  i = 1일때는 dy[1][0] ~ dy[1][9] 까지가 모두 1의 값을 갖는다!4
 *
 *  4. 점화식 구하기
 *  4-1) dy[i][k] 계산에 필요한 탐색 경우를 공통점끼리 묶어내기
 *  예로..
 *  dy[4][6]
 *  0006
 *  0016
 *  0026
 *  ..0066
 *
 *  1116
 *  1126
 *  1136
 *  ..
 *  6666
 *
 *  공통점끼리 묶어내기!
 *  6으로 끝나는 것은 당연하니깐 그 전수에 나오는 자리를 묶기
 *  0006
 *
 *  0016
 *  0116
 *  1116
 *  ...
 *  이렇게 0~6으로 나누자.
 *
 *  이거면 6빼고 앞에 3자리를 보면
 *  dy[3][0] ~ dy[3][6] 에 정보를 담고 있습니다..
 *
 *  2688 1562 2096 5557 1495 9095 15988 15990
 * */

public class DynamicProgramming_2 {
    public static void main(String[] args) {
        int [] a = {0,10,20,15,25,10,20};
        int [] dp = new int[a.length];

        dp[0] = 0;
        dp[1] = a[1];
        dp[2] = a[1] + a[2];

        for(int i = 3; i<a.length;i++){
            dp[i] = Math.max(dp[i-1]+a[i], dp[i-2]+a[i]);
        }

    }
}
