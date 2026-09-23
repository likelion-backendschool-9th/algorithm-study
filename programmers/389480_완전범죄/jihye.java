class Solution {
    public int solution(int[][] info, int n, int m) {
        boolean dp[][] = new boolean[n][m];
        dp[0][0] = true;
        
        for(int i=0; i<info.length; i++){
            boolean next[][] = new boolean[n][m];
            
            for(int a=0; a<n; a++){
                for(int b=0; b<m; b++){
                    if(!dp[a][b]) continue;
                    
                    int newA = a + info[i][0];
                    if(newA < n){
                        next[newA][b] = true;
                    }
                    
                    int newB = b + info[i][1];
                    if(newB < m){
                        next[a][newB] = true;
                    }
                }
            }
            dp = next;
        }
        for(int a=0; a<n; a++){
            for(int b=0; b<m; b++){
                if(dp[a][b]){
                   return a; 
                }
            }
        }
        return -1;
    }
}
