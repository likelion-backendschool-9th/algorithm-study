import java.util.*; 

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        // 크기 별 개수 세기
        HashMap<Integer, Integer> box = new HashMap<>();
        
        for(int i=0; i<tangerine.length; i++){
            Integer temp = tangerine[i];
            
            if(box.containsKey(temp)){
                box.put(temp, box.get(temp)+1);
            } else {
                box.put(temp, 1);
            }            
        }
        
        // 개수만 배열로 옮기기
        Integer[] arr = new Integer[box.size()];
        int count=0;
        for(Integer i:box.values()){
            arr[count] = i;
            count++;
        }
        
        // 내림차순 정렬
        Arrays.sort(arr, Collections.reverseOrder());
        
        // 큰 것부터 더하다가 K 이상이 되면 종료함. 
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
            
            // 상자에 귤이 꽉 찼을 때
            if(k <= sum) {
                
                // 서로 다른 종류의 수의 최솟값 저장
                answer = i+1;
                break;
            }
        }
        
        
        return answer;
    }
}
