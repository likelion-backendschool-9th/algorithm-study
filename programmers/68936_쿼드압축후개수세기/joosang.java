class Solution {
    // 모든 재귀 호출이 같은 answer 배열을 공유하도록 전역 변수 처리
    private int[] answer;

    public int[] solution(int[][] arr) {
        // 정답 배열을 0의 갯수, 1의 갯수로 저장
        answer = new int[2];
        // 분류 · 압축 작업 실행
        execute(arr, 0, 0, arr.length);
        // execute 재귀호출 모두 완료 후 answer 전달
        return answer;
    }

    private void execute(int[][] arr, int row, int col, int size) {
        // 각 정사각형 영역의 첫번째 칸 값(col에 0을 넣어서 첫번째)
        // 처음 실행 시엔 [0,0]으로 처음 스타트 값을 넣음
        int value = arr[row][col];

        // row - 현재 진행할 정사각형 영역
        // r < size 대신 r < row + size
        // (첫번째 정사각형 영역이면 상관없는데, 두번째 세번째부터는 그만큼 시작 위치가 커지기 때문에
        // 그만큼 row를 더해줘야 시작위치와 한계위치 아귀가 맞는다. col도 마찬가지)
        for (int r = row; r < row + size; r++) {
            for (int c = col; c < col + size; c++) {
                // 지금 검사하는 칸의 값이 현재 영역이 첫번째 칸과 다른지 확인
                // arr[r][c]: 지금 검사하는 칸의 값
                // value: 현재 정사각형(arr[row][col]) 영역의 첫 번째 값
                if (arr[r][c] != value) {
                    // 한 변이 길이를 절반으로 나눠서 나온 값 half를 저장
                    int half = size / 2;
                    // [재귀호출]
                    //  호출   |   시작 행   |   시작 열   | 검사할 영역
                    // 첫 번째 |     row    |     col    | 왼쪽 위
                    // 두 번째 |     row    | col + half | 오른쪽 위
                    // 세 번째 | row + half |     col    | 왼쪽 아래
                    // 네 번째 | row + half | col + half | 오른쪽 아래
                    execute(arr, row, col, half);
                    execute(arr, row, col + half, half);
                    execute(arr, row + half, col, half);
                    execute(arr, row + half, col + half, half);
                    // 4개 호출이 끝나면 원래 호출로 복귀
                    return;
                }
            }
        }

        // value가 0인지 1인지에 따라 0 인덱스 또는 1 인덱스에 카운트 +1
        answer[value]++;
    }
}
