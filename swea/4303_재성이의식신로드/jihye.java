import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
메모: 테스트는 맞는데 시간초과 나와서 억울해서 올림. 

첫 번째 접근법 : DFB + 백트래킹. 단, 속도를 위해 메모제이션을 해야 할 것 같음. 
==> 실패! 어떻게 해도 속도를 맞출 수가 없음. DP+메모제이션 문제인듯

배운 것
- 백트래킹 원칙: 재귀 전에 바꾼 것은, 재귀 후에 반드시 되돌린다 / 이번 칸에서 표시한 것들을 전부 원복한다. / 내가 바꾼 것만 내가 되돌린다. 
*/

public class Solution { //Solution

	static int[][] isVisited; //방문 여부 배열 -> 0 = 아직 방문 안함 / 1 = 방문함 이전에 먹음 / 2 = 이번에 먹은 것.
	static int[][] map;
	static int[] size;
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0}; // 0~1은 이동 경로를 겸함. 
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int testCase = Integer.parseInt(br.readLine());

		for(int i=1; i<=testCase; i++){
			size = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			//변수 세팅
			isVisited = new int[size[0]][size[1]];
			map =  new int[size[0]][size[1]];

			answer = Integer.MAX_VALUE;

			//지도 세팅
			char[][] temp = new char[size[0]][size[1]];
			for(int j=0; j<size[0]; j++){
				String line = br.readLine();

				for(int k=0; k<size[1]; k++){
					temp[j][k] = line.charAt(k);
				}
			}
			setMap(temp);
			
			//DFS+백트래킹
			DFS(0, new int[] {0, 0});

			bw.write("#"+i+" "+answer+"\n");
		}

		bw.close();

	}

	public static void DFS(int cost, int[] current){

		//종료 지점에 도달했을 떄
		if(current[0]==size[0]-1 && current[1]==size[1]-1) {
			answer = Math.min(answer, cost + map[current[0]][current[1]]);
			return;
		}

		//탐색 범위를 벗어났을 때
		if(current[0] < 0 || current[0] >= size[0] || current[1] < 0 || current[1] >= size[1]) return;
		
		//더 탐색할 가치가 없을 때(answer 보다 현재 cost가 크거나 같을 때)
		if(answer <= cost) return;
		
	
		//로직 시작
		//현재 위치가 방문한 위치인지 검사
		int x = current[0], y = current[1];

		boolean ateHere = false; // 지금 위치가 원래 방문한 적 없는지 표식 ( 추후 백트래킹 분기 오염을 막기 위해 )

		if(isVisited[x][y] == 0) {
			cost += map[x][y];
			isVisited[x][y] = 1;
			ateHere = true;		// 지금 방문으로 인해 1로 변한 경우
		}

		//탐색 범위 지정
		// Queue<int[]> nextPoint = new LinkedList<>();  => Queue를 쓰면 안됨!! 왜냐면 다른 분기에서 재사용 할 수 없기 때문
		List<int[]> nextPoint = new ArrayList<>();
		for(int i=0; i<4; i++){
			int nx = x + dx[i], ny = y + dy[i];
			
			// 검사 조건에 안맞는 경우 건너뛰기
			if(nx < 0 || size[0] <= nx || ny < 0 || size[1] <= ny) { continue; }	//검사 범위를 벗어나는 곳일 경우
			if(isVisited[nx][ny] != 0 || map[nx][ny] == 0) { continue; }			//방문했던 곳이거나 비용이 0원일 경우
		
			cost += map[nx][ny];					// 주변 맛집 비용 전부 방문했다 가정
			isVisited[nx][ny] = 2; 					
			nextPoint.add(new int[] {nx, ny});		// 제외할 맛집 위치 저장
		}

		//만일 주변에 맛집이 없었을 경우
		if(nextPoint.isEmpty()){
			DFS(cost, new int[] {x+1, y});
			DFS(cost, new int[] {x, y+1});
		} 

		//만일 주변에 맛집이 있을 경우 (비어 있으면 while 이 실행되지 않기 때문에 else문 불필요)
		for(int[] now : nextPoint){
			//현재 위치 값 지정
			int nx = now[0], ny = now[1];

			//분기를 위해 2 -> 0으로 변경 (제외하는 맛집을 뜻함. 안 먹었다는 표시)
			isVisited[nx][ny] = 0;

			//분기점
			DFS(cost-map[nx][ny], new int[] {x+1, y});
			DFS(cost-map[nx][ny], new int[] {x, y+1});

			//제외 맛집 표시 복원. 
			isVisited[nx][ny] = 2;

		}

		//백트래킹을 위해 표기 복원
		for(int[] m : nextPoint) {
			isVisited[m[0]][m[1]] = 0;  // 이번 분기 떄 변경한 것만 원복
		}

		//분기 중 방문으로 바뀐 경우만 원복. 
		if(ateHere) isVisited[x][y] = 0;
	}

	public static void setMap(char[][] before){
		for(int i=0; i<before.length; i++){
			for(int j=0; j<before[0].length; j++){
				map[i][j] = before[i][j] == '.' ? 0 : (before[i][j] - '0');
			}
		}
	}
}
