def solution(mats, park):
    answer = -1
    max_size = 0
    height = len(park)
    width = len(park[0])
    
    for i in range(height):
        for j in range(width):
            if park[i][j] == "-1":
                max_size = max(max_size, count(park, i, j, height, width))

    for m in mats:
        if m <= max_size:
            answer = max(answer, m)
                
    return answer

def count(park, r, c, height, width):
    
    idx = 1
    while True:
        end_r = r + idx
        end_c = c + idx
        
        if end_r >= height or end_c >= width:
            break
        
        for i in range(idx + 1):
            now = c + i
            if now >= width or park[end_r][now] != "-1":
                return idx
        
        for i in range(idx):
            now = r + i
            if now >= height or park[now][end_c] != "-1":
                return idx
            
        idx += 1
        
    return idx