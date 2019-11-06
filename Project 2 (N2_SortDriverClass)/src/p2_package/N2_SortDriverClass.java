/**
 * N2_SortDriverClass has a Bubble sorting method,
 * Insertion sorting method, Selection sorting, and
 * Shell sorting method that all take in an array
 * and sort them accordingly.
 * 
 * @author miguelvillareal
 */
package p2_package;


public class N2_SortDriverClass
extends java.lang.Object
{
    /**
     *
     * Default constructor
     * 
     * No data maintained in the class, no action
     */
    public N2_SortDriverClass()
    {
        
    }
    
   /**
    * Sorts elements using the bubble sort algorithm
    * 
    * @param charArr character array of items to be sorted 
    * 
    * @param size integer value holding the number of characters in the array
    * 
    * @return character array of sorted items
    */
    public static char[] runBubbleSort( char[] charArr, int size )
    {
        // Sets the boolean flag to false
        boolean swapFlag = false;
        // Index variable
        int ind;
        
        while( swapFlag == false )
        {
            //Once inside loop I set the boolean flag to true
            swapFlag = true;
            
            for( ind = 0; ind < size - 1; ind++ )
            {
                //If the next index is greater set swap to true and place flag
                if( charArr[ ind ] > charArr[ ind + 1 ] )
                {
                    swapValues( charArr, ind, ind + 1 );
                    
                    swapFlag = false;
                    
                }
                
            }
            
        }
        //Returns the sorted array
        return charArr;
    }
    
   /**
    * Sorts character elements using the insertion sort algorithm
    * 
    * @param charArr character array of items to be sorted 
    * 
    * @param size integer value holding the number of characters in the array
    * 
    * @return character array of sorted items
    */
    public static char[] runInsertionSort( char[] charArr, int size )
    {
        
        int tempVal;
        
        int index;
        
        //Outer loop points to second position and so on
        for( index = 1; index < size; index++ )
        {
        	tempVal = charArr[ index - 1 ];
            // Inner loop goes down array, looks at object below and checks it
            while (index > 0 && tempVal < charArr[index])
            {
                charArr[ index - 1 ] = charArr[ index ];
                
                index--;
                
            }
            
            tempVal = charArr[ index ];
            
        }
        
        return charArr;
        
    }
    
   /**
    * Sorts character elements using the selection sort algorithm
    * 
    * @param charArr character array of items to be sorted 
    * 
    * @param size integer value holding the number of characters in the array
    * 
    * @return String data holding list of sorted items
    */
    public static char[] runSelectionSort(char[] charArr, int size)
    {
        //Starts with first index in a set and loops through the rest
        int minVal;
        
        int nextVal;
        
        for( minVal = 0; minVal < size; minVal++ )
        {
            for( nextVal = minVal + 1; nextVal < size; nextVal++ )
            {
                if( charArr[ minVal ] > charArr[ nextVal ] )
                {
                    //swaps the values within the array
                    swapValues( charArr,minVal, nextVal );
                    
                }
                
            }
            
        }
        //returns the sorted array
        return charArr;
        
    }
    
   /**
    * Uses Shell's sorting algorithm to sort an array of integers
    * 
    * Shell's sorting algorithm is an optimized insertion algorithm
    * 
    * @param charArr character array of items to be sorted 
    * 
    * @param size integer value holding the number of characters in the array
    * 
    * @return character array holding list of sorted items
    */
    public static char[] runShellSort(char[] charArr, int size)
    {
        //The Index's current location
        int currentInd;
        //The gap between the sorters
        int Gap;
        
        char temporary;
        //The Index's next location
        int nextInd;
        
        //Take the array length and split in half for first gap
        for( Gap = size / 2; Gap > 0; Gap /= 2 )
        {
            
            for( currentInd = Gap; currentInd < size; currentInd++  )
            {
                //the temporary location of the index
                temporary = charArr[ currentInd ];
                
                for( nextInd = currentInd; nextInd >= Gap && 
                        charArr[ nextInd - Gap ] > 
                        temporary; nextInd -= Gap )
                {
                    
                    charArr[ nextInd ] = charArr[ nextInd - Gap ];
                    
                }
                
                charArr[ nextInd ] = temporary;
            }
            
        }
        //returns the sorted array
        return charArr;
        
    }
    
   /**
    * Swaps values within given array
    * 
    * @param charArray array of characters used for swapping
    * 
    * @param indexOne integer index for one of the two items
    * to be swapped
    * 
    * @param indexOther integer index for the other of the two items
    * to be swapped
    * 
    */
    private static void swapValues(char[] charArray,
                                   int indexOne,
                                   int indexOther)
    {
        //the temporary variable
        char temporaryVar;
        //sets the temorary variable to the first index
        temporaryVar = charArray[indexOne];
        //changes the index
        charArray[indexOne] = charArray[indexOther];
        
        charArray[indexOther] = temporaryVar;
        
    }

}
