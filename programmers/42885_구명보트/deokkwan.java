import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
    
        Arrays.sort(people);

        int low = 0;
        
        for (int i = people.length - 1; i >= low; i--){
            if (people[i] == 0)
                break;
            
            if (limit - people[i] >= people[low])
                people[low++] = 0;
            
            answer++;
        }
        
        return answer;
    }
}