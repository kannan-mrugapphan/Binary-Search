//69.
// time - O(log n)
class Solution {
    public int mySqrt(int x) {
        //edge
        if(x <= 0)
        {
            return 0; //root of 0 is 0 and root of negative numbers are complex
        }
        //1 <= sqrt(x) <= x
        int low = 1; //binary search for sqrt(x) between 1 and x
        int high = x;
        int result = 1;
        
        while(low <= high)
        {
            long mid = low + (high - low) / 2;
            if(mid * mid == x) //root found
            {
                return (int)mid;
            }
            else if(mid * mid > x) //root can't be mid or any number in right half i.e. [mid, high]
            {
                high = (int)mid - 1;
            }
            else if(mid * mid < x) //tracking current mid in result to keep track of numbers that aren't perfect squares
            {
                result = (int)mid;
                low = (int)mid + 1;
            }
        }
        
        return result;
    }
}
