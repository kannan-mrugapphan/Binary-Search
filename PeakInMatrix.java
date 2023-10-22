// 1901.
// time - O(n logm) with constant space
class Solution {
    public int[] findPeakGrid(int[][] matrix) {
        //edge
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return new int[0]; 
        }

        //peak element can lie from 0th col to the last col
        int low = 0;
        int high = matrix[0].length - 1;

        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //max element in this mid col has highest probablity to be the peak
            int maxRow = findMax(matrix, mid);

            //this element will be largest than top and bottom (because it is largest in col)
            //it is peak if it is larger than left and right
            if((mid == 0 || matrix[maxRow][mid] > matrix[maxRow][mid - 1]) && (mid == matrix[0].length - 1 || matrix[maxRow][mid] > matrix[maxRow][mid + 1]))
            {
                return new int[]{maxRow, mid};
            }
            
            //greater than right but smaller than left, continue tracking this peak in left
            else if(mid == matrix[0].length || matrix[maxRow][mid] > matrix[maxRow][mid + 1])
            {
                high = mid - 1;
            }

            //greater than left but smaller than right, continue tracking this peak in right
            else if(mid == matrix[0].length || matrix[maxRow][mid] < matrix[maxRow][mid + 1])
            {
                low = mid + 1;
            }
        }

        return new int[]{-1, -1}; //code never reaches here
    }

    //returns row index of max element in given col
    //time - O(n) with constant space
    private int findMax(int[][] matrix, int col)
    {
        int maxRow = -1;
        int maxRowValue = Integer.MIN_VALUE;

        for(int row = 0; row < matrix.length; row++)
        {
            if(matrix[row][col] > maxRowValue)
            {
                maxRowValue = matrix[row][col];
                maxRow = row;
            }
        }

        return maxRow;
    }
}
