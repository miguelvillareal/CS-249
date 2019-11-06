/**
 * BasicLinkedListClass Class wrapper for a Java linked list,
 * with management operations
 * 
 * CS 249 
 * 
 * DUE: 3-12-19
 * 
 * @author Miguel Villareal
 */
package p6_package;

public class BasicLinkedListClass
{
		// Node Class used for the creation of nodes
		private class NodeClass
		{
			NodeClass nextRef;
		
			int nodeData;
			// initialization constructor for the node class
			private NodeClass( int newData )
			{
				nodeData = newData;
				
				nextRef = null;
			
			}
			//Copy constructor for the node class
			private  NodeClass( NodeClass copied )
			{
				nextRef = copied.nextRef;
				
				nodeData = copied.nodeData;
			}
		}
		//All the constants that were provided to us.
		
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
		 * Member - linked list head reference
		 */
		private NodeClass headRef;
	
		/**
		 * Default constructor, initializes linked to default capacity
		 * 
		 */
		protected BasicLinkedListClass()
		{
			//initializing the fields that were given
			headRef = null;
			
		}
		
		/**
		 * Copy constructor, initializes linked list, then copies all nodes to
		 * local(this) linked list
		 * 
		 * @param copied BasicLinkedListClass object to be copied
		 */
		protected BasicLinkedListClass( BasicLinkedListClass copied )
		{
			// copying the fields that were given
			headRef = new NodeClass( copied.headRef );
			
			NodeClass copyRef = copied.headRef;
			
			NodeClass localRef = headRef;
			// creates a copy of the list
			while( copyRef.nextRef != null ) 
			{
				NodeClass newRef = new NodeClass( copyRef.nextRef );
				
				copyRef = copyRef.nextRef;
				
				localRef.nextRef = newRef;
				
				localRef = localRef.nextRef;
				
			}
		}
		
		/**
		 * Utility method used by getAtIndex and removeAtIndex to access and
		 * possibly remove element depending on control code
		 *  <p>
		 * Note: Data is managed with virtual index found by getRefAtIndex
		 *  <p>
		 * Note: Uses only one loop
		 * 
		 * @param controlCode integer value with either RETRIEVE or REMOVE
		 *  to control operations
		 *  
		 * @param index virtual index of element to be retrieved or removed
		 *  
		 *  @return integer value at element or FAILED_ACCESS if attempt to 
		 *  access data out of bounds
		 */
		private int accessAtIndex( int controlCode,
								  int index )
		{
			// index node
			NodeClass indexRef = getRefAtIndex( headRef, index );
			// the node that is before the index node
			NodeClass prevIndexRef = getRefAtIndex( headRef, index - 1 );
			// our integer that should be returned in the method
			int valueReturned;
			
			// control code is retrieve then run
			if( controlCode == RETRIEVE ) 
			{
				//As long as the next index is not null it returns 
				//that node data
				if( indexRef != null ) 
				{
					
					return indexRef.nodeData;
					
				}
				else 
				{
					return FAILED_ACCESS;
				}
			}
			// control code is remove then run
			else if( controlCode == REMOVE ) 
			{
				// set the int to that value from that node
				valueReturned = indexRef.nodeData;
				// point the previous node to the node after the one that was
				// removed
				prevIndexRef.nextRef = indexRef.nextRef;
				
				return valueReturned;
			}
			
			return FAILED_ACCESS;
			
		}
		
		/**
		 * Clears linked list of all valid values by setting linked list
		 * head reference to null
		 * 
		 */
		protected void clear()
		{
			headRef = null;
		}
		
		/**
		 * Accesses item in linked list at specified virtual index if its 
		 * within linked list limits
		 * <p>
		 * Note: Linked list value specified by virtual index is returned 
		 * to calling program
		 * <p>
		 * Note: Calls accessAtIndex with RETRIEVE to conduct action
		 * 
		 * @param accessIndex integer virtual index of requested element value
		 *  
		 * @return integer accessed value if successful, FAILED_ACCESS if not
		 */
		protected int getAtIndex(int accessIndex)
		{
			//calls access at index to run
			return accessAtIndex( RETRIEVE, accessIndex ); 
			
		}
		
		/**
		 * Gets virtual index of last item in linked list
		 * <p>
		 * Note: Uses getCurrentSizeHelper
		 * <p>
		 * Note: Handles empty list prior to calling helper
		 * 
		 * @return integer virtual index
		 */
		protected int getCurrentSize()
		{
			//if list is empty then the size would be zero
			if( isEmpty() ) 
			{
				return 0;
				
			}
			// else it would return the product of get size helper method
			return getCurrentSizeHelper( headRef );
			
		}
		
		/**
		 * Uses recursion to find virtual linked list size
		 * 
		 * @param wkgRef NodeClass current reference in recursion, initially
		 * called with head reference
		 * 
		 * @return integer linked list size
		 */
		private int getCurrentSizeHelper( NodeClass wkgRef )
		{
			//while if the next node is null then there is only one item
			if( wkgRef.nextRef == null )
			{
				return 1;
				
			}
			// else it counts how many items there are
			return 1 + getCurrentSizeHelper( wkgRef.nextRef );
			
		}
		
		/**
		 * Private recursive method that finds a node represented by a
		 * virtual index
		 * <p>
		 * Note: if requested index is less than zero or outside linked list
		 * boundary, returns null
		 * 
		 * @param wkgRef NodeClass reference that initially called with the
		 * head reference, and is used for recursive operations
		 * 
		 * @param requestedIndex integer value representing virtual index
		 * requested by the user

		 * 
		 * @return NodeClass reference to the element at the virtual index
		 */
		private NodeClass getRefAtIndex( NodeClass wkgRef,
				int requestedIndex )
		{
			//check for in bounds and if it is empty
			if( requestedIndex < 0 && requestedIndex > getCurrentSize() || isEmpty()) 
			{
				return null;
			}
			// if it equals the 0 index then it is the headRef
			if( requestedIndex == 0 ) 
			{
				return headRef = wkgRef;
			}
			
			
			return getRefAtIndex( wkgRef.nextRef, requestedIndex - 1 );
			
		}
		
		/**
		 * Tests for empty linked list
		 * 
		 * @return Boolean result of test for empty
		 */
		protected boolean isEmpty()
		{
			return headRef == null;
			
		}
		
		/**
		 * Description: Removes item from linked list at specified 
		 * virtual index if index within linked list size bounds
		 * <p>
		 * Note: Linked list node specified by virtual index is 
		 * removed from list
		 * <p>
		 * Note: Calls accessAtIndex with REMOVE to conduct action
		 * 
		 * @param removeIndex integer index of element value to be removed
		 *  
		 * @return removed integer value if successful, FAILED_ACCESS if not
		 */
		protected int removeAtIndex(int removeIndex)
		{
			// calls access at index to run
			return accessAtIndex( REMOVE, removeIndex );
		}
		
		/**
		 * Displays formatted list with virtual indices
		 * 
		 * @param showIndex Boolean value turns on display of index under
		 * value if set to true, otherwise only shows pipe delimited values
		 */
		protected void runDiagnosticDisplay( boolean showIndex )
		{
			// my pointer node in the method
			NodeClass pointRef = headRef;
			// my moving index
			int wkgMover;
			// index in the method
			int index = 0;
			
			System.out.print("List: | ");
			// while my pointer node is not null then it goes into loop
			while(pointRef != null) 
			{
				// if the index is not = to 0 then it adds the dash 
				if( index != 0) 
				{
					System.out.print('|');
					
				}
				// formats the data received and prints it
				System.out.format("%3d", pointRef.nodeData);
				// move onto the next node
				pointRef = pointRef.nextRef;
				// increment my index 
				index++;
			}
			// print new line
			System.out.println();
			// if the index received is a good index to use
			if( showIndex == true )
			{
				// print the index row
				System.out.print("Index: | ");
				// loops through the index's
				for(wkgMover = 0; wkgMover < index; wkgMover++) 
				{
					System.out.format("%5d", wkgMover);
				}
			}
			
			System.out.println();
			
		}
			
		/**
		 * Description: sets item in linked list at specified virtual index
		 * <p>
		 * Note: If constant REPLACE is used, new value overwrites value at 
		 * current virtual index
		 * <p>
		 * Note: If constant INSERT_BEFORE is used, new value is inserted prior 
		 * to the value at the current virtual index.
		 * <p>
		 * Note: If constant INSERT_AFTER is used, new value is inserted after 
		 * the value at the current virtual index
		 * <p>
		 * Note: Method must check for correct virtual array boundaries; if 
		 * index requested is below zero or above list size - 1, method must 
		 * take no action and return false
		 * <p>
		 * Note: Method must check for correct replace flag; if it is not one 
		 * of the three specified flags, it must take no action and 
		 * return false
		 * 
		 * @param setIndex integer index of element at which value is to be 
		 * replaced, or value is to be inserted before or after
		 * 
		 * @param newValue integer value to be placed in linked list
		 * 
		 * @param replaceFlag integer flag to indicate insertion or 
		 * replacement in the linked list
		 *  
		 * @return Boolean success if inserted, or failure if incorrect 
		 * index or replace flag was used
		 */
		protected boolean setAtIndex( int setIndex,
				int newValue, 
				int replaceFlag ) 
		{
			// My boolean flag that will be returned either true or false
			boolean boolReturn = false;
			// my wkg node 
			NodeClass wkgRef = getRefAtIndex( headRef, setIndex );
			// my index node
			NodeClass indexRef = getRefAtIndex( wkgRef, setIndex );
			// the node that comes before my index node
			NodeClass prevIndexRef = getRefAtIndex( wkgRef, setIndex - 1 );
			// replace flag must be replace to execute
			if( replaceFlag == REPLACE ) 
			{
				indexRef.nodeData = newValue;
				
				boolReturn = true;
			}
			// replace flag must be insert_before to execute
			else if( replaceFlag == INSERT_BEFORE ) 
			{
				//As long as there's a node prior to the current index then run
				if( prevIndexRef != null ) 
				{
					prevIndexRef.nextRef = new NodeClass( newValue );
					// return true
					boolReturn = true;
				}
				// the node is at the beginning of the list then run
				else 
				{
					// set wkg node equal to the head
					wkgRef = headRef;
					// then place the value to be inserted in the headref
					headRef = new NodeClass( newValue );
					
					headRef.nextRef = wkgRef;
					//return true
					boolReturn = true;
				}
				
			}
			// replace flag must be insert_after to execute
			else if( replaceFlag == INSERT_AFTER ) 
			{
					// set the index's next node to that value
					indexRef.nextRef.nodeData = newValue;
					// return true
					boolReturn = true;

			}
			// return whichever bool return is passed in
			return boolReturn;
			
		}
}