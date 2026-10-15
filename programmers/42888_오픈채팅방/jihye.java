import java.util.*;

/*
설명 포인트
1. 닉네임으로는 사람을 구분할 수 없다. -> 유저 아이디로 사람을 구분해야 한다. 
2. 모든 로그가 기록되어 있어야 한다. 단, 기록은 1번에 의해 닉네임이 아닌 유저 아이디로 기록되어야 한다. 
- '수정' 및 '나갔다 들어오기' 둘 다 기존 닉네임도 변경하기 때문. 
- 닉네임을 로그로 기록할 필요는 없음. 

구현 포인트: logs를 처음에는 userID를 나타내는 String[], 행위 메세지를 나타내는 String[]으로 하려 했음. 그러나 List<String[]> 형태로 한 번에 관리하는 것이 데이터를 묶어서 관리할 수 있을 것 같아서 List<String[]> 채택. 
*/

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> nick = new HashMap<>();     // {userId : 닉네임 구조}
        List<String[]> logs = new ArrayList<>();        // 기록되는 로그

        for (String r : record) {
            // 명령어, userId, 닉네임 순으로 자르기
            String[] cmd = r.split(" ");

            if (cmd[0].equals("Enter")) {
                //채팅 입장. userID와 닉네임 기록.
                nick.put(cmd[1], cmd[2]);
                //입장 로그
                logs.add(new String[]{cmd[1], "님이 들어왔습니다."});
            } else if (cmd[0].equals("Leave")) {
                //퇴장 로그
                logs.add(new String[]{cmd[1], "님이 나갔습니다."});
            } else {
                //닉네임 변경 (로그는 안 남김)
                nick.put(cmd[1], cmd[2]);
            }
        }

        String[] answer = new String[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            //순서대로 로그 꺼내기 -> log[0]에는 userId / log[1]에는 행위 메세지
            String[] log = logs.get(i);
            //최종적으로 변경된 닉네임 + 로그 메세지
            answer[i] = nick.get(log[0]) + log[1];
        }

        return answer;
    }
}
