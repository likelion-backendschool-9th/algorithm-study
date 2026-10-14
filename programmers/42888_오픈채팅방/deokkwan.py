def solution(record):
    answer = []
    member = {}
    result = []
    
    for r in record:
        now = r.split()
        command = now[0]    
        uid = now[1]
        
        if len(now) > 2:
            member[uid] = now[2]
        result.append((command, uid))
            
    for r in result:
        command, uid = r
        name = member[uid]
        now = name
        
        if command == "Enter":
            now += "님이 들어왔습니다."
        elif command == "Leave":
            now += "님이 나갔습니다."
        else:
            continue
            
        answer.append(now)
        
    return answer