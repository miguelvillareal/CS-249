/**
 * QueueClass that uses BasicLinkedListClass as data structure
 * 
 * CS 249 
 * 
 * DUE: 3 - 12 - 19
 * 
 * @author Miguel Villareal
 */
package p6_package;


public class QueueClass
{
	// queue class has a basic linked list class to use
	/**
	 * blank
	 */
	private BasicLinkedListClass queueData;
	
	/**
	 * Stack default constructor
	 */
	public QueueClass() 
	{
		// setting queue data to have a basic linked list class
		queueData = new BasicLinkedListClass();
		
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param copied QueueClass object to be copied
	 */
	public QueueClass( QueueClass copied ) 
	{
		// copying over that data
		queueData = copied.queueData;
		
	}
	
	/**
	 *Clears queue
	 *
	 */
	public void clear() 
	{
		//uses basic linked list class's clear
		queueData.clear();
			
	}
	
	/**
	 *Displays queue as comma-delimited list
	 *
	 */
	public void displayQueue()
	{
		int itemCount;
		// displays the queue tail "start"
		System.out.print( "queue tail-->" );
		
		for( itemCount = 0; itemCount < queueData.getCurrentSize();
				itemCount++ ) 
		{
			System.out.print( queueData.getAtIndex( itemCount ) );
			// comma delimited list
			System.out.print( ", " );
			
		}
		// displays the queue head "end"
		System.out.print( "<--queue head\n" );
	}
	
	/**
	 *Enqueues value
	 *
	 *@param newValue - Value to be enqueued
	 */
	public void enqueue( int newValue )
	{
		// uses basic linked list class's set at index with "flag"
		// insert before
		queueData.setAtIndex( queueData.getCurrentSize(), newValue,
				BasicLinkedListClass.INSERT_BEFORE );
	}
	
	/**
	 *Removes and returns value from front of queue
	 *
	 *@return integer value if successful, FAILED_ACCESS if not
	 */
	public int dequeue()
	{
		// index to be removed
		int indexRemoved;
		// sets that index using the basic linked list class's remove at index
		indexRemoved = queueData.removeAtIndex( queueData.getCurrentSize() - 1);
		// returns that removed index
		return indexRemoved;
	}
	
	/**
	 *Reports queue empty state
	 *
	 *@return Boolean evidence of empty list
	 */
	public boolean isEmpty()
	{
		// uses basic linked list class's empty method
		return queueData.isEmpty();
		
	}
	
	/**
	 *provides peek at front of queue
	 *
	 *@return integer value if successful, FAILED_ACCESS if not
	 */
	public int peekFront()
	{
		// gets the head of the list using basic linked list class's
		// get at index 
		return queueData.getAtIndex( queueData.getCurrentSize() - 1 );
		
	}
}