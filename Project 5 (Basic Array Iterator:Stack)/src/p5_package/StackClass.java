/**
 * StackClass uses peek, push, and pops the array given
 * CS 249 
 * DUE: 2-26-19
 * @author Miguel Villareal
 */
package p5_package;


public class StackClass
{
	/**
	 * blank
	 */
	private BasicArrayClass stackData;
	
	/**
	 * Stack default constructor
	 */
	public StackClass() 
	{
		//uses the basic array class default
		stackData = new BasicArrayClass();
		
	}
	
	/**
	 * Stack initialization constructor
	 * 
	 * @param capacitySetting integer value for setting initial 
	 * capacity of array
	 */
	public StackClass( int capacitySetting ) 
	{
		stackData = new BasicArrayClass(capacitySetting);
		
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param copied StackClass object to be copied
	 */
	public StackClass( StackClass copied ) 
	{
		stackData = copied.stackData;
		
	}
	
	/**
	 *Clears stack
	 *
	 */
	public void clear() 
	{
			stackData.clear();
			
	}
	
	/**
	 *Displays stack
	 *
	 */
	public void displayStack()
	{
		int itemCount;
		//bottom of stack
		System.out.print( "stack bottom-->" );
		
		for( itemCount = 0; itemCount < stackData.getCurrentSize();
				itemCount++ ) 
		{
			System.out.print( stackData.getAtIndex( itemCount ) );
			
			System.out.print( ", " );
			
		}
		// top of stack
		System.out.print( "<--stack top\n" );
	}
	
	/**
	 *Reports stack empty status
	 *
	 *@return Boolean evidence of empty list
	 */
	public boolean isEmpty()
	{
		return stackData.isEmpty();
		
	}
	
	/**
	 *provides peek at top of stack
	 *
	 *@return value if successful, FAILED_ACCESS if not
	 */
	public int peekTop()
	{
		// looks at the top of the stack provided
		return stackData.getAtIndex( stackData.getCurrentSize() - 1 );
		
	}
	
	/**
	 *Removes and returns data from top of stack
	 *
	 *@return value if successful, FAILED_ACCESS if not
	 */
	public int pop()
	{
		//removes from top of stack provided
		return stackData.removeAtIndex( stackData.getCurrentSize() - 1 );
		
	}
	
	/**
	 *Places data item on top of the stack
	 *
	 *@param newVal integer value to pushed onto stack
	 */
	public void push(int newVal)
	{
		
		stackData.setAtIndex( stackData.getCurrentSize(), newVal,
				stackData.INSERT_BEFORE );
		
	}
	
}