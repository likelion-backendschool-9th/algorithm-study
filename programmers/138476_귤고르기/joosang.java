import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 귤의 크기별 갯수를 저장
        Map<Integer, Integer> counts = new HashMap<>();

        for (int size : tangerine) {
            // counts에 키가 처음 들어오면 값을 저장하고, 이미 있으면 기존 값과 새 값을 합치기
            counts.merge(size, 1, Integer::sum);
        }

        // 크기별 갯수 Map에서 귤 갯수들만 꺼내 ArrayList로 저장
        List<Integer> frequencies = new ArrayList<>(counts.values());
        // 큰 순서대로 정렬
        frequencies.sort(Comparator.reverseOrder());

        // 종류 카운트 변수 선언
        int answer = 0;
        // 수확한 귤(k)가 0 이하가 될 때까지
        while (k > 0) {
            // 갯수가 많은 귤 종류부터(frequencies.get(answer)) 수확한 귤을 가져와서(k -=)
            k -= frequencies.get(answer);
            // 상자에 담는다
            answer++;
        }
        // 서로 다른 종류를 각 박스에 담았으므로, 다른 종류를 return
        return answer;
    }
}
