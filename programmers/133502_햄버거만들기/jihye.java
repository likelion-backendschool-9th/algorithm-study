/*
시작: 26.09.14 오후 2시 24분
종료: 26.09.14 오후 2시 38분
*/

import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        int[] sequence = {1, 2, 3, 1};
        Stack<Integer> stack = new Stack<>();
        
        for(int i:ingredient){
            stack.add(i);
            
            if(sequence.length <= stack.size()){
                
                int size = stack.size();
                boolean check = true;
                
                for(int j=0; j<sequence.length; j++) {
                    if(stack.get((size-sequence.length)+j) != sequence[j]){
                        check = false;
                        break;
                    }
                }
                
                if(check) {
                    answer++;
                    
                    for(int j=0; j<sequence.length; j++){
                        stack.pop();
                    }
                }
                
            }
        }
        
        return answer;
    }
}
