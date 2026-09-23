def solution(info, n, m):
    INF = float('inf')
    dp = [INF] * n # dp배열 초기화
    dp[0] = 0 
    
    # dp 갱신
    for a, b in info:
        new = [INF] * n # 변경사항을 반영할 배열
        
        for j in range(n):
            if dp[j] == INF: # 아직 A의 흔적이 해당 개수만큼 남지 않음
                continue
            
            new[j] = min(new[j], dp[j] + b) # 새로 초기화된 new[j]가 있다면 new[j] 선택
            
            if new[j] >= m: # new[j]의 값이 m보다 큰 경우 붙잡힘
                new[j] = INF
                
            if j + a < n: # A의 흔적이 늘어나는 경우
                new[j + a] = min(new[j + a], dp[j]) 
        
        dp = new # 변경된 dp배열로 교체
    
    for i in range(n):
        if dp[i] < INF: # 붙잡히지 않은 경우
            return i 
        
    return -1