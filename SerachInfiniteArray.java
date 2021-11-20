/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

class Solution {
    public int search(ArrayReader reader, int target) {
        //initial window size = 1
        int low = 0;
        int high = 0;
        
        //as long as largest element in current window (element at high) is lower than target, expand the window to twixe the original length
        while(reader.get(high) < target)
        {
            int newHigh = high + ((high - low + 1) * 2);
            int newLow = high + 1;
            low = newLow;
            high = newHigh;
        }
        //target is within low and high as loop broke
        return find(reader, target, low, high);
    }
    
  //normal O(log n) binary search to find target
    private int find(ArrayReader nums, int target, int low, int high) {
        int result = -1;
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(nums.get(mid) == target)
            {
                result = mid;
                return result;
            }
            else if(nums.get(mid) < target)
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }
        return result;
    }
}
