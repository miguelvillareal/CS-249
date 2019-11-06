package p8_package;
/**
 * Binary Search Tree (BST) class with self-balancing attributes
 * specifically using the Red/Black Tree (RBT) strategy
 * 
 * CS 249 
 * 
 * DUE: 4-02-19
 * 
 * @author Miguel Villareal
 *
 */
public class RB_TreeClass
{
	/**
     * Binary Search Tree node class for managing
     * generic data
     *
     */
	private class RBT_Node
    {
		/**
         * Character node data
         */
         private char nodeData;
           
        /**
         * Color for node
         */
         private int nodeColor;
           
        /**
         * Parent reference for node
         */
         private RBT_Node parentRef;
           
        /**
         * Left child reference for node
         */
         private RBT_Node leftChildRef;
           
        /**
         * Right child reference for node
         */
         private RBT_Node rightChildRef;

        /**
         * Initialization constructor for data
         * 
         * @param inData char quantity
         */
         public RBT_Node( char inData )
         {
        	 nodeData = inData;
               
             nodeColor = COLOR_RED;
               
             parentRef = leftChildRef = rightChildRef = null;
         }

         /**
          * copy constructor for RBT_Node
          * 
          * @param copied RBT_Node object to be duplicated
          */
          public RBT_Node( RBT_Node copied )
          {
        	  nodeData = copied.nodeData;
               
              nodeColor = copied.nodeColor;
              
              parentRef = leftChildRef = rightChildRef = null;
          }         

      }
	  /**
       * Root of RBTree
       */
      private RBT_Node RBTree_Root;
      
      /**
       * Constant comma break for printing traversals
       */
      public final String slashBreak = "/";
      
      /**
       * Constant comma break for printing traversals
       */
      public final String semicolonBreak = "; ";
      
      /**
       * Null character returned if data not available
       */
      public final char NULL_CHAR = '\n';
      
      /**
       * Color black constant
       */
      private final int COLOR_BLACK = 66;
      
      /** 
       * Color red constant
       */
      private final int COLOR_RED = 82;
      
      /**
       * Provides user choice to display node color
       * in BST Tree display
       */
      public static final int NODE_COLOR = 191;
      
      /**
       * Provides user choice to display node data
       * in BST Tree display
       */
      public static final int NODE_DATA = 192;
      
      /**
       * Variable provides user-selected choice between output
       * of data or color, initialized to NODE_DATA
       */
      private int treeCharDisplayCode;

      /**
       * Traverse code - preorder
       */
      public static final int PREORDER_TRAVERSE = 101;
      
      /**
       * Traverse code - inorder
       */
      public static final int INORDER_TRAVERSE = 102;
      
      /**
       * Traverse code - postorder
       */
      public static final int POSTORDER_TRAVERSE = 103;
      
      /**
       * Class global variable used to display tree structure
       */
      private boolean rowStartFlag;
      
      /**
       * Constant used to represent space
       * 
       */
      private static final char SPACE = ' ';
      
      /**
       * Constant used to represent dash
       * 
       */
      private static final char DASH = '-';
            
      /**
       * Default class constructor, no initialization actions
       */
      public RB_TreeClass()
      {
          RBTree_Root = null;
          
          treeCharDisplayCode = NODE_DATA;
          
          rowStartFlag = false;
       }
      
      /**
       * Class copy constructor
       * <p>
       * Note: Uses copyConstructorHelper
       * 
       * @param copied reference to RB_TreeClass object 
       */
      public RB_TreeClass( RB_TreeClass copied )
      {
          treeCharDisplayCode = copied.treeCharDisplayCode;
          
          rowStartFlag = copied.rowStartFlag;
          
          RBTree_Root = copyConstructorHelper( copied.RBTree_Root );
      }

      /** 
       * Implements tree duplication effort with recursive method;
       * copies data into newly created nodes and creates all new
       * references to child nodes
       * <p>
       * Note: Uses pre order traversal strategy
       * 
       * @param workingCopiedRef RBT_Node reference that is updated
       * to lower level children with each recursive call
       * 
       * @return RBT_Node reference that links to next higher level node;
       * last return is to root node of THIS object
       */
      private RBT_Node copyConstructorHelper( RBT_Node workingCopiedRef )
      {
    	  //Make a Root node
    	  RBT_Node localRoot = null;
    	  // if the copying ref does not equal null
    	  if( workingCopiedRef != null ) 
    	  {
    		  // pass in the data into local Root
    		  localRoot = new RBT_Node( workingCopiedRef );
    		  
    		  localRoot.leftChildRef = copyConstructorHelper( 
    				  workingCopiedRef.leftChildRef );
    		  //check for the roots right and left child
        	  if( localRoot.leftChildRef != null ) 
        	  {
        		  localRoot.leftChildRef.parentRef = localRoot;
        	  }
        	  
        	  localRoot.rightChildRef = copyConstructorHelper( 
    				  workingCopiedRef.rightChildRef );
        	  
        	  if( localRoot.rightChildRef != null ) 
        	  {
        		  localRoot.rightChildRef.parentRef = localRoot;
        	  }
    	  }

          return localRoot;  // temporary stub return
      }

      /**
       * Local recursive method to display a specified number
       * of a specified character
       * 
       * @param numChars number of characters to display
       * 
       * @param outChar character to display
       */
      private void displayChars( int numChars, char outChar )
      {
          if( numChars > 0 )
          {
              System.out.print( outChar );
              
              displayChars( numChars - 1, outChar );
          }
      }

      /**
       * Method that displays null or blank nodes for a tree at null locations
       * <p>
       * Note: used by displayAtTreeLevel
       * 
       * @param nodeHeight height of tree plus two
       * for current height of nodes, including lowermost null children
       * 
       * @param displayLevel level of the tree at which
       * the display will be applied
       * 
       * @param workingLevel level of tree just below
       * non-null node at which method is currently working
       */
      private void displayEmptyNodeSpaces( int nodeHeight, 
                                          int displayLevel, int workingLevel )
      {
          int nodesToDisplay = toPower( 2, displayLevel - workingLevel ); 
          char charOut = SPACE;
          
          if( displayLevel == workingLevel )
          {
              charOut = DASH;
              
              if( treeCharDisplayCode == NODE_COLOR )
              {
                  charOut = 'B';
              }
          }
          
          while( nodesToDisplay > 0 )
          {
              displayValue( charOut, nodeHeight, displayLevel );
              
              nodesToDisplay--;
          }
      }
      
      /**
       * Provides inOrder traversal action
       * 
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       */
      private void displayInOrder( RBT_Node localRoot )
      {
          if( localRoot != null )
          {
              displayInOrder( localRoot.leftChildRef );
              
              if( !rowStartFlag )
              {
                  System.out.print( semicolonBreak );
              }

              rowStartFlag = false;

              System.out.print( localRoot.nodeData + slashBreak 
                                               + (char)localRoot.nodeColor );
              displayInOrder( localRoot.rightChildRef );
             }
       }
      
      /**
       * Provides postOrder traversal action
       * 
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       */
      private void displayPostOrder( RBT_Node localRoot )
      {
          if( localRoot != null )
          {
              displayPostOrder( localRoot.leftChildRef );
              displayPostOrder( localRoot.rightChildRef );

              if( !rowStartFlag )
              {
                  System.out.print( semicolonBreak );
              }

              rowStartFlag = false;

              System.out.print( localRoot.nodeData + slashBreak 
                                               + (char)localRoot.nodeColor );
          }
      }
      
      /**
       * Provides preOrder traversal action
       * 
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       */
      private void displayPreOrder( RBT_Node localRoot )
      {
          if( localRoot != null )
          {
              if( !rowStartFlag )
              {
                  System.out.print( semicolonBreak );
              }

              rowStartFlag = false;

              System.out.print( localRoot.nodeData + slashBreak 
                                               + (char)localRoot.nodeColor );
              displayPreOrder( localRoot.leftChildRef );
              displayPreOrder( localRoot.rightChildRef );
          }
      }
      
      /**
       * Displays text-graphical representation of one level/line
       * of the RBT tree
       * <p>
       * Note: choice of color or letter data is made with 
       * setTreeDisplayCharacter
       * 
       * @param workingNode node reference at current recursive level
       * 
       * @param nodeHeight height of tree plus two
       * for current height of nodes, including lowermost null children
       * 
       * @param displayLevel level of tree at which the current line
       * of display is to be presented
       * 
       * @param workingLevel current level during recursive actions
       */
      private void displayAtTreeLevel( RBT_Node workingNode, int nodeHeight, 
                                         int displayLevel, int workingLevel )
      {
          char charOut = workingNode.nodeData;
          
          if( treeCharDisplayCode == NODE_COLOR )
          {
              charOut = (char)workingNode.nodeColor;      
          }
          
          if( workingLevel == displayLevel )
          {
              displayValue( charOut, nodeHeight, workingLevel );

              return;
          }
          
          if( workingNode.leftChildRef != null )
          {
              displayAtTreeLevel( workingNode.leftChildRef, nodeHeight,
                                            displayLevel, workingLevel + 1 );
          }
          
          else
          {
              displayEmptyNodeSpaces( nodeHeight, 
                                            displayLevel, workingLevel + 1 );
          }
              
          if( workingNode.rightChildRef != null )
          {
              displayAtTreeLevel( workingNode.rightChildRef, nodeHeight,
                          displayLevel, workingLevel + 1 );
          }

          else
          {
              displayEmptyNodeSpaces( nodeHeight, 
                                            displayLevel, workingLevel + 1 );
          }              
      }
      
      /**
       * Provides user with three ways to display RBTree data
       * 
       * @param traverseCode integer code for selecting RBTree traversal method,
       * accepts PREORDER_TRAVERSE, INORDER_TRAVERSE, POSTORDER_TRAVERSE
       */
      public void displayTree( int traverseCode )
      {
          rowStartFlag = true;
          
          switch( traverseCode )
          {
                case PREORDER_TRAVERSE:
                   displayPreOrder( RBTree_Root );
                   break;
                   
                case POSTORDER_TRAVERSE:
                   displayPostOrder( RBTree_Root );
                   break;
                   
                default:
                   displayInOrder( RBTree_Root );
                   break;
          }
          
          System.out.println();
      }
      
      /**
       * Displays text-graphical representation of RBT tree
       *  
       */
      public void displayTreeStructure()
      {
          int displayLevel, nodeHeight = findTreeHeight() + 2;
          int workingLevel = 1;
          
          if( RBTree_Root != null )
          {
              for( displayLevel = 1; 
                                 displayLevel <= nodeHeight; displayLevel++ )
              {
                  rowStartFlag = true;
                  
                  displayAtTreeLevel( RBTree_Root, nodeHeight, 
                                               displayLevel, workingLevel );
                  
                  System.out.println();
              }
          }
          
          else
          {
              System.out.println( "\nEmpty Tree - No Display");
          }
      }
      
      /**
       * Method used to display a character or color letter
       * along with calculated leading spaces
       * <p>
       * Note: used in displayAtTreeLevel and displayEmptyNodeSpaces
       * 
       * @param data character value to display, either letter or color data
       * 
       * @param nodeHeight integer height of tree plus two
       * for current height of nodes, including lowermost null children
       * 
       * @param workingLevel integer current level during recursive actions
       */
      private void displayValue( char data, int nodeHeight, int workingLevel )
      {
          int leadingSpaces;
          
          if( rowStartFlag )
          {
              leadingSpaces = toPower( 2, nodeHeight - workingLevel );

              rowStartFlag = false;
          }
          
          else
          {
              leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 ) - 1;
          }

          displayChars( leadingSpaces, SPACE );
          
          System.out.print( data );         
      }
      
      /**
       * Finds height of tree using helper method
       * 
       * @return height of tree
       */
      public int findTreeHeight()
      {
          return findTreeHeightHelper( RBTree_Root );
      }
      
      /**
       * Recursive tree height helper
       * <p>
       * Note: Empty tree height is -1; single root node tree height is 0
       * 
       * @param localRoot RBT_Node reference at current recursive level
       * 
       * @return integer height at a given level
       */
      private int findTreeHeightHelper( RBT_Node localRoot )
      {
          if( localRoot != null )
          {
              return getMax( 
                   findTreeHeightHelper( localRoot.leftChildRef ), 
                       findTreeHeightHelper( localRoot.rightChildRef ) ) + 1;
          }
          
          return -1;
      }
      
      /**
       * Finds maximum of two given numbers
       * 
       * @param one integer value of one of the values to be tested
       * 
       * @param other integer value of the other of the two values to be tested
       * 
       * @return integer greater of the two values
       */
      private int getMax( int one, int other )
      {
          int max = one;
          
          if( other > max )
          {
              max = other;
          }
          
          return max;
      }
      
      /**
       * Test for empty tree
       * 
       * @return Boolean result of test
       */
      public boolean isEmpty()
      {
          return RBTree_Root == null;
      }

      /** Insert method for RBTree
       * <p>
       * Note: uses insert helper method which returns
       * the inserted node reference to this method
       * <p>
       * Note: After value has been inserted as a Red node,
       * this method calls the resolveRBT_Issues method
       * to rebalance and/or recolor the tree
       * 
       * @param inData char data to be added to RBTree
       *  
       * @return Boolean result of insertion action
       */
      public boolean insert( char inData )
      {
          System.out.println( "\nInserting " + inData + " and resolving");                 
          
          RBT_Node insertedNode = insertHelper( RBTree_Root, inData );
          
          if( insertedNode != null )
          {
              resolveRBT_Issues( insertedNode );
              
              return true;
          }
          
          return false;
      }
      
      /**
       * Insert helper method for RBTree insert action
       * <p>
       * Note: Does not accept duplicate values
       *  
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       * 
       * @param inData char item to be added to RBTree
       * 
       * @return RBT_Node reference of inserted node
       */
      private RBT_Node insertHelper( RBT_Node localRoot, char inData )
      {
          int compareResult;
          
          if( RBTree_Root == null )
          {
              RBTree_Root = new RBT_Node( inData );
              
              RBTree_Root.parentRef = null;
              
              return RBTree_Root; 
          }
          
          compareResult = localRoot.nodeData - inData;
          
          if( compareResult > 0 )
          {
              if( localRoot.leftChildRef == null )
              {
                  localRoot.leftChildRef = new RBT_Node( inData );
              
                  localRoot.leftChildRef.parentRef = localRoot;
                  
                  return localRoot.leftChildRef;
              }
              
              return insertHelper( localRoot.leftChildRef, inData );
          }
          
          else if( compareResult < 0 )
          {
              if( localRoot.rightChildRef == null )
              {
                  localRoot.rightChildRef = new RBT_Node( inData );
                  
                  localRoot.rightChildRef.parentRef = localRoot;
                  
                  return localRoot.rightChildRef;
              }
              
              return insertHelper( localRoot.rightChildRef, inData );
          }
          
          return null;  // doesn't allow duplicate entries      
      }
      
      /**
       * Recursively rebalances and recolors tree as needed
       * <p>
       * Note: Indicates actions being taken by displaying them
       * 
       * @param localRef RBT_Node reference for the local tree being managed
       */
      private void resolveRBT_Issues( RBT_Node localRef )
      {
    	  //make a node for the grandparent, parent, and uncle
    	  RBT_Node grandParentRef;
    	  
    	  RBT_Node uncleRef;
    	  
    	  RBT_Node parentRef;
    	  
    	  //check if you have a parent and grandparent
    	  if( localRef.parentRef != null &&
    			  localRef.parentRef.parentRef != null ) 
    	  {
    		  //set the parent and grandparent
    		  grandParentRef = localRef.parentRef.parentRef;
    		  
    		  parentRef = localRef.parentRef;
    		  
    		  //check for uncle and where uncle is
    		  if( grandParentRef.rightChildRef == parentRef ) 
    		  {
    			  uncleRef = grandParentRef.leftChildRef;
    		  }
    		  else 
    		  {
    			  uncleRef = grandParentRef.rightChildRef;
    		  }
    		  //if parent is red
    		  if( parentRef.nodeColor == COLOR_RED ) 
    		  {
    			  System.out.println( "   - Parent is red" );
    			  
    			  if( uncleRef != null && uncleRef.nodeColor == COLOR_RED ) 
    			  {
    				  System.out.println( "   - Uncle is red" );
    				  
    				  parentRef.nodeColor = COLOR_BLACK;
    				  
    				  System.out.println( "       - Parent set to black" );
    				  
    				  uncleRef.nodeColor = COLOR_BLACK;
    				  
    				  System.out.println( "       - Uncle set to black" );
    				  
    				  grandParentRef.nodeColor = COLOR_RED;
    				  
    				  System.out.println( "       - Grandparent set to red" );
    				  
    				  System.out.println( "       - Resolving Grandparent" );
    				  
    				  resolveRBT_Issues( grandParentRef );
    				  
    				  RBTree_Root.nodeColor = COLOR_BLACK;
    			  }
    			  //uncle is black instead of red
    			  else 
        		  {
    				  System.out.println( "   - Uncle is black" );
    				  
    				  //Check for cases
        			  if( parentRef == grandParentRef.leftChildRef ) 
        			  {
        				  //Left Left Case
        				  if( localRef == parentRef.leftChildRef ) 
        				  {
        					  System.out.println( "   - Left Left Case" );
        					  
        					  System.out.println( "      - Rotate Right" );
        					  
        					  rotateRight(localRef, parentRef, grandParentRef );
        					  
        					  System.out.println( "      - Rotating right "
        					  		+ "from left side of grandparent" );
        					  
        					  System.out.println( "      - Swap "
        					  		+ "grandparent/parent colors" );
        					  
        					  swapColors( parentRef, grandParentRef );
        					  
        					  RBTree_Root.nodeColor = COLOR_BLACK;
        				  }
        				  //Left Right Case
        				  else if( localRef == parentRef.rightChildRef ) 
        				  {
        					  System.out.println( "   - Left Right Case" );
        					  
        					  rotateLeft(localRef, parentRef, grandParentRef );
        					  
        					  resolveRBT_Issues( localRef.leftChildRef);
        					  
        					  RBTree_Root.nodeColor = COLOR_BLACK;
        				  }
        			  }
        			  if( parentRef == grandParentRef.rightChildRef ) 
        			  {
        				  //Right Right Case
        				  if( localRef == parentRef.rightChildRef ) 
        				  {
        					  System.out.println( "   - Right Right Case" );
        					  
        					  System.out.println( "       - Rotate Left" );
        					  
        					  System.out.println( "       - Rotating "
        					  		+ "left from right side of grandparent" );
        					  
        					  rotateLeft(localRef, parentRef, grandParentRef );
        					  
        					  System.out.println( "       - Swap "
        					  		+ "grandparent/parent colors" );
        					  
        					  swapColors( parentRef, grandParentRef );
        					  
        					  RBTree_Root.nodeColor = COLOR_BLACK;
        				  }
        				  //Right Left Case
        				  else if( localRef == parentRef.leftChildRef ) 
        				  {
        					  System.out.println( "   - Right Left Case" );
        					  
        					  rotateRight(localRef, parentRef, grandParentRef );
        					  
        					  resolveRBT_Issues( localRef.rightChildRef);
        					  
        					  RBTree_Root.nodeColor = COLOR_BLACK;
        				  }
        			  }
        		  }
    			  
    		  }
    		  
    		  
    	  }
    	  //If you are the root set color to black
    	  if( localRef == RBTree_Root ) 
    	  {
    		  localRef.nodeColor = COLOR_BLACK;
    		  
    		  System.out.println( "   - Root color set to black" );
    	  }
      }
      
      /**
       * Rotates local tree left or CCW
       * 
       * @param localRef reference of current item
       * 
       * @param parRef reference of current item parent
       * 
       * @param grndParRef reference of current item grandparent
       */
      private void rotateLeft( RBT_Node localRef, 
                                  RBT_Node parRef, RBT_Node grndParRef )
      {
    	  //make Great grand parent node
    	  RBT_Node greatGrndParRef = grndParRef.parentRef;
    	  //Check if parent is the right child of the grandparent
    	  if( parRef == grndParRef.rightChildRef ) 
    	  {
    		  if( parRef.leftChildRef != null ) 
    		  {
    			  grndParRef.rightChildRef = parRef.leftChildRef;
    			  
    			  parRef.leftChildRef.parentRef = grndParRef;
    		  }
    		  else 
    		  {
    			  grndParRef.rightChildRef = null;
    		  }
    		  //If greatGrandParent doesnt equal null
    		  if( greatGrndParRef != null ) 
    		  {
    			  if( greatGrndParRef.leftChildRef == grndParRef ) 
    			  {
    				  greatGrndParRef.leftChildRef = parRef;
    				  
    				  parRef.parentRef = greatGrndParRef;
    			  }
    			  if( greatGrndParRef.rightChildRef == grndParRef ) 
    			  {
    				  greatGrndParRef.rightChildRef = parRef;
    				  
    				  parRef.parentRef = greatGrndParRef;
    			  }
    		  }
    		  //else set root to the parent
    		  else 
    		  {
    			  RBTree_Root = parRef;
    			  
    			  parRef.parentRef = null;
    		  }
    		  parRef.leftChildRef = grndParRef;
    		  
    		  grndParRef.parentRef = parRef;
    	  }
    	  // set the nodes to their correct positions
    	  else
    	  {
    		  parRef.rightChildRef = localRef.leftChildRef;
    		  
    		  localRef.leftChildRef = parRef;
    		  
    		  parRef.parentRef = localRef;
    		  
    		  localRef.parentRef = grndParRef;
    		  
    	  }
      }

      /**
       * Rotates local tree right or CW   
       * 
       * @param localRef reference of current item
       * 
       * @param parRef reference of current item parent
       * 
       * @param grndParRef reference of current item grandparent
       */
      private void rotateRight( RBT_Node localRef, 
                                  RBT_Node parRef, RBT_Node grndParRef )
      {
    	  //make Great grand parent node
    	  RBT_Node greatGrndParRef = grndParRef.parentRef;
    	  //Check if parent is the left child of the grandparent
    	  if( parRef == grndParRef.leftChildRef ) 
    	  {
    		  if( parRef.rightChildRef != null ) 
    		  {
    			  grndParRef.leftChildRef = parRef.rightChildRef;
    			  
    			  parRef.rightChildRef.parentRef = grndParRef;
    		  }
    		  else 
    		  {
    			  grndParRef.leftChildRef = null;
    		  }
    		  //If greatGrandParent doesnt equal null
    		  if( greatGrndParRef != null ) 
    		  {
    			  if( greatGrndParRef.leftChildRef == grndParRef ) 
    			  {
    				  greatGrndParRef.leftChildRef = parRef;
    				  
    				  parRef.parentRef = greatGrndParRef;
    			  }
    			  if( greatGrndParRef.rightChildRef == grndParRef ) 
    			  {
    				  greatGrndParRef.rightChildRef = parRef;
    				  
    				  parRef.parentRef = greatGrndParRef;
    			  }
    		  }
    		  //else set root to the parent
    		  else 
    		  {
    			  RBTree_Root = parRef;
    			  
    			  parRef.parentRef = null;
    		  }
    		  parRef.rightChildRef = grndParRef;
    		  
    		  grndParRef.parentRef = parRef;
    	  }
    	  // set the nodes to their correct positions
    	  else 
    	  {
    		  parRef.leftChildRef = localRef.rightChildRef;
    		  
    		  localRef.rightChildRef = parRef;
    		  
    		  parRef.parentRef = localRef;
    		  
    		  localRef.parentRef = grndParRef;
    		  
    	  }
      }
      
      /** 
       * Searches for data in RBTree given char with necessary key
       * 
       * @param searchData char item containing key
       * 
       * @return char found data
       */
      public char search( char searchData )
      {
          return searchHelper( RBTree_Root, searchData );
      }
      
      /**
       * Helper method for RBTree search action
       * 
       * @param localRoot RBT_Node tree root reference 
       * at the current recursion level
       * 
       * @param searchData char item containing key
       * 
       * @return Boolean result of search
       */
      private char searchHelper( RBT_Node localRoot, char searchData )
      {
          int compareResult;
          
          if( localRoot != null )
          {
              compareResult = localRoot.nodeData - searchData;
              
              if( compareResult > 0 )
              {
                  return searchHelper( localRoot.leftChildRef, searchData );
              }
              
              else if( compareResult < 0 )
              {
                  return searchHelper( localRoot.rightChildRef, searchData );
              }
              
              return localRoot.nodeData;
          }
                    
          return NULL_CHAR;
      }
      
      /**
       * Allows user to set tree display character
       * 
       * @param displayCode specifies whether to set tree
       * to display data character (NODE_DATA) 
       * or color letter (NODE_COLOR)
       */
      public void setTreeDisplayCharacter( int displayCode )
      {
          treeCharDisplayCode = displayCode;          
      }
      
      /**
       * Swaps colors between two RBT nodes
       * 
       * @param one one of the RBT nodes
       * 
       * @param other other of the RBT nodes
       */
      public void swapColors( RBT_Node one, RBT_Node other )
      {
          int tempColor = one.nodeColor;
          
          one.nodeColor = other.nodeColor;
          
          other.nodeColor = tempColor;
      }

      /**
       * Local recursive method to calculate exponentiation
       * with integers
       * 
       * @param base base of exponentiation
       * 
       * @param exponent exponent of exponentiation
       * 
       * @return result of exponentiation calculation
       */
      private int toPower( int base, int exponent )
      {
          if( exponent > 0 )
          {
              return toPower( base, exponent - 1 ) * base;
          }
          
          return 1;
      }
}