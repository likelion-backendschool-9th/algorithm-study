class Solution {
    public String[] solution(String[] names) {
        // 5명씩 묶었을 때 그룹이 몇개 필요한지 계산
        // (정수끼리 나누면 소수점 이하가 버려지는 특징.
        // 5명이 안되는 나머지 사람들은 그룹 수로 계산 안한다.)
        // 사람 수 |      계산      |  그룹 수
        //   5명  |  (5 + 4) / 5   |    1
        //   6명  |  (6 + 4) / 5   |    2
        //  10명  |  (10 + 4) / 5  |    3
        //  11명  |  (11 + 4) / 5  |    4
        int size = (names.length + 4) / 5;
        // 줄선 사람들 명단 리스트를 list에 size만큼 담음
        String[] list = new String[size];

        // size만큼 for문 돌림
        for (int i = 0; i < size; i++) {
            // 각 그룹의 첫번째 이름을 결과 배열에 저장
            // i가 0부터 시작하므로 names의 5번째는 그룹의 첫번째 명단
            list[i] = names[i * 5];
        }

        // 그룹의 가장 앞에 서있는 사람들의 이름을 담은 리스트를 return
        return list;
    }
}