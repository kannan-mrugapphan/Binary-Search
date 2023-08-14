class Solution 
{   
    //time - O(log(sum(books[]) - max(books[])) * length of books[]) with costant space
    public static int findPages(int[] A, int N, int M)
    {
        //all books must be allocated
        //every student should atleast have 1 book (possibly more)
        //book allocation should be contiguous i.e book1,book3 to student1 and book2 to student2 is not possible
        //edge
        if(M > N)
        {
            return -1; //assignment not possible as number of students is larger than that of books
        }
        
        //min number of pages that each student must take should be book with largest pages
        //some student should pick this largest book
        //worst case, if M = 1, all books should be alloted to single student
        //so max pages a student can take is sum of pages in all books
        int low = Integer.MIN_VALUE; //max in bookes[]
        int high = 0; //sum of all books
        for(int pages : A)
        {
            low = Math.max(low, pages);
            high += pages;
        }
        
        //optimal pages is in [low, high]
        int result = high; //minimize number of pages, so set it to max in range
        while(low <= high)
        {
            int mid = low + (high - low) / 2;
            //compute number of students needed if max pages that a student can take is mid
            int students = findStudents(A, mid);
            
            System.out.println(mid + "----" + students);
            
            if(students == M)
            {
                //mid is valid allocation
                result = mid;
                //try to see if N books can be allocated to M students with lower limit, search in left half
                high = mid - 1; 
            }
            else if(students < M)
            {
                //increase the limit of pages for each student to increase count of students possible
                //search in right half
                low = mid + 1;
            }
            else if(students > M)
            {
                //less students are needed, increase limit for each student, search in left half
                high = mid - 1;
            }
        }
        
        return result;
    }
    
    //computes number of students to finish all books if number of pages a student can take is given
    //time - O(n) with constant space
    private static int findStudents(int[] books, int limit)
    {
        int students = 1;
        int pagesForCurrentStudent = 0; //initially 1st student has no books so 0 pages
        for(int book : books)
        {
            if(pagesForCurrentStudent + book <= limit)
            {
                //current student can pick current book
                pagesForCurrentStudent += book;
            }
            else
            {
                //assign current book to next student
                pagesForCurrentStudent = book;
                students++;
            }
        }
        
        return students;
    }
}
