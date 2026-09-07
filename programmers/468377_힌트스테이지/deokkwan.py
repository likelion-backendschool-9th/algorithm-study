def solution(cost, hint):
    answer = [float('inf')]
    n = len(cost)
    
    def dfs(i, idx_arr, total):
        if i == n - 1:
            answer[0] = min(answer[0], total + cost[i][idx_arr[i]])
            return
        
        total += cost[i][idx_arr[i]]
        dfs(i + 1, idx_arr, total)
        
        new_idx_arr = idx_arr[:]
        for j in hint[i][1:]:
            if new_idx_arr[j - 1] == n - 1:
                continue
            
            new_idx_arr[j - 1] += 1
        
        
        dfs(i + 1, new_idx_arr, total + hint[i][0])
    
    
    dfs(0, [0] * n, 0)    
    
    return answer[0]