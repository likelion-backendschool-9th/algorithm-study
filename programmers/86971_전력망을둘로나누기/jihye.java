import java.util.*;

public class Solution {
    private static List<Integer>[] list;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < wires.length; i++) { 
            list = new ArrayList[n + 1];
            for (int j = 1; j <= n; j++) {
                list[j] = new ArrayList<>();
            }

            // i번째 전선을 제외하고 그래프 세팅
            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;
                list[wires[j][0]].add(wires[j][1]);
                list[wires[j][1]].add(wires[j][0]);
            }

            boolean[] visit = new boolean[n + 1];
            
            // DFS가 연결된 노드의 총 개수 반환
            int count = dfs(1, visit); 

            // 두 전력망의 차이 계산 후 최솟값 갱신
            answer = Math.min(answer, Math.abs((n - count) - count));
        }
        
        
        return answer;
    }

    // 방문한 노드 개수를 반환하는 DFS
    private static int dfs(int start, boolean[] visit) {
        visit[start] = true;
        int cnt = 1; // 자기 자신(시작 노드) 포함 1부터 시작

        for (int next : list[start]) {
            if (!visit[next]) {
                cnt += dfs(next, visit); // 자식 노드들이 반환한 개수를 누적
            }
        }
        return cnt; // 총 연결된 노드 수 반환
    }
}
