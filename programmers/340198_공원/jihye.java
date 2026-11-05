import java.util.*;

class Solution {
    // 내림차순으로 정렬한 돗자리 크기들 (두 메소드에서 같이 쓰려고 전역 변수로 둠)
    Integer[] sortedMats;
    
    public int solution(int[] mats, String[][] park) {
        int answer = -1;        // 아무것도 못 깔면 -1 그대로 반환됨
        int n = park.length;    // 공원의 세로 길이
        int m = park[0].length; // 공원의 가로 길이
        
        // 전역 변수 처리
        sortedMats = new Integer[mats.length];
        for(int i=0; i<mats.length; i++){
            sortedMats[i] = mats[i];
        }
        
        // 큰 돗자리부터 시도하려고 내림차순 정렬.
        Arrays.sort(sortedMats, Collections.reverseOrder());
        
        // 공원의 모든 칸을 돗자리의 왼쪽 위 모서리 후보로 확인
        for(int i=0; i<n; i++){
            
            for(int j=0; j<m; j++){
                
                // 빈자리 일 때 
                if(park[i][j].equals("-1")){
                    // 이 칸에서 깔 수 있는 가장 큰 돗자리 크기를 받아옴 (없으면 -1)
                    int result = findMat(park, i, j);
                    
                    // 모든 칸의 결과 중 최댓값을 유지함
                    // (처음 성공한 값에서 바로 끝내면 뒤쪽 칸의 더 큰 돗자리를 놓치기 때문)
                    if(result > answer){
                        answer = result;
                    }
                    
                    // 가장 큰 돗자리가 이미 깔렸다면 더 볼 필요가 없으므로 바로 종료함
                    if(answer == sortedMats[0]){
                        return answer;
                    }
                }
            }
        }
        
        return answer;
    }
    
    // (x, y)를 왼쪽 위 모서리로 했을 때 깔 수 있는 가장 큰 돗자리 크기를 반환함
    // 어떤 돗자리도 못 깔면 -1을 반환함
    int findMat(String[][] park, int x, int y){
        int n = park.length;
        int m = park[0].length;
        
        // 안쪽 for문에서 바깥 for문의 다음 반복으로 넘어가기 위한 라벨임.
        // outer 바로 밑에 있는 for문이 되돌아가는 대상이 됨. 
        outer:
        for(int idx=0; idx<sortedMats.length; idx++){
            int size = sortedMats[idx]; // 큰 것부터 시도하기
            
            // 공원 밖으로 나가는 경우
            if(x + size > n || y + size > m){
                continue;
            }
            
            // size x size 영역에 사람이 있는지 전부 확인함
            for(int i=x; i<x+size; i++){
                for(int j=y; j<y+size; j++){
                    // "-1"이 아닌 값(알파벳)을 만나면 이 크기는 불가능함
                    // 라벨 continue로 돗자리 반복문의 다음 크기(idx+1)로 넘어감
                    if(!park[i][j].equals("-1")){
                        continue outer;
                    }
                }
            }
            
            // 영역 전체가 비어 있었다면 이 크기가 성공임
            // 큰 것부터 시도했으니 이 칸에서의 최대 크기가 됨
            return size;
        }
        
        // 모든 돗자리 크기가 실패한 경우
        return -1;
    }
}
