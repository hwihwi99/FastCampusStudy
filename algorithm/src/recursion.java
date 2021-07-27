import java.util.ArrayList;

/*
* 재귀 용법
* - 함수 안에서 동일한 함수를 호출하는 형태
*
* 재귀 함수의 일반적인 형태
 * 같은 함수를 계속 호출하는 것이기 때문에 특정 조건에서 종료가 되어야지 함수가 끝이난다.
 * 아니면 무한 반복
 * 특정 조건 : 어떤 값보다 이상이면? 이하면?
 * --> 재귀 용법은 종료 조건이 중요하다.
 *
 * 재귀 호출은 스택의 전형적인 예시이다.
 * */
public class recursion {
    /*
    * 팩토리얼의 시간 복잡도와 공간 복잡도
    * 시간 복잡도
    * n-1번의 반복문을 호출하는 것과 동일하다. -> O(n-1) == O(n)
    * 공간 복잡도
    * 각 인자에 대한 공간이 필요.. -> O(n-1) == O(n)
    * */
    public static int factorial(int n){
        if(n<=1)
            return 1;
        else return n * factorial(n-1);
    }

    // 숫자 배열에 들어있는 원소의 합을 리턴하는 코드 작성하기
    public static int sum(ArrayList<Integer> arrayList){
        if(arrayList.size() <= 0){
            return 0;
        }else{
            int num = arrayList.remove(0);
            return num + sum(arrayList);

            //return arrayList.get(0) + sum(new ArrayList<Integer>(arrayList.subList(1,arrayList.size())));
            // 이렇게 해줘도 되는데 이러면 할때마다 계속 새로운 배열을 생성해주어야해서 비효율적이다.
        }
    }

    // 정수 n이 입력으로 주어졌을 때, n을 1,2,3의 합으로 나타낼 수 있는 방법의 수를 구하시오오
    public int Count(int n){
        if(n == 1){
            // 1일때는 한가지
            return 1;
        }else if (n == 2){
            // 2일때는 (1,1) 2 2가지
            return 2;
        }else if (n == 2){
            // 3일때는 (1,1,1) (1,2) (2,1) 3 4가지
            return 4;
        }else{
            return Count(n-1)+Count(n-2)+Count(n-3);
        }
    }

    public static void main(String[] args) {
        System.out.println(factorial(6));
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(7);
        arrayList.add(3);
        arrayList.add(10);
        System.out.println(sum(arrayList));
    }
}
