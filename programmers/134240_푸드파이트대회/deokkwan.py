def solution(food):
    answer = ''
    half = ''
    
    for i in range(1, len(food)):
        cnt = food[i]
        cnt //= 2
        
        for j in range(cnt):
            half += str(i)
        
    
    answer = half + "0" + half[::-1]
    
    return answer