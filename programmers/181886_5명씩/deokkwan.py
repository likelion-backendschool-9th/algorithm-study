def solution(names):
    answer = []
    size = len(names)
    
    idx = 0
    while idx < size:
        answer.append(names[idx])
        idx += 5
    
    return answer