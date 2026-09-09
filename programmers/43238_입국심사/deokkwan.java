import java.util.Arrays;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long left = 0;
        long right;
        
        Arrays.sort(times);
        
        right = (long) times[times.length - 1] *  n;
        
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = countPerson(mid, times);
            
            
            if (count >= n) {
                right = mid - 1;
                answer = mid;
            }
            else {
                left = mid + 1;
            }
            
        }
        
        return answer;
    }
    
    public long countPerson (long mid, int[] times){
        long count = 0;
        
        for (int time : times){
            count += mid / time;
        }
        
        return count;
        
    }
}