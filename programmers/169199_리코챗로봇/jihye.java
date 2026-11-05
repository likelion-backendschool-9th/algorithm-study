import java.util.*;

// BFS 문제
class Solution {
    public int solution(String[] board) {
        int answer = -1;
        int n = board.length;
        int m = board[0].length();
        
        // 맵 세팅
        int[][] dist = new int[n][m];
        for(int i=0; i<n; i++){
            Arrays.fill(dist[i], -1);
        }
        
        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                // 시작 위치 찾기
                if(board[i].charAt(j) == 'R'){
                    dist[i][j] = 0;
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        // BFS
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            
            if(board[x].charAt(y) == 'G'){
                answer = dist[x][y];
                break;
            }
            
            for(int d=0; d<4; d++){
                int nx = x, ny = y;
              
                // 벽에 부딧칠 때까지 이동
                while(true){
                    int tx = nx + dx[d];
                    int ty = ny + dy[d];
                    
                    // 범위에서 벗어난 경우 & 장애물인 경우 탐지
                    if(tx < 0 || ty < 0 || tx >= n || ty >= m || board[tx].charAt(ty) == 'D'){
                        break;
                    }
                    nx = tx;
                    ny = ty;
                }
                
                // 새로운 위치 등록
                if(dist[nx][ny] == -1){
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        return answer;
    }
}
