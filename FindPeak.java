// 162.
// time - O(log n) 
//space - constant

class Solution {
    public int findPeakElement(int[] nums) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return Integer.MIN_VALUE;
        }
        
        int low = 0;
        int high = nums.length - 1;
        
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if((mid == 0 || nums[mid - 1] <= nums[mid]) && (mid == nums.length - 1 || nums[mid + 1] <= nums[mid]))
            {
                //mid is a valid peak -> mid is greater than or equal to both prev and next
                return mid;
            }
            
            else if(mid > 0 && nums[mid - 1] >= nums[mid]) //prev greater than or equal to mid
            {
                //prev is a possible peak -> so continue seraching in left
                high = mid - 1;
            }
            
            else //next greater than or equal to mid
            {
                //next is a possible peak -> so continue seraching in right
                low = mid + 1;
            }
        }
        
        return -1;
    }
}
