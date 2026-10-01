import java.util.*;

class Solution {
    // 각 정점의 진입/진출 수를 저장할 배열
    static int[] in = new int[1_000_001];
    static int[] out = new int[1_000_001];
    
    static int[] answer = new int[4];
    static Set<Integer> nodes = new HashSet<>();
        
    public int[] solution(int[][] edges) {
        // 초기화
        in = new int[1_000_001];
        out = new int[1_000_001];
        answer = new int[4];
        nodes = new HashSet<>();
        
        // 1. 노드 별 진입과 진출 카운트
        setCount(edges);
        
        getVertex();
        getBarAndEight(answer[0]);
        getDonut();
        
        return answer;
    }
    
    // 진입/진출 카운트
    public void setCount(int[][] edges){
        for(int[] edge : edges){
            int start = edge[0], end = edge[1];
            
            out[start]++;   // start로 나가는 거니까 out
            in[end]++;      // end로 들어오니까 in
            nodes.add(start);
            nodes.add(end);
        }
        
        return;
    }
    
    // 생성된 정점 찾기
    public void getVertex(){
        for(int node : nodes){
            if(in[node]==0 && 2<=out[node]){      //진출이 2이상
                answer[0] = node;
            }
        }
    }
    
    public void getBarAndEight(int vertex) {
        for(int node : nodes){
            // 정점 제외
            if(node==vertex) continue;
            
            //막대 그래프 카운트
            if(out[node]==0) { 
                answer[2]++; 
            }
            
            //8자 그래프 카운트
            if(out[node]==2){
                answer[3]++;
            }
        }
    }
    
    public void getDonut(){
        answer[1] = out[answer[0]] - (answer[2]+answer[3]);
    } 
}
