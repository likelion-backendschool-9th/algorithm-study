import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        // 끝점(e) 기준 오름차순 정렬
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));

        int answer = 0;
        int lastEnd = -1; // 마지막으로 쏜 요격 미사일 위치 기준이 되는 끝점

        for (int[] t : targets) {
            int s = t[0];
            int e = t[1];
            // 개구간 (s, e) 이므로 s == lastEnd 이면 같은 미사일로 요격 불가

            // s >= lastEnd 이면 새 미사일 필요
            if (s >= lastEnd) {
                answer++;
                lastEnd = e; // 현재 구간의 끝 직전(e - 0.5 같은 지점)에 발사
            }
        }
        return answer;
    }
}
