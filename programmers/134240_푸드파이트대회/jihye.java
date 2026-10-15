import java.util.*;

class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < food.length; i++) {
            int cnt = food[i] / 2;
            for (int j = 0; j < cnt; j++) {
                sb.append(i);
            }
        }

        String left = sb.toString();
        return left + "0" + sb.reverse().toString();
    }
}
