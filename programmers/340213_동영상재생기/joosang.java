class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        // 문자열을 초로 변환
        int videoLength = toSeconds(video_len);
        int current = toSeconds(pos);
        int openingStart = toSeconds(op_start);
        int openingEnd = toSeconds(op_end);

        // 오프닝 구간에 있으면 오프닝이 끝나는 위치로 이동
        current = skipOpening(current, openingStart, openingEnd);
        
        // commands 명령 배열 순회
        for (String command : commands) {
            // prev 입력 시
            if (command.equals("prev")) {
                // 현재 위치에서 -10초 (음수 안되도록 Math.max로 0이 크면 선택)
                current = Math.max(0, current - 10);
            }
            // next 입력 시
            else if (command.equals("next")) {
                // 현재 위치에서 +10초 (동영상 길이를 넘지 않도록 Math.min으로 처리)
                current = Math.min(videoLength, current + 10);
            }

            // 매 명령 순회마다 오프닝 구간에 있는지 확인해야 한다.
            // (동영상 재생기의 일반적인 동작을 생각했을 때 재생 제외구간은 몰아서 스킵하는게 아닌 즉각적인 스킵을 하는게 맞다.)
            // 현재 위치가 10초고, 15~30초가 오프닝이면 next next prev하고 나서 오프닝 구간인지 검사하는게 아니라
            // 10초일 때 next하고 바로 검사 -> 20초에 있으면 30초로 이동 -> 30초에서 next -> ... 이런 식으로 가야 정상이다.
            // 오프닝 구간에 있으면 오프닝이 끝나는 위치로 이동
            current = skipOpening(current, openingStart, openingEnd);
        }
        
        // 초를 문자열로 변환
        answer = toString(current);
        
        return answer;
    }

    int skipOpening(int current, int op_start, int op_end) {
        // '오프닝 시작 위치 <= 현재 재생 위치 <= 오프닝 끝 위치'인 경우
        if (op_start <= current && current <= op_end) {
            // 오프닝 끝 위치로 보냄
            return op_end;
        }
        // 해당 안되면 현재 재생 위치로 보냄
        return current;
    }

    int toSeconds(String video_len) {
        int result = 0;
        // 콜론을 기준으로 문자열을 나눔
        String[] parts = video_len.split(":");
        // 분*60 + 초
        result = Integer.parseInt(parts[0])*60 + Integer.parseInt(parts[1]);
        return result;
    }
    
    String toString(int current) {
        // 분과 초를 담을 int형 선언
        int minute;
        int second;
        // 반환할 문자열
        String string;

        // 분은 60으로 나눠서 나머지를 버린다
        minute = current / 60;
        // 초는 60으로 나눈 나머지를 가져온다
        second = current % 60;
        // 합쳐서 반환
        string = String.format("%02d:%02d", minute, second);
        return string;
    }
}