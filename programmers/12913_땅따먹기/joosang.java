import java.util.Arrays;

class Solution {
    int solution(int[][] land) {
        int answer = 0;

        // // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        // System.out.println("Hello Java");

        // 각 행을 순회
        // (1부터 시작하는 이유는 이전 행과 비교할 때 현재 행이 0이면
        // ArrayIndexOutOfBoundsException를 막기 위함)
        for (int i = 1; i < land.length; i++) {
            // 각 열을 순회
            for (int j = 0; j < land[i].length; j++) {
                // 최대값 변수 선언
                int max = 0;
                // 이전 행에서 지금 확인 중인 열 번호
                int current = 0;
                // 이전 행의 각 열 점수를 하나씩 꺼내서, 현재 칸에 더할 최대 누적점수 구함
                // (current가 0부터 시작하므로 인덱스 차이를 막기 위해 i - 1)
                for (int previous : land[i - 1]) {
                    // 이전 행에서 현재 열과 같은 열을 제외 (한 행씩 내려올 때, 같은 열을 연속해서 밟을 수 없는 특수 규칙이 있습니다.)
                    if (current != j) {
                        // max: 현재까지 찾은 최대 점수
                        // previous: 이전 행에서 지금 꺼내 확인 중인 점수
                        max = Math.max(max, previous);
                    }
                    // 다음 열 번호로 이동
                    current++;
                }
                // 현재 위치에 누적
                land[i][j] += max;
            }
        }

        // land의 마지막 행
        int[] lastRow = land[land.length - 1];
        // 마지막 행이 값들 중 큰 값이 뒤로 가도록 정렬
        Arrays.sort(lastRow);
        // land의 마지막 행(lastRow) 배열의 마지막 값(큰 값)
        // 인덱스는 0부터 시작하므로 -1
        answer = lastRow[lastRow.length - 1];

        return answer;
    }
}