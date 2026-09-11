import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < bridge_length - 1; i++) {
            queue.offer(0);
        }
        queue.offer(truck_weights[0]);
        
        int pointer = 1;

        while (queueSize(queue) != 0) {
            queue.poll();

            if (pointer < truck_weights.length) {
                if (queueSize(queue) + truck_weights[pointer] <= weight) {
                    queue.offer(truck_weights[pointer]);
                    pointer++;
                }
                else {
                    queue.offer(0);
                }
            } 
            else {
                queue.offer(0);
            }
            answer++;
        }
        return answer;
    }

    static int queueSize(Deque<Integer> queue) {
        int sum = 0;
        for (int x : queue) {
            sum += x;
        }
        return sum;
    }
}