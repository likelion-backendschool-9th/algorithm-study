import math

def solution(arr):
    answer = []
    size = len(arr)
    
    answer = split_calc(arr, size // 2, 0, 0, size // 2)
    
    if answer[0] == -1:
        answer[0] = 0
    if answer[1] == -1:
        answer[1] = 1
    
    return answer

def split_calc(arr, max_size, r, c, size):
    cnt = [0, 0]
    
    if size == 1:
        for i in range(2):
            for j in range(2):
                cnt[arr[r + i][c + j]] += 1

        compress(cnt, cnt)

        return cnt
    
    next_size = size // 2
    split_cnt = []
    
    split_cnt.append(split_calc(arr, max_size, r, c, next_size))
    split_cnt.append(split_calc(arr, max_size, r + size, c, next_size))
    split_cnt.append(split_calc(arr, max_size, r, c + size, next_size))
    split_cnt.append(split_calc(arr, max_size, r + size, c + size, next_size))
    
    check = [0, 0]
    for z, o in split_cnt:
        if z == -1:
            cnt[0] += 1
            check[0] += 1
        else:
            cnt[0] += z  
            
        if o == -1:
            cnt[1] += 1
            check[1] += 1
        else:
            cnt[1] += o
    
    compress(cnt, check)
              
    return cnt

def compress(cnt, check):
    if check[0] == 4:
        cnt[0] = -1
    if check[1] == 4:
        cnt[1] = -1
       