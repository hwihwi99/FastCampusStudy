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
* */

public class HashTable {
    // 객체 배열을 만들고
    public Slot[] hashTable;

    // 해쉬 테이블 크기를 정해주고
    public HashTable(Integer size){
        hashTable = new Slot[size];
    }

    public class Slot{
        String value;
        Slot (String value){
            this.value = value;
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
        if(hashTable[address] != null){
            hashTable[address].value = value;
        }else{
            this.hashTable[address] = new Slot(value);
        }
        return true;
    }

    // 저장되어있는 해당 키의 값을 반환해오고고
   public String getData(String key){
        int address = hashFunction(key);
        if(hashTable[address] != null)
            return hashTable[address].value;
        else
            return null;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(20);
        hashTable.saveData("Lee","01011112222");
        hashTable.saveData("Park","01033334444");
        System.out.println(hashTable.getData("Lee"));
        System.out.println(hashTable.getData("lee"));
        System.out.println(hashTable.getData("Pee"));
    }
}
