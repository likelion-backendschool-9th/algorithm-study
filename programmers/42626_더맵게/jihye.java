import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        // 최소 힙, 우선순위가 낮은 숫자가 먼저 나옴 (작은 숫자)
        PriorityQueue<Long> heep = new PriorityQueue<>();
        for(int i : scoville) {
            heep.offer((long)i);
        }

        int count = 0;

        // 가장 맵지 않은 음식이 K 미만인 동안 반복(=모든 음식의 스코빌 지수가 k 이상인 경우 반복 중지)
        while(heep.peek() < K) {
            // 섞을 음식이 하나뿐이면 더 이상 불가능
            if(heep.size() < 2) {
                break;
            }

            long firstFood = heep.poll();   // 가장 맵지 않은 음식
            long secondFood = heep.poll();  // 두 번째로 맵지 않은 음식

            // 새로운 음식의 스코빌 지수
            heep.offer(firstFood + secondFood * 2);
            count++;
        }

        return count;
    }
}
