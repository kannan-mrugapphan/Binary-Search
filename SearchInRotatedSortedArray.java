// 39.
// time - O(log n) with constant space
class Solution {
    public int search(int[] nums, int target) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return -1; //nums[] is invalid, target absent
        }    

        //initially search space is the whole array
        int low = 0;
        int high = nums.length - 1;

        //as long as search space has valid elements
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //check if mid index has the target
            if(nums[mid] == target)
            {
                return mid;
            }

            //identify the sorted half
            if(nums[low] <= nums[mid]) // <= equal condition even though the array has unique elements to account for scenario when both low and mid are at same index
            {
                //left half is sorted, check if target is within left sorted half
                if(nums[low] <= target && target < nums[mid])
                {
                    high = mid - 1; //search in left sorted half
                }
                else
                {
                    low = mid + 1; //search in right unsorted half
                }
            }
            else
            {
                //right half is sorted, check if target is within right sorted half
                if(nums[mid] < target && target <= nums[high])
                {
                    low = mid + 1; //search in right sorted half
                }
                else
                {
                    high = mid - 1; //search in left unsorted half
                }
            }
        }

        return -1; //target absent
    }
}

// 81.
// time - O(log n) in average case and O(n) in worst case for examples like [3,3,3,3,3] with target = 4
// space - constant
class Solution {
    public boolean search(int[] nums, int target) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return false; //nums[] is invalid, target absent
        }

        //initially search space is whole array
        int low = 0;
        int high = nums.length - 1;

        //as long as search space is valid
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //check if mid index has target
            if(nums[mid] == target)
            {
                return true;
            }
            
            //if it is not possible to identify the sorted half
            //eg: [3,1,3,3,3] 
            if(nums[low] == nums[mid] && nums[mid] == nums[high])
            {
                //just ignore low and high indices, and proceed to next iteration
                low++;
                high--;
                continue;
            }

            //identify sorted half
            if(nums[low] <= nums[mid])
            {
                //left half is sorted, check if target is within left half
                if(nums[low] <= target && target < nums[mid])
                {
                    high = mid - 1; 
                }
                else
                {
                    low = mid + 1;
                }
            }
            else
            {
                //right half is sorted, check if target is within right half
                if(nums[mid] < target && target <= nums[high])
                {
                    low = mid + 1;
                }
                else
                {
                    high = mid - 1;
                }
            }
        }

        return false; //target absent
    }
}
