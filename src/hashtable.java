/*
* 해쉬 테이블 구조를 활용하여 구현된
* 자바 컬렉션 프레임웨크인 HashMap이 있다다*
*
*
*
* */

import java.util.HashMap;

public class hashtable{
    public static void main(String[] args) {
        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1,"사과");
        map1.put(2,"바나나");
        System.out.println(map1.get(1)); // 사과 출력
    }
}