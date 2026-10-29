def solution(a, b):
    answer = 0
    
    check_a = True
    check_b = True
    
    if a % 2 == 0:
        check_a = False
    if b % 2 == 0:
        check_b = False
    
    if check_a and check_b:
        answer = a ** 2 + b ** 2
    elif not check_a and not check_b:
        answer = abs(a - b)
    else:
        answer = 2 * (a + b)
    
    return answer