import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    
    public int solution(int[] picks, String[] minerals) {
        // 광물 피로도 세팅 (돌 도끼 기준)
        map.put("diamond", 25);
        map.put("iron", 5);
        map.put("stone", 1);
        
        // 도끼의 수 구하기
        int num = 0;
        for(int i:picks){
            num += i;
        }
        
        // 캘 수 있는 묶음 수 = min(도끼 수, 광물 묶음 수)
        int groupCnt = Math.min(num, (minerals.length+4) / 5);
        
        // 묶음별 광물 개수 구하기 {다이아, 철, 돌}
        int[][] groups = getFatigue(groupCnt, minerals);
        
        // 다이아 많은 순 -> 같으면 철 많은 순
        Arrays.sort(groups, (a, b) -> {
            if( a[0] != b[0] ) return b[0] - a[0];
            return b[1] - a[1];
        });
        
        int point = 0;
        int answer = 0;
        for(int[] group:groups){
            // 다 쓴 도끼는 넘기기
            while(picks[point] == 0){
                point++;
            }
            
            // 도끼 사용 표시
            picks[point]--;
            
            answer+=getResult(point, group);
        }
        
        return answer;
    }
    
    // 도끼 하나로 묶음 하나 캘 때 피로도
    public static int getResult(int pick, int[] group){
        int response = 0;
        
        // 도끼 세기 : 다이아 25, 철 5, 돌 1
        int pickPower = (int) Math.pow(5, 2-pick);
        
        response += group[0] * Math.max(1, map.get("diamond") / pickPower);
        response += group[1] * Math.max(1, map.get("iron") / pickPower);
        response += group[2] * Math.max(1, map.get("stone") / pickPower);
        
        return response;
    }
    
    // 5개씩 쪼개서 묶음마다 광물 개수 세기
    public static int[][] getFatigue(int num, String[] minerals){
        int[][] response = new int[num][3];
        
        for(int i=0; i<num; i++){
            int start = i * 5;
            int end = Math.min(start+5, minerals.length);
            
            for(int j=start; j<end; j++){
                if(minerals[j].equals("diamond")) response[i][0]++;
                else if(minerals[j].equals("iron")) response[i][1]++;
                else response[i][2]++;
            }
        }
        
        return response;
    }
}
