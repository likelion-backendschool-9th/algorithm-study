def solution(users, emoticons):
    answer = [0] * 2
    size = len(emoticons)

    # 모든 경우의 수 탐색
    def dfs(idx, result):
        if idx == size:
            nonlocal answer
            
            cnt, total = calc(users, emoticons, size, result)
            
            if  answer[0] < cnt: # 가입자 수가 기록된 것보다 많은 경우
                answer[0] = cnt
                answer[1] = total
            elif answer[0] == cnt and answer[1] < total: # 가입자 수는 같은데, 쓴 돈이 더 많은 경우
                answer[1] = total
    
            return
        
        for i in range(4):
            result[idx] = i
            dfs(idx + 1, result)
            
        
    dfs(0, [-1] * size)
    
    return answer

def calc(users, emoticons, size, discounts):
    # 계산용 배열
    discount_rate = [40, 30, 20, 10]
    probability = [0.4, 0.3, 0.2, 0.1]
    
    cnt = 0
    total = 0
    for user in users:
        now = 0
        
        for i in range(size):
            idx = discounts[i]
            
            # 유저가 구입하는 최소 할인율과 비교
            if user[0] <= discount_rate[idx]:
                now += emoticons[i] - (emoticons[i] * probability[idx])
        
        if now >= user[1]:
            cnt += 1
        else:
            total += now
            
    return (cnt, total)