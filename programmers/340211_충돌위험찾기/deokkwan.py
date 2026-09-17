def solution(points, routes):
    answer = 0
    n = len(points) # 포인트 수
    x = len(routes) # 로봇 수
    m = len(routes[0]) # 경로 수
    location = [] # 로봇의 위치를 저장하는 배열
    dest_idx = [1] * x # 현재 로봇이 진행 중인 경로 표시
    
    # 로봇들의 시작 위치를 location에 저장
    for i in range(x):
        now_loc = points[routes[i][0] - 1][:] 
        location.append(now_loc)
    
    end = 0 # 반복문 확인
    while end < x:
        before_crash = set() # 현 시간에 로봇이 이동한 좌표 모음
        crashes = set() # 로봇끼리 부딪힌 좌표 모음
        
        for i in range(x):
            now_loc = location[i][:] # 로봇의 현재 위치
            now_dest = dest_idx[i]
            destination = points[routes[i][now_dest] - 1] # 로봇의 현재 목적지
            
            # 로봇이 움직임을 마친 경우
            if now_loc[0] == -1:
                continue
                             
            # set에 넣기 위한 자료형 tuple
            crash = (now_loc[0], now_loc[1])
            

            if crash in before_crash: # 같은 좌표에 로봇이 있는 경우
                crashes.add(crash)
            else:
                before_crash.add(crash) # 기록된 좌표에 로봇이 없는 경우
            
            # 현재 위치와 현재 목적지가 일치하는 경우
            if now_loc[:] == destination:
                if dest_idx[i] == m - 1: # 마지막 목적지인 경우
                    location[i] = [-1, -1]
                    end += 1
                    continue
                else: 
                    dest_idx[i] += 1
                    destination = points[routes[i][dest_idx[i]] - 1]
            
            now_loc = move(now_loc, destination) # 다음 좌표로 이동
            
            location[i] = now_loc # 현재 위치 저장
    
        answer += len(crashes) # 충돌 가능성이 있는 지점 저장
    return answer

def move(now_loc, destination):
    # 목적지까지 남은 거리 계산
    r_distance = destination[0] - now_loc[0]
    c_distance = destination[1] - now_loc[1]
            
    if r_distance != 0: # r부터 움직이기에,  r이 0이 아니면  r로 이동
        if r_distance > 0:
            now_loc[0] += 1
        else:
            now_loc[0] -= 1
    else: # r 목표에는 도달해서 c를 이동하는 경우
        if c_distance > 0:
            now_loc[1] += 1
        else:
            now_loc[1] -= 1
    
    return now_loc