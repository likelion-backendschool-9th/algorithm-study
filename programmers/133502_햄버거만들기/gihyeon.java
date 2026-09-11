import java.util.*;
class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        List<Integer> stack = new ArrayList<>();
        for(int x : ingredient) {
            stack.add(x);
            if(stack.size() >= 4) {
                if(check(stack)) {
                    for(int i = 0; i < 4; i++) {
                        stack.remove(stack.size() - 1);
                    }
                    answer++;
                }
            }                
        }
        return answer;
    }
    
     static boolean check(List<Integer> stack) {
        int size = stack.size();
        if(stack.get(size - 1) == 1 && 
           stack.get(size - 2) == 3 && 
           stack.get(size - 3) == 2 && 
           stack.get(size - 4) == 1) return true;
        return false;
    }
}