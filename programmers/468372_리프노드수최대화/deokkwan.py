def solution(dist_limit, split_limit):
    answer = 0
    
    # 완전 탐색
    x = 0
    while 2 ** x <= split_limit: # 2로 나누는 경우
        y = 0
        while (2 ** x) * (3 ** y) <= split_limit: # 3으로 나누는 경우
            dist_now = 0
            
            # 현재 분배도 계산
            for i in range(x):
                dist_now += 2 ** i
            
            for i in range(y):
                dist_now += (2 ** x) * (3 ** i)
            
            # 가능한 분배노드가 아직 남은 경우
            if dist_now <= dist_limit:
                result = (2 ** x) * (3 ** y) # 현재 리프 노드 수
                rest = dist_limit - dist_now # 남은 분배도
                can_split = min(rest, result) # 리프 노드 보다 많은 수의 분배 노드를 추가로 가질 수 없음
                
                # 추가 분배 계산
                if result * 3 <= split_limit: 
                    answer = max(answer, result + can_split * 2)
                elif result * 2 <= split_limit:
                    answer = max(answer, result + can_split * 1)
                else:
                    answer = max(answer, result)
            
            y += 1
    
        x += 1
    
    
    return answer