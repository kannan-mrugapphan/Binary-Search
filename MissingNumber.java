// 268.

class Solution {
    public int missingNumber(int[] nums) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return 0;
        }
        
        return arithmeticSum(nums);
    }
    
    //time - O(n)
    //space - O(1)
    private int arithmeticSum(int[] nums) {
        int sum = 0; //stores sum of all numbers in nums[]
        for(int num : nums)
        {
            sum += num;
        }
        
        //[0, 1, 3] is  [0, 1, 2, 3] (including the missing number)
        //so the actual nums[] has numners from [1 to input-nums.length] plus 0
        //so sum of that is n(n + 1) / 2
        //difference gives the missing number
        int expected = (nums.length * (nums.length + 1)) / 2;
        
        return expected - sum;
    }
    
    //binary serach approach - works when array is sorted
    //time - O(log n)
    //space - O(1)
    private int search(int[] nums) {
        Arrays.sort(nums); //sort nums[]
        
        int low = 0;
        int high = nums.length - 1;
        
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(mid == nums[mid]) //missing number is in the right half
            {
                low = mid + 1;
            }
            else //missing number is in the left half
            {
                high = mid - 1;
            }
        }
        
        return low;
    }
}
