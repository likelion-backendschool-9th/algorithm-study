from collections import deque

def solution(n, wires):
    answer = 100
    graph = [[False] * (n + 1) for _ in range(n + 1)] 
    
    for wire in wires:
        x = wire[0]
        y = wire[1]
    
        graph[x][y] = True
        graph[y][x] = True
    
    
    for wire in wires:
        x = wire[0]
        y = wire[1]
    
        graph[x][y] = False
        graph[y][x] = False
        
        visited = [False] * (n + 1)
        cnt = [0, 0]
        
        cnt[0] = bfs(n, graph, visited, 1) 
        
        for i in range(1, n+1):
            if not visited[i]:
                cnt[1] = bfs(n, graph, visited, i)
                break
    
        graph[x][y] = True
        graph[y][x] = True

    
        answer = min(answer, abs(cnt[0] - cnt[1]))
    

    return answer

def bfs(n, graph, visited, start):
    q = deque([start])
    visited[start] = True
    cnt = 0
    
    while q:
        node = q.popleft()
        for i in range(1, n + 1):
            now = graph[node][i]
            
            if visited[i] or not graph[node][i]:
                continue
            
            q.append(i)
            visited[i] = True
            cnt += 1
        
    return cnt