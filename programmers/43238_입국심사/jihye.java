import java.util.*;

/*
회고: 심사 받는 시간을 배열로 저장했다가 메모리 초과

알고리즘: 이분탐색
솔루션: 시간을 시작, 끝 점으로 설정하고 진행
배운 거: 자바에서 int와 long을 연산하면 자동으로 큰 타입인 long으로 변환(Promotion)되어 계산됨. 이분탐색 구현.
*/
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        Long start = (long) 1, end = (long) (times[times.length-1]* (long)n);
        
        while(start <= end){
            Long mid = (start+end)/2;
            long people=0;
            
            for(int i:times){
                people += mid/i;
            }
            
            if(n <= people) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        
        return start;
    }
}
