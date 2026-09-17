def solution(ingredient):
    answer = 0
    stack = []
    check = [1, 2, 3, 1]
    
    for i in ingredient:
        stack.append(i)
        
        if stack[-4:] == check:
            answer += 1
            del stack[-4:]
    
    return answer