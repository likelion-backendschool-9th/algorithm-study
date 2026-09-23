def solution(video_len, pos, op_start, op_end, commands):
    v_min, v_sec = map(int, video_len.split(":"))
    v_time = total_sec(v_min, v_sec)
    
    pos_min, pos_sec = map(int, pos.split(":"))
    pos = total_sec(pos_min, pos_sec)
    
    op_start_min, op_start_sec = map(int, op_start.split(":"))
    op_start = total_sec(op_start_min, op_start_sec)
    
    op_end_min, op_end_sec = map(int, op_end.split(":"))
    op_end = total_sec(op_end_min, op_end_sec)
    
    if check_op(pos, op_start, op_end):
        pos = op_end
            
    for c in commands:
        if c == "prev":
            pos -= 10
            
            if pos < 0:
                pos = 0
                
        elif c == "next":
            pos += 10
            
            if pos > v_time:
                pos = v_time
        
        if check_op(pos, op_start, op_end):
            pos = op_end
    
    
    minute = pos // 60
    second = pos % 60
    
    return ("0" if minute < 10 else "") + str(minute) + ":" + ("0" if second < 10 else "") + str(second)

def total_sec(minute, second):
    return minute * 60 + second

def check_op(pos, op_start, op_end):
    if op_start <= pos <= op_end:
        return True
    
    return False
    