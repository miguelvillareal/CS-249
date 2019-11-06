/**
 * BasicArrayClass makes the array and the functions for that array which
 * we will use for iterator and stack.
 * 
 * CS 249 
 * 
 * DUE: 2-26-19
 * 
 * @author Miguel Villareal
 */
package p5_package;


public class BasicArrayClass
{
	//All the constants that were provided to us.
	
		/**
		 * Member data
		 */
		private int arrayCapacity;
		
		/**
		 * Member data
		 */
		private int arraySize;
		
		/**
		 *Default constant capacity
		 */
		private static final int DEFAULT_CAPACITY = 10;
		
		/**
		 * Default failed access constant
		 */
		public static final int FAILED_ACCESS = -999999;
		
		/**
		 * Constant used for allowing setAtIndex to insert value at index
		 */
		public static final int INSERT_BEFORE = 1002;
		
		/**
		 * Constant used for allowing setAtIndex to insert value at index
		 */
		public static final int INSERT_AFTER = 1003;
		
		/**
		 * Member integer array
		 */
		private int[] localArray;
		
		/**
		 * Constant used for allowing accessAtIndex to remove an item
		 */
		public static final int REMOVE = 1004;
		
		/**
		 * Constant used for allowing setAtIndex to replace value at index
		 */
		public static final int REPLACE = 1001;
		
		/**
		 * Constant used for allowing accessAtIndex to retrieve an item
		 */
		public static final int RETRIEVE = 1005;
		
		/**
		 * Default constructor, initializes array to default capacity
		 * 
		 */
		protected BasicArrayClass()
		{
			//sets arrayCapacity equal to default capacity
			arrayCapacity = DEFAULT_CAPACITY;
			//sets arraySize equal to 0
			arraySize = 0;
			
			localArray = new int[ arrayCapacity ];
			
		}
		
		/**
		 * Initializing constructor, initializes array to specified capacity
		 * 
		 */
		protected BasicArrayClass(int capacity)
		{
			arrayCapacity = DEFAULT_CAPACITY;
			
			arraySize = 0;
			
			localArray = new int [ capacity ];
		}
		
		/**
		 * Copy constructor, initializes array to size and capacity of copied
		 *  array, then copies only the elements up to the given size
		 * 
		 * @param copied BasicArrayClass object to be copied
		 */
		protected BasicArrayClass(BasicArrayClass copied)
		{
			arraySize = copied.arraySize;
			
			arrayCapacity = copied.arrayCapacity;
			
			localArray = new int[ arrayCapacity ];
			
			int index;
			// for loop that copies the array
			for ( index = 0; index < arraySize; index++ ) 
			{
				
				this.localArray[ index ] = copied.localArray[ index ];
				
			}
			
		}
		
		/**
		 * Utility method used by getAtIndex and removeAtIndex to access and
		 * possibly remove element depending on control code
		 *  <p>
		 * Note: Uses only one loop
		 * 
		 * @param controlCode - integer value with either RETRIEVE or REMOVE
		 *  to control operations
		 *  
		 * @param index integer index of element to be retrieved or removed
		 *  
		 *  @return integer value at element or FAILED_ACCESS if attempt to 
		 *  access data out of bounds
		 */
		private int accessAtIndex(int controlCode,
								  int index)
		{
			//index that will be helping to remove the index
			int removingIndex;
			// the actual removed index
			int removedIndex=0;
			
			int elementValue = localArray[ index ];
			// index has to be less than or equal to arraySize and greater than
			// or equal to 0
			if( index <= arraySize && index >= 0 ) 
			{
				if( controlCode == RETRIEVE ) 
				{
					return elementValue;
					
				}
				else 
				{
					for( removingIndex = index; removingIndex < arraySize - 1;
							removingIndex++ ) 
					{
						removedIndex = localArray[ index ];
						localArray[removingIndex] = 
								localArray[removingIndex + 1];
						
					}
					
					arraySize--;
					
					return removedIndex;
					
				}
				
			}
			// failed access is returned if nothing works
			return FAILED_ACCESS;
			
		}
		
		/**
		 * Checks for need to resize; if this is necessary, 
		 * creates new array with double the original capacity, loads data
		 * from original array to new one, then sets localArray to new array
		 * 
		 */
		protected void checkForResize()
		{
			//temporary Array 
			int[] temporaryArray;
			// the capacity times two
			int doubleCapacity;
			// the index
			int index;
			//array size needs to be equal to array capacity for it to double
			if( arraySize == arrayCapacity ) 
			{
				doubleCapacity = 2 * arrayCapacity;
				
				temporaryArray = new int [ doubleCapacity ];
				
				for(index = 0; index < arraySize; index++) 
				{
					temporaryArray[ index ] = localArray[ index ];
					
				}
				//after it doubles the capacity
				arrayCapacity = doubleCapacity;
				// sets my temporary equal to my local array
				localArray = temporaryArray;
				
			}
			
		}
		
		/**
		 * Clears array of all valid values by setting array size to zero, 
		 * values remain in array but are not accessible
		 * 
		 */
		protected void clear()
		{
			// arraySize = 0
			arraySize = 0;
			
		}
		
		/**
		 * Accesses item in array at specified index if index within array
		 * size bounds
		 * <p>
		 * Note: Calls accessAtIndex with RETRIEVE to conduct action
		 * 
		 * @param accessIndex integer index of requested element value
		 *  
		 * @return integer accessed value if successful, FAILED_ACCESS if not
		 */
		protected int getAtIndex(int accessIndex)
		{
			// uses access at index to get the current index
			return accessAtIndex( RETRIEVE, accessIndex ); 
			
		}
		
		/**
		 * Gets current capacity of array
		 * <p>
		 * Note: capacity of array indicates number of values the array 
		 * can hold
		 * 
		 * @return integer capacity of array
		 */
		protected int getCurrentCapacity()
		{
			return arrayCapacity;
			
		}
		
		/**
		 * Gets current size of array
		 * <p>
		 * Note: size of array indicates number of valid or viable
		 * values in the array
		 * 
		 * @return integer size of array
		 */
		protected int getCurrentSize()
		{
			return arraySize;
			
		}
		
		/**
		 * Tests for size of array equal to zero, no valid
		 * values stored in array
		 * 
		 * @return Boolean result of test for empty
		 */
		protected boolean isEmpty()
		{
			return arraySize == 0;
			
		}
		
		/**
		 * Description: Removes item from array at specified index if index 
		 * within array size bounds
		 * <p>
		 * Note: Each data item from the element immediately above the remove 
		 * index to the end of the array is moved down by one element
		 * <p>
		 * Note: Calls accessAtIndex with REMOVE to conduct action
		 * 
		 * @param removeIndex integer index of element value to be removed
		 *  
		 * @return removed integer value if successful, FAILED_ACCESS if not
		 */
		protected int removeAtIndex(int removeIndex)
		{
			return accessAtIndex( REMOVE, removeIndex );
		}
		
		/**
		 * Description: sets item in array at specified index
		 * <p>
		 * Note: If constant REPLACE is used, new value overwrites value at 
		 * given index
		 * <p>
		 * Note: If constant INSERT_BEFORE is used, new value is inserted prior 
		 * to the value at the given index moving all other elements up by one
		 * <p>
		 * Note: If constant INSERT_AFTER is used, new value is inserted after 
		 * the value at the given index moving all other elements up by one
		 * <p>
		 * Note: Method checks for available array capacity and adjusts it as 
		 * needed prior to inserting new item
		 * <p>
		 * Note: Method must also check for correct array boundaries depending 
		 * upon INSERT/REPLACE state
		 * 
		 * @param setIndex integer index of element at which value is to 
		 * be inserted
		 * 
		 * @param newValue integer value to be placed in array
		 * 
		 * @param replaceFlag integer flag to indicate insertion or 
		 * replacement in the array
		 *  
		 * @return Boolean success if inserted, or failure if incorrect 
		 * index was used
		 */
		protected boolean setAtIndex(int setIndex,
				int newValue, 
				int replaceFlag)
		{
			// my index
			int index;
			// my flag 
			boolean flag = false;
			// set index has to be greater than array capacity
			if( setIndex < arrayCapacity ) 
			{
				if( replaceFlag == REPLACE ) 
				{
					localArray[ setIndex ] = newValue;
					
					flag = true;
					
				}
				//replace flag is equal to insert before
				else if( replaceFlag == INSERT_BEFORE ) 
				{
					if( arraySize == 0 ) 
					{
						localArray[ 0 ] = newValue;
					}
					
					else 
					{
						for( index = arraySize; index > setIndex - 1; index-- )
						{
							localArray[ index ] = localArray[ index - 1 ];
							
						}
						
						localArray[ setIndex - 1 ] = newValue;
					}
					arraySize++;
					
					checkForResize();
					
					flag = true;
				}
				// replace flag is equal to insert after
				else if(replaceFlag == INSERT_AFTER)
				{
					if( setIndex == 0 ) 
					{
						localArray[ setIndex ] = newValue;
					}
					else 
					{
						for( index = arraySize; index > setIndex; index-- )
						{

								localArray[ index ] = localArray[ index - 1 ];
								
						}
						localArray[ setIndex ] = newValue;
						
					}
					arraySize++;
					
					checkForResize();
					
					flag = true;
				}
				
			}
			//if all else fails the flag returns false
			return flag;
		}
}
