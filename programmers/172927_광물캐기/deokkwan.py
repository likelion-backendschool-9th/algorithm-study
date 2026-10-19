def solution(picks, minerals):
    answer = 0
     
    d = {"diamond":100, "iron":10, "stone":1} # 광물 별 가치 설정
    energy = [[1, 1, 1], [5, 1, 1], [25, 5, 1]] # 피로도
    
    picks_cnt = (picks[0] + picks[1] + picks[2]) * 5 # 캘 수 있는 광물의 수
    
    # 캘 수 있는 광물의 수보다 광물이 많을 경우 그만큼 줄이기
    if len(minerals) > picks_cnt:
        minerals = minerals[:picks_cnt]
    
    size = len(minerals) // 5 # 광물을 5개씩 그룹으로 묶으면 몇 그룹이 나오는 지
    # 5개가 안되는 그룹이 생기는 경우
    if len(minerals) % 5 > 0:
        size += 1
    
    # 그룹별 광물 가치 합 기록
    values = [0] * size 
    for i in range(size):
        total = 0
        start = 5 * i
        
        for j in range(5):
            idx = start + j
            if idx >= len(minerals):
                break
            
            total += d.get(minerals[idx])
    
        values[i] = total
    
    # 광물의 가치가 높은(캘 때 피로도가 높은) 순으로 내림차순 정렬
    values.sort(reverse = True)
    
    pick = 0 # 현재 사용하는 곡괭이
    cnt = 0 # 현재 사용 중인 곡괭이를 몇개 사용했는지
    for v in values:
        # 곡괭이 확인
        # 남은 곡괭이가 있는지 and (현재 사용 중인 곡괭이를 다 사용했는지)
        while pick <= 2 and cnt == picks[pick] :
            pick += 1
            cnt = 0
            
        # 곡괭이를 다 사용한 경우
        if pick > 2:
            break
    
        # 다이아몬드 캘 때 피로도
        answer += (v // 100) * energy[pick][0]
        v %= 100
        
        # 철 캘 때 피로도
        answer += (v // 10) * energy[pick][1]
        v %= 10
        
        # 돌 캘 때 피로도
        answer += v * energy[pick][2]
        
        cnt += 1
        
    return answer