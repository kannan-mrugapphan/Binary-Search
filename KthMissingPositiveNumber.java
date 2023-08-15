// 1539.
class Solution {
    public int findKthPositive(int[] arr, int k) {
        return findKthPositiveNumberOptimal(arr, k);
    }

    //time - O(n) with constant space
    private int findKthPositiveLinearTime(int[] nums, int k)
    {
        //eg: [10,20,30,40] with k = 4 -> result = 4 as all the 1st 4 positive numbers are missing
        //eg: [1,6,7,8,9] with k = 4 -> result can't be 4 as 1 among the 1st 4 positive numbers is present
        int result = k; //assuming that all 1st k positive numbers are missing
        for(int num : nums)
        {
            if(num <= result) //current number is not missing in range [1, result] which was initially [1, k]
            {
                result++; //increase result by 1 to account for current not missing number
            }
            else
            {
                //current number and all numbers to right of current won't affect the result as they are larger
                break;
            }
        }

        return result;
    }

    //time - O(log n) with constant space
    private int findKthPositiveNumberOptimal(int[] nums, int k)
    {
        //eg: [1,2,5,9] with k = 4
        //temp = [0,0,2,5] with temp[i] is number of positive numbers missing from 1 to nums[i]
        //k = 4 is between index 2 and 3 in temp[]
        //ideally if no positive number is missing, then in the sorted array the number x should be at index x - 1
        //so temp[i] = nums[i] - 1 - i
        int low = 0;
        int high = nums.length - 1;
        //as long as search space has atleast 1 element
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            int countOfMissingNumbersTillMid = nums[mid] - 1 - mid;

            if(countOfMissingNumbersTillMid < k)
            {
                //kth missing positive is larger than mid
                low = mid + 1;
            }
            else if(countOfMissingNumbersTillMid >= k)
            {
                //kth missing positive is smaller than mid
                high = mid - 1;
            }
        }

        //kth missing positive is between [high, low] after search space is exhausted
        //kth missing number is nums[high] + (k - number of positive numbers missing till high)
        //nums[high] will be array index out of bounds if high = -1
        //result = nums[high] + (k - count of missing numbers till high) 
        //       = nums[high] + (k - (nums[high] - high - 1))
        //       = nums[high] + k - nums[high] + high + 1 = k + high + 1
        return k + high + 1;
    }
}
