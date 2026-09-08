/*
1. 지폐를 접은 횟수를 저장할 정수 변수 answer를 만들고 0을 저장합니다.
2. 반복문을 이용해 bill의 작은 값이 wallet의 작은 값 보다 크거나 bill의 큰 값이 wallet의 큰 값 보다 큰 동안 아래 과정을 반복합니다.
    2-1. bill[0]이 bill[1]보다 크다면
        bill[0]을 2로 나누고 나머지는 버립니다.
    2-2. 그렇지 않다면
        bill[1]을 2로 나누고 나머지는 버립니다.
    2-3. answer을 1 증가시킵니다.
3. answer을 return합니다.
*/

class Solution {
    public static void main(String[] args) {
        int[] wallet = {30, 15};
        int[] bill = {26, 17};
        System.out.println(solution(wallet, bill));
    }

    public static int solution(int[] wallet, int[] bill) {
        int answer = 0;

        // 반복문을 이용해 bill의 작은 값이 wallet의 작은 값 보다 크거나
        // bill의 큰 값이 wallet의 큰 값 보다 큰 동안
        // 아래 과정을 반복합니다.
        while (true) {
            // 탈출 조건은 임의로 만들 것. 반복이 필요하므로 무한루프를 while문으로 생성
            int walletMin = Math.min(wallet[0], wallet[1]);
            int walletMax = Math.max(wallet[0], wallet[1]);
            int billMin = Math.min(bill[0], bill[1]);
            int billMax = Math.max(bill[0], bill[1]);

            // '지폐'가 '지갑'에 안 들어감
            // ('작은 지갑(walletMin)'이 '작은 지폐(billMin)'보다 작거나)
            // ('큰 지갑(walletMax)'이 '큰 지폐(billMax)'보다 작은 경우)
            if (walletMin < billMin || walletMax < billMax) {
                // bill[0]이 bill[1]보다 크다면 (bill[0]이 큰 지폐)
                if (bill[0] > bill[1]) {
                    // bill[0]을 2로 나누고 나머지는 버립니다. (접음. 지폐를 접는데 나머지는 필요가 없다.)
                    bill[0] = bill[0] / 2;
                }
                // bill[0]이 bill[1]보다 작다면 (bill[1]이 큰 지폐)
                else {
                    // bill[1]을 2로 나누고 나머지는 버립니다. (접음. 지폐를 접는데 나머지는 필요가 없다.)
                    bill[1] = bill[1] / 2;
                }
                answer++;
            }
            // '지폐'가 '지갑'에 들어감 (접을만한 지폐는 다 접음)
            else {
                // 탈출
                break;
            }
        }

        return answer;
    }
}