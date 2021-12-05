// 231.
// time - O(log n)
//space - o(log n) -> call stack size
class Solution {
    public boolean isPowerOfTwo(int n) {
        //edge
        if(n <= 0) //negative numbers
        {
            return false;
        }
        //16 = 2^4 -> 16/2 = 8 -> 8/2 = 4 -> 4/2 = 2 -> 2/2 = 1 -> true
        //12 -> 12/2 = 6 -> 6/2 = 3 -> 3%2 != 0 -> false
        while(n % 2 == 0)
        {
            n = n / 2;
        }
        if(n == 1)
        {
            return true;
        }
        return false;
    }
}
