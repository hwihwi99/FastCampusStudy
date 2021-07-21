/*
 * 스택
 * 데이터를 제한적으로 접근할 수 있는 구조
 * LIFO : last in first out
 *
 * 스택의 활용
 * 컴퓨터 내부의 프로세스 구조의 함수 동장 방식에 스택 사용
 * -> 재귀 용법 관련이 스택의 구조를 사용한다.
 *
 * push() : 데이터 넣기
 * pop() : 데이터 빼기
 *
 * 장점
 * 구조가 단순해서 구현이 쉽고 데이터 저장/ 읽기 속도가 빠름
 *
 * 단점
 * 데이터 최대 갯수를 미리 알아야한다.
 * 저장 공간의 낭비가 발생할 수 있다.
 *
 * 스택은 단순하고 빠른 성능을 위해 사용되므로
 * 배열을 이용해서 구현하는 것이 좋다.
 *
 * Stack 클랫를 사용하면 편함
 * push, pop, size, isEmpty 등의 메서드가 존재한다.
 * */

import java.util.ArrayList;

public class myStack<T> { //<T> 이런게 제너릭을 사용하는 방법
    ArrayList<T> stack = new ArrayList<>();

    public void push (T t){
        // 가장 뒷부분에 자료 삽입
        stack.add(t);
    }

    public T pop(){
        if(stack.isEmpty())
            return null;
        //비어있지 않다면 배열의 가장 끝부분 원소 제거
        return stack.remove(stack.size()-1);
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public int size(){
        return stack.size();
    }

    public static void main(String[] args) {
        myStack<Integer> s = new myStack<>();
        s.push(1);
        s.push(2);
        System.out.println(s.pop());
        s.push(3);
        s.push(4);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        s.push(5);
        System.out.println(s.pop());
    }
}
