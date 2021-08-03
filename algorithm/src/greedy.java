/*
* 탐욕 알고리즘(greedy Algorithm)
* 최적의 해에 가까운 값을 구하기 위해 사용된다.
* 매 순간 최적이라고 생각되는 경우를 선택하는 방식
*
* 이게 진짜 최적일지에 대한 보장은 없다.
* 하지만 매 순간 최적인 순간을 찾아가면 최적에 가까운 값을 구할 수 있다.
*
* 예제 1) 동전 문제 -> 아래 코드 있음
* 4720원을 지불해야되는데 1,50,100,500원이 있다. 동전을 가장 적게 내는 법은?
* 4720 / 500 = 9
* 220 / 100 = 2
* 20 / 1 = 20
* -> 31개의 동전 사용
*
* 예제 2) 부분 배낭 문제 (Fractional Knapsack Problem)
* 무게 제한이 k인 배낭에 최대 가치를 가지도록 물건을 넣는 무제
*
* 각 물건에 대한 무게, 가치 값이 존재한다.
* *아이디어*
* 무게당 가치가 가장 높은 것 부터 넣자! == 가치/무게 값이 큰 물건부터!
*
* 물건은 쪼갤 수 있어서 물건의 일부분이 배낭에 넣어질 수 있다.
*
* 객체 배열을 정렬해야 할 때..-> Comparable, Comparator 인터페이스를 사용
* Comparable 인터페이스 -> compareTo() 메서드를 override해서 구현
* 정렬할 객체에 implements로 Comparable 인터페이스를 추가해서 구현함
*
* Comparator 인터페이스 -> compare() 메서드를 override해서 구현
* 별도의 클래스를 정의해서 구현하며 동일 객체에 다양한 정렬 기준을 가진 클래스를 작성할 수 있다.
* (즉, 정렬 기준이 여러개 일 때)
*
* 만약 comparable, comparator 두개가 다 정의되어 있다면 comparator의 정렬기준을 따른다.
*
* 탐욕 알고리즘의 한계
* 오직 근사치 추저! -> 최적의 해는 아니다.
* *
*/

import java.util.*;

public class greedy {
    // 동전 문제
    public static int coin(int price, ArrayList<Integer> coinList){
        Collections.sort(coinList);
        Collections.reverse(coinList);
        int count = 0;
        for(int coin : coinList){
            System.out.println(coin+"원 : "+price/coin+"개");
            count += price/coin;
            price %= coin;
        }
        return count;
    }

    public static void knapsack(int [][]objectList, double capacity) { //capacity ==> 넣을 수 있는 무게
        double totalValue = 0.0;
        double fraction = 0.0; // 하나의 물건을 쪼개서! 몇 프로 들어갔는지!
        Arrays.sort(objectList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o2[1] / o2[0]) - (o1[1] / o2[0]);
            }
        });

        // 위에서 정렬을 했기때문에 무게당 가치가 가장 큰것부터 내림차순 되어있다.
        for (int i = 0; i < objectList.length; i++) {
            // 물건을 쪼갤필요 없어
            if ((capacity - (double) objectList[i][0]) > 0) {
                capacity -= (double) objectList[i][0]; // 무게 빼고
                totalValue += (double) objectList[i][1]; // 가치는 더하고
                System.out.println("무게 : " + objectList[i][0] + " 가치 : " + objectList[i][1]);
            }
            // 물건을 쪼개야해
            else {
                fraction = capacity / (double) objectList[i][0]; //이제 다 넣었으니깐 capacity는 0인된다.
                totalValue += (double) objectList[i][1] * fraction;
                System.out.println("무게 : " + objectList[i][0] + " 가치 : " + objectList[i][1] + " 비율 : " + fraction);
                break;
            }
        }
        System.out.println("총 담을 수 있는 가치 : " +totalValue);
    }
//    // 배낭 문제 -> 객체 만들기
//    public static class Bag implements Comparable<Bag>{
//        int weight;
//        int worth;
//        double ans;
//        Bag(int weight, int worth){
//            this.weight = weight;
//            this.worth = worth;
//            ans = worth/(double)weight;
//        }
//
//        // 특정 기준을 이용해서 정렬을 할 수 있다.
//        @Override
//        public int compareTo(Bag b){
//            return (int)b.ans - (int)ans;
//        }
//    }

    public static void main(String[] args) {
        // 문제 1) 동전 문제
        ArrayList<Integer> coin = new ArrayList<>();
        coin.add(1);
        coin.add(100);
        coin.add(50);
        coin.add(500);
        System.out.println("총 동전의 갯수 : "+coin(4720,coin)+"개");

        // 문제 2) 부분 배낭 문제

        int [][]object = {{10,10},{15,12},{20,10},{25,8},{30,5}};
        knapsack(object,30.0);

        // 정렬 기준 정의하기

    }
}
