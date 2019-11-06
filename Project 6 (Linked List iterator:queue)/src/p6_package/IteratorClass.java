/**
 * IteratorClass inherited from BasicLinkedListClass
 * Conducts iterator operations
 * 
 * CS 249 
 * 
 * DUE: 3-12-19
 * 
 * @author Miguel Villareal
 */
package p6_package;

import p6_package.BasicLinkedListClass;

public class IteratorClass extends BasicLinkedListClass
{
	// All fields given for this class
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
		// uses the constructor of the basicLinkedListClass
		super();
		// set current index equal to 0
		currentIndex = 0;
		
	}
	
	/**
	 *Copy constructor for IteratorClass
	 *
	 *@param copied IteratorClass object to be copied
	 */
	public IteratorClass( IteratorClass copied ) 
	{
		// uses the BasicLinkedList copy constructor
		super( copied );
		
		this.currentIndex = copied.currentIndex;
		
	}
	
	/**
	 *Clears virtual array
	 *
	 *@overrides clear in class BasicArrayClass
	 */
	public void clear() 
	{
		// uses the basic linked list class clear method
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
		// calls the basic linked list class's get at index
		return super.getAtIndex(currentIndex);
		
	}
	
	/**
	 *Reports if iterator cursor is at beginning of existing list
	 *<p>
	 *Note: Must not return true from empty list
	 *
	 *@return Boolean result of action; true if at beginning of existing list,
	 *false otherwise
	 */
	public boolean isAtBeginning()
	{
		// checks if list is empty and if the index is at the 0 index 
		if( !isEmpty() && currentIndex == 0 ) 
		{
			return true;
		}
		// if list is empty then it returns false
		return false;
		
	}
	
	/**
	 *Reports if iterator cursor is at end of existing list
	 *<p>
	 *Note: Must not return true from empty list
	 *
	 *@return Boolean result of action; true if at end of existing list, 
	 *false otherwise
	 */
	public boolean isAtEnd()
	{
		//checks if list is empty
		if( !isEmpty() ) 
		{
			return currentIndex == getCurrentSize();
		}
		//if list is empty then it returns false
		return false;
		
	}
	
	/**
	 *Reports if list is empty
	 *
	 *@overrides isEmpty in class BasicLinkedListClass
	 *
	 *@return Boolean result of action; true if empty,
	 *false otherwise
	 */
	public boolean isEmpty()
	{
		// uses the basic linked list class's empty method
		return super.isEmpty();
		
	}
	
	/**
	 *Moves iterator cursor one position to the right, or next, of
	 *existing list
	 *<p>
	 *Note: Must not return true from empty list
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean moveNext()
	{
		// checks if list is not empty and if its not at  the end of the list
		if( !isEmpty() && !isAtEnd() ) 
		{
			currentIndex++;
			
			return true;
			
		}
		
		return false;
	}
	
	/**
	 *Moves iterator cursor one position to the left, or previous, 
	 *of existing list
	 *
	 *Note: Must not return true from empty list
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean movePrev()
	{
		// checks if list is not empty and if its not at the start of the list
		if( !isEmpty() && !isAtBeginning() ) 
		{
			currentIndex--;
			
			return true;
			
		}
		
		return false;
	}
	
	/**
	 *Removes and returns a data value from the iterator cursor position
	 *<p>
	 *Note: cursor must remain in same relative position after removal 
	 *unless the last item is removed
	 *
	 *@return integer value removed from list
	 */
	public int removeAtCurrent()
	{
		// return value is removed using basic linked list class's remove at
		// index
		int returnValue = super.removeAtIndex( currentIndex );
		
		if( super.isEmpty() ) 
		{
			currentIndex = 0;

		}
		
		else if( currentIndex > super.getCurrentSize() - 1 )
		{
			currentIndex--;
			
		}
		
		return returnValue;
		
	}
	
	/**
	 *Replaces value at iterator cursor
	 *
	 *@param newValue integer value to be replaced in list
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean replaceAtCurrent( int newValue )
	{
		//calls the basic linked list class's set at index with control code
		// replace 
		return super.setAtIndex( currentIndex, newValue, REPLACE );
		
	}
	
	/**
	 *Shows space-delimited list with cursor location indicated
	 *
	 *Indicates cursor with left/right brackets (e.g., "[##]")
	 */
	public void runDiagnosticDisplay()
	{
		// my index for the display
		int index;
		// loops through the list 
		for( index = 0; index < super.getCurrentSize() ; index++ ) 
		{
			// as long as the index equals the current index minus one
			if( index == currentIndex - 1 ) 
			{
				
				System.out.print( " "+ SPACE + LEFT_BRACKET +
						getAtIndex( index ) + RIGHT_BRACKET );
				
			}
			else 
			{
				System.out.print( " "+ SPACE + getAtIndex( index ) );
			}
		}
		// prints new line
		System.out.println();
		
	}
	
	/**
	 *Sets value to location after current index
	 *
	 *@param newValue integer value to be inserted in list
	 *
	 *@return Boolean result of operation
	 */
	public boolean setAfterCurrent( int newValue )
	{
		// boolean "flag" setAfter is set to false
		boolean setAfter = false;
		//calls the basic linked list class's set at index with flag
		// insert after
		setAfter = super.setAtIndex( currentIndex, newValue, INSERT_AFTER );
		
		return setAfter;
		
	}
	
	/**
	 *Sets value to location before current index
	 *
	 *@param newValue integer value to be inserted into list
	 *
	 *@return Boolean result of operation
	 */
	public boolean setBeforeCurrent( int newValue )
	{
		// boolean "flag" setBefore is set to false
		boolean setBefore = false;
		//calls the basic linked list class's set at index with flag
		// insert before to set before current
		setBefore = super.setAtIndex( currentIndex, newValue, INSERT_BEFORE );
		
		currentIndex++;
		
		return setBefore;
		
	}
	
	/**
	 *Sets iterator cursor to beginning of existing list
	 *<p>
	 *Note: Empty list does not exist
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean setToBeginning()
	{
		// sets the index to 0 to be at the start of the list
		return currentIndex == 0;
		
	}
	
	/**
	 *Sets iterator cursor to the end of existing list
	 *
	 *Note: Empty list does not exist
	 *
	 *@return Boolean result of action; true if successful,
	 *false otherwise
	 */
	public boolean setToEnd()
	{
		// sets the index at the end of the list using the current size of the
		// list
		return currentIndex == super.getCurrentSize();
		
	}
	

}