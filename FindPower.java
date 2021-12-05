// 50.
// time - O(2^logn)
// space - O(log n) -> call stack size
class Solution {
    public double myPow(double x, int n) {
        //edge
        if(x == 0)
        {
            return 0.0; //0^n = 0
        }
        if(n == 0)
        {
            return 1.0; //x^0 = 1
        }
        if(n < 0)
        {
            double result = power(x, -n);
            return 1 / result;
        }
        return power(x, n);
    }
    
    private double power(double x, int n) {
        //base
        if(n == 0)
        {
            return 1.0; //x^0 = 1
        }
        //logic
        //2^10 = 2*2*2*2*2*2*2*2*2*2 -> 2^5 * 2^5 -> (2^2 * 2^2 * 2) -> (2^1 * 2^1)
        double result = power(x, n/2);
        if(n % 2 == 0)
        {
            //1st recurse call in 2^10 example
            return result * result;
        }
        //n is odd -> 2^5 recursive call example
        return result * result * x;
    }
}
