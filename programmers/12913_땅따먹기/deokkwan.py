def solution(land):
    answer = 0
    dp = land.copy()
    size = len(land) - 1
    
    for i in range(size):
        for j in range(4):
            dp[i + 1][j] = get_max(i, j, dp) + land[i + 1][j] 
    
    for i in range(4):
        answer = max(answer, dp[size][i])
        
    return answer
                           
def get_max(y, x, dp):
    return max(dp[y][(x + 1) % 4], max(dp[y][(x + 2) % 4], dp[y][(x + 3) % 4]))
                           