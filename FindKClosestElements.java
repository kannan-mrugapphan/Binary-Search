//658.

class Pair {
    int element;
    int distance;
    
    public Pair(int element, int distance) {
        this.element = element;
        this.distance = distance;
    }
}

//custom comparator for max heap
class custom implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2)
    {
        if(p1.distance < p2.distance)
        {
            return 1;
        }
        else if(p1.distance > p2.distance)
        {
            return -1;
        }
        return 0;
    }
}

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //edge 
        if(arr == null || arr.length == 0)
        {
            return null;
        }
        
        //return findUsingHeap(arr, k, x);
        //return twoPointer(arr, k, x);
        return binarySearch(arr, k, x);
    }
    
    //time - O(nlogk)
    //space - O(k)
    private List<Integer> findUsingHeap(int[] arr, int k, int x) {
        //support is a max heap of ojects (with values and distance from x) based on distance 
        PriorityQueue<Pair> support = new PriorityQueue<>(k, new custom());
        List<Integer> result = new ArrayList<>();
        //iterate and populate pq till size less than k
        //then, if dist of root is greater than the distance of current, remove root and insert current
        for(int num : arr)
        {
            Pair current = new Pair(num, Math.abs(num - x));
            if(support.size() < k)
            {
                support.offer(current);
            }
            else
            {
                if(current.distance < support.peek().distance)
                {
                    support.poll();
                    support.offer(current);
                }
            }
        }
        //poll from the pq and insert into result
        while(!support.isEmpty())
        {
            result.add(support.poll().element);
        }
        //return sorted version of result
        Collections.sort(result); //runs for klogk 
        return result;
    }
    
    //time - O(n - k)
    //space - constant - excluding the result
    private List<Integer> twoPointer(int[] arr, int k, int x) {
        int low = 0;
        int high = arr.length - 1;
        
        while(high - low + 1 > k) //as long as there are more elements than k
        {
            int lowDist = Math.abs(arr[low] - x); //dist of last element from x
            int highDist = Math.abs(arr[high] - x); //dist of first element from x
            if(lowDist > highDist) //first element is farther and can be ignored
            {
                low++;
            }
            else //last element is farther and can be ignored
            {
                high--;
            }
        }
        
        List<Integer> answer = new ArrayList<>();
        for(int i = low; i <= high; i++)
        {
            answer.add(arr[i]);
        }
        return answer;
    }
    
    //time - O(logn + k)
    //space - O(1) excluding the result
    private List<Integer> binarySearch(int[] arr, int k, int x) {
        int lowStart = 0; //lowest possible left bound of the optimal region
        int highStart = arr.length - k; //highest possible left bound of the optimal region
        
        while(lowStart < highStart)
        {
            int mid = lowStart + (highStart - lowStart) / 2; //find middle (possible left bound)
            int right = mid + k; //calculate corresponding right bound
            
            int leftDist = Math.abs(arr[mid] - x); //find left bound dist
            int rightDist = Math.abs(arr[right] - x); //find right bound dist
            
            if(leftDist > rightDist)
            {
                lowStart = mid + 1;
            }
            else
            {
                highStart = mid;
            }
        }
        
        List<Integer> answer = new ArrayList<>();
        for(int i = lowStart; i < lowStart + k; i++)
        {
            answer.add(arr[i]);
        }
        return answer;
    }
}
