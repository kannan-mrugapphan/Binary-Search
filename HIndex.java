// 274.
// time - O(nlogn) with constant space
// 275.
// time - O(log n) with constant space
class Solution {
    public int hIndex(int[] citations) {
        //hIndex is dependent on the number of papers written by a person
        //so min hIndex = 0 (when no paper written by this person has any citation) or can be n (when all the papers written by the person has atleast n citations)
        int low = 0;
        int high = citations.length; 

        int result = low; //result must be max in the range
        while(low <= high)
        {
            int mid = low + (high - low) / 2; //check if mid can be the hIndex
            //get the count of papers that have atleast mid citations
            int count = countPapersOnSortedArray(citations, mid);

            //if mid is valid hIndex then count >= mid
            if(count >= mid)
            {
                result = mid;
                low = mid + 1; //search in right half for even larger hIndex
            }
            else
            {
                //number of paper with citations as atleast mid is smaller than mid, so mid can't be the hIndex
                high = mid - 1; //search in left half for lower values
            }
        }

        return result;
    }

    //returns the count of papers that have atleast given citations
    //time - O(n) with constant space
    private int countPapers(int[] papers, int limit)
    {
        int count = 0;
        for(int paper : papers)
        {
            if(paper >= limit)
            {
                count++; //current paper has more citations than limit
            }
        }

        return count;
    }

    //the given citations array is sorted
    //ceil of limit will be the count of papers with citations more than limit
    // time - O(log n) with constant space
    private int countPapersOnSortedArray(int[] papers, int limit)
    {
        int low = 0;
        int high = papers.length - 1;

        //find the ceil index
        int result = papers.length; //to account for case when ceil is not there, eg: [1,2,3,4,5] with limit = 6
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(papers[mid] == limit)
            {
                result = mid; //target found
                high = mid - 1; //to handle duplicates eg: [7,7,7,7,7,7] this should find the 1st 7
            }

            else if(papers[mid] < limit)
            {
                low = mid + 1; //all papers in left half have citations lower than limit, search in right half
            }

            else if(papers[mid] > limit)
            {
                result = mid; //mid can be the ceil
                high = mid - 1; //continue seraching in left half for lower ceil
            }
        }

        int count = papers.length - result; //all papers after result (including result) will have higher citations
        return count;
    }
}
