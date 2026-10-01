import java.util.*;

class Solution {
    
    static int[] answer = {0, 0};
    static int[][] box;
    
    public int[] solution(int[][] arr) {
        
        //전역 세팅
        box = arr;
        compress(box.length, 0, 0);
        
        return answer;
    }
    
    public void compress(int size, int row, int col) {
        
        // 영역 내의 모든 값이 같다면 true -> 카운트 하고 분할 종료
        if(check(size, row, col)){
            int value = box[row][col];      //0인지 1인지
            answer[value]++;
            
            return;
        }
        
        int half = size / 2;
        
        compress(half, row, col);               // 제 2사분면
        compress(half, row, col+half);          // 제 1사분면
        compress(half, row+half, col);          // 제 3사분면
        compress(half, row+half, col+half);     // 제 4사분면
        
    }
    
    // 영역 내 값이 모두 같은지 확인
    public boolean check(int size, int row, int col){
        int value = box[row][col];      //0인지 1인지
        
        for(int i=row; i<row+size; i++){
            for(int j=col; j<col+size; j++){
                if(box[i][j] != value){
                    return false;
                }
            }
        }
        
        return true;
    }
}
