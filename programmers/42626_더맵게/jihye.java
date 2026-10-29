import java.util.PriorityQueue;

// 빅오 표기법

class Solution {
    public int solution(int[] scoville, int K) {
        // 최소 힙, 우선순위가 낮은 숫자가 먼저 나옴 (작은 숫자)
        PriorityQueue<Long> heep = new PriorityQueue<>();

        // PriorityQueue에 요소를 삽입하는 offer() 연산의 시간 복잡도는 (O(log N)) ==> 트리의 높이(깊이)만큼만 비교하기 때문
        // 근데 반복문 만큼 도니까 (O(N))보다 더 무거운 (O(N log N)) 홀리!
        for(int i : scoville) {
            heep.offer((long)i);
        }

        int answer = 0;

        // 가장 맵지 않은 음식이 K 미만인 동안 반복(=모든 음식의 스코빌 지수가 k 이상인 경우 반복 중지)
        // 총 반복 횟수 = 최악 가정으로 ((N-1))번
        // 루프 1번 돌 때 걸리는 시간 = (O(log N))
        while(heep.peek() < K) {
            // 섞을 음식이 하나뿐이면 더 이상 불가능
            if(heep.size() < 2) {
                break;
            }

            long firstFood = heep.poll();   // 가장 맵지 않은 음식
            long secondFood = heep.poll();  // 두 번째로 맵지 않은 음식

            // 새로운 음식의 스코빌 지수
            heep.offer(firstFood + secondFood * 2);
            answer++;
        }

        return heep.poll()<K?-1:answer;
    }
}
