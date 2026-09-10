def solution(cost, hint):
    answer = [float('inf')] # 큰 값 대입
    n = len(cost) # cost 배열의 길이로 총 스테이지 수를 구함
    
    # 재귀 함수를 통한 완전 탐색
    def dfs(i, idx_arr, total):
        if i == n - 1: # 재귀 함수를 멈추는 조건문
            answer[0] = min(answer[0], total + cost[i][idx_arr[i]])
            return
        
        total += cost[i][idx_arr[i]] # 현재 단계 cost를 total에 합
        dfs(i + 1, idx_arr, total) # 힌트 번들을 안샀을 때
        
    
        new_idx_arr = idx_arr[:] # 다음 재귀를 위해 복사
        for j in hint[i][1:]:
            if new_idx_arr[j - 1] == n - 1: # cost 배열 마지막 index를 가리키는 경우
                continue
            
            new_idx_arr[j - 1] += 1 
        
        dfs(i + 1, new_idx_arr, total + hint[i][0]) # 힌트 번들을 샀을때
    
    dfs(0, [0] * n, 0)
    
    return answer[0]