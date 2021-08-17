/**
 * 그래프 탐색 _ dfs bfs 응용편
 *
 * 잊지마! 항상 정답의 최대치를 구해보자!
 *
 * 2667번
 * 격자의 위치 (2차원 배열의 좌표)를 이용해서 정점의 값을 넣자.
 * 현재 위치에서의 상하좌우 값을 찾아야합니다!
 *
 * 인접리스트 형태로 관리를 한다면.. -> 이건 한번 해보자! 할만할 듯!
 *
 * <격자형 그래프 연습>
 * 1012, 11724, 4963, 3184
 *
 * <일반 그래프 연습>
 * 2606 11403 11725
 *
 * 2251번 ( 난이도 : 어려움!! -> 이유는 이 문제는 그래프 문제라고 생각하기 쉽지 않다.)
 * 한가지 상태를 -> (a,b,c) 이런 형태로 현재 담긴 물의 양에 대한 표현이 가능해진다.
 *
 * 물을 부으면? -> 상태가 변경된다!!
 * 상태가 변경되면서!--> 방향 그래프여야 합니다.
 *
 * 현 상태에서 다음 상태가 될 수 있는 모든 상태를 다음 간선으로 표현한다.
 *
 * 이 문제는 하나의 상태를 숫자 3개의 조합으로 만들어주어야합니다.
 *
 * 14502번
 * -> 삼성 역량 테스트..
 * 이거 진짜,,, 코테에서 많이 본 문제...!!
 *
 * 우선 바이러스가 감염시킬 수 있는 지역들을 확인해보자. -> 위 아래 양옆으로 계속 퍼져나가게!
 *
 * 기존 dfs, bfs는 시작점이 한 공간이였다.
 * 이 문제는 시작점이 여러개인 bfs,dfs이고 이는 Multisource BFS를 사용하면 된다.
 * How to Multisource BFS??
 * 가능한 모든 시작점은 큐에 넣어서 연산을 시작하면 됩니다!
 *
 * 이 문제는 벽 위치를 모두 다 완전탐색으로 만들고
 * 이 때, 벽을 모두 다 세워보는 이 행위는 dfs를 사용합니다.
 * 그 후에 바이러스가 어디로 얼마나 퍼져나가는지 확인하는 방향으로
 * 이 때, 바이러스가 어디로 가는지는 bfs를 사용하면 됩니다.
 *
 * */

public class GraphSearch_2 {


}