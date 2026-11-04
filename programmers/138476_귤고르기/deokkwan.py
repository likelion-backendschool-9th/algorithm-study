def solution(k, tangerine):
    answer = 0
    cnt = dict()
    
    for t in tangerine:
        if t in cnt:
            cnt[t] = cnt.get(t) + 1
        else:
            cnt[t] = 1
    
    arr = sorted(cnt.items(), key=lambda x:x[1], reverse=True)

    for a in arr:
        answer += 1
        k -= a[1]
        
        if k <= 0:
            break
    
    return answer