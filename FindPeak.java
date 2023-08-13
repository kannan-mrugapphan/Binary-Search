// 162.
// time - O(log n)
class Solution {
    public int findPeakElement(int[] nums) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return -1; 
        }
        //theoretically -infinity is present before element at 0th index and after element at last index
        //array has only one element
        if(nums.length == 1)
        {
            return 0;
        }
        //to avoid cases to handle array index out of bounds exception, initialize search space to [1,nums.length-2]
        //check if 0th index and last index is a peak seperately
        if(nums[0] > nums[1])
        {
            return 0;
        }
        if(nums[nums.length - 1] > nums[nums.length - 2])
        {
            return nums.length - 1;
        }
        

        int low = 1;
        int high = nums.length - 2;

        //as long as search space has atleast 1 element
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //check if mid is a peak
            if(nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1])
            {
                return mid;
            }

            //check if mid is part of decreasing half
            if(nums[mid] > nums[mid + 1])
            {
                //mid is not a peak and is in decreasing half
                high = mid - 1; //check for peak in left half
            }

            //else mid is in increasing half
            else if(nums[mid] < nums[mid + 1])
            {
                //mid is not a peak and is in increasing half
                low = mid + 1; //check for peak in right half
            }
        }

        return -1; //peak doesn't exist
    }
}
