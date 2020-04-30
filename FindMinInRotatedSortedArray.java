// 153.
//time - O(log n)
//space - constant

class Solution {
    public int findMin(int[] nums) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return -1;
        }
        
        int low = 0;
        int high = nums.length - 1;
        
        while(low < high)
        {
            int mid = low + (high - low) / 2;
            int prev = (mid - 1 + nums.length) % nums.length;
            int next = (mid + 1) % nums.length;
            
            if(nums[mid] <= nums[prev] && nums[mid] <= nums[next]) //mid is smaller than both prev and next, return mid
            {
                return nums[mid];
            }
            
            if(nums[low] <= nums[high]) //full array sorted
            {
                return nums[low];
            }
            
            if(nums[low] <= nums[mid]) //left half sorted
            {
                low = mid + 1;
            }
            
            else //right half sorted
            {
                high = mid - 1;
            }
        }
        
        return nums[low];
    }
}
