import java.util.Arrays;

class Solution {
    public int solution(int[] mats, String[][] park) {
        // 큰 돗자리가 앞으로 오도록 정렬
        Arrays.sort(mats);

        // 돗자리 사이즈를 하나씩 확인
        for (int i = 0; i < mats.length; i++) {
            // 큰 돗자리부터 확인하도록 정렬
            int size = mats[(mats.length - 1) - i];

            // '(row, col)은 돗자리의 왼쪽 위 모서리'
            // row: 돗자리를 놓기 시작하는 행
            // park.length: 공원의 전체 행 개수
            // size: 돗자리 한 변의 길이

            // 0부터 공원 전체 행 개수(park.length)까지 순회하는데
            // 거기서 돗자리 한 변의 길이만큼은 빼서(park.length - size) 순회해야함
            for (int row = 0; row <= park.length - size; row++) {
                // int col = 0: 가장 왼쪽인 0번 열부터 시작
                // col <= park[0].length - size: 돗자리가 공원 오른쪽 밖으로 나가지 않는 위치까지만 확인
                // col++: 시작 위치를 오른쪽으로 한 칸 이동
                // -> 왼쪽에서 오른쪽으로 돗자리가 공원 밖으로 나가지 않는 위치까지 한 칸씩 이동
                for (int col = 0; col <= park[0].length - size; col++) {
                    // 지정한 위치에 돗자리를 놓을 수 있는지 확인
                    if (canPlace(park, row, col, size)) {
                        // 돗자리를 놓을 수 있으면(canPlace가 true)면 돗자리 한 변 길이(size) 반환
                        return size;
                    }
                }
            }
        }

        return -1;
    }

    // 지정한 위치에 돗자리를 놓을 수 있는지 확인
    // park: 공원의 자리 배치
    // row, col: 돗자리의 왼쪽 위 모서리 위치
    // size: 돗자리 한 변의 길이
    private boolean canPlace(String[][] park, int row, int col, int size) {
        // 돗자리를 놓기 시작하는 행부터(int r = row) '시작 행 순번 누적+남은 확인할 갯수(r < row + size)'만큼 하나씩 확인(r++)
        for (int r = row; r < row + size; r++) {
            // 열의 길이만큼도 확인
            for (int c = col; c < col + size; c++) {
                // 현재 행과 열 위치의 자리(park[r][c])가 빈 자리(-1)가 아니라면(!)
                // 즉 그 자리에 사람이 있다면
                if (!park[r][c].equals("-1")) {
                    // 돗자리를 놓을 수 없다(false 반환)
                    return false;
                }
            }
        }

        // 위의 for문에서 돗자리를 덮으려는 칸 전체가 비어있으면
        // 놓을 수 있으므로 true 반환
        return true;
    }
}
