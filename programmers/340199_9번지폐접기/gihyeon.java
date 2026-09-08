class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        int walletWidth = Math.max(wallet[0], wallet[1]);
        int walletHeight = Math.min(wallet[0], wallet[1]);
        
        while(true) {
            int billWidth = Math.max(bill[0], bill[1]);
            int billHeight = Math.min(bill[0], bill[1]);
            if(walletWidth >= billWidth && walletHeight >= billHeight) break;
            
            if(bill[1] > bill[0]) bill[1]/= 2;
            else bill[0]/=2;

            answer++;
        }
        return answer;
    }
}