class Solution {
    public int solution(int dist_limit, int split_limit) {
        // 사용할 수 있는 분배 노드의 최대 개수(dist_limit)를 D에 대입
        long D = dist_limit;
        // 리프의 분배도가 넘을 수 없는 최댓값(split_limit)을 S에 대입
        long S = split_limit;
        // 지금까지 찾은 리프 개수의 최댓값 (분배 노드를 사용하지 않아도 리프 하나는 있으므로 1로 시작)
        long answer = 1;

        // 2갈래로 분기하는 깊이의 수 (처음에는 2갈래 분기를 사용하지 않으므로 0)
        int binaryDepth = 0;

        // 2갈래로 분기하는 깊이를 0개, 1개, 2개, ...로 바꾸면서 각각의 경우에 만들 수 있는 최대 리프 수를 확인
        // twoPower = 2^a, binaryDepth = a
        for (long twoPower = 1; twoPower <= S;
             twoPower *= 2, binaryDepth++) {

            // 2^a * 3^b <= S를 만족하는 가장 큰 3^b
            long threePower = 1;

            // 현재 정한 2갈래 분기 깊이에서 3갈래 분기 깊이를 얼마나 더 늘릴 수 있는지 확인
            while (threePower <= S / twoPower / 3) {
                threePower *= 3;
            }

            // 시작점 하나가 수용할 수 있는 3갈래 분배 노드 수
            long perRoot = (threePower - 1) / 2;

            // 3갈래 분기를 사용할 수 없다면 2갈래만 사용
            if (perRoot == 0) {
                answer = Math.max(answer, Math.min(D + 1, twoPower));
                continue;
            }

            // 현재 구조의 3갈래 분배 노드 최대 수용량
            long maxThree = twoPower * perRoot;

            // 해당 형태를 전부 채웠을 때의 분배 노드 수로 제한
            long used = Math.min(D, twoPower - 1 + maxThree);

            // 탐색할 3갈래 분배 노드 수의 하한 (하나도 사용하지 않는 경우인 0부터 시작)
            long low = 0;
            // 탐색할 3갈래 분배 노드 수의 상한 (전체 분배 노드 예산과 3갈래 수용량을 모두 넘을 수 없다.)
            long high = Math.min(used, maxThree);

            // 사용할 수 있는 3갈래 분배 노드 수의 최댓값
            while (low < high) {
                // mid : 이번에 배치할 수 있는지 검사할 3갈래 노드 수
                // (low + high + 1) / 2 : +1을 넣어 두 중간값 중 큰 쪽을 선택한다. (low = mid로 변경할 때 탐색이 멈추지 않게 하기 위함)
                long mid = (low + high + 1) / 2;

                // mid개의 3갈래 노드를 배치하는 데 필요한 전체 분배 노드 수를 계산
                long required =
                    requiredNodes(mid, perRoot, binaryDepth);

                if (required <= used) {
                    // 필요한 노드 수가 예산 이하라면 배치 가능 (mid 이상에서 더 큰 값을 탐색)
                    low = mid;
                } else {
                    // 필요한 노드 수가 예산을 초과하면 배치 불가능 (mid를 제외하고 더 작은 범위를 탐색)
                    high = mid - 1;
                }
            }

            // 리프 수 = 1 + 전체 분배 노드 수 + 3갈래 노드 수
            answer = Math.max(answer, 1 + used + low);
        }

        // int형 변환 후 반환
        return (int) answer;
    }

    private long requiredNodes(
        long threeNodes, long perRoot, int binaryDepth
    ) {
        // 필요한 3갈래 부분 트리의 시작점 수
        // (나머지가 있으면 시작점이 하나 더 필요하므로 올림 나눗셈)
        long roots = (threeNodes + perRoot - 1) / perRoot;
        // 지금까지 계산한 전체 분배 노드 수
        // (우선 배치하려는 3갈래 노드 수부터 포함)
        long required = threeNodes;

        // 시작점들을 연결하는 2갈래 부모 노드 수(binaryDepth)를
        // 아래에서 위로 한 깊이씩 올라가며 계산
        for (int i = 0; i < binaryDepth; i++) {
            // (roots + 1) / 2 : 현재 노드 수를 2로 나누고 올림
            // (부모 하나가 자식을 최대 2개 연결할 수 있다. ex) 현재 5개라면 부모는 3개 필요)
            roots = (roots + 1) / 2;
            // 이번 깊이에서 필요한 부모 노드 수를 누적
            required += roots;
        }

        // 3갈래 노드와 이를 연결하는 2갈래 노드의 총합 반환
        return required;
    }
}