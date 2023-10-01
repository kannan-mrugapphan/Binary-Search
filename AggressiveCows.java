// 1552.
//Aggressive Cows
//n stalls where position of ith stall is in positions[i]
//m workers have to be placed into n stalls such that
    //each of the m works should be placed
    //2 workers can't be placed in the same stall
//after placing all m workers, the min distance between any 2 works among all m workers should be as large as possible

//time - O(n * log(largest element - smallest element in positions[]))
//space - constant
class Solution {
    public int maxDistance(int[] position, int m) {
        //edge
        if(m > position.length)
        {
            //number of workers is larger than available stalls
            return Integer.MIN_VALUE; //placement not possible
        }

        //consider an unsorted positions array and all m workers are placed
        //to find min distance between any 2 workers among all m workers, n^2 computations are needed
        //if the positions array is sorted, min distance between any 2 workers among all m workers can be computed in linear time
        Arrays.sort(position);

        int lowestDistance = 1; //lowest possible distance between 2 workers among all is 1 as 2 workers can't be in same stall
        int highestDistance = position[position.length - 1] - position[0]; //largest possible distance is possible when number of workers is 2 and place 1st worker in 0th stall and 2nd worker in last stall
        int result = 1; // lowestDistance <= result <= highestDistance, result has to be maximized, so initialize result to min in range

        while(lowestDistance <= highestDistance)
        {
            int middleDistance = lowestDistance + (highestDistance - lowestDistance) / 2;
            //count number of workers that can be placed if min distance between any 2 workers among all m workers is middle distance
            int workers = countWorkers(position, middleDistance);

            if(workers >= m)
            {
                //more than m workers can be placed if the distance between any 2 workers is atleast middle
                result = middleDistance; //update result
                lowestDistance = middleDistance + 1; //search in right for even larger distance
            }
            else
            {
                //m workers can't be placed if the distance between any 2 workers is atleast middle
                highestDistance = middleDistance - 1; //search in left for smaller distance
            }
        }

        return result;
    }

    //returns count of workers that can be placed if distance between any 2 workers is atleast the given int
    //time - O(n) with constant space
    private int countWorkers(int[] position, int distance)
    {
        int workers = 1; //greedily place the 1st worker in 0th stall as the target is to place more workers
        int lastWorkerPosition = position[0];

        for(int i = 1; i < position.length; i++)
        {
            //worker can be placed in current stall if distance between current stall and prev worker is larger than incoming distance
            if(position[i] - lastWorkerPosition >= distance)
            {
                workers++;
                lastWorkerPosition = position[i];
            }
        }

        return workers;
    }
}
