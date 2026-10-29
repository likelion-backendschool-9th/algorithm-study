class Solution {
    int[] discounts; // 각 이모티콘에 적용할 할인율 배열
    int maxSubscribers = 0; // 지금까지 확인한 할인율 배치 중 가장 많은 서비스 가입자 수
    int maxSales = 0; // maxSubscribers만큼 가입자를 확보한 배치 중 가장 높은 이모티콘 매출

    public int[] solution(int[][] users, int[] emoticons) {
        // 이모티콘 m개의 정가를 담은 1차원 정수 배열 emotions를 담는다.
        discounts = new int[emoticons.length];

        // DFS를 통한 백트래킹으로 계산
        dfs(0, users, emoticons);

        // 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액 return
        return new int[]{maxSubscribers, maxSales};
    }

    void dfs(int index, int[][] users, int[] emoticons) {
        // 모든 이모티콘의 할인율을 정한 경우
        if (index == emoticons.length) {
            // 현재 할인율 배치에서 서비스에 가입하는 사용자 수
            int subscribers = 0;
            // 현재 할인율 배치에서 발생하는 이모티콘 매출
            int sales = 0;

            // users에서 사용자 정보를 한 명씩 꺼내서 반복
            for (int[] user : users) {
                int total = 0;

                // 모든 이모티콘을 첫 번째부터 마지막까지 하나씩 확인
                for (int i = 0; i < emoticons.length; i++) {
                    // 사용자가 원하는 할인율 이상이면
                    if (discounts[i] >= user[0]) {
                        // 조건을 만족하면 할인된 가격을 total에 더한다. (백분율 계산)
                        total += emoticons[i] * (100 - discounts[i]) / 100;
                    }
                }

                // 구매 합계가 기준 금액 이상이면
                if (total >= user[1]) {
                    // 가입자 수 1명 추가
                    subscribers++;
                } else {
                    // 기준 금액 이하면 이모티콘 구매
                    sales += total;
                }
            }

            // 가입자 수를 먼저 비교하고, 같으면 매출 비교
            if (subscribers > maxSubscribers ||
                (subscribers == maxSubscribers && sales > maxSales)) {
                maxSubscribers = subscribers;
                maxSales = sales;
            }

            // 모든 할인율을 정하고 결과까지 계산하여 dfs 호출을 종료
            return;
        }

        // 현재 이모티콘의 할인율을 하나씩 선택
        for (int rate = 10; rate <= 40; rate += 10) {
            // index번째 이모티콘의 할인율을 rate로 저장한다.
            discounts[index] = rate;
            // dfs()를 다시 호출해서 다음 이모티콘의 할인율을 정한다.
            dfs(index + 1, users, emoticons);
        }
    }
}