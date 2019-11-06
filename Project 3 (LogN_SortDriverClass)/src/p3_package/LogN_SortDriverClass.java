/**
 * This project implements both Merge Sort and Quick Sort as stated.
 * 
 * CS 249
 * 
 * @author miguel villareal
 */
package p3_package;


public class LogN_SortDriverClass 
{
    public LogN_SortDriverClass()
    {
        
    }
    
   /**
    * Compares two strings character by character set to lower case
    * to see which is alphabetically greater than the other;
    * if all tested letters of a name are equal, then compares
    * string lengths.
    * results are as follows:
    * Alphabetically, if strOne is greater than strTwo, returns 
    * value greater than zero.(e.g.,Susan is greater than Bill)
    * 
    * if strOne is less than strTwo, returns values less than zero
    * 
    * if strOne is equal to strTwo alphabetically but is different length,
    * returns difference in length
    * 
    * if strOne is equal to strTwo both alphabetically and in length, 
    * returns zero
    * <p>
    * Note: .length utility method may used in this method
    * 
    * @param strOne first String value to be compared 
    * 
    * @param strTwo Second String value to be compared
    * 
    * @return integer difference as specified
    */
    public static int compareStrings( String strOne, String strTwo )
    {
        int index = 0;
        {
        	while( index < strOne.length() && index < strTwo.length() )
            {
                char str0neLetter = toLowerCase(strOne.charAt(index));
                
                char strTwoLetter = toLowerCase(strTwo.charAt(index));
                // This shows that two words are not the same in terms
                // of the alphabet.
                int alphabetDifference = str0neLetter - strTwoLetter;
                
                if( alphabetDifference != 0 )
                {
                     return alphabetDifference;
                     
                }
                
                index++;
                
            }
        	
        	return strOne.length() - strTwo.length();

        }
        
    }
    
   /**
    * Merges String values brought in between a low and high index
    * segment (inclusive) of an array
    * <p>
    * Note: uses locally sized single array for temporary storage
    * 
    * @param localArray String array holding unsorted values 
    * 
    * @param lowIndex lowest index of array segment to be managed
    * 
    * @param middleIndex middle index of array segment to be managed
    * 
    * @param highIndex high index of array segment to be managed
    */
    private static void runMerge( String[] localArray, int lowIndex,
                                int middleIndex, int highIndex )
    {
        int size = ( ( highIndex - lowIndex ) + 1 );
        
        String tempArray[] = new String[ size ];
        
        int tempInd = 0;
        
        int index;
        
        int lowWorkIndex = 0;
        
        int lowCap =  (size - 1) / 2;
        
        int highWorkIndex = lowCap + 1;
        
        int highCap = size - 1;
        
        
        for( index = lowIndex; index <= highIndex; index++ )
        {
        	tempArray[ tempInd ] = localArray[ index ];
            
            tempInd++;
        }
        
        index = lowIndex;
        
        while( ( lowWorkIndex <= lowCap ) && (highWorkIndex <= highCap ) )
        {
            if( compareStrings( tempArray[ lowWorkIndex ],
                    tempArray[ highWorkIndex ] )<0 )
            {
            	localArray[ index ] = tempArray[ lowWorkIndex ];
                
                lowWorkIndex++;
                
                index++;
            }
            
            else
            {
            	localArray[ index ] = tempArray[ highWorkIndex ];
               
                highWorkIndex++;
               
                index++;
            }
            
        }
        
        while ( lowWorkIndex <= lowCap )
        {
        	localArray[ index ] = tempArray[ lowWorkIndex ];
            
            lowWorkIndex++;
            
            index++;
            
        }
        
        while( highWorkIndex <= highCap )
        {
        	localArray[ index ] = tempArray[ highWorkIndex ];
            
            highWorkIndex++;
            
            index++;
            
        }
        
    }
    
   /**
    * String data sorted using merge sort algorithm
    * <p>
    * Note: Calls runMergeSortHelper with lower and upper indices
    * of array to be sorted
    * 
    * @param localArray String array holding unsorted values
    * 
    * @param size integer value holding number of values in array
    */
    public static void runMergeSort( String[] localArray, int size )
    {

        runMergeSortHelper( localArray, 0, size-1 );
        
    }
    
   /**
    * Merge sort helper, recursively breaks given array segment down to
    * smaller segments between lowIndex and highIndex (inclusive), 
    * then sorts data using merge sort method
    * 
    * @param localArray String array holding unsorted values 
    * 
    * @param lowIndex lowest index of array segment to be managed;
    * this varies as the segments are broken down recursively
    * 
    * @param highIndex highest index of array segment to be managed;
    * this varies as the segments are broken down recursively
    */
    private static void runMergeSortHelper( String[] localArray,
                                          int lowIndex, int highIndex )
    {
    	int middleIndex;
    	
        if( lowIndex < highIndex )
        {
            middleIndex = ( highIndex + lowIndex )/2;
            
            runMergeSortHelper( localArray, lowIndex, middleIndex );
            
            runMergeSortHelper( localArray, middleIndex + 1 , highIndex );
            
            runMerge( localArray, lowIndex, middleIndex, highIndex );
            
        }
        
    }
    
   /**
    * Partitions array using the first value as the partition; 
    * when this method is complete the partition value 
    * is in the correct location in the array
    * 
    * @param localArray String array holding unsorted values 
    * 
    * @param lowIndex low index of array segment to be partitioned
    * 
    * @param highIndex high index of array segment to be partitioned
    * 
    * @return integer index of partition pivot
    */
    private static int runPartition( String[] localArray, int lowIndex,
                                   int highIndex )
    {
    	// My working Index
        int wkgIndex;
        // My pivot Index
        int pivotIndex = lowIndex;
        
        for( wkgIndex = pivotIndex + 1 ; wkgIndex <= highIndex; wkgIndex++ )
        {
            if( compareStrings( localArray[ wkgIndex ],
                    localArray[ lowIndex ] )<=0)
            {
                pivotIndex++;
                
                swapValues( localArray, pivotIndex, wkgIndex );
            }
            
        }
        swapValues( localArray, pivotIndex, lowIndex );
        
        return pivotIndex;
        
    }
    
   /**
    * Data sorted using quick sort algorithm
    * <p>
    * Note: Call runQuickSortHelper with lower and
    * upper indices of array to be sorted
    * 
    * @param localArray String array holding unsorted values
    * 
    * @param size integer value holding the number of values in the array
    */
    public static void runQuickSort( String[] localArray, int size )
    {
        runQuickSortHelper( localArray, 0, size - 1 );
        
    }
    
   /**
    * Helper method run with parameters that support recursive access
    * 
    * @param localArray String array holding unsorted values 
    * 
    * @param lowIndex low index of the segment of the array to be processed
    * 
    * @param highIndex high index of the segment of the array to be processed
    */
    private static void runQuickSortHelper( String[] localArray,
                                           int lowIndex, int highIndex )
    {
        if( highIndex > lowIndex )
        {
            int runningPart = runPartition( localArray, lowIndex, highIndex );
            
            runQuickSortHelper( localArray, lowIndex, runningPart - 1 );
            
            runQuickSortHelper( localArray, runningPart + 1, highIndex );
            
        }
        
    }
    
   /**
    * Swaps values within given array
    * 
    * @param localArray array of Strings used for swapping 
    * 
    * @param indexOne integer index for one of the two items to be swapped
    * 
    * @param indexOther integer index for the other of the two items
    * to be swapped
    */
    private static void swapValues( String[] localArray,
                                   int indexOne, int indexOther )
    {
        //the temporary variable
        String temporaryV;
        //sets the temporary variable to the first index
        temporaryV = localArray[ indexOne ];
        //changes the index
        localArray[ indexOne ] = localArray[ indexOther ];
        
        localArray[ indexOther ] = temporaryV;
        
    }
    
   /**
    * If character is upper case letter (i.e., 'A' - 'Z'), changes letter
    * to lower case (i.e., 'a' - 'z'); otherwise, returns character unchanged
    * 
    * @param testChar character value to be tested and possibly modified 
    * 
    * @return character value modified as specified
    */
    private static char toLowerCase( char testChar )
    {
        if( testChar >= 'A' && testChar <= 'Z' )
       {
           testChar = ( char )( testChar - 'A' + 'a');
       }
       
       return testChar;
       
    }

}
