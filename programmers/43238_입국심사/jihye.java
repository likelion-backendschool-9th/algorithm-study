import java.util.*;

/*
회고: 심사 받는 경우의 수를 전부 배열로 저장하고 탐색 => 메모리 초과 발생.

알고리즘: 이분탐색
솔루션: 시간을 배열로 저장하지 않고 시작 시간(가장 짧은 심사 시간), 끝나는 시간(가장 심사가 오래 걸리는 시간 * 인원)으로 탐색 범위를 설정하고 진행
배운 거: 
    1. 자바에서 int와 long을 연산하면 자동으로 큰 타입인 long으로 변환(Promotion)되어 계산됨. 
    2. 이분탐색 구현 및 배열을 지정하지 않아도 값만으로 탐색이 가능함을 알게 되어 사고가 확장됨.
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
