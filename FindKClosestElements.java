// 658.
// time - O(log (n-k)) binary serach on possible windows
// space - costant
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //eg: [1,2,3,4,5] with k = 2, the possible windows are [1,2] [2,3] [3,4] [4,5]
        //window with lowest start is at index 0
        //window with highest start is at index n - k
        //consider window [3,4] -> if element just outside the window (5) is more closer to target, then all windows with starting point to left of current start (including the current window) are discarded 
        //if the element just outside (5) is farther to target than current start, then all windows with start point towards right can be discarded (current window is also stored in result as it could be the potential answer and then the windows in the left are searched for)

        int n = arr.length;
        int low = 0; //window with lowest start point
        int high = n - k - 1; //window with highest start point

        //since at every point, the element just outside the window is compared with element at window start
        //this outside element can be out of bounds for window starting at n-k
        //so this window is set to result and this window is not considered in search space
        int result = n - k; //let the window at the rightmost start be the result initially

        //as long as there are more windows
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            int elementOutsideWindow = arr[mid + k];

            //current window start element is more farther than element just outside
            if(Math.abs(elementOutsideWindow - x) < Math.abs(arr[mid] - x))
            {
                //all windows with starts as [low, mid] are more farther as they don't include outside element
                low = mid + 1; 
            }
            else if(Math.abs(elementOutsideWindow - x) > Math.abs(arr[mid] - x))
            {
                //outside element is farther than window start
                //all windows with start from [mid + 1, end] won't include nums[mid] so are more farther
                result = mid;
                high = mid - 1;
            }
            else
            {
                //element just outside the window is at same distance as the window start
                if(arr[mid] == elementOutsideWindow)
                {
                    //start element same as outside element
                    //eg: [2,2,2] with outside element as 2
                    //in this case current window is same as next window as that will include outside element but distance is same
                    //eindows starting at left will either be at same or farther distance
                    low = mid + 1;
                }
                else
                {
                    //in othe cases when start element < outside elmenent (start element is more closer)
                    result = mid;
                    high = mid - 1;
                }
            }
        }

        //result is start of correct window
        List<Integer> answer = new ArrayList<>();
        for(int i = result; i < result + k; i++)
        {
            answer.add(arr[i]);
        }

        return answer;
    }
}
