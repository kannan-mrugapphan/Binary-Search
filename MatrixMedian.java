// 2387.
class Solution {
    // time - O(log(max - min) * O(n logm)) + O(m)
    //space - constant
    public int matrixMedian(int[][] matrix) {
        //brute force - flatten into 1d array and find center element
        //i/p grid has odd number of elements
        //median is the center element with x/2 elements in left and x/2 elements in right if total number of elements is x
        //so far an element y to be the matrix median, the total number of elements in grid <= y should atleast be x/2 + 1
        //if this count is less than x/2 + 1 then y can't be the center element with x/2 in left and x/2 in right

        //min in matrix <= median <= max in matrix
        //min is min among all elements in 0th col (because each row is sorted)
        //max is max among all elements in last col (because each row is sorted)
        int low = findMinOrMax(matrix, 0, true); //min
        int high = findMinOrMax(matrix, matrix[0].length - 1, false); //min
        int result = -1; //return value

        int numElements = matrix.length * matrix[0].length;

        while(low <= high)
        {
            int mid = low + (high - low) / 2; //check if mid is median
            int count = countNumbersLessThanOrEqualToLimit(matrix, mid);

            //if count <= numElements/2, then mid is not median and median is in right
            if(count <= numElements / 2)
            {
                low = mid + 1;
            }
            //this can be a potential median continue searching in left for left most occurence
            else
            {
                result = mid;
                high = mid - 1;
            }
        }

        return result;
    }

    //finds min(or max) in a given col in matrix
    //time - O(m) with constant space
    private int findMinOrMax(int[][] matrix, int col, boolean isMin)
    {
        int result = Integer.MAX_VALUE; //result has to be minimized if flag is set
        if(!isMin)
        {
            result = Integer.MIN_VALUE; //result has to be maximized if flag is not set
        }

        for(int row = 0; row < matrix.length; row++)
        {
            if(isMin)
            {
                result = Math.min(result, matrix[row][col]);
            }
            else
            {
                result = Math.max(result, matrix[row][col]);
            }
        }

        return result;
    }

    //count number of elements in matrix which is <= given limit
    //each row in i/p grid is sorted
    //find ceil of limit in each row, all elements to left of ceil will be less than or equal to limit in that row
    //time - O(nlogm) with constant space
    private int countNumbersLessThanOrEqualToLimit(int[][] matrix, int limit)
    {
        int count = 0;
        for(int row = 0; row < matrix.length; row++)
        {
            count += countInRow(matrix, row, limit);
        }
        return count;
    }

    //counts number of elements less than or equal to limit in given row
    //time - O(log m) with constant space
    private int countInRow(int[][] matrix, int row, int limit)
    {
        int ceilIndex = matrix[row].length; //to account for cases where ceil is not present
        int low = 0;
        int high = matrix[row].length - 1;

        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(matrix[row][mid] <= limit)
            {
                low = mid + 1;
            }
            else if(matrix[row][mid] > limit)
            {
                ceilIndex = mid; //potential ceil, continue searching in left for even closer values
                high = mid - 1;
            }
        }

        //all elements from ceil till end are larger
        return ceilIndex;
    }
}
