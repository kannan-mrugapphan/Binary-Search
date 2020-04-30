// 33.
// time - O(log n)
// space - O(1)

class Solution {
    public int search(int[] nums, int target) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return -1;
        }
        
        int low = 0;
        int high = nums.length - 1;
        
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target)
            {
                return mid;
            }
            if(nums[low] <= nums[mid]) //left half is sorted
            {
                if(nums[low] <= target && target < nums[mid]) //target is present in this half
                {
                    high = mid - 1;
                }
                else
                {
                    low = mid + 1;
                }
            }
            else //right half is sorted
            {
                if(nums[mid] < target && target <= nums[high]) //target is present in this half
                {
                    low = mid + 1;
                }
                else
                {
                    high = mid - 1;
                }
            }      
        }
        return -1;
    }
}
