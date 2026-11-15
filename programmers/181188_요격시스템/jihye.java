import java.util.*;

// 그리디(탐욕) 알고리즘. 
// 현재 내릴 수 있는 최선의 선택에만 집중하라.

// 문제 포인트: 뒤에서부터 확인한다. 

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // 2번째 원소 기준 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

        // 요격 포인트 끝점 설정
        int standard = targets[targets.length-1][1];
        
        for(int i=targets.length-1; i>=0; i--){
            int back = targets[i][1];
            int front = targets[i][0];

            // 끝점(back)이 지금 요격포인트보다 작거나 같을 경우 => 요격 포인트를 새롭게 잡아야 하기 때문에 교체 후 answer ++ 
            if(back <= standard) {
                standard = front;
                answer++;
            } else if(standard < front) {  // 끝점(back)은 요격 포인트보다 큰데(=요격 포인트 안에 이미 해당하는데), 시작점(front)도 요격 포인트 안에 있는 경우 => 요격 포인트를 좁힐 수 있기 때문에 교체만 진행. 
                standard = front;
            }
        }
        
        return answer;
    }
}
