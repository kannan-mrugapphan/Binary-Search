// Aggressive Cows
// 1552.
// time - O(nlogn) for sorting + O(log(max element) * n) for search
// space - constant
class Solution {
    public int maxDistance(int[] position, int m) {
        //requirements
        //each basket can have only 1 ball
        //all balls should be placed in basket
        //edge
        if(m > position.length)
        {
            return Integer.MAX_VALUE; //number of balls is larger than number of baskets
        }

        //consider 4 baskets at [x1,x2,x3,x4] and 3 balls b1,b2,b3
        //let b1 be placed at x1 and b2 at x2
        //now the last ball b3 can be placed either at x3 or x4
        //to choose the optimal basket, distance between x1,x3 and x2,x3 is needed also distance between x1,x4 and x2,x4 are also needed (the one with min distance is selected)
        //if x1,x2,x3,x4 are sorted in incresing order
        //to place b3 at x3, distance between x2 and x3 is needed and to place at x4, distance between x2, x4 is needed, the min is selected
        Arrays.sort(position);

        int low = 1; //min distance should atleast be 1 as each ball needs its own basket
        int high = position[position.length - 1] - position[0]; //max distance is possible if count of balls is 2 and the 1st ball is at 0th basket and 2nd ball is at last basket

        //optimal distance is between [low, high]
        int result = low; //maximize result, so default to min in range
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //compute the number of balls that can be placed if distance between 2 balls is atleast mid
            int balls = countBalls(position, mid);

            //if number of balls is m
            if(balls >= m)
            {
                result = mid; //update result and try increasing distance to place all m balls, search in right half
                low = mid + 1;
            }
            else
            {
                //balls count is less than m, try reducing distance to place more balls, search in left half
                high = mid - 1;
            }
        }

        return result;
    }

    //computes number of balls that can be placed if the distance between 2 balls is atleast d
    // time - O(n) with constant space
    private int countBalls(int[] position, int distance)
    {
        //target is place to as many baskets as possible such that distance between 2 baskets is atleast d
        //so greedily place 1st basket in 0th position
        int balls = 1; 
        int lastBallPosition = position[0]; 

        for(int i = 1; i < position.length; i++)
        {
            if(position[i] - lastBallPosition >= distance)
            {
                balls++; //distance between current basket and prev ball is larger than d, place ball in current
                lastBallPosition = position[i]; 
            }
        }

        return balls;
    }
}
