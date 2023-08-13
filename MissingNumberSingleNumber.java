// 268.
// time - O(n logn) for sorting and O(log n) to find missing number
// brute force - missing number = (n * n + 1) / 2 - sum of all elements
class Solution {
    public int missingNumber(int[] nums) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return 0; //empty array, 1st missing number is 0
        }

        Arrays.sort(nums);

        //if array didn't have any missing number, the index will be same as number
        //eg: [0,1,2,3,4] -> length = 5, all numbers are at their correct index
        //eg: [0,1,3,4,5] -> first 2 numbers are at the correct index, remaining numbers are at wrong index
        //1st number at which index mismatch happens is 3 (index 2) so missing number is 2 (its index)
        int low = 0; //initially search space is whole array
        int high = nums.length - 1;

        int result = nums.length; //if all numbers are at correct position, the 1st missing number is n

        //as long as search space has atleast 1 element
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(nums[mid] == mid)
            {
                //[low, mid] is at correct position
                //missing number is in right half
                low = mid + 1;
            }
            else
            {
                //all numbers in [mid, high] are at wrong index
                //missing number is in range [low, mid] -> 1st position where index mismatch happens in this range
                //track mid in result and go to left half
                result = mid;
                high = mid - 1;
            }
        }

        return result;
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
