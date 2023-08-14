// 1283.
// time - O(log(max in nums[]) * length of nums[]) with constant space
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        //edge
        if(threshold <= 0)
        {
            return Integer.MAX_VALUE; //any divisor won't result in sum <= 0
        }

        //lowest divisor is 1, sum = sum of all nums
        //highest divisor is max(nums), sum = length of nums[]
        int minDivisor = 1;
        int maxDivisor = nums[0];
        for(int num : nums)
        {
            maxDivisor = Math.max(maxDivisor, num);
        }

        //optimal divisor is in [min, max] range
        int result = maxDivisor; //result should be minimized in range so initialized to max
        while(minDivisor <= maxDivisor)
        {
            int mid = minDivisor + (maxDivisor - minDivisor) / 2;
            //compute sum if divisor = mid
            int sum = findSum(nums, mid);

            //if sum is lower than threshold
            if(sum <= threshold)
            {
                result = mid; //update result as a valid divisor is found
                maxDivisor = mid - 1; //search in left for even smaller valid divisors
            }
            else
            {
                minDivisor = mid + 1; //sum is above threshold, search in right for larger divisor to reduce sum
            }
        }

        return result;
    }

    //computes sum given the divisor
    //time - O(n) with constant space
    private int findSum(int[] nums, int divisor)
    {
        int sum = 0;
        for(int num : nums)
        {
            //if num is 1 and divisor is 4, current should be 1 (ceil of absolute division)
            int current = (int) Math.ceil(num / (double)divisor);
            sum += current;
        }
        return sum;
    }
}
