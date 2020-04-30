class Main {
  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4};
    System.out.println(findRange(nums, 4));
  }
 
  //time - O(log n) with constant space
  private static int findRange(int[] nums, int target) {
    //edge
    if(nums == null || nums.length == 0)
    {
      return Integer.MAX_VALUE;
    }
    
    int low = 0;
    int high = 1;
    int current = nums[high];

    while(current < target)
    {
      //if() never executes in an infinite []
      if(high * 2 > nums.length - 1) //index out of bounds
      {
        //start binary serach from current high till end of array
        return binarySerach(nums, high, nums.length, target);
      }
      high = high * 2;
      current = nums[high];
    }

    //when while() breaks, current >= target
    low = high / 2; //current was less than high till prev iteration of while()

    //so target is between updated low and high
    int result = binarySerach(nums, low, high, target);

    return result;
  }

  //time - O(log n) with constant space
  private static int binarySerach(int[] nums, int low, int high, int target) {
    while(low <= high)
    {
      int mid = low + (high - low) / 2;
      if(nums[mid] == target)
      {
        return mid;
      }

      else if(nums[mid] > target)
      {
        high = mid - 1;
      }

      else   
      {
        low = mid + 1;
      }
    }
    return -1;
  }
}
