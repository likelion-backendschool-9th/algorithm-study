def solution(food):
    answer = ''
    half = ''
    
    for i in range(1, len(food)):
        cnt = food[i]
        if cnt % 2 == 1:
            cnt -= 1
        
        cnt //= 2
        
        for j in range(cnt):
            half += str(i)
        
    
    answer = half + "0" + half[::-1]
    
    return answer