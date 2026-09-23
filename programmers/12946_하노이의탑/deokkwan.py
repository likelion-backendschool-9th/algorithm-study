def solution(n):
    answer = []

    move(answer, 1, 3, n)
    
    return answer

def move(answer, start, end, cnt):
    if cnt == 1:
        answer.append([start, end])
        return
    
    next = 6 - start - end
    move(answer, start, next, cnt - 1)
    answer.append([start, end])
    move(answer, next, end, cnt - 1)