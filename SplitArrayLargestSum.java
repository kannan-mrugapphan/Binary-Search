// 410.
// time - O(log(sum(nums[] - max(nums[]))) * length of nums[]) with constant space
class Solution {
    public int splitArray(int[] nums, int k) {
        //each element in nums[] should be part of 1 sub array
        //each subarray should have atleast 1 element
        //edge
        if(k > nums.length)
        {
            return -1; //not possible to form k sub arrays with at least 1 element
        }

        //min sum is each element will be its own sub array with sum = max(nums[])
        //max sum is all elements are in same sub array with sum = sum(nums[])
        int low = nums[0]; //should track max in nums[]
        int high = 0; //should track sum(nums[])
        for(int num : nums)
        {
            low = Math.max(low, num);
            high += num;
        }

        //optimal ans is in [low, high]
        int result = high; //should be minimized in range, so set it to largest in range
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //count sub arrays possible if max sum is mid
            int subarrays = findSubArrays(nums, mid);

            if(subarrays <= k)
            {
                //k sub arrays can be formed
                result = mid;
                high = mid - 1; //check if k subarrays can be formed with lower sum, search in left half
            }
            else
            {
                 //subarrays is more than k, increase max sum so sub arrays can be reduced, search in right half
                low = mid + 1;
            }
        }

        return result;
    }

    //computes number of sub arrys possible if largest sub array sum is given
    // time - O(n) with constant space
    private int findSubArrays(int[] nums, int limit)
    {
        int count = 1;
        int currentSum = 0; //current sub array initially doesn't have any elements so sum is 0
        for(int num : nums)
        {
            if(num + currentSum <= limit)
            {
                currentSum += num; //current element can be included in current subarray
            }
            else
            {
                count++; //current will be part of new sub array
                currentSum = num;
            }
        }

        return count;
    }
}
