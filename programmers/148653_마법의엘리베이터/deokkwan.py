def solution(storey):
    answer = 0
    str_num = str(storey) # 현재 층수를 문자열로
    size = len(str_num) # 문자열의 길이

    str_num = str_num[::-1] # 1층부터 보기 위해 뒤집기
    up = False
    for i in range(size):
        now = int(str_num[i])

        # 이전 자리에서 올림이었던 경우
        if up: 
            now += 1 
            up = False

        # 마지막 index인 경우
        if i == size - 1: 
            if now <= 5:
                answer += now
            else: # 올림하는 게 더 빠른 경우
                answer += 10 - now + 1
            break

        next = int(str_num[i + 1]) # 다음 자릿수

        # 5보다 작으면 내림, 5보다 큰 경우 올림
        # 5인 경우 다음 자릿수가 5보다 크거나 같으면 올림, 5보다 작으면 내림
        if now < 5: 
            answer += now
        elif now > 5:
            answer += 10 - now
            up = True
        elif next >= 5:
            answer += 10 - now
            up = True
        else:
            answer += now
    
    return answer