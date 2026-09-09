import java.util.*;

/*
시작: 26.09.10 오전 1시 00분
종료: 26.09.10 오전 1시 18분

구분: 수학(?)/구현
*/

class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int walletMin = Math.min(wallet[0], wallet[1]);
        int walletMax = Math.max(wallet[0], wallet[1]);
        
        while(true){
            // 더 큰값 구하기
            int point = bill[0]<bill[1]?1:0;
            
            // 접어서 지갑에 들어갈 때
            if(bill[point] <= walletMin){
                break;
            } 
            
            //돌려서 지갑에 들어갈 때
            int billMin = Math.min(bill[0], bill[1]);
            if(bill[point] <= walletMax && billMin <= walletMin){
                break;
            }
            
            answer++;
            bill[point] /= 2;
        }
        
        return answer;
    }
}
