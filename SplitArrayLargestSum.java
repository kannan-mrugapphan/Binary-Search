// 410.
// time - O(n * log[sum of all elemnts - max element]) with constant space
class Solution {
    public int splitArray(int[] nums, int m) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return 0;
        }
        
        //observation
        //largest answer = sum of all elements (m = 1)
        //smallest answer = max of all elements (m = size of nums)
        int low = Integer.MIN_VALUE;
        int high = 0;
        for(int num : nums)
        {
            low = Math.max(low, num);
            high += num;
        }
        int result = 0; //return value
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //for this mid find number of partitions needed
            int partitions = findNumberOfPartitions(nums, mid);
            if(partitions > m)
            {
                //increase target sum to reduce parttions
                low = mid + 1;
            }
            // else if(partitions < m)
            // {
            //     //reduce target sum to increase number of partitions
            //     high = mid - 1;
            // }
            else
            {
                //parttions = m, update result and reduce target sum to find a better answer
                result = mid;
                high = mid - 1;
            }
        }
        
        return result;
    }
    
    //util method to find the number of partitions needed such that sum of all elements in any sub array is <= targetSum
    //time - O(n)
    private int findNumberOfPartitions(int[] nums, int targetSum) {
        int partitions = 1;
        int currentSum = 0;
        for(int num : nums)
        {
            if(num + currentSum > targetSum)
            {
                //current element can't go in current sub array as sum exceeds target
                partitions++;
                currentSum = num;
            }
            else
            {
                //add current element to current sub array
                currentSum += num;
            }
        }
        return partitions;
    }
}
