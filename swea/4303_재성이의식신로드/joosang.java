import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    static final int RIGHT = 0; // 오른쪽 방향으로 왔음을 0으로 표시
    static final int DOWN = 1; // 아래쪽 방향으로 왔음을 1로 표시
    static final int INF = 1_000_000_000; // 갈수 없는 위치거나 아직 계산 안된 경로를 무한대 값으로 처리
    
    static int N, M; // 격자의 행, 열 크기
    static int[][] map; // 격자 각 칸의 가격을 저장 (해당 칸에 식당이 없으면 그 위치의 값은 0)
    // 한 칸마다 [이전 방향 2개][먹음 상태 8개]를 저장한다.
    static int[] memo; // DFS 메모이제이션 테이블. "칸 위치 (i, j)"와 "그 칸에서의 상태(state)"를 조합한 키로 캐시를 저장
    
    public static void main(String[] args) throws Exception {
        // 표준 입력 읽기용 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테스트케이스 개수
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder answer = new StringBuilder();

        // 입력받은 테스트 케이스를 하나씩 처리
        for (int test_case = 1; test_case <= T; test_case++) {
            // DFS 메모이제이션 준비
            // DFS로 재귀 탐색을 할 때, 이미 계산한 결과를 저장해 두고 나중에 같은 상태가 오면 다시 계산하지 않고 저장된 값을 바로 반환
            readCase(br);

            // 시작 포인트 준비
            // (격자가 한 칸뿐인 경우 시작점 도착점을 한 칸으로 처리해야 하므로 필요)
            int startPoint = map[0][0];

            // 시작점 다음부터 도착점까지 드는 최소 비용을 담는 변수
            int bestNext = INF;

            // 전체 이동은 왼쪽 위(0,0)로부터 오른쪽 아래(N-1,M-1) 방향
            // 한 번 이동할 때마다 오른쪽으로 한 칸 또는 아래쪽으로 한 칸만 갈 수 있다.
            
            // 열이 2개 이상이라 오른쪽으로 이동 가능한 경우에 진입
            if (M > 1) {
                /* 오른쪽으로 이동한만큼의 좌표 값을 대입 */
                /* 재귀함수이므로 i, j, a, b, c 매개변수 값은 재귀호출 중에 변한다. */
                // i=0, j=1 -> 시작점 0,0에서 오른쪽으로 1칸 간 위치
                // a: 현재 칸
                // b: 다른 뒤쪽 이웃
                // c: 꺾으면 다시 만날 음식점의 먹음 여부
                bestNext = dfs(0, 1, RIGHT, 0, 0, 0);
            }
            // 행이 2개 이상이라 아래쪽으로 이동 가능한 경우에 진입
            if (N > 1) {
                bestNext = Math.min(bestNext, dfs(1, 0, DOWN, 0, 0, 0));
            }

            int result = startPoint + (bestNext == INF ? 0 : bestNext);

            System.out.println(result);
            // answer.append('#').append(test).append(' ').append(result).append('\n');
        }
    }

    static void readCase(BufferedReader br) throws IOException {
        // 받아온 br 한 줄을 읽고, 공백 기준으로 나눈다
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 첫번째 토큰을 행 수 N으로 저장
        N = Integer.parseInt(st.nextToken());
        // 두번째 토큰을 열 수 M으로 저장
        M = Integer.parseInt(st.nextToken());

        // 행과 열의 크기만큼 공간 생성
        map = new int[N][M];
        // DFS 상태의 계산 결과를 저장할 메모 배열
        // - N * M: 격자의 칸 수
        // - 16: 한 칸에서 구분할 DFS 상태 수
        memo = new int[N * M * 16];
        // memo 배열의 모든 값은 값이 계산되지 않았단 의미로 '-1'을 부여
        Arrays.fill(memo, -1);

        /* 격자 행들을 하나씩 읽어 map 배열에 가격으로 저장 */
        // N개(행 크기)만큼의 반복
        for (int n = 0; n < N; n++) {
            // 행(row)을 위에서부터 하나씩 처리
            String row = br.readLine().trim();
            // M개(열 크기)만큼의 반복
            for (int m = 0; m < M; m++) {
                // m 행에 있는 n번째 열 문자를 꺼냄
                char ch = row.charAt(m);
                // 해당 격자 칸의 가격을 저장
                // 꺼낸 문자가 '.'이면 : 음식점이 없으므로 가격은 0
                // 꺼낸 문자가 값이 있으면 : char형인 ch에 문자 코드값인 '0'을 빼서 int형으로 저장
                // (예를 들어 '10'이란 값이 ch로 들어가 있을 때, ch - '0'이면 값은 10으로 표시됨)
                map[n][m] = (ch == '.') ? 0 : ch - '0';
            }
        }
    }

    static int getMemoIndex(int i, int j, int direction, int a, int b, int c) {
        // 가중치 8 + 가중치 4 + 가중치 2 (비트 연산자로 >> 3, >> 2, >> 1)
        int state = direction * 8 + a * 4 + b * 2 + c;
        // 2차원 위치 (i,j)와 상태 state를 memo 배열의 1차원 인덱스로 변경
        // i번째 행에 도달하기 전에 지나온 칸은 i * M개
        // 거기에 열 위치 j를 더함
        // → 격자를 1차원으로 펼친 칸 번호가 된다
        // 각 칸마다 상태가 16개므로, 그 칸의 상태들이 차지할 공간을 확보
        // + state: 그 16개 중 해당 상태의 위치를 선택
        return (i * M + j) * 16 + state;
    }

    static int dfs(int i, int j, int direction, int a, int b, int c) {
        // a: 현재 칸, b: 다른 뒤쪽 이웃, c: 꺾으면 다시 만날 음식점의 먹음 여부
        
        // DFS 상태를 1차원 memo 배열의 인덱스 하나로 변경
        int key = getMemoIndex(i, j, direction, a, b, c);

        // 메모이제이션 테이블에 이미 결과가 있는지 확인
        // 음수면 결과가 없으므로 return 안하고 양수면 return
        if (memo[key] != -1) {
            return memo[key];
        }

        // 현재 칸에 아직 안 먹은 음식점이 있으면 먹는다.
        // a가 0 : 안 먹은 음식점
        // 안 먹었으면 map[i][j]에 있는 가격 가져와서 저장
        int currentCost = (a == 0) ? map[i][j] : 0;

        // ** 모든 이동을 마치고 모든 재귀호출을 하나씩 끝냄 **
        // 도착점에서는 더 이동할 곳이 없으므로 현재 칸의 비용만 반환
        if (i == N - 1 && j == M - 1) {
            return memo[key] = currentCost;
        }

        // 방금 온 방향의 반대쪽에 있는 다른 이웃 칸의 가격
        int back;
        // 왼쪽에서 현재 칸으로 왔습니다. 다른 뒤쪽 이웃은 위쪽 (i-1, j)입니다.
        if (direction == RIGHT) {
            // (i > 0) -> 해당 칸이 격자 안에 있는지 확인
            // 격자 안에 있으면 그 값을 가져오고, 없으면 back은 안 먹은 걸로 처리(0)
            back = (i > 0) ? map[i - 1][j] : 0;
        }
        // 위쪽에서 현재 칸으로 왔습니다. 다른 뒤쪽 이웃은 왼쪽 (i, j-1)입니다.
        else {
            // (j > 0) -> 해당 칸이 격자 안에 있는지 확인
            // 격자 안에 있으면 그 값을 가져오고, 없으면 back은 안 먹은 걸로 처리(0)
            back = (j > 0) ? map[i][j - 1] : 0;
        }

        // [현재 칸 이웃 중 아직 먹지 않은 음식점 후보]
        // back 위치에 음식점이 있고(back > 0), 그 음식점을 아직 먹지 않았다면(b == 0) true
        // true여도 반드시 먹는 것은 아니고, 이번에 제외할 후보가 될 수도 있다.
        boolean canEatBack = back > 0 && b == 0;
        // [현재 위치 (i,j)의 오른쪽 칸에 있는 음식 가격을 저장] - (j + 1 < M) 오른쪽 열 j+1이 존재하는지 확인
        int right = (j + 1 < M) ? map[i][j + 1] : 0;
        // [현재 위치 (i,j)의 아래쪽 칸에 있는 음식 가격을 저장] - (i + 1 < N) 아래쪽 행 i+1이 존재하는지 확인
        int down = (i + 1 < N) ? map[i + 1][j] : 0;
        // [현재 칸의 이웃 중 아직 먹지 않은 음식점이 하나라도 있는지 확인]
        // 뒤쪽 이웃(먹을 수 있는 경우) || 오른쪽 || 아래쪽
        boolean hasCandidate = canEatBack || right > 0 || down > 0;

        // 현재 DFS 상태에서 도착점까지 가는 경로 중 최소 비용을 담는 변수
        int best = INF;

        // 현재 칸 주변에서 이번에 먹지 않을 음식점을 네 가지 경우로 나누어 모두 시도
        // 제외 대상: -1=없음, 0=뒤쪽, 1=오른쪽, 2=아래쪽 (지나온 좌표와는 무관)
        for (int excluded = -1; excluded <= 2; excluded++) {
            // [좌표 주변에 음식점이 없거나 먹어본 음식점 제외]
            // 주변에 먹지 않은 음식점이 있으면
            if (hasCandidate) {
                if (
                    excluded == -1 // 제외할 음식점이 없거나
                    || (excluded == 0 && !canEatBack) // 뒤쪽 && 먹지않은 음식점 없음
                    || (excluded == 1 && right == 0) // 오른쪽 && 오른쪽에 음식점 없음
                    || (excluded == 2 && down == 0) // 아래쪽 && 아래쪽에 음식점 없음
                ) {
                    // 다음 excluded 값 확인
                    continue;
                }
            // 칸 주변에 안 먹어본 음식점이 없으면
            } else if (excluded != -1) {
                // 다음 excluded 값 확인
                continue;
            }

            // 먹으러 갈 음식점 (ate != eat 아직 먹진 않았음)
            int ateRight = (right > 0 && excluded != 1) ? 1 : 0; // (오른쪽에 음식점이 있고 && 오른쪽(1)이 제외 안됨) 이면(?) 먹을거 : 안 먹을거
            int ateDown = (down > 0 && excluded != 2) ? 1 : 0; // (아래쪽에 음식점이 있고 && 아래쪽(2)이 제외 안됨) 이면(?) 먹을거 : 안 먹을거

            // 발생한 비용
            int cost = currentCost;
            // 현재 칸의 음식점을 아직 안 먹었으면 앞에 이웃 칸 가격을 더한다
            if (canEatBack && excluded != 0) cost += back;
            // 오른쪽에 먹으러 갈 음식점이 있으면 먹고(ate) 가격을 더함
            if (ateRight == 1) cost += right;
            // 아래쪽에 먹으러 갈 음식점이 있으면 먹고(ate) 가격을 더함
            if (ateDown == 1) cost += down;
            // 이제 먹었기 때문에 각각 ateRight, ateDown 변수명이 말이 됨

            // 오른쪽 칸으로 한 칸 이동 (오른쪽 칸으로 이동한 열 번호가 M보다 작은지 확인)
            if (j + 1 < M) {
                // 오른쪽으로 이동한 뒤 새로 도착한 칸의 b 값을 결정
                int nextB = (direction == DOWN) ? c : 0;
                // 계산된 값을 기존 best와 비교(Math.min)해 더 작은 경로를 저장한다.
                // 현재 칸에서 발생한 비용 cost와, 오른쪽으로 이동한 뒤 남은 최소 비용을 더한다.
                // 재귀호출로 현재 칸 (i,j)에서 오른쪽 칸 (i,j+1)으로 이동한 뒤, 새 칸의 상태를 전달
                // ateRight : 새 칸의 a 상태(오른쪽 칸이 이미 먹혔는지 확인)
                // nextB : 새 칸의 b 상태, ateDown : 새 칸의 c 상태
                best = Math.min(best, cost
                        + dfs(i, j + 1, RIGHT, ateRight, nextB, ateDown));
                // 재귀 호출 탈출 후 아래쪽도 이동 가능한지 확인
            }
            // 아래쪽 칸으로 한 칸 이동 (아래쪽 칸으로 이동한 행 번호가 N보다 작은지 확인)
            if (i + 1 < N) {
                // 아래쪽으로 이동한 뒤 새로 도착한 칸의 b 값을 결정
                int nextB = (direction == RIGHT) ? c : 0;
                // 계산된 값을 기존 best와 비교(Math.min)해 더 작은 경로를 저장한다.
                // 현재 칸에서 발생한 비용 cost와, 아래쪽으로 이동한 뒤 남은 최소 비용을 더한다.
                // 재귀호출로 현재 칸 (i,j)에서 아래쪽 칸 (i+1,j)으로 이동한 뒤, 새 칸의 상태를 전달
                // ateDown : 새 칸의 a 상태(아래쪽 칸이 이미 먹혔는지 확인)
                // nextB : 새 칸의 b 상태, ateRight : 새 칸의 c 상태
                best = Math.min(best, cost
                        + dfs(i + 1, j, DOWN, ateDown, nextB, ateRight));
            }
        }

        memo[key] = best; // 현재 상태의 결과 저장
        return best; // main에서 호출한 DFS로 결과 반환
    }
}
