import java.util.*;

class Solution {

    static int answer = Integer.MAX_VALUE;

    public int solution(int[][] cost, int[][] hint) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= cost.length; i++) {
            map.put(i, 0);
        }

        dfs(0, 0, cost, hint, map);

        return answer;
    }

    static void dfs(int stage, int totalCost,
                    int[][] cost, int[][] hint,
                    Map<Integer, Integer> map) {

        if (stage == cost.length) {
            answer = Math.min(answer, totalCost);
            return;
        }

        int hintCount = map.get(stage + 1);

        hintCount = Math.min(hintCount, cost.length - 1);

        int currentCost = cost[stage][hintCount];

        dfs(
            stage + 1,
            totalCost + currentCost,
            cost,
            hint,
            map
        );

        if (stage < cost.length - 1) {

            int bundleCost = hint[stage][0];

            for (int i = 1; i < hint[stage].length; i++) {

                int hintNumber = hint[stage][i];

                map.put(
                    hintNumber,
                    map.get(hintNumber) + 1
                );
            }

            dfs(
                stage + 1,
                totalCost + currentCost + bundleCost,
                cost,
                hint,
                map
            );

            for (int i = 1; i < hint[stage].length; i++) {

                int hintNumber = hint[stage][i];

                map.put(
                    hintNumber,
                    map.get(hintNumber) - 1
                );
            }
        }
    }
}