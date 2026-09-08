/*
무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다.
구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게 제한도 있습니다.

예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 
구명보트의 무게 제한이 100kg이라면 2번째 사람과 4번째 사람은 같이 탈 수 있지만
1번째 사람과 3번째 사람의 무게의 합은 150kg이므로 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.

구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.

사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때,
'모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성'해주세요.
*/

/*
[제한사항]
- 무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다.
- 각 사람의 몸무게는 40kg 이상 240kg 이하입니다.
- 구명보트의 무게 제한은 40kg 이상 240kg 이하입니다.
- 구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.
*/

import java.util.Arrays;

class Solution
{
    public static void main(String[] args) {
        int[] people = {70, 50, 80, 50}; // 각 인원의 몸무게
        int limit = 100; // 구명보트의 무게 제한
        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people); // 이 부분에서 이미 오름차순 정렬이 됨 (작은 것에서 큰 것 순)
        int answer = 0;
        int lightest = 0; // 가장 왼쪽 (여기서는 people[0] 객체가 아니라 인덱스를 가져와야 한다.)
        int heaviest = people.length - 1; // 가장 오른쪽
        
        // 투 포인터 방식 정렬 (이미 위에서 오름차순 정렬함)
        while (lightest <= heaviest) {
            // '가장 가벼운 사람'과 '가장 무거운 사람'이 '구명보트의 무게 제한'을 넘지 않는 경우
            // 탐욕 기법(Greedy)으로 확인해서 태움 (남는 갯수를 최소화할 때 최적)
            if (people[lightest] + people[heaviest] <= limit) {
                lightest++;  // 가벼운 사람 탑승
            }
            heaviest--; // 무거운 사람은 항상 탑승
            answer++; // 확인됐으면 구명보트 1정 추가
        }

        return answer;
    }
}