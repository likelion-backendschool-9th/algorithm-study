def solution(edges):
    size = max(max(e) for e in edges) + 1
    out_cnt = [0] * size
    in_cnt = [0] * size
    
    for x, y in edges:
        out_cnt[x] += 1
        in_cnt[y] += 1
        
    start = bar = eight = 0
    for i in range(1, size):
        now_out = out_cnt[i]
        now_in = in_cnt[i]
        
        if now_out == 2 and now_in >= 2:
            eight += 1
        elif now_out == 0 and now_in >= 1:
            bar += 1
        elif now_out >= 2 and now_in == 0:
            start = i
    
    return [start, out_cnt[start] - bar - eight, bar, eight]