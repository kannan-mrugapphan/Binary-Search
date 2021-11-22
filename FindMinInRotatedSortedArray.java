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
        
        //explore as long as current sub array size is above 1
        //if sub array size is 1, then that is the min element
        while(low < high)
        {
            int mid = low + (high - low) / 2;
            if(nums[mid] > nums[high])
            {
                //eg: [4,5,6,7,0,1,2]
                //we are at the first increasing chunk
                //all the elements to left of mid and mid are rotated, so min is at the right half
                low = mid + 1;
            }
            else if(nums[mid] < nums[high]) //else -> not <= as mid and high can't be same within while 
            {
                //eg: [6,5,0,1,2,3,4]
                //the rotation has happened in the left half and we are currently in the second increasing chunk
                //potentially this mid could be min
                high = mid;
            }
        }
        
        return nums[high]; //nums[low]
    }
}

// 154.
// time -> avg: O(log n), worst: O(n)
class Solution {
    public int findMin(int[] nums) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return -1;
        }
        
        int low = 0;
        int high = nums.length - 1;
        
        //explore as long as current sub array size is above 1
        //if sub array size is 1, then that is the min element
        while(low < high)
        {
            int mid = low + (high - low) / 2;
            if(nums[mid] > nums[high])
            {
                //eg: [4,5,6,7,0,1,2]
                //we are at the first increasing chunk
                //all the elements to left of mid and mid are rotated, so min is at the right half
                low = mid + 1;
            }
            else if(nums[mid] < nums[high])
            {
                //eg: [6,5,0,1,2,3,4]
                //the rotation has happened in the left half and we are currently in the second increasing chunk
                //potentially this mid could be min
                high = mid;
            }
            else
            {
                //eg: 3,1,3,3,3
                high--;
            }
        }
        
        return nums[high]; //nums[low]
    }
}
