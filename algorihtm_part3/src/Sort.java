/**
 * 정렬
 * 오름차순 : 크기가 점점 커지게!
 * 내림차순 : 크기가 점점 작아지게!
 *
 * 정렬은...
 * 1. 조건이 필요하다(오름차순, 내림차순)
 * 구현은
 * implements Comparable<Elem>
 *     int item
 *     public int compareTo(Elem e){
 *         return this.item - e.item; // 이러면 오름차순
 *     }
 *
 * 2. 시간 복잡도는 약 O(NlogN)이다.
 *  primitive 원소 정렬
 *  (int, double, char.. 등등)
 *  Dual-Pivot Quick Sort (퀵 소트) -> inplace 정렬
 *
 *  Object 원소 정렬
 *  Tim Sort (선택 + 병합 정렬) -> stable
 *
 *  3. In-place / Stable
 *  1) In-place
 *  정렬 알고리즘이 제자리 한가?
 *  정렬하는 과정에서 N에 비해 충분히 무시할 만한 개수의 메모리만큼만 추가적으로 사용하는가?
 *
 *  2) Stable
 *  정렬알고리즘이 안정한가?
 *  동등한 위상의 원소들의 순서 관계가 보존되는가?
 *
 *  정렬 기초 백준
 *  10825
 *  implements Comparalbe<Item>안의 compareTo()함수 적극적으로 사용하기
 *
 *  정렬 응용 백준
 *  1015번
 *
 *  방법 1)
 *  가장 쉬운 방법 (시간복잡도 O(N^2)
 *  크기가 작기 때문에 배열을 다 찾아서 값을 연결해서 P배열을 만들어준다.
 *
 *  방법 2)
 *  A -> P -> B이므로 A 배열 속 인덱스에서 P의 인덱스의 값을 가지고 A로 가는것 이기 때문에
 *  P[idx] = b_idx 이런식으로 처리해주어도 된다.
 *
 *  우선 배열을 정렬하고 (O(NlogN)) -> p 배열 구한다. (O(N))
 *  시간 복잡도 : O(NlogN), 공간 복잡도 : O(N)
 *
 *  11652번
* */
public class Sort {
}
