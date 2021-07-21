/*
 * 배열
 *
 * 같은 종류의 데이터를 효율적으로 관리하기 위해서
 * 같은 종류의 데이터를 순차적으로 저장
 *
 * 장점
 * 빠은 접근 가능 -> 인덱스를 이용한 접근 가능능
 *
 * 단점
 * 미리 배열의 최대 길이를 알아야 한다.
 * 데이터의 추가/ 삭제가 어렵다.
 *
 * primitive 자료형 vs Wrapper 클래스
 * primitive : int
 * Wrapper : Integer
 *
 * Wrapper 사용하는 이유
 * null을 용이하게 처리가능
 * ArrayList 등 객체만을 핸들린하는 기능을 사용하기 위해서
 *
 * List (인터페이스) vs ArrayList (클래스)
 * 클래스 : 일반 클래스와 추상 클래스(메소드 중 추상 메서드가 하나 이상 존재시)가 있다.
 * 인터페이스 : 모든 메서드가 추상 메서드인 경우 -> 인터페이스 속 정의된 추상 메서드는 모두 구현 필요
 * => 즉, 다양한 클래스를 상속받는 특정 인터페이스는 결국 동일한 메서드를 제공한다.
 *
 * ArrayList 메서드
 * get(int index) : index 번째 원소 삭제하지는 않고 그냥 거기 있는 원소만 반환
 * set(int index, Object o) : index 번째의 값을 o로 변경
 * add (Object o) : o를 마지막에 삽입
 * add(int index, Object o) : index 번째에 o 삽입
 * */

import java.awt.*;
import java.util.*;
import java.util.List;

public class Array {
    public static void main(String[] args) {
        Integer [] data_list = new Integer[10];
        data_list[0] = 1;
        System.out.println(data_list[0]); //1 출력
        System.out.println(data_list[1]); // null 출력 만약 int [] arr 이런식의 배열이였다면 0 출력

        Integer [] data_list1 = new Integer[] {1,2,3,4,5,6};
        System.out.println(Arrays.toString(data_list1));

        List<Integer> list = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();

        // List(인터페이스)로 선언된 변수는 아래와 같이 필요에 따라 다은 클래스를 쓸 수 있다.
        // 즉 구현상의 유연성을 제공한다.

        List<Integer> list2 = new ArrayList<>();
        list2 = new LinkedList<>(); // List의 기능을 같이 받아서 이렇게해도 같은 기능

        String [] dataset = {
                "Braund, Mr. Owen Harris",
                "Cumings, Mrs. John Bradley (Florence Briggs Thayer)",
                "Heikkinen, Miss. Laina",
                "Futrelle, Mrs. Jacques Heath (Lily May Peel)",
                "Allen, Mr. William Henry",
                "Moran, Mr. James",
                "McCarthy, Mr. Timothy J",
                "Palsson, Master. Gosta Leonard",
                "Johnson, Mrs. Oscar W (Elisabeth Vilhelmina Berg)",
                "Nasser, Mrs. Nicholas (Adele Achem)",
                "Sandstrom, Miss. Marguerite Rut",
                "Bonnell, Miss. Elizabeth",
                "Saundercock, Mr. William Henry",
                "Andersson, Mr. Anders Johan",
                "Vestrom, Miss. Hulda Amanda Adolfina",
                "Hewlett, Mrs. (Mary D Kingcome) ",
                "Rice, Master. Eugene",
                "Williams, Mr. Charles Eugene",
                "Vander Planke, Mrs. Julius (Emelia Maria Vandemoortele)",
                "Masselmani, Mrs. Fatima",
                "Fynney, Mr. Joseph J",
                "Beesley, Mr. Lawrence",
                "McGowan, Miss. Anna",
                "Sloper, Mr. William Thompson",
                "Palsson, Miss. Torborg Danira",
                "Asplund, Mrs. Carl Oscar (Selma Augusta Emilia Johansson)",
                "Emir, Mr. Farred Chehab",
                "Fortune, Mr. Charles Alexander",
                "Dwyer, Miss. Ellen",
                "Todoroff, Mr. Lalio"
        };
        System.out.println("이 배열이 갖는 아이템의 갯수는 : "+ dataset.length);
        int Mcount = 0;
        for(String str : dataset) {
            if(str.indexOf("M") != -1) {
                Mcount++;
            }
        }
        //str.indexOf(String Key) :문자 key가 존재하면 그 시작 위치를 리턴하고 아니면 -1을 반환한다.
        System.out.println("그 중 문자 M을 가지고 있는 아이템의 수는 : "+ Mcount);

    }
}
