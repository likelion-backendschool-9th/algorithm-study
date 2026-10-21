import java.util.*;

class Solution {
    
    // 1. 메모이제이션을 위해 2차원 배열 생성
    static int[][] memo;
    static int[][] arr;
    
    int solution(int[][] land) {
        arr = land;
        // 초기화 (-1은 아직 계산 안한 것)
        memo = new int[land.length][4];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        // 0번째 행의 0, 1, 2, 3번째 열에서 시작했을 때의 최댓값 중 가장 큰 값 선택
        int answer = 0;
        for (int i = 0; i < 4; i++) {
            answer = Math.max(answer, dp(0, i));
        }
        
        return answer;
    }
    
    // dp(height, col) : 현재 height 행의 col 열을 선택했을 때, 마지막 행까지 얻을 수 있는 최댓값
    public static int dp(int height, int col) {
        // 마지막 행을 넘어가면 0 반환
        if (height == arr.length) {
            return 0;
        }
        
        // 이미 계산한 적이 있다면 그 값을 그대로 재사용 ==> 메모이제이션 방식
        if (memo[height][col] != -1) {
            return memo[height][col];
        }
        
        int maxResult = 0;
        
        // 다음 행(height + 1)의 열(nextCol)을 순회
        for (int nextCol = 0; nextCol < 4; nextCol++) {
            // 같은 열은 연속해서 밟을 수 없으니 넘어가기
            if (col == nextCol) {
                continue;
            }
            
            // 다음 단계에서 얻을 수 있는 최댓값 저장
            maxResult = Math.max(maxResult, dp(height + 1, nextCol));
        }
        
        // 현재 칸의 점수 + 다음 단계들의 최댓값을 memo에 저장
        memo[height][col] = arr[height][col] + maxResult;
        return memo[height][col];
    }
}
