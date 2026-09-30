import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    // INF를 기본적으로 도달할 수 없는 값으로 적용 (도달 가능한 경우 Math.min으로 대체함)
    static final int INF = 1_000_000_000;

    // 상태(mask): 현재 칸 (i, j) 주변 4칸을 먹었는지 비트로 기록한다.
    static final int UP_RIGHT = 1;  // (i-1, j+1)
    static final int RIGHT = 2;     // (i,   j+1)
    static final int DOWN = 4;      // (i+1, j)
    static final int DOWN_LEFT = 8; // (i+1, j-1)

    // 격자의 행, 열 크기
    static int N, M;
    // 음식점 가격이 있는 격자판
    static int[][] board;
    // 특정 위치 및 그 위치 방문 후 식사 시 최소 누적 비용
    // dp[i][j][mask] = (i, j)에 mask 상태로 도착하는 최소 비용
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        // 표준 입력 읽기용 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테스트케이스 개수
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder answer = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            // 받아온 br 한 줄을 읽고, 공백 기준으로 나눈다
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 첫번째 토큰을 행 수 N으로 저장
            N = Integer.parseInt(st.nextToken());
            // 두번째 토큰을 열 수 M으로 저장
            M = Integer.parseInt(st.nextToken());

            // [격자판 초기화] (각 위치에 가격 기록)
            // N + 2 - 행을 위아래로 한칸씩 더 만듬
            // M + 2 - 열을 좌우로 한칸씩 더 만듬
            // N의 경우 상하로, M의 경우 좌우로 공간을 만들기 때문에 범위 검사 코드를 줄여준다
            // 전체 격자판의 상하좌우로 바깥 여백이 하나씩 있다고 생각하면 된다
            board = new int[N + 2][M + 2];
            // 행을 한 줄씩 읽는다
            for (int i = 1; i <= N; i++) {
                // 한 줄에 있는 내용을 가져온다
                String row = br.readLine().trim();
                // 한 줄에 있는 문자열을 왼쪽부터 하나씩 읽는다 (j는 격자에 있는 열 번호)
                for (int j = 1; j <= M; j++) {
                    // 문자열 인덱스는 0부터 시작한다
                    // 격자의 j번째 열은 문자열의 j - 1번째 문자
                    // (j = 1 → row.charAt(0), j = 2 → row.charAt(1))
                    char c = row.charAt(j - 1);
                    // 읽은 문자를 숫자로 바꿔서 저장
                    // '.'인 경우 가격이 없으므로 0
                    // 가격이 저장되어 있으면 가격 저장 ('0'이란 문자열을 빼면 숫자형으로 변환되어 저장됨)
                    board[i][j] = (c == '.') ? 0 : c - '0';
                }
            }

            // 현재 테스트 케이스의 최소 비용 기록
            answer
                .append('#').append(test_case) // 테스트 케이스 번호 표시
                .append(' ') // 띄어쓰기로 구분
                .append(solve()) // solve로 도출된 값 저장
                .append('\n'); // 줄바꿈
        }
        // 모든 기록 출력
        System.out.print(answer);
    }

    static int solve() {
        // 위치와 상태별 최소 비용을 저장하는 3차원 배열
        // 16은 상태 비트가 4개라서 가능한 조합이 2⁴ = 16개이기 때문
        // ex) dp[2][3][5]는 (2, 3) 위치에서 상태값이 5일 때의 최소 비용을 의미
        dp = new int[N + 1][M + 1][16];

        // [초기화]
        // dp 안에서 행 하나를 차례대로 꺼낸다
        for (int[][] row : dp) {
            // 현재 행에서 한 칸의 상태 배열을 꺼낸다
            for (int[] cell : row) {
                // 모든 dp의 열과 행을 INF로 초기화
                // (실제로 도달 가능한 경로를 찾으면 더 작은 비용으로 갱신)
                Arrays.fill(cell, INF);
            }
        }
        // 출발 칸은 모든 상태를 비용 0으로 시작
        Arrays.fill(dp[1][1], 0);

        // mask = 0  / 2진수 = 0000 / 먹은 방향 : 없음
        // mask = 1  / 2진수 = 0001 / 먹은 방향 : UP_RIGHT
        // mask = 2  / 2진수 = 0010 / 먹은 방향 : RIGHT
        // mask = 3  / 2진수 = 0011 / 먹은 방향 : UP_RIGHT, RIGHT
        // mask = 4  / 2진수 = 0100 / 먹은 방향 : DOWN
        // mask = 5  / 2진수 = 0101 / 먹은 방향 : UP_RIGHT, DOWN
        // mask = 6  / 2진수 = 0110 / 먹은 방향 : RIGHT, DOWN
        // mask = 7  / 2진수 = 0111 / 먹은 방향 : UP_RIGHT, RIGHT, DOWN
        // mask = 8  / 2진수 = 1000 / 먹은 방향 : DOWN_LEFT
        // mask = 9  / 2진수 = 1001 / 먹은 방향 : UP_RIGHT, DOWN_LEFT
        // mask = 10 / 2진수 = 1010 / 먹은 방향 : RIGHT, DOWN_LEFT
        // mask = 11 / 2진수 = 1011 / 먹은 방향 : UP_RIGHT, RIGHT, DOWN_LEFT
        // mask = 12 / 2진수 = 1100 / 먹은 방향 : DOWN, DOWN_LEFT
        // mask = 13 / 2진수 = 1101 / 먹은 방향 : UP_RIGHT, DOWN, DOWN_LEFT
        // mask = 14 / 2진수 = 1110 / 먹은 방향 : RIGHT, DOWN, DOWN_LEFT
        // mask = 15 / 2진수 = 1111 / 먹은 방향 : UP_RIGHT, RIGHT, DOWN, DOWN_LEFT

        // [계산]
        // 위에서 아래 방향으로 행을 순서대로 확인
        for (int i = 1; i <= N; i++) {
            // 현재 행에서 왼쪽에서 오른쪽 방향으로 칸을 확인
            // 즉 (1, 1)부터 (N, M)까지 순서대로 각 칸을 처리한다.
            // 오른쪽과 아래로만 이동하므로, 이 순서로 처리하면 이전 위치의 DP 결과가 이미 계산되어 있다.
            for (int j = 1; j <= M; j++) {
                // 현재 칸 (i, j)에서 가능한 16가지 상태를 하나씩 확인
                // mask - '현재 칸'의 16방위에 음식점을 이미 먹었는지 기록한 상수 (마킹)
                for (int mask = 0; mask < 16; mask++) {
                    // 현재 위치(i,j)와 상태(mask)로 도착했을 때의 최소 비용(cost)
                    int cost = dp[i][j][mask];
                    // [i][j][mask]에 도착할 방법이 없다면(INF) 다음 상태 확인
                    if (cost == INF) continue;

                    // 다음 칸을 먹은 상태여야 그 칸으로 이동할 수 있다.

                    // 오른쪽 칸이 실제 격자 안에 있는지 확인
                    // && 오른쪽 음식점에 대한 상태 비트가 유효하면
                    if (j < M && has(mask, RIGHT)) {
                        // 오른쪽으로 이동
                        moveRight(i, j, mask, cost);
                    }
                    // 아래쪽 칸이 실제 격자 안에 있는지 확인
                    // && 아래쪽 음식점에 대한 상태 비트가 유효하면
                    if (i < N && has(mask, DOWN)) {
                        // 아래쪽으로 이동
                        moveDown(i, j, mask, cost);
                    }
                }
            }
        }

        // 반복문 전체가 끝나면 도착점 (N, M)의 모든 상태별 최소 비용이 계산되어 있다.

        // answer를 매우 큰 값으로 시작
        int answer = INF;
        // dp[N][M] -> dp[최대 행수][최대 열수] -> dp 도착점
        // dp[N][M][mask] -> dp 도착점에서 어느 방향(mask)으로 왔는지
        // 모르므로 16가지 방향 케이스를 모두 검사
        // 16방위 중 제일 작은 값을 answer에 대입
        for (int mask = 0; mask < 16; mask++) {
            answer = Math.min(answer, dp[N][M][mask]);
        }
        // answer 반환
        return answer;
    }

    // 현재 위치 (i, j)에서 오른쪽인 (i, j + 1)으로 이동했을 때,
    // 그 다음 위치를 어디로 가면 최소 비용인지 갱신하는 메서드
    static void moveRight(int i, int j, int mask, int cost) {
    // i, j - 현재 위치
    // mask - 현재 위치 주변 음식점 섭취 상태
    // cost - 현재 위치와 상태까지의 최소 누적 비용
        // 현재 칸의 오른쪽 칸 위치에서 다음 칸으로 이동할 때
        // next - '다음 칸'의 16방위 중 어느 방향으로 가야 돈이 적게 나올지 체크
        for (int next = 0; next < 16; next++) {
            // 이전 칸의 DOWN과 새 칸의 DOWN_LEFT는 같은 칸 (i+1, j)이다.
            // (새 칸에선 DOWN에서 오른쪽으로 이동해서 이전 칸이 DOWN+LEFT가 됐으므로)
            // 둘이 다르면 같은 음식점을, 한 상태에서는 "먹음", 다른 상태에서는 "안 먹음"으로 기록한 것이므로, 잘못된 상태다.
            if (has(mask, DOWN) != has(next, DOWN_LEFT)) continue;

            // 새 칸의 위, 오른쪽, 아래 이웃 중 2곳 이상을 먹어야 한다. (왼쪽은 지나온 칸)

            // 오른쪽으로 이동한 새 칸 (i, j + 1)의 위·오른쪽·아래 방향
            int eatenNeighbors =
                    bit(mask, UP_RIGHT) // 오른쪽으로 이동할 예정이므로 오른쪽에 있으면서 위쪽
                    + bit(next, RIGHT) // 다음 칸으로 이동한 곳의 RIGHT
                    + bit(next, DOWN); // 다음 칸으로 이동한 곳의 DOWN
            // "그 중에서 한 음식점을 골라 제외하고 나머지 음식점에서 모두 음식을 시켜 먹는다."
            // 조건 중 한 음식점을 제외해야 하므로 세 방향 중 음식점이 하나만 있으면 조건에 맞지 않는다.
            // 두 곳 미만이면 문제의 음식 주문 규칙에 맞지 않아 건너뛴다.
            if (eatenNeighbors < 2) continue;

            // 오른쪽으로 이동하면서 새로 먹게 되는 음식 가격을 합산할 변수
            int eat = 0;
            // 새 상태에 UP_RIGHT 비트가 켜져 있다면 (i - 1, j + 2) 음식점 가격을 더한다.
            if (has(next, UP_RIGHT)) eat += board[i - 1][j + 2];
            // 새 상태에 RIGHT 비트가 켜져 있다면 (i, j + 2) 음식점 가격을 더한다.
            if (has(next, RIGHT)) eat += board[i][j + 2];
            // 새 상태에 DOWN 비트가 켜져 있다면 (i + 1, j + 1) 음식점 가격을 더한다.
            if (has(next, DOWN)) eat += board[i + 1][j + 1];

            // (i, j) -> (i, j+1), next는 새 칸 (i, j+1) 기준의 상태
            // 현재 경로의 누적 비용 cost와 이번에 새로 먹은 비용 eat을 더한 값이,
            // (i, j + 1)의 next 상태에 저장된 기존 비용보다 작으면 교체한다.
            dp[i][j + 1][next] = Math.min(dp[i][j + 1][next], cost + eat);
        }
    }

    // 현재 위치 (i, j)에서 아래쪽인 (i + 1, j)으로 이동했을 때,
    // 그 다음 위치를 어디로 가면 최소 비용인지 갱신하는 메서드
    static void moveDown(int i, int j, int mask, int cost) {
    // i, j - 현재 위치
    // mask - 현재 위치 주변 음식점 섭취 상태
    // cost - 현재 위치와 상태까지의 최소 누적 비용
        // 현재 칸의 아래쪽 칸 위치에서 다음 칸으로 이동할 때
        // next - '다음 칸'의 16방위 중 어느 방향으로 가야 돈이 적게 나올지 체크
        for (int next = 0; next < 16; next++) {
            // 이전 칸의 RIGHT와 새 칸의 UP_RIGHT는 같은 칸 (i, j+1)이다.

            // (새 칸에선 RIGHT에서 아래쪽으로 이동해서 이전 칸이 RIGHT+UP가 됐으므로)
            // 둘이 다르면 같은 음식점을, 한 상태에서는 "먹음", 다른 상태에서는 "안 먹음"으로 기록한 것이므로, 잘못된 상태다.
            if (has(mask, RIGHT) != has(next, UP_RIGHT)) continue;

            // 새 칸의 왼쪽, 오른쪽, 아래 이웃 중 2곳 이상을 먹어야 한다. (위는 지나온 칸)

            // 아래쪽으로 이동한 새 칸 (i + 1, j)의 왼쪽·오른쪽·아래 방향
            int eatenNeighbors =
                    bit(mask, DOWN_LEFT) // 아래쪽으로 이동할 예정이므로 아래쪽에 있으면서 왼쪽
                    + bit(next, RIGHT) // 다음 칸으로 이동한 곳의 RIGHT
                    + bit(next, DOWN); // 다음 칸으로 이동한 곳의 DOWN
            if (eatenNeighbors < 2) continue;

            // 아래쪽으로 이동하면서 새로 먹게 되는 음식 가격을 합산할 변수
            int eat = 0;
            // 새 상태에 DOWN_LEFT 비트가 켜져 있다면 (i + 2, j - 1) 음식점 가격을 더한다.
            if (has(next, DOWN_LEFT)) eat += board[i + 2][j - 1];
            // 새 상태에 DOWN 비트가 켜져 있다면 (i + 2, j) 음식점 가격을 더한다.
            if (has(next, DOWN)) eat += board[i + 2][j];
            // 새 상태에 RIGHT 비트가 켜져 있다면 (i + 1, j + 1) 음식점 가격을 더한다.
            if (has(next, RIGHT)) eat += board[i + 1][j + 1];

            // (i, j) -> (i+1, j), next는 새 칸 (i+1, j) 기준의 상태
            // 현재 경로의 누적 비용 cost와 이번에 새로 먹은 비용 eat을 더한 값이,
            // (i + 1, j)의 next 상태에 저장된 기존 비용보다 작으면 교체한다.
            dp[i + 1][j][next] = Math.min(dp[i + 1][j][next], cost + eat);
        }
    }

    // 비트 AND 연산으로 특정 상태 비트가 포함됐는지 확인
    static boolean has(int mask, int cell) {
        // 0이 아니면 true
        return (mask & cell) != 0;
    }

    // mask에 특정 방향 비트(cell)가 들어 있는지 확인한 뒤, 결과를 숫자 1 또는 0으로 바꾸는 메서드
    static int bit(int mask, int cell) {
        // has 메서드로 비트 포함 여부 확인
        return has(mask, cell) ? 1 : 0;
    }
}
