// 33.
// time - O(log n)
class Solution {
    public int search(int[] nums, int target) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return -1; //not found
        }
        
        int low = 0;
        int high = nums.length - 1;
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target)
            {
                return mid; //target found - return
            }
            //check if left half is sorted eg: [5,6,7,8,9,1,2]
            else if(nums[low] <= nums[mid]) //equality condition to handle case when mid and low are same
            {
                if(nums[low] <= target && target < nums[mid]) //target is in the left half which is sorted
                {
                    high = mid - 1; 
                }
                else
                {
                    low = mid + 1; //go to right half which is a smaller rotated sorted array
                }
            }
            else //right half is sorted eg: [5,0,1,2,3]
            {
                if(nums[mid] < target && target <= nums[high]) //target is in right sorted half
                {
                    low = mid + 1;
                }
                else
                {
                    high = mid - 1; //go to left half which is a smaller rotated sorted array
                }
            }
        }
        
        return -1; //not found
    }
}

// 81.
// time - O(log n)
class Solution {
    public boolean search(int[] nums, int target) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return false; //not found
        }
        
        int low = 0;
        int high = nums.length - 1;
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target)
            {
                return true; //target found - return
            }
            //eg: [3,1,3,3,3] - can't decide on which half is sorted
            while(low < mid && nums[low] == nums[mid])
            {
                low++;
            }
            while(mid < high && nums[mid] == nums[high])
            {
                high--;
            }
            
            //check if left half is sorted eg: [5,6,7,8,9,1,2]
            if(nums[low] <= nums[mid]) //equality condition to handle case when mid and low are same
            {
                if(nums[low] <= target && target < nums[mid]) //target is in the left half which is sorted
                {
                    high = mid - 1; 
                }
                else
                {
                    low = mid + 1; //go to right half which is a smaller rotated sorted array
                }
            }
            else //right half is sorted eg: [5,0,1,2,3]
            {
                if(nums[mid] < target && target <= nums[high]) //target is in right sorted half
                {
                    low = mid + 1;
                }
                else
                {
                    high = mid - 1; //go to left half which is a smaller rotated sorted array
                }
            }
        }
        
        return false; //not found
    }
}
