// 34.
//time - O(log n)
//space - O(1)

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        
        //edge
        if(nums == null || nums.length == 0)
        {
            return result;
        }
        
        result[0] = search(nums, target, true); //find 1st occurence
        result[1] = search(nums, target, false); //find last occurence
        
        return result;
    }
    
    private int search(int[] nums, int target, boolean first) {
        int low = 0;
        int high = nums.length - 1;
        int result = -1;
        
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target)
            {
                result = mid;
                if(first) //find first occurence -> continue searching in the left
                {
                    high = mid - 1;
                }
                else //find last occurence - > continue seraching in the right
                {
                    low = mid + 1;
                }
            }
            else if(target < nums[mid]) //target wont be present in right half
            {
                high = mid - 1;
            }
            else //target wont be present in left half
            {
                low = mid + 1;
            }
        }
        
        return result;
    }
}
