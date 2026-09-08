import java.util.*;

//시작 시간: 26.09.08 오후 2시 11분
//종료 시간: 26.09.08 오후 
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
