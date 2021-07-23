/*
* 해쉬 테이블 (Hash Table)
*
* key와 value를 매핑할 수 있는 데이터 구조
*       LeeHwiJeong(키) : 01022223333(value)
*
* 해쉬 함수를 통해 배열에 키에 대한 데이터를 저장할 수 있는 주소를 계산
*       * 해쉬 함수란?
*          임의의 데이터를 고정된 길이의 값으로 리턴해주는 함수
*       * 해쉬, 해쉬 값, 해쉬 주소
*           해싱 함수를 통해 리턴된 고정된 길이의 값
*
* key를 통해 바로 데이터가 저장되어 있는 주소를 알 수 있어 저장, 탐색 속도가 빠르다.
*   => key값 -> 해쉬 함수로 인덱스 계산 -> 해당 인덱스로 해쉬 테이블 접근
*
* 해쉬 테이블은 해쉬 함수가 반환할 수 있는 값(주소)에 대한 공간들을 배열로 할당해 두어야 한다.
* 키에 따른 데이터 저장 및 탐색을 지원해준다.
*       * 해쉬 테이블이란?
*           키 값의 연산에 의해 직접 접근이 가능한 데이터 구조
*       * 슬롯
*           해쉬 테이블에서 한 개의 데이터를 저장할 수 있는 공간*
*
* 장점
* 데이터 저장/읽기 속도가 빠르다.
* 해쉬는 키에 대한 데이터가 있는지 확인이 쉽다.
*
* 단점
* 저장공간이 더 필요하다.
* 여러 키에 해당하는 주소가 동일할 경우(해쉬 값이 동일한 경우) -> 기존 값을 덮어 써버린다.
* 충돌을 해결하기 위한 별도의 자료구조가 필요하다.
*
* 주요 용도
* 검색이 많이 필요한 경우
* 저장 읽기 삭제가 빈번한 경우
* 캐쉬 메모리 같은거 구현할 때 ( 중복 확인이 쉽기 때문에)
*
* 충돌 해결 알고리즘
* 1. chaining 기법 _ 열린 해슁
*   해쉬 테이블 저장공간 외의 공간을 활용하는 기법
*   충돌이 일어나면 링크드 리스드라는 자료구조를 이용해서 데이터를 추가로 뒤에 연결시켜서 저장하는 기법
*
* 2. Linear Probing 기법 _ 폐쇄 해슁
*   해쉬 테이블 저장공간 안에서 충돌 문제를 해결하는 기법
*   충돌이 일어나면, 해당 해쉬 주소의 다음 주소부터 맨 처음에 나오는 빈공간에 저장하는 기법이다.
*       -> 저장공간의 활용도를 높이는 방법
* */

public class HashTable_chaining {
    // 객체 배열을 만들고
    public Slot[] hashTable;

    // 해쉬 테이블 크기를 정해주고
    public HashTable_chaining(Integer size){
        hashTable = new Slot[size];
    }

    public class Slot{
        String key;
        String value;
        Slot next;
        Slot (String key, String value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    //해쉬 함수 : 키가 문자열일때 앞 글자를 숫자로 변환해서 division 기법을 사용해
    //key에 대한 인덱스 번호로 계산하겠다.
    //이러면 크기가 미리 지정해둔 해쉬 테이블을 만들겠다는 의미이다.
    public int hashFunction(String key){
        return (int)(key.charAt(0))%hashTable.length;
    }
    //객체 배열 선언 시, 각 배열의 아이템은 각 객체를 참조할 수 있는
    //주소를 담을 수 있는 공간만 할당

    // 값을 해쉬 테이블에 저장하는 함수
    public boolean saveData(String key, String value){
        int address = hashFunction(key);
        // 해쉬가 차있다면? -> 체이닝 방법을 사용하므로 링크드 리스트로 연결해야한다.
        if(hashTable[address] != null){
            // 일단 둘 다 처음
            Slot findSlot = hashTable[address];
            Slot prevSlot = hashTable[address];
            while (findSlot != null) {
                if(findSlot.key == key){
                    findSlot.value = value;
                    return true;
                }
                prevSlot = findSlot;
                findSlot = findSlot.next;
            }
            prevSlot.next = new Slot(key,value);
        }else
        // 만약 해쉬가 비어 있었다면 그냥 값 대입해주면 된다.
        {
            this.hashTable[address] = new Slot(key,value);
        }
        return true;
    }

    // 저장되어있는 해당 키의 값을 반환해오고고
   public String getData(String key){
        int address = hashFunction(key);
        Slot findSlot = hashTable[address];
        while(findSlot != null){
           if(findSlot.key == key){
               return findSlot.value;
           }
           findSlot= findSlot.next;
        }
        return null;
    }


    // 체이닝 기법_ 열린 해슁 -> 충돌이 일어나면 링크드 리스트처럼


    public static void main(String[] args) {
        HashTable_chaining hashTable = new HashTable_chaining(20);
        hashTable.saveData("Lee","01011112222");
        hashTable.saveData("Park","01033334444");
        hashTable.saveData("Park1","01055554444");
        System.out.println(hashTable.getData("Lee"));
        System.out.println(hashTable.getData("lee"));
        System.out.println(hashTable.getData("Park"));
        System.out.println(hashTable.getData("Park1"));
    }
}
