class Solution {
    public int solution(int storey) {
        int answer = 0;

        while (storey > 0) {
            int digit = storey % 10; // 현재 일의 자리
            storey /= 10;           // 다음 자릿수부터 남김

            // 현재 자릿수를 올림처리할지 판단
            // (현재 자릿수 digit이 5보다 크다.) 또는
            // (현재 자릿수 digit이 5이고, 다음 자릿수도 5 이상이다.)
            // = 5보다 크면 올리고, 딱 5면 다음 숫자까지 보고 올린다.
            if (digit > 5 || (digit == 5 && storey % 10 >= 5)) {
                answer += 10 - digit; // 올라가는 횟수
                storey++;             // 다음 자릿수에 올림
            } else {
                answer += digit;      // 내려가는 횟수
            }
        }

        return answer;
    }
}
