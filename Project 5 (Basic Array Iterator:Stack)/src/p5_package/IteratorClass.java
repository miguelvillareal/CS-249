/**
 * IteratorClass goes through list and prints it out space delimited
 * with cursor location indicated.
 * 
 * CS 249 
 * 
 * DUE: 2-26-19
 * 
 * @author Miguel Villareal
 */
package p5_package;


public class IteratorClass extends BasicArrayClass
{
	/**
	 * Current index of iterator
	 */
	private int currentIndex = 0;
	
	/**
	 * Constant character for display
	 */
	private final char LEFT_BRACKET = '[';
	
	/**
	 * Constant character for display
	 */
	private final char RIGHT_BRACKET = ']';
	
	/**
	 * Constant character for display
	 */
	private final char SPACE = ' ';
	
	/**
	 * Default constructor for IteratorClass
	 * 
	 */
	public IteratorClass() 
	{
		//uses all of Basic array class
		super();
		// set current index equal to 0
		currentIndex = 0;
		
	}
	
	/**
	 *Initialization constructor for IteratorClass
	 *
	 *@param initCapacity integer value at which to set initial
	 *array capacity
	 */
	public IteratorClass( int initCapacity ) 
	{
		super( initCapacity );
		
		currentIndex = 0;
		
	}
	
	/**
	 *Copy constructor for IteratorClass
	 *
	 *@param copied IteratorClass object to be copied
	 */
	public IteratorClass( IteratorClass copied ) 
	{
		super( copied );
		
		this.currentIndex = copied.currentIndex;
		
	}
	
	/**
	 *Clears array
	 *
	 *@overrides clear in class BasicArrayClass
	 */
	public void clear() 
	{
		// uses the basic array class clear method
		super.clear();
		
		currentIndex = 0;
		
	}
	
	/**
	 *Gets value at iterator cursor location
	 *
	 *@return integer value returned; FAILED_ACCESS if not found
	 */
	public int getAtCurrent()
	{
		
		return getAtIndex(currentIndex);
		
	}
	
	/**
	 *Reports if iterator cursor is at beginning
	 *Must consider whether list is empty
	 *
	 *@return Boolean result of action; true if at end,
	 *false otherwise
	 */
	public boolean isAtBeginning()
	{
		//is at beginning when the index is at 0
		return currentIndex == 0;
		
	}
	
	/**
	 *Reports if iterator cursor is at end
	 *Must consider whether list is empty
	 *
	 *@return Boolean result of action; true if at end,
	 *false otherwise
	 */
	public boolean isAtEnd()
	{
		// is at end when index is at the end of the array
		return currentIndex == getCurrentCapacity();
		
	}
	
	/**
	 *Reports if list is empty
	 *
	 *@overrides isEmpty in class BasicArrayClass
	 *
	 *@return Boolean result of action; true if empty,
	 *false otherwise
	 */
	public boolean isEmpty()
	{
		//uses the basic array class empty method
		return super.isEmpty();
		
	}
	
	/**
	 *If possible, moves iterator cursor one position to the right,
	 *or next
	 *Must consider whether list is empty
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean moveNext()
	{
		// if its not empty the return true
		if( !isEmpty() ) 
		{
			currentIndex++;
			
			return true;
			
		}
		
		return false;
	}
	
	/**
	 *If possible, moves iterator cursor one position to the left,
	 *or previous
	 *Must consider whether list is empty
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean movePrev()
	{
		// if its not empty the return true
		if( !isEmpty() ) 
		{
			currentIndex--;
			
			return true;
			
		}
		
		return false;
	}
	
	/**
	 *Removes and returns a data value from the iterator cursor position
	 *<p>
	 *Note: cursor must be located at succeeding element unless last item
	 *removed
	 *
	 *@return integer value removed from list, 
	 *or FAILED_ACCESS if not found
	 */
	public int removeAtCurrent()
	{
		// sets the current value equal to returnValue
		int returnValue = super.removeAtIndex( currentIndex );
		// if the array is empty it wont decrement instead it will make it 0
		if( super.isEmpty() ) 
		{
			currentIndex = 0;

		}
		// else it will decrement the index
		else if( currentIndex > super.getCurrentSize() - 1 )
		{
			currentIndex--;
			
		}
		
		return returnValue;
		
	}
	
	/**
	 *Replaces value at iterator cursor with new value
	 *
	 *@param newValue integer value to be inserted in list
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean replaceAtCurrent(int newValue)
	{
		return setAtIndex( currentIndex, newValue, REPLACE );
		
	}
	
	/**
	 *Shows space-delimited list with cursor location indicated
	 */
	public void runDiagnosticDisplay()
	{
		// my index
		int Index;
		// for loop goes through list
		for( Index = 0; Index < super.getCurrentSize() ; Index++ ) 
		{
			if( Index == currentIndex - 1 ) 
			{
				// prints out space delimited list with cursor located
				System.out.print( " "+ SPACE + LEFT_BRACKET +
						getAtIndex( Index ) + RIGHT_BRACKET );
				
			}
			else 
			{
				System.out.print( " "+ SPACE + getAtIndex( Index ) );
			}
		}
		
		System.out.println();
		
	}
	
	/**
	 *Inserts new value after value at iterator cursor
	 *<p>
	 *Note: Current value must remain the same after data set
	 *
	 *@param newValue integer value to be inserted in list
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean setAfterCurrent(int newValue)
	{
		// set after set to false
		boolean setAfter = false;
		// this is what will set value after current value
		setAfter = super.setAtIndex( currentIndex, newValue, INSERT_AFTER );
		// increments index
		currentIndex++;
		
		return setAfter;
		
	}
	
	/**
	 *Inserts new value before value at iterator cursor
	 *<p>
	 *Note: Current value must remain the same after data set
	 *
	 *@param newValue integer value to be inserted in list
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean setBeforeCurrent(int newValue)
	{
		// set before equal to false
		boolean setBefore = false;
		// this is what will set value before the current value
		setBefore = super.setAtIndex( currentIndex, newValue, INSERT_BEFORE );
		// increments index
		currentIndex++;
		
		return setBefore;
		
	}
	
	/**
	 *Sets iterator cursor to beginning of list
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean setToBeginning()
	{
		return currentIndex == 0;
		
	}
	
	/**
	 *Sets iterator cursor to the end of the list
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean setToEnd()
	{
		return currentIndex == super.getCurrentSize();
		
	}
	

}