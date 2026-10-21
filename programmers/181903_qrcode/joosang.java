class Solution {
    public String solution(int q, int r, String code) {
        String answer = "";
        for (int i = 0; i < code.length(); i++) {
            // 인덱스를 q로 나누었을 때 r이면
            if (i % q == r) {
                // 그 위치의 문자를 answer에 담음
                answer += code.charAt(i);
            }
        }
        return answer;
    }
}
