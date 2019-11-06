package p10_package;

/**
 * Simple generic hash class
 * 
 * CS 249 
 * 
 * DUE: 4-16-19
 * 
 * @author Miguel Villareal
 *
 */
public class GenericHashClass<GenericData extends Comparable<GenericData>>
{
	/**
     * Table size default
     */
     private final int DEFAULT_TABLE_SIZE = 10;
       
    /**
     * Constant for returning item not found with search
     */
     public final int ITEM_NOT_FOUND = -1;
       
    /**
     * Constant for setting linear probing
     */
     public static final int LINEAR_PROBING = 101;
       
    /**
     * Flag for setting linear or quadratic probing
     */
     private int probeFlag;
       
    /**
     * Constant for setting quadratic probing
     */
     public static final int QUADRATIC_PROBING = 102;
     
    /**
     * Array for hash table
     */
     private Object[] tableArray;
      
    /**
     * Size of the array table
     */
     private int tableSize;
     
    /**
     * Default constructor
     * Initializes to default table size with probe flag set to linear probing
     */
     public GenericHashClass()
     {
    	 //initializes any not final fields
     	 tableSize = DEFAULT_TABLE_SIZE;
     	 
     	 probeFlag = LINEAR_PROBING;
     	 
     	 tableArray = new StudentClass[ tableSize ];
     	 // sets values to null
     	 clearHashTable();
     }
     
    /**
     * Initialization constructor
     * Initializes to default table size with probe 
     * flag set to probe flag parameter
     * 
     * @param inProbeFlag sets linear or quadratic probing
     */
     public GenericHashClass( int inProbeFlag )
     {
    	 tableSize = DEFAULT_TABLE_SIZE;
    	 
         probeFlag = inProbeFlag;
     	 
     	 tableArray = new StudentClass[ tableSize ];
     	 
     	 clearHashTable();
     }

    /**
     * Initialization constructor
     * 
     * @param inTableSize sets table size (capacity) but 
     * does not allow table size to be less than default capacity
     * 
     * @param inProbeFlag sets linear or quadratic probing
     */
     public GenericHashClass( int inTableSize, int inProbeFlag )
     {
    	 if( inTableSize >= DEFAULT_TABLE_SIZE ) 
    	 {
    		 tableSize = inTableSize;
    	 }
    	 
    	 if( inProbeFlag == LINEAR_PROBING || inProbeFlag == QUADRATIC_PROBING ) 
    	 {
    		 probeFlag = inProbeFlag;
    	 }
    	 
    	 tableArray = new StudentClass[ tableSize ];
    	 
    	 clearHashTable();
     }
       
    /**
     * Adds GenericData item to hash table
     * <p>
     * Note: Uses hash index value from generateHash
     * <p>
     * Note: Shows probed index with data at the point of insertion
     * <p>
     * Note: Probe attempts are limited to the current size (capacity) 
     * of the table
     * 
     * @param newItem GenericData item
     * 
     * @return Boolean success of operation
     */
	public boolean addItem( GenericData newItem )
     {
		 //my index in add item
    	 int myIndex = generateHash( newItem );
    	 // my counter
    	 int myProbeCounter = 1;
    	 // as long as its not null and less then or equal to table Size
    	 while( tableArray[ myIndex ] != null && myProbeCounter <= tableSize ) 
    	 {
    		 // if flag is linear
    		 if( probeFlag == LINEAR_PROBING ) 
    		 {
    			 myIndex += myProbeCounter;
    		 }
    		 // if flag is quadratic
    		 else if( probeFlag == QUADRATIC_PROBING )
    		 {
    			 myIndex += toPower( myProbeCounter, 2 );
    		 }
    		 // my index modulo table size everytime
    		 myIndex %= tableSize;
    		 // increment counter
    		 myProbeCounter++;
    	 }
    	 // counter less than table size
    	 if( myProbeCounter < tableSize ) 
    	 {
    		 // set the value to the new item
    		 tableArray[ myIndex ] = newItem;
    		 // print it out with the index
    		 System.out.println( newItem.toString() + ", probed index: " + 
    		 myIndex );
    		 
    		 return true;
    		 
    	 }
    	 
    	 return false;
     }
       
    /**
     * Clears hash table by setting all bins to null
     */
     public void clearHashTable()
     {
    	 int index;
    	 // loop through table and set all values to null
    	 for( index = 0; index < tableSize; index++ ) 
    	 {
    		 tableArray[ index ] = null;
    	 }
     }
        
    /**
     * Returns item found
     * 
     * @param searchItem GenericData value to be found; uses findItemIndex
     * 
     * @return GenericData item found
     */
     public GenericData findItem( GenericData searchItem )
     {
    	 // uses find item index
    	 int findIndex = findItemIndex( searchItem );
    	 // if value is not, not found
    	 if( findIndex != ITEM_NOT_FOUND ) 
    	 {
    		 // return the value
    		 return searchItem;
    	 }
    	 return null;
     }
         
    /**
     * Searches for item index in hash table
     * 
     * Uses linear or quadratic probing as configured
     *
     * @param searchItem GenericData value to be found
     *
     * @return integer index location of search item
     */
	@SuppressWarnings("unchecked")
	private int findItemIndex( GenericData searchItem )
     {
		 // my index
    	 int myIndex = generateHash( searchItem );
    	 //my counter
    	 int myProbeCounter = 1;
    	 //sets the data and casts it from object to generic
		 GenericData hashing = (GenericData) tableArray[ myIndex ];
    	 // while the value is not null and counter is less than or equal to
		 // table size
    	 while( hashing != null && hashing.compareTo(searchItem) != 0 && 
    			 myProbeCounter <= tableSize) 
    	 {
    		 //flag is linear
    		 if( probeFlag == LINEAR_PROBING ) 
    		 {
    			 myIndex = myIndex + myProbeCounter;
    		 }
    		 //flag is quadratic
    		 else if( probeFlag == QUADRATIC_PROBING )
    		 {
    			 myIndex = myIndex + toPower( myProbeCounter, 2 );
    		 }
    		 // index modulo table size for everytime 
    		 myIndex %= tableSize;
    		 // increment counter
    		 myProbeCounter++;
    		 // set hashing again
    		 hashing = (GenericData) tableArray[ myIndex ];
    		 
    	 }
    	 // if the value is not null and the counter is equal to table size
    	 if( hashing != null && myProbeCounter < tableSize ) 
    	 {
    		 // return that index
    		 return myIndex; 
    	 }
    	 else 
    	 {
    		 // it was not found
    		 return ITEM_NOT_FOUND;
    	 }
     }
          
    /**
     * Method converts GenericData hash value to index for use in hash table
     * 
     * @param Method converts GenericData hash value to index for 
     * use in hash table
     * <p>
     * Note: gets data from object via hashCode, then calculates index
     * <p>
     * Note: Uses hashCode from object
     * 
     * @return integer hash value
     */
     public int generateHash( GenericData item )
     {
    	 // turns item into a index
    	 return item.hashCode() % tableSize;
     }
           
    /**
     * Removes item from hash table
     *
     * @param toBeRemoved GenericData value used 
     * for requesting data uses findItemIndex
     * 
     * @return GenericData item removed, or null if not found
     */
     @SuppressWarnings("unchecked")
	 public GenericData removeItem( GenericData toBeRemoved )
     {
    	 // the removed index value
    	 int removedIndex = findItemIndex( toBeRemoved );
    	 //set value to null
    	 GenericData returnedValue = null;
    	 // removed index is within table size
    	 if( removedIndex < tableSize ) 
    	 {
    		 // value returned equals value removed
    		 returnedValue = ( GenericData ) tableArray[ removedIndex ];
    	 }
    	 // return value
    	 return returnedValue;
     }
           
    /**
     * traverses through all array bins, finds min and max number 
     * of contiguous elements, and number of empty nodes;
     * also shows table loading
     * <p>
     * NOTE: Generates string of used and unused bins in 
     * addition to displaying results on screen
     * 
     * @return String result of hash table analysis
     */
     public String showHashTableStatus()
     {
    	 // my Index
    	 int index;
    	 // the empty bins
    	 int emptyBins = 0;
    	 // my contiguous counter
    	 int contCount = 0;
    	 // my minimum contiguous counter
    	 int minContNum = tableSize;
    	 // my maximum contiguous counter
    	 int maxContNum = 0;
    	 // the Output of the bins
    	 String binOut = "";
    	 // for loop for Null and Data
    	 for( index = 0; index < tableSize; index++ ) 
    	 {
    		 // if value is null
    		 if( tableArray[ index ] == null ) 
    		 {
    			 // return N
    			 binOut += "N";
    		 }
    		 else 
    		 {
    			 // return D
    			 binOut += "D";
    		 }
    	 }
    	 // for loop for min and max Contiguous counter
    	 for( index = 0; index < tableSize; index++ ) 
    	 {
    		 // if value is not null
    		 if( tableArray[ index ] != null ) 
    		 {
    			 // increment counter
    			 contCount++;
    			 // counter greater than 0
    			 if( contCount > 0 ) 
    			 {
    				 //max less than counter
    				 if( maxContNum < contCount ) 
    				 {
    					 // set max to counter
    					 maxContNum = contCount;
    				 }
    				 // min greater than counter
    				 if( minContNum > contCount ) 
    				 {
    					 // set min to counter
    					 minContNum = contCount;
    				 }
    			 }
    		 }
    		 else 
    		 {
    			 // increment number of empty bins
    			 emptyBins++;
    			 // set counter to zero
    			 contCount = 0;
    		 }
    	 }
    	 //Print statements for output
		 
		 System.out.println("");
		 
		 System.out.println("Hash Table Status: " + binOut);
		 
		 System.out.println("Minimum Contiguous Bins: " + minContNum);
		 
		 System.out.println("Maximum Contiguous Bins: " + maxContNum);
		 
		 System.out.println("   Number of empty bins: " + emptyBins);
		 
		 System.out.println("");
		 
		 System.out.println("Array Dump:");
		 
		 // for loop for null or output
		 for( index = 0; index < tableSize; index++ ) 
		 {
			 if( tableArray[ index ] == null ) 
			 {
				 System.out.println("null");
			 }
			 else 
			 {
				 System.out.println( tableArray[ index ].toString() );
			 }
		 }
		 return null;	 
     }
     
    /**
     * Local recursive method to calculate exponentiation with integers
     * 
     * @param base base of exponentiation
     * 
     * @param exponent exponent of exponentiation
     * 
     * @return result of exponentiation calculation
     */
     private int toPower( int base, int exponent )
     {
    	 // exponent greater than 0
    	 if( exponent > 0 )
         {
    		 // recursively call it
             return toPower( base, exponent - 1 ) * base;
         }
         return 1;
     } 
}