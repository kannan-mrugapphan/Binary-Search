// 4.
//brute force - merge 2 sorted arrays into single sorted array, median is computed with the center elements
// time - O(log(min(n1, n2))) with constant space
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        //perform binary search on shorter array to have smaller [low, high] range
        if(n1 > n2)
        {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        //a = [1,3,5,7,9] and b = [0,2,4,6,8]
        //merged = [0,1,2,3,4 | 5,6,7,8,9] since merged has even elements, median is the avg of elements around the center => (4+5)/2 = 4.5
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
        //once the split is found, median is (max(l1, l2) + min (r1, r2)) / 2 -> 1st half is left of center and 2nd half is right of center, median is avg if combined array has even elements
        //if combined array has odd elements, median is max(l1, l2) as it is the at the left of center

        int low = 0; //at min 0 elements can be picked from 1st array
        //all elements in left can be from 1st array ((n1+n2+1)/2) 
        //if number of elements in 1st array is smaller than this then pick all
        int high = Math.min(n1, (n1 + n2 + 1) / 2); 

        //as long as search space has valid elements
        while(low <= high)
        {
            int array1Contribution = low + (high - low) / 2; //pick mid elements from array 1
            int array2Contribution = ((n1 + n2 + 1) / 2) - array1Contribution; //the remaining elements in left half are from 2nd array

            //compute the left and right boundary elements
            //if no elements are picked in the left half from array1(array 2) then l1(l2) is -infinity
            int l1 = (array1Contribution == 0) ? Integer.MIN_VALUE : nums1[array1Contribution - 1];
            int l2 = (array2Contribution == 0) ? Integer.MIN_VALUE : nums2[array2Contribution - 1];
            //if all elements are picked in the left half from array1(array 2) then r1(r2) is infinity
            int r1 = (array1Contribution == n1) ? Integer.MAX_VALUE : nums1[array1Contribution];
            int r2 = (array2Contribution == n2) ? Integer.MAX_VALUE : nums2[array2Contribution];

            //check if it is a valid split
            if(l1 <= r2 && l2 <= r1)
            {
                if((n1+n2) % 2 == 0)
                {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0; //combined array has even elements
                }
                else
                {
                    return Math.max(l1, l2); //combined array has odd elements
                }
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

        return 0.0; //code never reaches here
    }
}
