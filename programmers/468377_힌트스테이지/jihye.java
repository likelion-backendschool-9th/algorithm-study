import java.util.*;

/*
시작: 26.09.09 오전 12시 27분
종료: 26.09.09 오전 03시 04분

오전 2시 8분: DP -> 백트래킹으로 방향 전환, 전체 그림 파악 완.

회고
1. 더 깊게 들어갈 필요 없는 경우 가지치기
2. 디테일한 세부 조건 정확히 파악하고 설계하기
3. 백트래킹 시 다른 분기를 위해 복구해야 할 값과 시점 정확히 이해하기
*/

class Solution {
    
    static int minCost, endStage;
    static int[][] gCost, gHint;
    
    public int solution(int[][] cost, int[][] hint) {
        // 전역 변수 세팅
        endStage = cost.length;
        minCost = Integer.MAX_VALUE;
        gCost = cost;
        gHint = hint;
        
        backtracking(0, 0, new int[endStage+1]);
        
        return minCost;
    }
    
    public static void backtracking(int stage, int total, int[] hintCount) {
        
        // 더 깊게 들어갈 필요 없는 경우의 수 가지치기
        if(minCost <= total){
            return;
        }
        
        // 스테이지 끝까지 도달했을 경우 
        if(endStage <= stage) {
            minCost = Math.min(total, minCost);
            return;
        }
        
        
        // 1. 힌트권을 구매하지 않을 경우
        int maxHintIndex = gCost[stage].length - 1; // 이 스테이지 cost 배열의 마지막 인덱스 (안전장치)
        int clearCost = hintCount[stage] > maxHintIndex ? 
            gCost[stage][maxHintIndex] : gCost[stage][hintCount[stage]];
        
        backtracking(stage+1, total+clearCost, hintCount);
        
        
        // 2. 힌트권을 구매할 경우
        if(stage < endStage-1) {
            
            // 번들 가격
            int bundlePrice = gHint[stage][0];
            
            // 힌트권 개수 누적
            for(int i = 1; i < gHint[stage].length; i++){
                int index = gHint[stage][i]-1;
                hintCount[index]++;
            }
            
            // 구매할 경우 함수 호출
            backtracking(stage+1, total+clearCost+bundlePrice, hintCount);
            
            // 백트래킹을 위해 힌트권 개수 되돌리기
            for(int i = 1; i < gHint[stage].length; i++){
                int index = gHint[stage][i]-1;
                hintCount[index]--;
            }
        }
    }
}
