// 74.
// time - O(max(m, n))
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //edge
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return false;
        }
        
        //start from top right cell
        int row = 0;
        int col = matrix[0].length - 1;
        //as long as there are more rows and cols
        while(row < matrix.length && col >= 0)
        {
            if(matrix[row][col] == target)
            {
                return true; //found
            }
            else if(matrix[row][col] < target) //all elements in current row are smaller than target
            {
                row++;
            }
            else if(matrix[row][col] > target) //all elements in current col are greater than target
            {
                col--;
            }
        }
        return false; //not found
    }
}
