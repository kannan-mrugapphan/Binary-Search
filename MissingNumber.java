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

// 136.
// without sorting, find xor of all elements in nums[], duplicates will cancel out each other and single element will be the result - time - O(n) with constant space
// time - O(log n) with constant space
class Solution {
    public int singleNumber(int[] nums) {
       Arrays.sort(nums);

       //to account for array index out of bounds when checking if mid is the single element, initialize the search space with [1, nums.length - 2]
       //check if array has just one element
       if(nums.length == 1)
       {
           return nums[0];
       }
       //check if 1st element and last element are single numbers seperately
       if(nums[0] != nums[1])
       {
           return nums[0];
       }
       if(nums[nums.length - 1] != nums[nums.length - 2])
       {
           return nums[nums.length - 1];
       }
       int low = 1;
       int high = nums.length - 2;

       while(low <= high)
       {
           int mid = low + (high - low) / 2;
           //check if mid is the single element
           if(nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1])
           {
               return nums[mid];
           }
           
           //mid is at en even index
           if(mid % 2 == 0)
           {
               //check if left half is valid with all double elements
               //if all elements are present twice, each pair with start at an odd index and end at an even index
               //eg: [1,1,2,2,3]
               if(nums[mid] == nums[mid + 1])
               {
                   low = mid + 1;
               }
               //eg: [1,2,2,3,3]
               else
               {
                   high = mid - 1;
               }
           }
           //mid is at an odd index
           else
           {
               //eg: [1,1,2]
               if(nums[mid] == nums[mid - 1])
               {
                   low = mid + 1;
               }
               //eg: [1,2,2]
               else
               {
                   high = mid - 1;
               }
           }
       } 

       return -1; //code never reaches here
    }
}
