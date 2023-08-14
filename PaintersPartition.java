// time - O(log(sum(arr) - max(arr)) * length of arr) with constant space
 class Solution{
    static long minTime(int[] arr,int n,int k){
        //painters will paint contiguous walls
        //eg: [1,3] wall to painter1 and [2,4] wall to painter2 is not possible
        
        
        //min time needed is when each painter pick 1 wall, time to finish painting all walls = max(arr)
        //max time needed is when all walls are painted by 1 painter, time to finish painting = sum(arr)
        long low = arr[0]; //default to max(arr)
        long high = 0; //default to sum(arr)
        for(int wall : arr)
        {
            low = Math.max(low, wall);
            high += wall;
        }
        
        //optimal time is between [low, high]
        long result = high; //minimze result in range, so default to high in range
        while(low <= high)
        {
            long mid = low + (high - low) / 2;
            long painters = findPainters(arr, mid); //compute painters needed if max time for each painter is mid
            
            //if painters needed is less than or same as k
            if(painters <= k)
            {
                result = mid; //update result
                high = mid - 1; //search in left half for even lower time for each painter
            }
            else
            {
                //search in right half for higher time for each painter to increase painters to more than k
                low = mid + 1;
            }
        }
        
        return (int) result;
    }
    
    //returns painters needed to paint all walls if max time a painter can work is given
    // time - O(n) with constant space
    private static long findPainters(int[] walls, long time)
    {
        long painters = 1;
        int currentTime = 0; //current painter hasn't started working yet
        for(int wall : walls)
        {
            if(currentTime + wall <= time)
            {
                //current painter can paint current wall
                currentTime += wall;
            }
            else
            {
                //new painter picks current wall
                painters++;
                currentTime = wall;
            }
        }
        
        return painters;
    }
}
