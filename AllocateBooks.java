//n books and the number of pages in ith book is in arr[i]
//m students should be allocated books such that
        //each of the m students has atleast 1 book
        //all books should be allocated
        //2 students can't share a book
        //1 student can pick multiple books
        //book allocation should be in a continous manner - 1st & 3rd book to student isn't valid
//allocate books such that max pages that each student has is minimum

//time - O(n * log(sum(n) - max(n)))
//space - O(1)
import java.util.ArrayList;
public class Solution {
    public static int findPages(ArrayList<Integer> arr, int n, int m) {
        //edge
        if(m > n)
        {
            return -1; //number of students is larger than number of books
        }

        //if 1 book is alloted to each student (test case n = m), then max pages for student is 
        //size of largest book
        int lowestPages = Integer.MIN_VALUE; //max(arr)

        //if all books are allocated to same student (test case m = 1), then max pages for student
        //is sum of pages in all books
        int highestPages = 0;
        for(int i = 0; i < arr.size(); i++)
        {
            highestPages += arr.get(i);
            lowestPages = Math.max(lowestPages, arr.get(i));
        }

        //System.out.println(lowestPages + "-------" + highestPages);

        //lowestPages <= result <= highestPages and result should be min, so default it to max in range
        int result = highestPages; 
        while(lowestPages <= highestPages)
        {
            int mid = lowestPages + (highestPages - lowestPages) / 2;
            //compute number of students to which n books can be alloted 
            //if max pages a student can have is mid
            int students = countStudents(arr, mid);

            //System.out.println(mid + "-----" + students);
            if(students <= m)
            {
                //fewer students can finish all books
                //reduce pages per student as we have more students with us 
                //and are looking to reduce max pages per student - search in left
                result = mid;
                highestPages = mid - 1;
            }
            else
            {
                //we don't have enough students to finish all books if mid is max pages per student
                //reduce students, increase pages, search in right
                lowestPages = mid + 1;
            }
        }

        return result;
    }

    //computes number of students to finish all books if max pages per student is given
    //time - O(n) with constant space 
    private static int countStudents(ArrayList<Integer> arr, int maxPages)
    {
        int students = 1; 
        int pages = 0; //pages assigned to current student
        for(int i = 0; i < arr.size(); i++)
        {
            if(pages + arr.get(i) <= maxPages)
            {
                //assign current book to current student
                pages += arr.get(i);
            }
            else{
                //assign current book to next student
                pages = arr.get(i);
                students++;
            }
        }

        return students;
    }
}
