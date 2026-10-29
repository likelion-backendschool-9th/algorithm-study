import java.util.*;

class Solution {
    public int solution(int a, int b) {
        int answer = 0;
        
        boolean ac = (a % 2 == 0), bc = (b % 2 == 0);
        
        if(ac && bc) {
            answer = Math.abs(a-b);
        } else if(ac||bc){
            answer = 2 * (a+b);
        } else {
            answer = (int)(Math.pow(a, 2) + Math.pow(b, 2));
        }
        
        return answer;
    }
}
