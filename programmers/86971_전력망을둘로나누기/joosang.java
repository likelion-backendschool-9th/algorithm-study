import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        int n = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(solution(n, wires));
    }

    public static int solution(int n, int[][] wires) {
        int answer = -1;
        
        // cut번째 전선을 하나씩 끊어본다.
        for (int cut = 0; cut < wires.length; cut++) {
            // 인접 리스트 그래프 생성
            @SuppressWarnings("unchecked")
            List<Integer>[] graph = new ArrayList[n + 1];

            // 각 인접 리스트 배열 안에 양쪽 송전탑을 담을 ArrayList 객체 생성
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            // 끊을 전선을 제외하고 그래프 생성
            for (int i = 0; i < wires.length; i++) {
                // 연결하지 않을 전선 즉 끊을 전선은 그래프에서 제거
                if (i == cut) {
                    continue;
                }

                // wires 1개 배열 내 각 송전탑 번호 저장
                int a = wires[i][0];
                int b = wires[i][1];

                // graph 1개 배열에, 저장해둔 송전탑 번호 저장
                graph[a].add(b);
                graph[b].add(a);
            }

            // 한쪽 전력망의 송전탑 개수
            int count = bfs(graph);

            // 양쪽과의 개수 차이
            int difference = Math.abs(count - (n - count));

            // 개수 차이가 작은 번째에서 answer가 된다
            if (answer == -1 || difference < answer) {
                answer = difference;
            }
        }
        return answer;
    }

    // 깊이 우선 탐색
    private static int bfs(List<Integer>[] graph) {
        // 탐색이 완료된 true 항목을 저장 boolean 배열 (처음에는 전부 false)
        boolean[] visited = new boolean[graph.length];
        // 방문할 노드를 순서대로 저장
        // addFirst, addLast 등 양쪽에서 삽입 및 삭제 가능
        Queue<Integer> queue = new ArrayDeque<>();

        // 시작 노드를 넣어줘야 연결된 다른 노드가 탐색이 되고 for문 안의 queue도 돌아간다.
        // 시작 노드를 변수에 담음 (1부터 시작)
        int start = 1;
        // 시작 노드를 큐에 넣고 방문 처리
        queue.offer(start);
        visited[start] = true;

        // 방문한 노드를 카운트할 변수
        int count = 0;

        // 큐가 모두 처리될 때까지 반복
        while (!queue.isEmpty()) {
            // 현재 큐에 남은 맨 앞 노드를 꺼냄
            int current = queue.poll();
            // 꺼냈으니 카운트 증가
            count++;

            // 매개변수로 받아온 graph의 현재 노드를 돌림
            for (int next : graph[current]) {
                // 아직 방문 안 한 상태면
                if (!visited[next]) {
                    // 방문 처리
                    visited[next] = true;
                    // 다음 노드를 큐에 넣음
                    queue.offer(next);
                }
            }
        }

        return count;
    }
}