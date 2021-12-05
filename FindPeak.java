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

// 852.
// time - O(log n)
// find in a bitonic array -> find peak -> do BS in left half for target -> if not found do BS in right half
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        //edge
        if(arr == null || arr.length == 0)
        {
            return -1; //invalid case
        }
        
        int low = 0; //initial search space is the whole array
        int high = arr.length - 1;
        
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //mid is peak if nums[mid - 1] <= nums[mid] <= nums[mid + 1], equality condition not needed due to no duplicates
            if((mid == 0 || arr[mid] >= arr[mid - 1]) && (mid == arr.length - 1 || arr[mid] >= arr[mid + 1]))
            {
                return mid;
            }
            //current mid is less than next, so currently in increasing half, peak is in [mid + 1, high]
            else if(mid != arr.length - 1 && arr[mid] < arr[mid + 1])
            {
                low = mid + 1;
            }
            //current mid is less than prev, currently in decreasing half and mid is not peak as first if failed
            //peak is [low, mid - 1]
            else if(mid != 0 && arr[mid] < arr[mid - 1])
            {
                high = mid - 1;
            }
        }
        
        return -1; //code never reaches here
    }
}
