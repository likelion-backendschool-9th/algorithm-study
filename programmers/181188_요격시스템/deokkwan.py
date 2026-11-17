def solution(targets):
    answer = 0
    targets.sort(key=lambda x:x[1])
    
    now = 0
    for target in targets:
        s, e = target
        if s >= now:
            now = e
            answer += 1
    
    return answer