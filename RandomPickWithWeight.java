// 528.
class Solution {

    int[] runningSum;
    int[] data;

    //space - O(n) to store copy of i/p list and O(n) to store running sum
    //constructor takes O(n) time to populate running sum
    public Solution(int[] w) {
        //consider the i/p array as [1,2,3]
        //total sum is 6
        //proportion of 1 is 1/6, out of 6 times, 1 should get picked once
        //proportion of 2 is 2/6, out of 6 times, 2 should get picked twice
        //proportion of 3 is 3/6, out of 6 times, 3 should get picked thrice

        //runningSum array = [1,3,6]
        //approach - generate a random number between [1,6]
        //if the generated random number is x, find ceil index of x in runningSum array
        //return element at this ceil index in i/p array as result of random picker
        // random number = 1 => ceil index = 0, o/p = nums[0] = 1
        // random number = 2 or 3 => ceil index = 1, o/p = nums[1] = 2
        // random number = 4 or 5 or 6 => ceil index = 2, o/p = nums[2] = 3

        this.data = new int[w.length];
        //populate running sum
        this.runningSum = new int[w.length];
        for(int i = 0; i < w.length; i++)
        {
            if(i == 0)
            {
                runningSum[i] = w[i];
            }
            else
            {
                runningSum[i] = w[i] + runningSum[i - 1];
            }

            data[i] = w[i];
        }
    }
    
    //time - O(log n)
    //space - constant
    public int pickIndex() {
        //genarate random number between 1 and runningSum[runningSum.length - 1]
        int randomNumber = this.getRandom(1, runningSum[runningSum.length - 1] + 1);
        //System.out.println(randomNumber);

        //find ceil index of this number
        int ceilIndex = this.findCeilIndex(randomNumber);

        return ceilIndex; //this problem asks to return the index, so data[] is not needed, if the actual element is needed then return data[ceilIndex]
        //return data[ceilIndex];
    }


    //returns a random number in range [min, max)
    //time - constant
    private int getRandom(int minLimit, int maxLimit)
    {
        //Math.random() returns a flot random number between [0, 1]
        //shift it into range [min, max)
        //offset it by min
        return (int) (Math.random() * (maxLimit - minLimit)) + minLimit; 
    }

    //returns ceil index of target in runningSum array
    //time - O(log n) with constant space
    private int findCeilIndex(int target)
    {
        int low = 0;
        int high = this.runningSum.length - 1;

        int ceilIndex = this.runningSum.length; //to account for cases where ceil is not possible
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            if(this.runningSum[mid] == target)
            {
                return mid;
            }
            else if(this.runningSum[mid] < target)
            {
                low = mid + 1;
            }
            else if(this.runningSum[mid] > target)
            {
                ceilIndex = mid;
                high = mid - 1;
            }
        }

        return ceilIndex;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
