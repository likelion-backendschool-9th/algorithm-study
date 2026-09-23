class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLimit = toSeconds(video_len);
        int current = toSeconds(pos);
        int startOp = toSeconds(op_start);
        int endOp = toSeconds(op_end);

        // 1. 시작 위치가 오프닝 구간인 경우
        if (current >= startOp && current <= endOp) {
            current = endOp;
        }

        // 2. 명령어 수행
        for (String cmd : commands) {
            if (cmd.equals("prev")) {
                current = Math.max(0, current - 10);
            } else if (cmd.equals("next")) {
                current = Math.min(videoLimit, current + 10);
            }

            // 이동 후 오프닝 구간 체크
            if (current >= startOp && current <= endOp) {
                current = endOp;
            }
        }

        return toTimeString(current);
    }
    
    // "mm:ss" -> 초 변환
    private int toSeconds(String timeStr) {
        String[] split = timeStr.split(":");
        int m = Integer.parseInt(split[0]);
        int s = Integer.parseInt(split[1]);
        return m * 60 + s;
    }
    
    // 초 -> "mm:ss" 변환
    private String toTimeString(int seconds) {
        int m = seconds / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d", m, s);
    }
}
