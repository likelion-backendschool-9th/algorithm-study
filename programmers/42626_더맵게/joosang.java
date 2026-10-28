import java.util.PriorityQueue;

// ArrayList<Integer>의 sort, remove, add로 매번 정렬 시도 → 실패
// Queue를 통해 2개의 큐로 풀어보려 했지만 코드가 불필요하게 복잡해져서 중단
// PriorityQueue를 통해 우선순위가 높은 리스트 아이템부터 꺼냄 (Queue에 비해 기본적으로 작은 값이 우선순위가 먼저)

class Solution {
    public int solution(int[] scoville, int K) {
        // PriorityQueue - 들어간 순서와 관계없이 우선순위가 가장 높은 데이터가 먼저 나오는 클래스 (Queue 인터페이스를 구현)
        PriorityQueue<Long> foods = new PriorityQueue<>();

        // 스코빌 지수를 담은 배열을 foods에 담음
        for (int value : scoville) {
            foods.offer((long) value);
        }

        // return할 값
        int count = 0;

        // foods 배열 최소값이 K보다 작을 때 (크면 종료)
        while (foods.peek() < K) {
            // 더 섞어야 하는데 음식이 하나뿐인 경우
            // (모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우)
            if (foods.size() < 2) {
                // -1로 종료 (while문 탈출)
                return -1;
            }

            // 현재 foods 배열에서 첫번째 값을 빼고 그 값을 first에 옮긴다.
            long first = foods.poll();
            // '현재' foods 배열에서 첫번째 값을 빼고 그 값을 second에 옮긴다.
            long second = foods.poll();

            // 음식 섞음

            // 섞은 음식은 '가장 맵지 않은 음식의 스코빌 지수(first)'와 '두 번째로 맵지 않은 음식의 스코빌 지수 * 2(second * 2)' 값을 가진다.
            // 그 값을 foods 배열에 새로 더함
            foods.offer(first + second * 2);
            // 섞어야 하는 최소 횟수 +1
            count++;
        }

        // while문 종료되어 결과값 나오면 return
        return count;
    }
}