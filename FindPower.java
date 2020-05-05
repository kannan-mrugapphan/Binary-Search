// 50.

class Solution {
    public double myPow(double x, int n) {
        if(n > 0)
        {
            //return findPowerRecursive(x, n);
            return findPowerIterative(x, n);
        }
        else if(n < 0)
        {
            //return 1/findPowerRecursive(x, -n); //x^-n = 1/(x^n)
            return 1/findPowerIterative(x, -n);
        }
        //n = 0; x^0 = 1
        return 1.0;
    }
    
    //time - O(log n)
    //space - O(log n) for the call stack
    private double findPowerRecursive(double x, int n) {
        //base
        if(n == 0)
        {
            return 1.0;
        }
        //logic
        //find x^(n/2)
        double temp = findPowerRecursive(x, n / 2);
        if(n % 2 == 0)
        {
            return temp * temp; //x^n = x^(n/2) * x^(n/2) if n is even
        }
        else
        {
            return x * temp * temp; //x^n = x^(n/2) * x^(n/2) * x if n is even
        }
    }
    
    //time - O(log n)
    //space - constant
    private double findPowerIterative(double x, int n) {
        double result = 1.0;
        while(n > 0)
        {
            if(n % 2 != 0) //when power hits odd multiply result by current x
            {
                result *= x; 
            }
            // 2^4 -> 4^2 -> 16^1 (at this point result = 16) -> 256^0(breaks out to return result)
            n = n / 2; 
            x = x * x;
        }
        return result;
    }
}
