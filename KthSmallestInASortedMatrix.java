// 378.
//brute force - store the matrix into 1d array - sort it - find kth smallest - O(mnlog(mn))
//time - O(nlog(largest value in matrix))
//space - constant
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int smallest = matrix[0][0];
        int largest = matrix[matrix.length - 1][matrix[0].length - 1];
        int result = Integer.MAX_VALUE;
        //runs for log(largest value in matrix) time
        while(smallest <= largest)
        {
            int mid = smallest + (largest - smallest) / 2;
            int noOfElements = count(matrix, mid); // number of elements smaller than or equals mid
            if(noOfElements < k) 
            {
                //since noOfElements is smaller than k, say noOfElements = x(<k)
                //we can find only the xth smallest elemment in left half - so move to right half
                smallest = mid + 1;
            }
            else
            {
                //here noOfElements >= k..this mid is a potential answer
                //so store it and continue searching in left half
                result = mid;
                largest = mid - 1;
            }
        }
        return result;
    }
    
    //time - count() runs for 2n iterations at max, so O(n) with constant space
    //approach similar to search in a sorted matrix
    private int count(int[][] matrix, int mid) {
        //start from the top right corner
        int i = 0;
        int j = matrix[0].length - 1;
        int number = 0;
        //while index within bounds
        while(i < matrix.length && j >= 0)
        {
            //check if the current is smaller than or equals mid
            if(matrix[i][j] <= mid)
            {
                //all the elements in this row is smaller than or equals mid due to sorted nature
                number += j + 1; //add the count to answer
                i++; //go to next row
            }
            else
            {
                //element > mid
                //all elements in this column are larger than mid
                j--; //so decrement column
            }
        }
        return number;
    }
}
