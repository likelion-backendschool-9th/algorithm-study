/*
n명이 입국심사를 위해 줄을 서서 기다리고 있습니다.
각 입국심사대에 있는 심사관마다 심사하는데 걸리는 시간은 다릅니다.

처음에 모든 심사대는 비어있습니다.
한 심사대에서는 동시에 한 명만 심사를 할 수 있습니다.
가장 앞에 서 있는 사람은 비어 있는 심사대로 가서 심사를 받을 수 있습니다.
하지만 더 빨리 끝나는 심사대가 있으면 기다렸다가 그곳으로 가서 심사를 받을 수도 있습니다.

모든 사람이 심사를 받는데 걸리는 시간을 최소로 하고 싶습니다.

입국심사를 기다리는 사람 수 n, 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열 times가 매개변수로 주어질 때,
모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하도록 solution 함수를 작성해주세요.
*/

/*
[제한사항]
- 입국심사를 기다리는 사람은 1명 이상 1,000,000,000명 이하입니다.
- 각 심사관이 한 명을 심사하는데 걸리는 시간은 1분 이상 1,000,000,000분 이하입니다.
- 심사관은 1명 이상 100,000명 이하입니다.
*/

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        int n = 6; // 입국심사를 받아야 하는 사람 수
        int[] times = {7, 10}; // 각 심사관이 한 명을 심사하는데 걸리는 시간
        // (심사관은 1명 이상 100,000명 이하, 사용자가 입력하는 단계에서 제약을 만족하는 것으로 가정)
        System.out.println(solution(n, times));
    }

    public static long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long start = 0; // 최소 시간 (1분부터)
        long end = (long) times[times.length - 1] * n; // 최대 시간 (가장 오래 걸리는 심사위원 시간 * 입국신청자수)
        // 오버플로우 방지를 위해, int값인 'times[times.length - 1] * n'를 (long)으로 형변환
        // 적어도 1,000,000,000분(Long.MAX_VALUE)보다는 이하 범위다.
        long middle = 0; // 도출해낸 최소 시간 (현재까지 확인된 중간값 시간)

        // 이진 탐색
        while (start <= end) {
            middle = (start + end) / 2; // 1부터 end까지의 시간 중에서 최소값

            // middle 시간(도출된 중간값 시간) 동안 심사 가능한 사람 수 계산
            long count = 0;
            for (int i = 0; i < times.length; i++) {
                count += middle / times[i]; // middle 시간 / 심사관 한 명 시간
                // middle=28, times[0]=7 → count += 28/7 = 4
                // middle=28, times[1]=10 → count += 28/10 = 2
            }

            // 심사 가능한 사람 수가 n(입국심사를 받아야 하는 사람 수) 이상이면
            if (count >= n) {
                answer = middle; // 현재 middle를 답의 후보로 저장
                end = middle - 1; // 더 짧은 시간이 있나 확인하기 위해 범위 축소
            } else {
                start = middle + 1;  // 더 긴 시간 필요
            }
            // start와 end의 범위가 좁혀지고 다음 루프 시작
            // start가 end보다 크거나 같으면 종료
        }

        return answer;
    }
}
