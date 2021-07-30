import java.util.ArrayList;

/*
* 탐색
* 여러 데이터 중에서 원하는 데이터를 찾아내는 것을 의미
*
* 순차탐색
* 데이터가 담겨있는 리스트를 앞에서부터 하나씩 비교해서 원하는 데이터를 챶는 방법
*
* 성능
* 하나씩 알아보니깐 시간 복잡도는 O(n)
* */
public class SequentialSearch {
    // 임의 배열이 있을 때, 원하는 데이터의 위치를 리턴하는 순차탐색 알고리즘 작성하기
    // 원하는 데이터가 없으면 -1을 리턴한다.
    public static int sequential(ArrayList list, int data){
        for(int i = 0; i<list.size();i++){
            if(list.get(i).equals(data))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 0; i<10;i++)
            list.add((int)(Math.random()*100));
        System.out.println(list);
        System.out.println(sequential(list, 10));
    }
}
