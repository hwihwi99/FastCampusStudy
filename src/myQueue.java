/*
 * 큐
 * FIFO : first in first out
 * LILO : last in last out
 *
 * enqueue : 큐에 데이터 넣기
 * dequeue : 큐에 있는 데이터 빼기
 *
 * 자바에서의 큐 자료구조를 사용하기
 * java.util 패키지에 Queue 클래스르 제공하고 있음
 * enqueue -> Queue 클래스에선 add(value), offer(value) 메서드를 제공하고 있다.
 * dequeue -> Queue 클래스에선 poll(), remove() 메서드를 사용한다.
 *
 * Queue <Integer> queue = new LinkedList<>();
 * 이렇게 사용해야 한다.
 * queue.offer(Object o) -> 이러면 성공하면 true, 실패하면 false를 반환한다.
 *
 * 큐가 어디에 많이 쓰일까요?
 * 멍티 태스킹을 위한 프로세스 스케쥴링 방식을 구현하기 위해 많이 사용된다. (운영체제)
 * 스케쥴링의 방식을 이해하면 큐의 필요성을 알게 된다.
 * * * */

import java.util.*;

public class myQueue<T> {
    // ArrayList 를 이용해서 Queue의 enqueue dequeue 기능 구현해보기
    ArrayList <T> queue = new ArrayList<>();

    public void enqueue(T value){
        // 맨 뒤에 값 넣기
        queue.add(value);
    }

    public T dequeue(){
        if(queue.isEmpty())
            return null;
        return queue.remove(0);
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        myQueue<Integer> q = new myQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

    }


}
