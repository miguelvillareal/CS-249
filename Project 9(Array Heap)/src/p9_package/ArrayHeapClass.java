package p9_package;

/**
 * Array-based max heap class that manages students
 * 
 * CS 249 
 * 
 * DUE: 4-09-19
 * 
 * @author Miguel Villareal
 *
 */
public class ArrayHeapClass
{
	/**
     * Management data for array
     */
     private int arrayCapacity;
       
    /**
     * Management data for array
     */
     private int arraySize;
       
    /**
     * Initial array capacity
     */
     public int DEFAULT_ARRAY_CAPACITY = 10;
       
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
     private boolean displayFlag;
       
    /**
     * Array for heap
     */
     private StudentClass[] heapArray;
     
    /**
     * Default constructor sets up array 
     * management conditions and default display flag setting
     */
     public ArrayHeapClass()
     {
    	 //initializes any not final fields
     	 arraySize = 0;
     	 
     	 arrayCapacity = DEFAULT_ARRAY_CAPACITY;
     	 
     	 displayFlag = false;
     	 
     	 heapArray = new StudentClass[ arrayCapacity ];
     }

    /**
     * Copy constructor copies array and array 
     * management conditions and default display flag setting
     */
     public ArrayHeapClass( ArrayHeapClass copied )
     {
    	 // copys the fields
    	 this.arraySize = copied.arraySize;
			
		 this.arrayCapacity = copied.arrayCapacity;
		 
		 this.displayFlag = copied.displayFlag;
			
		 heapArray = new StudentClass[ arrayCapacity ];
			
		 int index;
		 // for loop that copies the array
		 for ( index = 0; index < arraySize ; index++ ) 
		 {
				
			 this.heapArray[ index ] = copied.heapArray[ index ];
				
		 }
     }
       
    /**
     * Accepts StudentClass item and adds it to heap
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve 
     * unbalanced heap after data addition
     * <p>
     * Note: Always checks for resize before adding data
     * 
     * @param newItem StudentClass item to be added
     */
     public void addItem( StudentClass newItem )
     {
    	 // checks for array resize
       	 checkForResize();
       	 //makes the last node equal to the new item
       	 heapArray[ arraySize ] = newItem;
         // checks the display flag then does whatever 
		 // printing that is needed
       	 if( displayFlag ) 
       	 {
       		 System.out.println( "Adding new student: " + newItem );
       		 
       		 System.out.println();
       	 }
       	 // calls bubble up with the last item
       	 bubbleUpArrayHeap( arraySize );
       	 // increment array size
       	 arraySize++;
     }
       
    /**
     * Recursive operation to reset data in the correct 
     * order for the max heap after new data addition
     * 
     * @param currentIndex index of current item being assessed, 
     * and moved up as needed
     */
     private void bubbleUpArrayHeap( int currentIndex )
     {
    	 // checks if the index is greater than 0
    	 if( currentIndex > 0 ) 
    	 {
    		 // makes a parentIndex
    		 int parentIndex = ( currentIndex - 1 ) / 2;
    		 // makes a parent Node
    		 StudentClass parNode = heapArray[ parentIndex ];
    		 // makes a Child Node
    		 StudentClass childNode = heapArray[ currentIndex ];
    		 // if the child is greater than the parent
    		 if( childNode.compareTo( parNode ) > 0 ) 
    		 {
    			 // checks the display flag then does whatever 
    			 // printing that is needed
    			 if( displayFlag ) 
        		 {
        			 System.out.println("  -Bubble up: ");
        			 
        			 System.out.println("     -Swapping parent: " 
        			 + parNode.toString() + "with child: " 
        			 + childNode.toString() );
        			 System.out.println();
        		 }
    			 // sets the value at the parentIndex equal to child Node
    			 heapArray[ parentIndex ] = heapArray[currentIndex];
    			 // sets the child Node equal to parent Node
    			 heapArray[currentIndex] = parNode;
    			 //calls bubble up with the parentIndex 
    			 bubbleUpArrayHeap( parentIndex );
    		 }
    	 }
     }
        
    /**
     * Automatic resize operation used prior to any 
     * new data addition in the heap
     * <p>
     * Note: Tests for full heap array, and resizes to 
     * twice the current capacity as required
     */
     private void checkForResize()
     {
    	 // creating a temp array
    	 StudentClass[] temporaryArray;
		 // make a int to hold double capacity
    	 int doubleCapacity;
		 // the index
		 int index;
		 // if the array has reached capacity
		 if( arraySize == arrayCapacity ) 
		 {
			 // set the new array to double the capacity
			 doubleCapacity = 2 * arrayCapacity;
				
			 temporaryArray = new StudentClass[ doubleCapacity ];
			 // goes through aray
			 for(index = 0; index < arraySize; index++) 
			 {
				 temporaryArray[ index ] = heapArray[ index ];
					
			 }
			 // sets the array capacity to double
			 arrayCapacity = doubleCapacity;
			 // makes our array equal the temp array
			 heapArray = temporaryArray;
				
		 }
     }
         
    /**
     * Tests for empty heap
     *
     * @return boolean result of test
     */
     public boolean isEmpty()
     {
    	 // sets Array size equal to zero
    	 return arraySize == 0;
     }
          
    /**
     * Removes StudentClass item from top of max heap
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve 
     * unbalanced heap after data removal
     * 
     * @return StudentClass item Removed
     */
     public StudentClass removeItem()
     {
    	 // checks if array is empty
    	 if( !isEmpty() ) 
    	 {
    		 // makes a node for the removed item
    		 StudentClass removedNode = heapArray[ 0 ] ;
    		 // sets the first index equal to the end of the array
    		 heapArray[ 0 ] = heapArray[ arraySize - 1 ];
    		 // decrements array size
    		 arraySize--;
    		 // checks the display flag then does whatever 
    		 // printing that is needed
    		 if( displayFlag ) 
           	 {
           		 System.out.println( "Removing student: " + removedNode.name );
           	 }
    		 // calls trickleDown with index 0
    		 trickleDownArrayHeap( 0 );
    		 // returns the removed node
    		 return removedNode;
    	 }
    	 // else returns null
         return null; 	 
     }
           
    /**
     * Utility method to set the display flag for displaying internal 
     * operations of the heap bubble and trickle operations
     *
     * @param setState flag used to set the state to display, or not
     */
     public void setDisplayFlag( boolean setState )
     {
    	 // sets the flag equal to the boolean input
    	 displayFlag = setState;
     }
           
    /**
     * Dumps array to screen as is, no filtering or management
     */
     public void showArray()
     {
    	 // the index in the array
    	 int index;
    	 // for loop that goes through the array
    	 for( index = 0; index < arraySize; index++ ) 
    	 {
    		 // prints out the array
    		 System.out.print( heapArray[ index ].toString() );
    		 
    	 }
     }
     
    /**
     * Recursive operation to reset data in the correct 
     * order for the max heap after data removal
     * 
     * @param currentIndex index of current item being assessed, 
     * and moved down as required
     */
     private void trickleDownArrayHeap( int currentIndex )
     {
    	 // if the left child is less than arraySize
    	 if ( ( currentIndex * 2 ) + 1 < arraySize  )
         {
    		 //makes child's index
    		 int childIndex = ( currentIndex * 2 ) + 1;
        	 // makes right child's index
        	 int rightIndex = childIndex + 1;
        	 // string for left child or right child
        	 String left_rightChild;
             // set the string to left child
             left_rightChild = "left child: ";
             
             // if right child's index id less than arrays size 
             // and is bigger than left child
             if( rightIndex < arraySize && 
            		 heapArray[ childIndex + 1 ].compareTo(
            				 heapArray[ childIndex ] ) > 0 ) 
             {
            	 // set string to right child
            	 left_rightChild = "right child: ";
            	 // set the childs index equal to the right child
            	  childIndex++;
            	 
             }
             // make a parent Node
             StudentClass parentNode = heapArray[ currentIndex ];
             // parent node is less than the child
             if( parentNode.compareTo( heapArray[childIndex] ) < 0) 
             {
            	 // make a child Node
            	 StudentClass childNode = heapArray[ childIndex ];
            	 
            	 // checks the display flag then does whatever 
        		 // printing that is needed
            	 if( displayFlag ) 
            	 {
            		 System.out.println("  -Trickle Down: ");
        			 
        			 System.out.println("     -Swapping parent: " 
        			 + parentNode.toString() + "with " + left_rightChild
        			 + childNode.toString() );
        			 
        			 System.out.println();
            	 }
            	 // sets the value and childs index to the current
            	 heapArray[ childIndex ] = heapArray[ currentIndex ];
            	 // sets current equal to the child node
            	 heapArray[ currentIndex ] = childNode;
            	 // calls trickle down with the childrens index
            	 trickleDownArrayHeap( childIndex );
             }

         }
     }  	 
}