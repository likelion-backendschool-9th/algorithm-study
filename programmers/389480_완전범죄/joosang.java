import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {
        // "A도둑은 자신이 남긴 흔적의 누적 개수가 n개 이상이면 경찰에 붙잡힙니다."
        // A가 안전하려면 흔적 합이 n 미만(n-1 이하)여야 한다.
        // 따라서 A는 n 미만이어야 하므로 여기까지만 추적
        int maxA = n - 1;
        // maxA = A가 가질 수 있는 최대 흔적

        // maxBTraces[i] = A 흔적이 i일때 가능한 B 흔적의 최솟값
        // A 흔적이 i면, 가능한 최소 B 흔적은 i
        // A의 여러 상태를 각각 저장해야 하므로 배열로 만듬
        // 인덱스 -> A의 흔적, 값 -> B의 흔적
        int[] minBTraces = new int[maxA + 1];
        
        // minBTraces 배열의 모든 칸을 Integer.MAX_VALUE로 채움
        // 각 칸의 Integer.MAX_VALUE = 아직 도달할 수 없는 상태
        // A 흔적이 i인 경우는 아직 만들어진 적이 없다.
        Arrays.fill(minBTraces, Integer.MAX_VALUE);
        // dp 메서드에서는 도달할 수 없는 상태를 건너뛴다. (if문으로 검사함)

        // 시작 상태는 직접 0으로 바꾼다
        minBTraces[0] = 0; // A 흔적 0일 때 B 흔적 0

        // 물건 갯수(info.length)만큼 순회
        for (int[] item : info) {
            // 각 회차의 B의 흔적을 dp 메서드로 계산
            minBTraces = dp(minBTraces, item, maxA);
        }

        // 경찰에 잡히지 않는 범위에서의 A 흔적의 최솟값
        // 인덱스 a → A의 누적 흔적
        // minBTraces 각 배열 값 → 해당 A 흔적일 때 B의 최소 누적 흔적
        // "B도둑은 자신이 남긴 흔적의 누적 개수가 m개 이상이면 경찰에 붙잡힙니다."
        for (int a = 0; a <= maxA; a++) {
            if (minBTraces[a] < m) {
                return a;
            }
        }
        return -1;
    }

    private int[] dp(int[] current, int[] item, int maxA) {
        // 남아있는 훔칠 물건들을 next 배열로 생성
        int[] next = new int[maxA + 1];

        // 새로 만든 next 배열의 모든 칸을 도달 불가능 상태로 초기화
        for (int i = 0; i <= maxA; i++) {
            next[i] = Integer.MAX_VALUE;
        }

        // 경찰에 잡히는 순간(maxA) 전까지 물건을 훔침
        for (int prevA = 0; prevA <= maxA; prevA++) {
            // 도달할 수 없는 상태 -> Integer.MAX_VALUE
            // 이전 물건부터 지금까지 처리한 것까지의 배열 -> current
            // 남아있는 훔칠 물건들 -> next
            // 초기인 경우(0인 경우) continue 실행 안됨
            if (current[prevA] == Integer.MAX_VALUE) {
                continue;
            }

            // B가 훔치는 경우
            // 다른 경로를 통해 이미 계산해 놓은 B 흔적(next[prevA])
            // 이번 물건을 B가 훔쳤을 때(current[prevA]) 만들어지는(+) 새로운 B(item[1]) 흔적
            // 두 경로의 A 흔적이 같다면, B 흔적이 더 작은 경로를 남긴다.
            next[prevA] =
                    Math.min(next[prevA], current[prevA] + item[1]);

            // A가 훔치는 경우
            // 지금까지 누적된 A의 흔적(prevA)에 A가 훔치면서 생기는 현재 물건의 흔적(item[0]을 더한다.
            int newA = prevA + item[0];

            // A가 안전한 흔적 합 이내라면
            if (newA <= maxA) {
                // A 흔적은 지금까지의 흔적과 현재 흔적과 비교해서 작은 값 대입
                next[newA] =
                        Math.min(next[newA], current[prevA]);
            }
        }

        return next;
    }
}
