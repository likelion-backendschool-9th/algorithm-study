class Solution {
    public int solution(int[] picks, String[] minerals) {
        return dfs(picks, minerals, 0);
    }

    // index부터 남은 광물을 캐는 데 필요한 최소 피로도
    private int dfs(int[] picks, String[] minerals, int index) {
        if (index >= minerals.length || // 광물을 전부 캤거나
            picks[0] + picks[1] + picks[2] == 0 // 다이아(picks[0]), 철(picks[1]), 돌(picks[2]) 남은 곡괭이가 없을 때
        ) {
            // 추가로 드는 피로도는 0으로 DFS 종료
            return 0;
        }

        int answer = Integer.MAX_VALUE;

        // 다이아 곡괭이, 철 곡괭이, 돌 곡괭이로 돌아가면서 채굴 시작
        for (int pick = 0; pick < 3; pick++) {
            // 해당 곡괭이(다이아, 철, 돌)가 0개면 넘어감
            if (picks[pick] == 0) {
                continue;
            }

            picks[pick]--; // 이번에 사용할 곡괭이를 하나 선택

            int fatigue = 0; // 피로도

            // 선택한 곡괭이로 광물 최대 5개를 연속으로 캐기
            for (int i = index; i < Math.min(index + 5, minerals.length); i++) {
                if (pick == 0) { // 다이아몬드 곡괭이
                    fatigue += 1; // 피로도 1 증가
                } else if (pick == 1) { // 철 곡괭이
                    fatigue += minerals[i].equals("diamond") ? 5 : 1; // 다이아몬드면 5, 철이나 돌이면 1
                } else { // 돌 곡괭이
                    if (minerals[i].equals("diamond")) {
                        fatigue += 25; // 다이아몬드면 25
                    } else if (minerals[i].equals("iron")) {
                        fatigue += 5; // 철이면 5
                    } else {
                        fatigue += 1; // 돌이면 1
                    }
                }
            }

            // 다음 5개는 새로운 곡괭이를 선택해서 캐기
            answer = Math.min(answer, fatigue + dfs(picks, minerals, index + 5));

            // 현재 가정의 다음 가정으로 넘어감 (ex. 다이아 곡괭이를 먼저 썼을 때, 나머지는 어떻게 캐는지)
            picks[pick]++;
        }

        return answer;
    }
}
