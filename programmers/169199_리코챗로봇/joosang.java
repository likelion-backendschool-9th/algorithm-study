import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public int solution(String[] board) {
        // 보드 크기를 구하고, 칸별 최소 이동 횟수와 탐색할 좌표를 담을 큐를 준비
        int rows = board.length;
        int cols = board[0].length();
        int[][] distance = new int[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();

        // 모든 칸을 미방문(-1)으로 초기화하면서, 시작점 R을 이동 횟수 0으로 큐에 저장
        for (int row = 0; row < rows; row++) {
            Arrays.fill(distance[row], -1);
            int startCol = board[row].indexOf('R');
            if (startCol >= 0) {
                distance[row][startCol] = 0;
                queue.offer(new int[]{row, startCol});
            }
        }

        // 상, 하, 좌, 우 순서로 이동할 때의 행과 열 변화량을 지정
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 큐에서 로봇이 멈춘 칸을 이동 횟수가 적은 순서대로 추출
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int moves = distance[row][col];

            // 목표 G에 처음 멈췄을 때의 이동 횟수가 최솟값
            if (board[row].charAt(col) == 'G') {
                return moves;
            }

            // 방향마다 다음 칸이 보드 안에 있고 장애물 D가 없는 동안 계속해서 슬라이드
            // G도 지나갈 수 있으며, 여기서는 실제로 멈출 칸의 좌표를 추출
            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextCol = col + direction[1];
                while (nextRow >= 0 && nextRow < rows
                        && nextCol >= 0 && nextCol < cols
                        && board[nextRow].charAt(nextCol) != 'D') {
                    nextRow += direction[0];
                    nextCol += direction[1];
                }

                // 반복이 끝난 좌표에서 한 칸 되돌아오면 실제로 멈추는 칸
                int stopRow = nextRow - direction[0];
                int stopCol = nextCol - direction[1];

                // 처음 방문한 칸이라면 현재 이동 횟수에 1을 더해 기록하고 큐에 입력
                // 바로 막혀 제자리에 있는 경우도 이미 방문한 칸이므로 패스
                if (distance[stopRow][stopCol] == -1) {
                    distance[stopRow][stopCol] = moves + 1;
                    queue.offer(new int[]{stopRow, stopCol});
                }
            }
        }

        // 멈출 수 있는 칸을 모두 탐색한 뒤에도 G에 도착하지 못하면 -1을 반환
        return -1;
    }
}
