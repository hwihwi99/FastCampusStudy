/*
* 충돌기법중 하나로
* 해쉬 테이블 저장공간 안에서 충돌 문제를 해결하는 기법
 *   충돌이 일어나면, 해당 해쉬 주소의 다음 주소부터 맨 처음에 나오는 빈공간에 저장하는 기법이다.
 *       -> 저장공간의 활용도를 높이는 방법
* */

/*
 * 빈번한 충돌을 개선하는 기법
 * 해쉬 테이블 저장공간을 확대하고 해쉬 함수를 재정의한다.
 *
 * 앞의 예제에서의 해쉬 함수는
 * 문자열을 앞글자를 아스키코드로 바꾸고 테이블 크기로 나눈 것을 주소호
 *
 * 이번 해쉬 함수는 예를 들어서
 * 모든 문자열의 문자를 아스키코드로 변환 후 다 더해서 해쉬 테이블의 크기로 나누고 이런식으로 하면 된다
 *
 * ----------------------------------------------------------------------------------------
 * public int hashFunction(String key){
        int address = 0;
        for (int i = 0; i < key.length(); i++) {
            address += key.charAt(i);
        }
        return address % hashTable.length;
    }
 * */


public class HashTable_LinerProbing {
    Slot[] hashTable;

    public HashTable_LinerProbing(int size){
        hashTable = new Slot[size];
    }

    // 이번에는 이 슬롯에 내 키에 해당되는 키가 저장되어 있다라는 보장이 없다.
    // 다만 이번에는 링크드 리스트가 아니라서 next 변수는 필요 없다.
    public class Slot{
        String key;
        String value;

        Slot(String key, String value){
            this.key = key;
            this.value = value;
        }
    }
    public int hashFunction(String key){
        return (int)(key.charAt(0)) % hashTable.length;
    }


    public void saveData(String key, String value){
        int address = hashFunction(key);
        if(hashTable[address] != null){
            // 현재 키값가 지금 들어온 키값이 같으면 같을 걍 바꿔치기
            if(hashTable[address].key == key){
                hashTable[address].value = value;
            }
            // 아니면 -> 이제 다음 칸으로 이동한다.
            else{
                int currentAddress = address + 1;

                // 다음 칸으로 왔는데 차있어? 그러면 그 칸의 키 값과 내 키값이 같은지 확인!
                while(hashTable[currentAddress]!=null){
                    //같으면 덮어쓰기
                    if(hashTable[currentAddress].key == key){
                        hashTable[currentAddress].value = value;
                    }
                    // 다르면
                   else{
                        currentAddress ++;
                        // 만약 마지막으로 왔다면? => 실패 => 근데 이거 다시 앞으로 돌려서 탐색가능하지 않을까하는게 의문..
                        if(currentAddress >= hashTable.length){
                            return;
                        }
                    }
                }
                hashTable[currentAddress] = new Slot(key,value);
            }
        }else{
            hashTable[address] = new Slot(key, value);
        }
    }

    public String getData(String key){
        int address = hashFunction(key);
        // 해당 위치가 차있다면
        if(hashTable[address].key != null){
            // 지금 키랑 같은지 확인 -> 같으면? 그거 반환
            if(hashTable[address].key == key){
                return hashTable[address].value;
            }else{
                // 다음칸으로 가서
                int currentAddress = address + 1;
                // 다음 슬롯이 비어있지 않다면
                while(hashTable[currentAddress] != null){
                    // 만약 키가 같으면 반환
                    if(hashTable[currentAddress].key == key){
                        return hashTable[currentAddress].value;
                    }else{
                        currentAddress++;
                        if(currentAddress >= hashTable.length)
                            return null;
                    }
                }
            }
        }
        // 만약 while문을 빠져 나와버렸다면 그 키는 없는겨! -> null 반환하기
        return null;
    }

    public static void main(String[] args) {
        HashTable_LinerProbing hashTable = new HashTable_LinerProbing(20);
        hashTable.saveData("Lee","01011112222");
        hashTable.saveData("Park","01033334444");
        hashTable.saveData("Park1","01055554444");
        System.out.println(hashTable.getData("Lee"));
        System.out.println(hashTable.getData("lee"));
        System.out.println(hashTable.getData("Park"));
        System.out.println(hashTable.getData("Park1"));
    }

}
