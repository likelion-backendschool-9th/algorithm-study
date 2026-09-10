import java.util.*;

/*
    시작 시간: 26.09.08 오후 2시 11분
    종료 시간: 26.09.08 오후 
    알고리즘: 그리디 + 투포인터
    포인트:
        1. 그리디: 가장 가벼운 사람을 먼저 태울 때, 남은 사람 중 가장 무거운 사람을 같이 태울 수 있는지 가정.
        2. 투포인터: 배열 정렬, 양 끝 사람을 가벼운 사람(start), 무거운 사람(end)로 설정 후 양쪽 포인터를 이동시키며 탐색. 
        3. 문제 포인트: 만일, 무거운 사람을 같이 태우지 못할 경우, 가장 가벼운 사람도 함께 태우지 못한다는 것이 증명되었으니 단독으로 answer 증가. 
*/
class Solution {
    
    public int solution(int[] people, int limit) {
        int start = 0, end = people.length-1;
        int answer = 0;
        
        Arrays.sort(people);
        
        while(start <= end){
            if(people[start]+people[end] <= limit){
                start++;
            }
            
            end--;
            answer++;
        }
        
        return answer;
    }
    
}
