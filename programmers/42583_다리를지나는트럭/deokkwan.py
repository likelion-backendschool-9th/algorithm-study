from collections import deque

def solution(bridge_length, weight, truck_weights):
    answer = 1
    q = deque([[truck_weights[0], 1]])
    total = truck_weights[0]
    
    idx = 1
    length = len(truck_weights)
    while q:
        if q[0][1] >= bridge_length:
            total -= q[0][0]
            q.popleft()
        

        for i in range(len(q)):
            q[i][1] += 1
        

        
        if idx < length:
            now = truck_weights[idx]
        
            if len(q) < bridge_length and (total + now) <= weight:
                q.append([now, 1])
                total += now
                idx += 1
        
        answer +=1
    
    return answer