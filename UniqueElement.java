// 540.
// time - O(log n)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return -1;
        }
        
        //brute force - xor of all elements is the unique element
        //observation - if all elements are present twice, then first occurence of each unique element is at even index
        //base
        if(nums.length == 1)
        {
            return nums[0];
        }
        if(nums[0] != nums[1])
        {
            return nums[0]; //1st element is unique
        }
        if(nums[nums.length - 1] != nums[nums.length - 2])
        {
            return nums[nums.length - 1]; //last element is unique
        }
        
        int low = 1;
        int high = nums.length - 2;
        int result = -1;
        
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1])
            {
                return nums[mid]; //mid is the unique element
            }
            //check if left half is fixed (left half doesn't have the unique element)
            //if mid is odd, then the pair starts at mid - 1 if left is fixed
            //if mid is even, then pair starts at mid if left is fixed
            else if((mid % 2 == 0 && nums[mid] == nums[mid + 1]) || mid % 2 != 0 && nums[mid] == nums[mid - 1])
            {
                low = mid + 1;
            }
            else
            {
                //right is fixed and unique is in left half
                high = mid - 1;
            }
        }
        
        return -1; //no unqiue
    }
}
