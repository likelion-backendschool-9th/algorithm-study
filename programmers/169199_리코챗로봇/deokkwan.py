from collections import deque

def solution(board):
    height = len(board)
    width = len(board[0])
    start = (-1, -1, 0)
    
    # 시작점 찾기
    for i in range(height):
        for j in range(width):
            if board[i][j] == "R":
                start = (i, j, 0)
                break
        if start[0] > -1:
            break
    
    visited = [[False] * len(board[0]) for _ in range(len(board))] # 방문 기록
    q = deque([start]) # 방문한 곳을 담는 queue
    dr = [1, 0, -1, 0] # 행 방향 지정
    dc = [0, 1, 0, -1] # 열 방향 지정

    visited[start[0]][start[1]] = True # 시작점 방문 기록
    
    # BFS 시작
    while q:
        # r : 행 위치, c : 열 위치, cnt : 현재 위치까지 이동 횟수
        r, c, cnt = q.popleft()
        
        cnt += 1
        
        for i in range(4):
            now_r = r
            now_c = c
            next_r = r + dr[i]
            next_c = c + dc[i]
            
            # 장애물이나 게임판 끝을 만날 때까지 이동
            while 0 <= next_r < height and  0 <= next_c < width and board[next_r][next_c] != "D" :
                now_r = next_r
                now_c = next_c
                next_r += dr[i]
                next_c += dc[i]
            
            # 이미 방문한 곳일 때
            if visited[now_r][now_c]:
                continue
            
            # 현위치가 목적지인 경우
            if board[now_r][now_c] == "G":
                return cnt
    
            visited[now_r][now_c] = True # 방문 기록
            q.append((now_r, now_c, cnt)) # queue에 현 위치를 담기
    
    return -1 # 목표위치에 도달하는 경우가 없을 때