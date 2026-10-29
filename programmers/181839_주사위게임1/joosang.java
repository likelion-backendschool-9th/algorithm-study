class Solution {
    public int solution(int a, int b) {
        // 제한사항 : a와 b는 1 이상 6 이하의 정수입니다.
        // 테스트 케이스로 a 값과 b 값이 들어올 때 이렇게 들어온다고 알고 있으면 된다.

        // 조건 1
        // a와 b가 모두 홀수라면
        if (a % 2 == 1 && b % 2 == 1) {
            // a² + b² 점을 얻습니다.
            return a * a + b * b;
        }

        // 조건 2
        // a와 b 중 하나만 홀수라면
        if (a % 2 == 1 || b % 2 == 1) {
            // 2 × (a + b) 점을 얻습니다.
            return 2 * (a + b);
        }

        // 조건 3
        // a와 b 모두 홀수가 아니라면 (앞에서 조건 1과 조건 2의 경우를 제외함)
        // 'a - b'점을 얻습니다.
        return Math.abs(a - b);
    }
}