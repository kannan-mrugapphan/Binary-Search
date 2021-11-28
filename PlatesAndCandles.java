//2055.
// time - O(n + qlogn)
//space - O(n) list size to store candle indices
class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        //edge
        if(s == null || s.length() == 0)
        {
            return new int[0];
        }
        
        List<Integer> candleIndices = new ArrayList<>();
        for(int i = 0; i < s.length(); i++)
        {
            //if char at i is a candle add to list
            if(s.charAt(i) == '|')
            {
                candleIndices.add(i);
            }
        }
        
        int[] result = new int[queries.length]; //return array
        int index = 0;
        for(int[] query : queries)
        {
            //find leftmost candle in the query substring -> ceil of query[0]
            int[] leftMostCandle = findFloorAndCeil(candleIndices, query[0], false);
            //no candle in the subarray -> so plates inbetween candles is 0
            if(leftMostCandle[0] > query[1])
            {
                result[index++] = 0;
                continue;
            }
            //find the rightmost candle in the query substring -> floor of query[1]
            int[] rightMostCandle = findFloorAndCeil(candleIndices, query[1], true);
            //no candle in the subarray -> so plates inbetween candles is 0
            if(rightMostCandle[0] < query[0])
            {
                result[index++] = 0;
                continue;
            }
            int targetSubArraySize = rightMostCandle[0] - leftMostCandle[0] + 1;
            int numberOfCandlesInTargetSubArray = rightMostCandle[1] - leftMostCandle[1] + 1;
            result[index++] = targetSubArraySize - numberOfCandlesInTargetSubArray;
        }
        
        return result;
    }
    
    //util function to find the floor (or ceil) of the target in nums list
    //time - O(log n)
    private int[] findFloorAndCeil(List<Integer> nums, int target, boolean flag)
    {
        int low = 0;
        int high = nums.size() - 1;
        int[] result = new int[]{-1, -1};//return list with floor (or ceil) at the 0th index and its index at the 1st index
        
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(nums.get(mid) == target)
            {
                result[0] = nums.get(mid);
                result[1] = mid;
                return result;
            }
            else if(nums.get(mid) < target)
            {
                if(flag)
                {
                    //find the floor - mid can be a potential floor
                    result[0] = nums.get(mid);
                    result[1] = mid;   
                }
                //target is in right half
                low = mid + 1;
            }
            else
            {
                if(!flag)
                {
                    //find the ceil - mid can be a potential ceil
                    result[0] = nums.get(mid);
                    result[1] = mid;   
                }
                //target is in left half
                high = mid - 1;
            }
        }
        
        return result;
    }
}
