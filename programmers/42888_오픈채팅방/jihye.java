import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> nick = new HashMap<>();
        List<String[]> logs = new ArrayList<>();

        for (String r : record) {
            String[] cmd = r.split(" ");

            if (cmd[0].equals("Enter")) {
                nick.put(cmd[1], cmd[2]);
                logs.add(new String[]{cmd[1], "님이 들어왔습니다."});
            } else if (cmd[0].equals("Leave")) {
                logs.add(new String[]{cmd[1], "님이 나갔습니다."});
            } else {
                nick.put(cmd[1], cmd[2]);
            }
        }

        String[] answer = new String[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            String[] log = logs.get(i);
            answer[i] = nick.get(log[0]) + log[1];
        }

        return answer;
    }
}
