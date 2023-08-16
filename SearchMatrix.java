// 74.
// brute force - scan each cell to check if target is present - O(nm) time
// brute force 2 - for each row in the matrix, check if target is within the row (0th element in row <= target <= last element in row), if true, search for the target in that row through binary serach - O(n) + O(logm) - n time for scanning each row and log m for binary search on selected row

// time - O(log mn) with constant space
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        //initially search space is the whole matrix
        int low = 0; //lowest cell number is 0
        int high = (m*n) - 1; //last cell number 

        while(low <= high)
        {
            //as long as search space is valid
            int mid = low + (high - low) / 2;
            //get the row, col for mid
            int row = mid / m;
            int col = mid % m;
            //check if mid cell is target
            if(matrix[row][col] == target)
            {
                return true;
            }

            else if(matrix[row][col] < target)
            {
                //all cells to left of mid (including mid) is small
                low = mid + 1; //search in right half
            }

            else if(matrix[row][col] > target)
            {
                //all cells to right of mid (including mid) is large
                high = mid - 1; //serach in left half
            }
        }

        return false;
    }
}

// 240.
// time - O(m + n) -> each iteration, either 1 row or 1 col is eliminated
// space - constant
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //start from top right
        int row = 0;
        int col = matrix[0].length - 1;

        while(row < matrix.length && col >= 0)
        {
            if(matrix[row][col] == target)
            {
                return true;
            }

            else if(matrix[row][col] < target)
            {
                //all elements in current row will be lower 
                row++;
            }

            else if(matrix[row][col] > target)
            {
                //all elements in current col will be larger than target
                col--;
            }
        }

        return false;
    }
}
