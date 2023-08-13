// 153.
// time - O(log n) with constant space
class Solution {
    public int findMin(int[] nums) {
        int result = Integer.MAX_VALUE; //return value, initialized to infinity as it has to be minimized
        int low = 0; //initially search space is the whole array
        int high = nums.length - 1;

        //as long as search space has atleast 1 element
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //identify the sorted half
            if(nums[low] <= nums[mid]) // <= to account for cases when low and mid are at same index, eg: [1,2]
            {
                //left half is sorted, smallest element in left half is nums[low], compare with result
                result = Math.min(result, nums[low]);
                //ignore the whole left half as the min in left half is already accounted for and the remaining elements in left half can't be the minimum element due to its sorted nature, continue searching in the right half
                low = mid + 1;
            }
            else
            {
                //sorted right half, min element in right half is nums[mid], compare with result
                result = Math.min(result, nums[mid]);
                //ignore right half, as the min in right half is at mid and is already accounted for and the remaining elements can't  be the min, continue searching in left half
                high = mid - 1;
            }
        }

        return result;
    }
}

// 154.
// time - O(log n) on average case and O(n) in worst case for exaples like [3,3,3,3,3] with constant space
class Solution {
    public int findMin(int[] nums) {
        int result = Integer.MAX_VALUE;
        int low = 0;
        int high = nums.length - 1;

        while(low <= high)
        {
            int mid = low + (high - low) / 2;

            //if it is not possible to find the sorted half, eg: [3,1,3,3,3]
            if(nums[low] == nums[mid] && nums[mid] == nums[high])
            {
                //mid, low and high have same element, check if it can be minimum
                result = Math.min(nums[low], result);
                low++; //ignore just low and high and continue to next iteration
                high--;
                continue;
            }

            if(nums[low] <= nums[mid])
            {
                result = Math.min(nums[low], result);
                low = mid + 1;
            }
            else
            {
                result = Math.min(nums[mid], result);
                high = mid - 1;
            }
        }

        return result;
    }
}
