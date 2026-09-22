import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] solution(int n) {
        List<int[]> moves = new ArrayList<>();
        move(n, 1, 3, 2, moves);
        // 배열로 변환
        return moves.toArray(new int[0][]);
    }

    // n : 옮겨야 하는 원판의 개수
    // from : 출발 기둥
    // to : 목적지 기둥
    // via : 보조 기둥
    // moves : 이동 상황을 저장하는 리스트
    private void move(int n, int from, int to, int via, List<int[]> moves) {
        // 시작 - 원판 개수:n, 출발 기둥:1, 목적지 기둥:3, 보조 기둥:2
        // 재귀 호출을 해도 moves는 매개변수로 가져오기 때문에 move 내에서 영향을 받지 않는다.

        // 옮길 원판이 없으면 그대로 이전 호출로 return (재귀 호출 종료)
        if (n == 0) return;
        // 맨 아래에 있는 1개 빼고(n-1) 전부 보조 기둥으로 옮김
        // 출발 기둥은 그대로, 목적지 기둥 <-> 보조 기둥
        move(n - 1, from, via, to, moves);
        // 위에 있는 재귀호출 완료 후, 가장 큰 원판만 남은 상태
        // 가장 큰 원판 하나를 출발 기둥에서 목적지 기둥으로 이동
        moves.add(new int[]{from, to});
        // 보조 기둥에 있는 원판을 목적지로 이동
        // 보조 기둥이 출발 기둥, 출발 기둥을 이제 보조 기둥으로 삼음
        move(n - 1, via, to, from, moves);
    }
}
