class Solution {
    public long kthElement( int arr1[], int arr2[], int n, int m, int k) {
        //perform binary search on shorter array to have smaller [low, high] range
        if(n > m)
        {
            return kthElement(arr2, arr1, m, n, k);
        }
        
        //a = [1,3,5,7,9] and b = [0,2,4,6,8] and k = 5
        //merged = [0,1,2,3,4 | 5,6,7,8,9] make a partition such that first k smallest elements are in the left half
        //left half has 5 elements and the remaining elements are in the right half
        //left half can 0 or 1 or 2 or 3 or 4 or 5 elements from the 1st array and the remaining elements from b
        //1 from 1st array and 4 from 2nd array
        // { 1 }           |   { 3, 5, 7, 9 }
        // { 0, 2, 4, 6 }  |   { 8 }
        //for this partition, all elements in left must be smaller than or equal to all elements in right
        // 1 <= 8, so all elements in 1st array in left half is smaller than all elements in 2nd array in right
        // but because 6 > 3 implies that all elements in 2nd array in left is not smaller than all elements in 1st array in right
        // so 6 in 2nd array in left should be pushed right and 3 in right half of 1st array should be pushed left
        //3 from 1st array and 2 from 2nd array
        // { 1, 3, 5 } |  { 7, 9 }
        // { 0, 2 }    |  { 4, 6, 8 }
        // 2 <= 7 so all elements in 2nd array in left is smaller than all elements in 1st array in right
        // but 5 > 4, so all elements in 1st array in left is not smaller than all elements in 2nd array in right
        // so 5 in 1st array should be pushed right and 4 in 2nd array should be pushed  left
        //2 from 1st array and 3 from 2nd array 
        // { 1, 3 }      |   { 5, 7, 9 }
        // { 0, 2, 4 }   |   { 6, 8 }
        // this split is valid as 4 <= 5 and 3 <= 6
        //out of all possible splits, only one will be valid
        //once the split is found, kth smallest element will be the last element in left half which is max(l1, l2)
        
        //eg: n = 9, m = 5 and k = 7
        //left half must have 7 elements, even if we pick all elements from nums2, we still need 2 from nums1
        //so min that can be picked from nums1 is max(0, k - m)
        int low = Math.max(0, k - m); 
        //all elements in left can be from 1st array (k elements) 
        //if number of elements in 1st array is smaller than this then pick all from nums1
        int high = Math.min(n, k); 

        //as long as search space has valid elements
        while(low <= high)
        {
            int array1Contribution = low + (high - low) / 2; //pick mid elements from array 1
            int array2Contribution = k - array1Contribution; //the remaining elements in left half are from 2nd array

            //compute the left and right boundary elements
            //if no elements are picked in the left half from array1(array 2) then l1(l2) is -infinity
            int l1 = (array1Contribution == 0) ? Integer.MIN_VALUE : arr1[array1Contribution - 1];
            int l2 = (array2Contribution == 0) ? Integer.MIN_VALUE : arr2[array2Contribution - 1];
            //if all elements are picked in the left half from array1(array 2) then r1(r2) is infinity
            int r1 = (array1Contribution == n) ? Integer.MAX_VALUE : arr1[array1Contribution];
            int r2 = (array2Contribution == m) ? Integer.MAX_VALUE : arr2[array2Contribution];

            //check if it is a valid split
            if(l1 <= r2 && l2 <= r1)
            {
                return Math.max(l1, l2); 
            }

            else if(l1 > r2)
            {
                //elements picked from nums1 should be pushed to right to make it fall under case1 in next iteration
                high = array1Contribution - 1;
            }

            else if(l2 > r1)
            {
                //elements picked from nums2 should be pushed to right, so pick more elements from nums1
                low = array1Contribution + 1;
            }
        }

        return -1; //code never reaches here
        
    }
}
