// 875.
// time - O(log(max element in piles[]) * length of piles[])
// space - O(1)
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        //edge
        if(h <= 0)
        {
            //if threshold time is 0 or less, piles can't be finished irrespecitve of speed
            return Integer.MAX_VALUE; 
        }
        if(piles == null || piles.length == 0)
        {
            return 0; //no piles are given
        }

        //koko can't stay idle, so minimum speed should atleast be 1, time needed in this scenario is sum of all elements in piles[]
        //koko can't eat 2 piles simultaneously, so min time koko can finish eating 1 pile is 1hr, and can finish all piles in length of piles[] hours, speed in this case should be atleast size of largest pile
        int minSpeed = 1;
        int maxSpeed = Integer.MIN_VALUE; //should be set to max in piles[]
        for(int pile : piles)
        {
            maxSpeed = Math.max(maxSpeed, pile);
        }

        //optimal speed is in [min, max] range
        int result = maxSpeed; //result should be minimized in range, so initialized to max in range
        while(minSpeed <= maxSpeed)
        {
            int mid = minSpeed + (maxSpeed - minSpeed) / 2;
            //compute time needed if speed is mid
            long timeNeeded = findTime(piles, mid);

            //if time needed with mid is lesser than threshold
            if(timeNeeded <= h)
            {
                result = mid; //update result with current speed (mid) and serach in left half for lesser speed
                maxSpeed = mid - 1;   
            }
            else
            {
                minSpeed = mid + 1; //time needed with mid is larger than threshold, search in right half for higher speeds to reduce time needed 
            }
        }

        return result;
    }

    //computes time needed in hours to finish all piles given speed
    // time - O(n) with constant space
    public long findTime(int[] piles, int speed)
    {
        long time = 0;
        for(int pile : piles)
        {
            //if pile has 3 bananas and speed is 2, time taken for current pile = 2 
            long timeNeededForPile = (long) Math.ceil((pile/ (double) speed)); 
            time += timeNeededForPile;
        }

        return time;
    }
}
