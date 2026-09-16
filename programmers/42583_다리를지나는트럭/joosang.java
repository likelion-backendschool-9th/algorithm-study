import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 지문에서 경과 시간과 대기 트럭에 대한 예시에서 최소 8초가 걸린다고 했을 때
        // 최소 8초 / 대기 트럭 대 수 4대 = 2
        // bridge_length 값이 나오는 걸 알 수 있다.
        // 변수 이름으로 추정했을 때, bridge_length는 다리의 마디고
        // 다리 마디만큼 1초가 소요된 것으로 가정한다.

        // bridge_length - 다리에 최대 올라갈 수 있는 트럭 수
        // weight - 다리 하중
        // truck_weights - 각 트럭의 무게

        int time = 0;

        Queue<Integer> bridge = new LinkedList<>(); // 다리 (앞뒤 트럭 옮기기 위해 LinkedList 사용)
        int bridgeTrucksWeight = 0; // 다리 위에 있는 트럭들 무게
        int nextTruck = 0; // 현재 트럭 몇번째인지

        // 다리의 각 칸을 1초 단위로 표현한다. 0은 빈 칸이다.
        for (int i = 0; i < bridge_length; i++) {
            // bridge에 추가
            bridge.offer(0);
        }

        // 다음 트럭이 없을 때까지 (다음 트럭 배열이 없을 때까지)
        while (nextTruck < truck_weights.length) {
            // 시간 카운트 증가
            time++;

            // 맨 앞 칸의 트럭(또는 빈 칸)이 다리를 벗어난다.
            // 맨 앞이 0이면 음수가 되진 않고 변화 없음
            bridgeTrucksWeight -= bridge.poll();

            // 다음 트럭을 올릴 수 있으면 올리고, 아니면 빈 칸을 추가한다.
            // (다음 트럭 배열이 남아있고 & 다리 위에 있는 트럭들+트럭들 충 무게가 다리 하중보다 낮은지)
            if (nextTruck < truck_weights.length
                    && bridgeTrucksWeight + truck_weights[nextTruck] <= weight) {
                // 현재 트럭의 무게
                int truck = truck_weights[nextTruck++];
                // 다리 위에 얹음
                bridge.offer(truck);
                // 다리 위에 있는 트럭들에 현재 트럭의 무게를 더함
                bridgeTrucksWeight += truck;
            } else {
                // 다리 위에 얹지 않음
                bridge.offer(0);
            }
        }

        // 마지막 트럭이 진입한 뒤 다리 마디만큼 더 이동해야 한다.
        // 마지막 트럭은 while문 순회를 안 한다.
        // 그래서 마지막 트럭은 bridge_length(다리 마디 당 n초)만큼 더한 후 return
        return time + bridge_length;
    }
}
