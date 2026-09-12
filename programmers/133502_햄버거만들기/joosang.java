/*
"상수가 일하는 가게는 정해진 순서(아래서부터, 빵 – 야채 – 고기 - 빵)로 쌓인 햄버거만 포장을 합니다."
즉 재료가 1 2 3 1 이렇게 연속되어있지 않으면 햄버거 하나가 완성이 안된다
어차피 ingredient가 순서대로 돌기 때문에
현재 인덱스 +1 +2 +3
이렇게 다닥다닥 1 2 3 1 인지 확인하는 if문으로 체크
그런 패턴이 ingredient 배열 안에 있으면 answer++하고 없으면 패스
...
정확성 테스트에서 실패
원인을 찾아보니 햄버거 재료 갯수만큼 ingredient 배열 요소를 사용했으니
그만큼의 인덱스에 있는 ingredient 요소를 제거해야 한다
1 2 3 1 패턴을 검사만 해서 answer에 카운트하는 것 외에 재료를 빼서 썼으므로
해당 요소들을 배열에서 제거
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        int[] ingredient = {2,1,1,2,3,1,2,3,1};
        // int[] ingredient = {1,3,2,1,2,1,3,1,2};
        solution(ingredient);
    }

    public static int solution(int[] ingredient) {
        int answer = 0; // 완성된 버거 갯수
        List<Integer> ingredients = new ArrayList<>(); // 재료 갯수가 다양하므로 배열 크기 제한에서 자유로운 ArrayList로 하나씩 담아서 변환
        for (int i : ingredient) {
            ingredients.add(i);
        }
        for (int i = 0; i < ingredients.size(); i++) { // 재료 갯수만큼 반복
            // 재료를 하나하나씩 쌓아서(stack해서) 완성할 수 있다
            // stack으로 처리할 수도 있지만, 배열이 1, 2, 3, 1로 연속된 아이템이 있어야 함. stack으로 굳이 쌓는 방식이 무의미할 것으로 판단
            // 현재 인덱스 +3까지 검사할 때 ArrayIndexOutOfBoundsException 안나도록 배열 길이 대조
            // + 현재 인덱스에 1(빵) 있고
            // + 현재 인덱스 +1에 2(야채) 있고
            // + 현재 인덱스 +2에 3(고기) 있고
            // + 현재 인덱스 +3에 1(빵) 있어야함
            if ((i + 3 < ingredients.size()) &&
                (ingredients.get(i) == 1 && ingredients.get(i + 1) == 2 &&
                ingredients.get(i + 2) == 3 && ingredients.get(i + 3) == 1)
            )
                {
                    // 배열 중에 그런 패턴이 있으면 햄버거 +1
                    answer++;
                    // 해당 배열 요소 4개 제거 (재료를 썼기 때문에 ingredient에서 빠짐, 그래야 빠진 재료를 중복 사용 안함)
                    for (int j = 0; j < 4; j++) {
                        ingredients.remove(i);
                    }
                    // 빠진 자리만큼 인덱스 다시 세팅 (4개) (i = i - 4)
                    // Math.max의 경우 i - 4이 0 이하면, 0을 max가 되도록 해서 음수가 되지 않도록 함
                    // for문(재료 갯수만큼 반복) 끝에서 i++ 실행되는 부분을 고려해 1을 빼 둠
                    // 예를 들어 다음 검사를 0부터 하고 싶으면, 일단 i=-1로 설정해 두어야 i++ 이후 0이 된다. (인덱스 공백 상쇄)
                    i = Math.max(0, i - 4) - 1;
                }
        }
        System.out.println(answer);
        return answer;
    }
}