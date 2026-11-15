import java.util.*;

/*
[처음 접근]
split_limit을 소인수분해하는 수학 문제로 접근.

[이후 재접근 과정]
- 2, 3 둘 중 하나만 선택 가능함. 
- 범위가 좁음. 
- 그렇다면 => 트리에서 어떤 층에 분배 노드를 몇 개 배치할지 결정하는 탐색(DFS/완전탐색) 문제.
- 2개인지 3개인지만 결정하면 되는 재귀로 풀이 가능?

[부족한 점]
- 상태(State)를 변수로 쪼개는 연습 및 사고 방식이 안되어 있음. 재귀는 변수를 줄이고 싶다는 생각 때문인지 복잡하게 가는 게 싫어서 인지 자꾸 늘리는 쪽으로 생각이 안됨. 
- 재귀 함수를 짤 때, 한 단계에서 다음 단계로 넘어갈 때 변하는 값이 무엇인지 명확히 구분하는 연습 필요. 

- 한번 더 풀어볼 때 주의할 점: "내가 지금 추적해야 할 숫자가 무엇이고 몇 개인가?"를 꼭 노트에 적으며 설계하기.
*/


class Solution {
    static long standard, answer=1;
    
    public int solution(int dist_limit, int split_limit) {
        standard = split_limit;
        dfs(1, 1, 1, dist_limit);
        
        return (int) answer;
    }
    
    public static void dfs(long split, long totalLeaves, long curNodes, long count) {
        // 지금까지 모인 totalLeaves로 answer 최댓값 갱신
        answer = Math.max(answer, totalLeaves);
        
        // 더 이상 쓸 수 있는 dist_limit이 없거나
        //현재 층에 후보 노드가 없으면(curNodes == 0) 탐색 종료
        if(count == 0 || curNodes==0){
            return;
        }

        // 현재 층 후보 노드(curNodes)와 남은 기회(remainDist) 중 더 작은 값만큼만 분배 노드로 사용 가능
        long use = Math.min(curNodes, count);

        // (다음 층 탐색) 자식 수를 2개 또는 3개로 선택해서 내려가기
        for (int i = 2; i <= 3; i++) {
            long nextSplit = split * i; // 다음 층의 분배도 계산
            
            // (가지치기) 다음 분배도가 standard 이하일 때만 진행
            if (nextSplit <= standard) {
                // 다음 층의 totalLeaves 계산
                long nextLeaves = totalLeaves - use + (use * i);
                // 다음 층의 후보 노드 개수(nextNodes) 계산
                long nextNodes = use * i;

                // 남은 dist_limit(remainDist - use)과 함께 dfs 재귀 호출
                dfs(nextSplit, nextLeaves, nextNodes, count - use);
            }
        }
    }
}
