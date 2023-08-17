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

// 2643.
// time - O(nlogm) with constant space
class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int[] result = {0, 0}; //defaulted to case where the whole matrix has only 0s
        for(int row = 0; row < mat.length; row++)
        {
            //for unsorted matrix, brute force of finding ones in each row and updating result is best 
            //this sorting is done to just to do Binary search
            Arrays.sort(mat[row]); 
            int firstIndex = findFirstPosition(mat[row]); //first index of 1 in current row
            if(firstIndex == -1)
            {
                continue; //current array has no 1s
            }
            int onesCount = mat[row].length - firstIndex; //lastIndex - firstIndex + 1
            if(result[1] < onesCount)
            {
                result[0] = row;
                result[1] = onesCount; //update result;
            }
        }

        return result;
    }

    //time - O(log m) with constant space
    private int findFirstPosition(int[] nums)
    {
        //returns the index of the 1st occurence of 1 in the binary nums[]
        int low = 0;
        int high = nums.length - 1;
        int result = -1; //to account for nums[] not having 1

        //as long as search space has 1 element
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(nums[mid] == 1)
            {
                result = mid; //this could be the 1st 1, update result and continue searching for more 1s in left
                high = mid - 1;
            }
            else
            {
                //mid has 0, and 1 will be in right half
                low = mid + 1;
            }
        }

        return result;
    }
}
