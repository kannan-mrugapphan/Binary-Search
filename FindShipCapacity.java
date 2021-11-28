// 1011.
// time - O(n * log[sum of all elements - max element]) with constant space
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        //edge
        if(weights == null || weights.length == 0)
        {
            return 0;
        }
        
        //observation - min weight of the ship = max element in the array
        //max weight of ship = sum of all elements in array - ships in 1 day taking all elements at once
        //brute force - go from low to high sequentially, check if the days is needed is less than target
        int low = Integer.MIN_VALUE;
        int high = 0;
        for(int num : weights)
        {
            low = Math.max(low, num);
            high += num;
        }
        int result = 0;
        
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            int daysNeeded = findDaysGivenCapacity(weights, mid); //days needed if min is the capacity
            if(daysNeeded <= days)
            {
                //if shipment can be done within given days, update result and try reducing capacity by moving to left
                result = mid;
                high = mid - 1;
            }
            else
            {
                //shipment takes more days
                //try increasing capacity by moving to right
                low = mid + 1;
            }
        }
        
        return result;
    }
    
    //util method to find days needed to ship all packages given capacity
    //time - O(n)
    private int findDaysGivenCapacity(int[] weights, int capacity)
    {
        int currentWeight = 0;
        int days = 1;
        for(int num : weights)
        {
            if(num + currentWeight > capacity)
            {
                days++; //increase days needed by 1
                currentWeight = num; //the next day current weight becomes weight of current item
            }
            else
            {
                currentWeight += num; //including current item in the same day
            }
        }
        return days;
    }
}
