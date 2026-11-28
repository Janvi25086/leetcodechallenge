
import java.util.*;

class Solution {
    public int maxPoints(int[][] points) {

        int n = points.length;

        if (n <= 2) return n;

        int ans = 0;

        for (int i = 0; i < n; i++) {

            Map<String, Integer> map = new HashMap<>();

            int duplicate = 1;
            int max = 0;

            for (int j = i + 1; j < n; j++) {

                long dx = (long) points[j][0] - points[i][0];
                long dy = (long) points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++;
                    continue;
                }

                long g = gcd(Math.abs(dx), Math.abs(dy));

                dx /= g;
                dy /= g;

                // Normalize slope
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                }

                if (dx == 0) {
                    dy = 1;
                }

                String key = dy + "/" + dx;

                map.put(key, map.getOrDefault(key, 0) + 1);

                max = Math.max(max, map.get(key));
            }

            ans = Math.max(ans, max + duplicate);
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
