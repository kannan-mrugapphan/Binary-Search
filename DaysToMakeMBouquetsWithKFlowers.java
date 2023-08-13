// 1482.
// time - O(log(max - min) * length of i/p[]) with constant space
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        //edge
        if(bloomDay.length < (long) m * k)
        {
            return -1; //insufficient flowers to make m bouquets with k flowers each
        }

        //if we wait till max(bloomDay), all flowers will be bloomed, and can be used to make bouquets
        //to use atleast 1 flower in a bouquet, atleast 1 flower has to be bloomed, min(bloomDay) will be the flower with earliest bloom
        int minDay = Integer.MAX_VALUE; //min(flowers) 
        int maxDay = Integer.MIN_VALUE; //max(flowers)
        for(int day : bloomDay)
        {
            minDay = Math.min(minDay, day);
            maxDay = Math.max(maxDay, day);
        }

        //optimal day is in [min, max] range
        int result = maxDay; //should be minimized in range, so initialized to max in range
        while(minDay <= maxDay)
        {
            int mid = minDay + (maxDay - minDay) / 2;
            //compute bouquets possible if we wait till mid
            int bouquets = findBouquets(bloomDay, k, mid);

            //if number of bouquets is more than threshold
            if(bouquets >= m)
            {
                result = mid; //update result and search for lower wait days in left half
                maxDay = mid - 1;
            }
            else
            {
                minDay = mid + 1; //bouquets possible is lower than threshold, increase wait day by searching in right
            }
        }

        return result;
    }

    //computes bouquets that can be make with k adjacent flowers after wait time of d days
    // time - O(n) with constant space
    private int findBouquets(int[] flowers, int k, int day)
    {
        int adjacentFlowers = 0; //initially count of adjacent flowers is 0
        int bouquets = 0;

        for(int flower : flowers)
        {
            //check if current flower is bloomed 
            if(flower <= day)
            {
                adjacentFlowers++; //increase the count of adjacent bloomed flowers by 1
            }
            else
            {
                //make bouquets with current adjacent bloomed flowers and reset its count to 0
                bouquets += (adjacentFlowers / k); 
                adjacentFlowers = 0;
            }
        }

        bouquets += (adjacentFlowers / k); //compute bouquets possible after all flowers are processed
        return bouquets;
    }
}
