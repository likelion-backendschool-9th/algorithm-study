import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long lt = 1;
        long rt = Long.MAX_VALUE / 10;
        while (lt < rt) {
            long mid = (lt + rt) / 2;
            long count = check(times, mid);

            if (count >= n) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }
        return lt;
    }

    static long check(int[] times, long mid) {
        long sum = 0;
        for (long x : times) {
            sum += mid / x;
        }
        return sum;
    }
}