
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;

        int m = n - k + 1;
        int[] sum = new int[m];

        // Calculate sum of every window of size k
        int windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        sum[0] = windowSum;

        for (int i = k; i < n; i++) {
            windowSum += nums[i] - nums[i - k];
            sum[i - k + 1] = windowSum;
        }

        // left[i] = index of maximum sum window from 0 to i
        int[] left = new int[m];

        int best = 0;

        for (int i = 0; i < m; i++) {
            if (sum[i] > sum[best]) {
                best = i;
            }

            left[i] = best;
        }

        // right[i] = index of maximum sum window from i to end
        int[] right = new int[m];

        best = m - 1;

        for (int i = m - 1; i >= 0; i--) {
            if (sum[i] >= sum[best]) {
                best = i;
            }

            right[i] = best;
        }

        // Find the best combination of three windows
        int[] result = new int[3];
        int maxTotal = Integer.MIN_VALUE;

        for (int mid = k; mid < m - k; mid++) {

            int l = left[mid - k];
            int r = right[mid + k];

            int total = sum[l] + sum[mid] + sum[r];

            if (total > maxTotal) {
                maxTotal = total;

                result[0] = l;
                result[1] = mid;
                result[2] = r;
            }
        }

        return result;
    }
}
