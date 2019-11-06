/**
 * Binary Search Tree (BST) class for managing generic data
 * <p>
 * Note: Data used must have implemented Comparable interface
 * 
 * CS 249 
 * 
 * DUE: 3-26-19
 * 
 * @author Miguel Villareal
 */
package p7_package;

public class Generic_BST_Class<GenericData 
extends java.lang.Comparable<GenericData>>
{
	/**
	 * Root of BST
	 */
	private BST_Node BST_Root;
	
	/**
	 * Binary Search Tree node class for managing generic data
	 */
	private class BST_Node
	{
		private GenericData nodeData;

		BST_Node leftChildRef;
		
		BST_Node rightChildRef;
		
		/**
		 * Initialization constructor for data
		 * 
		 * @param inData GenericData quantity
		 */
		public BST_Node( GenericData inData ) 
		{
			// initializes what is given
			nodeData = inData;
			
			leftChildRef = null;
			
			rightChildRef = null;
		}
		
	}
	
	/**
	 * Default class constructor, initializes BST
	 */
	public Generic_BST_Class() 
	{
		// initializes BST Root to null
		BST_Root = null;
	}
	
	/**
	 * Clears tree
	 */
	public void clearTree() 
	{
		// clears tree
		BST_Root = null;
	}
	/**
	 * Provides inOrder traversal action
	 * <p>
	 * Note: Calls displayInOrderHelper
	 */
	public void displayInOrder() 
	{
		displayInOrderHelper( BST_Root );
	}
	
	/**
	 * Provides inOrder traversal action using recursion
	 * 
	 * @param localRoot BST_Node tree root reference at the 
	 * current recursion level
	 */
	private void displayInOrderHelper( BST_Node localRoot ) 
	{
		// as long as the root is not null
		if( localRoot != null ) 
		{
			// display left child first then right child
			displayInOrderHelper( localRoot.leftChildRef );
			
			System.out.println( localRoot.nodeData.toString() );
			
			displayInOrderHelper( localRoot.rightChildRef );
		}
	}
	
	/**
	 * Provides PostOrder traversal action
	 * <p>
	 * Note: Calls displayPostOrderHelper
	 */
	public void displayPostOrder()
	{
		displayPostOrderHelper( BST_Root );
	}
	
	/**
	 * Provides PostOrder traversal action using recursion
	 * 
	 * @param localRoot BST_Node tree root reference at the 
	 * current recursion level
	 */
	private void displayPostOrderHelper( BST_Node localRoot ) 
	{
		// as long as the root is not null
		if( localRoot != null ) 
		{
			displayPostOrderHelper( localRoot.leftChildRef );
			
			displayPostOrderHelper( localRoot.rightChildRef );
			
			System.out.println( localRoot.nodeData.toString() );
		}
	}
	
	/**
	 * Provides preOrder traversal action
	 * <p>
	 * Note: Calls displayPreOrderHelper
	 */
	public void displayPreOrder()
	{
		displayPreOrderHelper( BST_Root );
	}
	
	/**
	 * Provides preOrder traversal action using recursion
	 * 
	 * @param localRoot BST_Node tree root reference at the 
	 * current recursion level
	 */
	private void displayPreOrderHelper( BST_Node localRoot ) 
	{
		// as long as the root is not null
		if( localRoot != null ) 
		{
			System.out.println( localRoot.nodeData.toString() );
			
			displayInOrderHelper( localRoot.leftChildRef );
			
			displayInOrderHelper( localRoot.rightChildRef );
		}
	}
	
	/**
	 * Insert method for BST
	 * <p>
	 * Note: uses insert helper method which returns root reference
	 * <p>
	 * Note:uses search to verify that key is not already in tree; if key
	 * is already in tree, insert is not conducted
	 * 
	 * @param inData GenericData data to be added to BST
	 */
	public void insert( GenericData inData )
	{
		//checks to see if the key is in the tree
		if( search( inData ) == null )
		{
			insertHelper( BST_Root, inData );
		}
	}
	
	/**
	 * Insert helper method for BST insert action
	 * <p>
	 * Note: Recursive method returns updated local root to
	 * maintain tree linkage
	 * 
	 * @param localRoot BST_Node tree root reference at the current 
	 * recursion level
	 * 
	 * @param inData GenericData data to be added to BST
	 * 
	 * @return BST_Node reference used to maintain tree linkage
	 */
	private BST_Node insertHelper( BST_Node localRoot, GenericData inData )
	{
		if( !isEmpty() ) 
		{
			if( localRoot.nodeData.compareTo( inData )<0 )
			{
				if( localRoot.rightChildRef != null ) 
				{
					localRoot.rightChildRef = 
							insertHelper( localRoot.rightChildRef, inData );
				}
				else 
				{
					localRoot.rightChildRef = new BST_Node( inData );
				}
				
			}
			else 
			{
				if( localRoot.leftChildRef != null ) 
				{
					localRoot.leftChildRef = 
							insertHelper(localRoot.leftChildRef, inData );
				}
				else 
				{
					localRoot.leftChildRef = new BST_Node( inData );
				}
			}
		}
		else 
		{
			BST_Root = new BST_Node( inData );
		}
		// return the updated local root
		return localRoot;
	}
	
	/**
	 * Test for empty tree
	 *
	 * @return Boolean result of test
	 */
	public boolean isEmpty()
	{
		// sets root to null so theirs nothing below it
		return BST_Root == null;
	}
	
	/**
	 * Searches tree from given node to maximum value node below it, 
	 * stores data value found, and then unlinks the node
	 * 
	 * @param maxParent BST_Node reference to current node
	 * 
	 * @param maxLoc BST_Node reference to child node to be tested
	 * 
	 * @return BST_Node reference containing removed node
	 */
	private BST_Node removeFromMax( BST_Node maxParent, BST_Node maxLoc )
	{
		//as long as the right child doesnt equal null
		if( maxLoc.rightChildRef != null ) 
		{
			//you keep going down the right children
			return removeFromMax( maxLoc, maxLoc.rightChildRef );
		}
		BST_Node myTemp = maxLoc;
		// once its null it sets that last right child equal to the parents
		// right child	
		maxParent.rightChildRef = maxLoc.leftChildRef;
		
		return myTemp;
	}
	
	/**
	 * Removes data node from tree using given key
	 * <p>
	 * Note: uses remove helper method
	 * <p>
	 * Note:uses search initially to get value, if it is in tree; if value 
	 * found, remove helper method is called, otherwise returns false
	 * 
	 * @param inData GenericData that includes the necessary key
	 * 
	 * @return GenericData result of remove action
	 */
	public GenericData removeItem( GenericData inData )
	{
		GenericData tempVar = search( inData );
		//checks to see if the key is in the tree
		if( tempVar != null ) 
		{
			BST_Root = removeItemHelper( BST_Root, inData);
			
		}
		return tempVar;
	}
	
	/**
	 * Remove helper for BST remove action
	 * <p>
	 * Note: Recursive method returns updated local root to 
	 * maintain tree linkage
	 * <p>
	 * Note:uses removeFromMax method
	 * 
	 * @param localRoot BST_Node tree root reference at 
	 * the current recursion level
	 * 
	 * @param outData GenericData item that includes the necessary key
	 * 
	 * @return BST_Node reference result of remove helper action
	 */
	private BST_Node removeItemHelper( BST_Node localRoot, 
			GenericData outData )
	{
		if( localRoot == null ) 
		{
			return null;
		}
		//checks if test item is less than local
		if( localRoot.nodeData.compareTo( outData )>0 ) 
		{
			localRoot.leftChildRef = 
					removeItemHelper( localRoot.leftChildRef, outData );
		}
		//checks if test item is greater than local
		else if( localRoot.nodeData.compareTo( outData )<0 ) 
		{
			localRoot.rightChildRef = 
					removeItemHelper( localRoot.rightChildRef, outData );
		}
		//checks for no left child
		else if( localRoot.leftChildRef == null ) 
		{
			localRoot = localRoot.rightChildRef;
			
		}
		//checks for no right child
		else if( localRoot.rightChildRef == null ) 
		{
			localRoot = localRoot.leftChildRef;
			
		}
		else 
		{
			//checks for left child with no right child
			if(localRoot.leftChildRef.rightChildRef == null )
			{
				//assigns the data from left child to the removed node
				localRoot.leftChildRef.nodeData = outData;
				//assign my left child to leftchild's leftchild
				localRoot.leftChildRef = 
						localRoot.leftChildRef.leftChildRef;
			}
			else 
			{
				//calls method remove from max
				BST_Node newTemp = removeFromMax(localRoot,
						localRoot.leftChildRef);
				
				localRoot.nodeData = newTemp.nodeData;
			}
			
		}
		
		return localRoot;
	}
	
	/**
	 * Searches for data in BST given GenericData with necessary key
	 *
	 * @param searchData GenericData item containing key
	 *
	 * @return GenericData reference to found data
	 */
	public GenericData search( GenericData searchData )
	{
		return  searchHelper( BST_Root, searchData );
	}
	
	/**
	 * Helper method for BST search action
	 *
	 * @param localRoot BST_Node tree root reference at the current 
	 * recursion level
	 *
	 * @param searchData GenericData item containing key
	 *
	 * @return GenericData item found
	 */
	private GenericData searchHelper( BST_Node localRoot,
            GenericData searchData )
	{
		// checks if the root is equal to null
		if ( localRoot == null ) 
		{
			return null;
		}
		//checks if the two are equal to each other
		if( localRoot.nodeData.compareTo( searchData ) == 0 ) 
		{
			return localRoot.nodeData;
			
		}
		//checks if the local is less than the one passed in
		if( localRoot.nodeData.compareTo( searchData )< 0 ) 
		{
			return searchHelper( localRoot.rightChildRef, searchData );
			
		}
		else
		{
			return searchHelper(localRoot.leftChildRef, searchData);
		}
		
		
	}
	
}