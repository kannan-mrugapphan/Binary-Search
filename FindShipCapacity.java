// 1011.
// time - O(log(sum of weights of all packages - weight of largest package) * number of packages)
// space - constant
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        //edge
        if(days <= 0)
        {
            return Integer.MAX_VALUE; //if threshold days is 0 or less, no package can be shipped with any capacity
        }
        if(weights == null || weights.length == 0)
        {
            return 0; //no packages, so ship is not needed
        }

        //every package must be shipped, so capacity of ship should atleast be weight of heaviest pacakage
        //with this capacity, each package will be shipped on its own day, total days needed = number of packages
        //if all packages have to be shipped on same day, the ship's capacity should be atleast sum of weights of all packages
        int minCapacity = Integer.MIN_VALUE; //weight of largest package should be set
        int maxCapacity = 0; //sum of weights of all packages should be set
        for(int weight : weights)
        {
            minCapacity = Math.max(minCapacity, weight);
            maxCapacity += weight;
        }

        //optimal capacity should be in [minCapacity, maxCapacity]
        int result = maxCapacity; //minize result within [min, max] so intialize result to max
        while(minCapacity <= maxCapacity)
        {
            int mid = minCapacity + (maxCapacity - minCapacity) / 2;
            //compute days needed if mid is ship's capacity
            int daysNeeded = findDays(weights, mid);

            //if days needed with mid is lesser than d
            if(daysNeeded <= days)
            {
                result = mid; //update result and try seraching with even lower capacities in left
                maxCapacity = mid - 1;
            }
            else
            {
                minCapacity = mid + 1; //days needed exceeds threshold, increase capacity by searching in right to reduce days
            }
        }

        return result;
    }

    //computes days needed to ship all packages with given ship's capacity
    //time - O(n) with constant space
    private int findDays(int[] packages, int capacity)
    {
        int days = 1;
        int currentWeight = 0; //initially ship is empty
        for(int weight : packages)
        {
            if(currentWeight + weight <= capacity)//current package can be included in current day
            {
                currentWeight += weight;
            }
            else
            {
                currentWeight = weight;
                days++; //current package should be shipped on the next day
            }
        }

        return days;
    }
}
