def solution(wallet, bill):
    answer = 0
    min_wallet = min(wallet[0], wallet[1])
    max_wallet = max(wallet[0], wallet[1])
    
    
    while(not check_size(min_wallet, max_wallet, bill)):
        if bill[0] >= bill[1]:
            bill[0] = bill[0] // 2
        else:
            bill[1] = bill[1] // 2
    
        answer += 1
    
    return answer

def check_size(min_wallet, max_wallet, bill):
    min_bill = min(bill[0], bill[1])
    max_bill = max(bill[0], bill[1])
    
    if min_wallet >= min_bill and max_wallet >= max_bill:
        return True
    return False