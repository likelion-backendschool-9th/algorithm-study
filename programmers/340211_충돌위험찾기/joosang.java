class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        int x = routes.length; // 로봇 수

        int[][] robot_saves = new int[x][2]; // 로봇이 어디에 있는가를 저장
        // robot_saves[i][0] - i번 로봇의 현재 r 좌표
        // robot_saves[i][1] - i번 로봇의 현재 c 좌표
        int[] robot_destination = new int[x]; // 다음으로 방문할 경로 항목

        int[][] occupied = new int[101][101]; // 각 좌표에 위치한 로봇 대수
        // ArrayIndexOutOfBoundsException 방지를 위해 101개로 잡음

        // 0초(출발 전) 상태 준비
        for (int i = 0; i < routes.length; i++) {
            // 출발 포인트 지정 (인덱스이므로 -1)
            int startPoint = routes[i][0] - 1;

            // i번째 로봇의 현재 r, c를 출발 좌표로 삼음
            robot_saves[i][0] = points[startPoint][0];
            robot_saves[i][1] = points[startPoint][1];

            // 출발 위치에 로봇 한대가 있다는 사실을 occupied에 담음
            occupied[robot_saves[i][0]][robot_saves[i][1]]++;

            robot_destination[i]++; // 다음 목표는 경로의 다음 인덱스 항목, 첫 포인트가 인덱스 0이면 다음은 routes[i][1]
        }

        // 0초(출발 전)에 출발 포인트에서 이미 1대 이상 점유한 경우
        // 아래에서도 위험 상황을 세지만, 출발 전의 변수도 고려해야 한다.
        for (int r = 0; r < occupied.length; r++) {
            for (int c = 0; c < occupied[r].length; c++) {
                // 이것도 위험 상황이므로 answer 1회씩 증가
                if (occupied[r][c] >= 2) {
                    answer++;
                }
            }
        }

        boolean[] finished = new boolean[x]; // i번 로봇이 도착을 마쳤는지 여부 (로봇 갯수만큼 배열 생성)
        
        int remaining = x; // 이동 대기중인 로봇 수

        while (remaining > 0) {
            occupied = new int[101][101];
            // 새로운 시각(출발 전인 0초로부터 동작 시작)의 위치를 기록하기 위해 초기화

            for (int i = 0; i < x; i++) {
                // 지정된 경로로 이동한 i번 로봇은 제외
                if (finished[i]) {
                    continue;
                }

                int targetPoint = routes[i][robot_destination[i]] - 1;
                int targetR = points[targetPoint][0]; // 목표 포인트의 r 좌표
                int targetC = points[targetPoint][1]; // 목표 포인트의 c 좌표

                int gapR = targetR - robot_saves[i][0]; // 현재 위치와 목표 위치의 차이 (r좌표)
                int gapC = targetC - robot_saves[i][1]; // 현재 위치와 목표 위치의 차이 (c좌표)

                if (gapR != 0) {
                    robot_saves[i][0] += gapR > 0 ? 1 : -1; // r좌표 먼저 이동
                } else if (gapC != 0) {
                    robot_saves[i][1] += gapC > 0 ? 1 : -1; // r을 다 옮겼으면 c 이동
                }

                // 목적지에 도착한 경우 (현재 좌표와 목표 좌표가 같은지 확인)
                if (robot_saves[i][0] == targetR && robot_saves[i][1] == targetC) {
                    robot_destination[i]++; // 다음 목적지로 (현재 로봇의 다음 목표)

                    // 해당 로봇이 모든 목적지를 방문한 경우
                    if (robot_destination[i] == routes[i].length) {
                        finished[i] = true; // 마지막 포인트 도착, 로봇 소멸
                        remaining--; // 운송을 마친 로봇은 남은 로봇 수에서 하나 제거
                    }
                }

                // 현재 시각에 로봇 한대가 있다는 사실을 occupied에 담음
                occupied[robot_saves[i][0]][robot_saves[i][1]]++;
            }

            // 로봇이 점유중인 좌표 순회
            for (int r = 0; r < occupied.length; r++) {
                for (int c = 0; c < occupied[r].length; c++) {
                    // 2대 이상이면 위험 상황이므로 answer 1회씩 증가
                    if (occupied[r][c] >= 2) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}
