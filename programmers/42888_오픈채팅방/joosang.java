import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> nicknames = new HashMap<>();

        // 입장 또는 닉네임 변경 시 갱신하여 최종 닉네임을 구한다.
        for (String log : record) {
            // 배열 아이템을 띄어쓰기로 자르고
            String[] parts = log.split(" ");
            // 'Enter'나 'Change'라는 문자열이 있으면 등록 또는 갱신
            if (parts[0].equals("Enter") || parts[0].equals("Change")) {
                nicknames.put(parts[1], parts[2]);
            }
        }

        // 기록 순서를 유지하면서 최종 닉네임으로 메시지를 만든다.
        List<String> answer = new ArrayList<>();
        for (String log : record) {
            String[] parts = log.split(" ");
            if (parts[0].equals("Enter")) {
                answer.add(nicknames.get(parts[1]) + "님이 들어왔습니다.");
            } else if (parts[0].equals("Leave")) {
                answer.add(nicknames.get(parts[1]) + "님이 나갔습니다.");
            }
        }

        return answer.toArray(new String[0]);
    }
}
