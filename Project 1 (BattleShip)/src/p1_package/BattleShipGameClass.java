/*
 * 
 *  
 */
package p1_package;

/**
 * BattleShip Game Class 
 * Used in BattleShip Main class to correctly run the game.
 * @author miguelvillareal
 */
public class BattleShipGameClass 
extends java.lang.Object
{  /**
    * constant aircraft carrier letter
    */
    public static final char AIRCRAFT_CARRIER = 'A';
    
   /**
    * constant aircraft carrier length
    */
    private final int AIRCRAFT_CARRIER_LENGTH = 5;
    
   /**
    * height of array
    */
    private int arrayHeight;
    
   /**
    * width of array
    */
    private int arrayWidth;
    
   /**
    * constant battleship letter
    */
    public static final char BATTLESHIP = 'B';
    
   /**
    * constant battleship length
    */
    private final int BATTLESHIP_LENGTH = 4;
    
   /**
    * constant value to identify computer player
    */
    public static final int COMPUTER = 103;
    
   /**
    * character array for computer playing field
    */
    private ShipClass[][] computerArray;
    
   /**
    * constant cruiser letter
    */
    public static final char CRUISER = 'C';
    
   /**
    * constant cruiser length
    */
    private final int CRUISER_LENGTH = 3;
    
   /**
    * constant destroyer letter
    */
    public static final char DESTROYER = 'D';
    
   /**
    * constant destroyer length
    */
    private final int DESTROYER_LENGTH = 2;
    
   /**
    * constant frigate letter
    */
    public static final char FRIGATE = 'F';
    
   /**
    * constant frigate length
    */
    private final int FRIGATE_LENGTH = 1;
    
   /**
    * constant horizontal letter
    */
    public static final char HORIZONTAL = 'H';
    
   /**
    * constant value to identify human player
    */
    public static final int HUMAN = 102;
    
   /**
    * character array for human playing field
    */
    private ShipClass[][] humanArray;
    
   /**
    * constant space character
    */
    private final char SPACE = ' ';
    
   /**
    * constant vertical letter
    */
    public static final char VERTICAL = 'V';
    
   /**
    * Configures BattleShip playing board with height and width 
    * Initializes array of ShipClass objects
    * 
    * @param height integer value for number of rows in array
    * 
    * @param width integer value for number of columns in array
    */
    public BattleShipGameClass( int height,
                                int width )
    {
        int rowLocation;
        
        int columnLocation;
        
        arrayHeight = height;
        
        arrayWidth = width;
        
        humanArray = new ShipClass[ arrayHeight ][ arrayWidth ];
        
        computerArray = new ShipClass[ arrayHeight ][ arrayWidth ];
        
        for( rowLocation = 0; rowLocation < arrayHeight; rowLocation++ )
        {
            
            for( columnLocation = 0; columnLocation < arrayWidth;
                    columnLocation++ )
            {
                humanArray[ columnLocation ][ rowLocation ] = new ShipClass();
                
                computerArray[columnLocation][rowLocation] = new ShipClass();
            }
            
        }
        
    }
    
    /**
    * Creates copy of other BattleShip object
    * 
    * @param copied BattleShip object to be copied
    */
    public BattleShipGameClass( BattleShipGameClass copied )
    {
        int rowLocation;
        
        int columnLocation;
        
        arrayHeight = copied.arrayHeight;
        
        arrayWidth = copied.arrayWidth;
        
        for( rowLocation = 0; rowLocation < arrayHeight; rowLocation++ )
        {
            
            for( columnLocation = 0; columnLocation < arrayWidth;
                    columnLocation++ )
            {
                humanArray[ arrayHeight ][ arrayWidth ] = new ShipClass( 
                copied.humanArray[ arrayHeight ][ arrayWidth ] );
                
                computerArray[ arrayHeight ][ arrayWidth ] = new ShipClass( 
                        copied.computerArray[ arrayHeight ][ arrayWidth ] );
            }
            
        }
        
    }

   /**
    * Verifies that a correct ship letter has been entered
    * 
    * @param testLetter character letter representing a ship, 
    * letter is compared to all possible ship letters
    * 
    * @return Boolean result of ship letter test
    */
    private boolean checkShipLetter( char testLetter )
    {
        return testLetter == 'A' || testLetter == 'B' || testLetter == 'C'
                || testLetter == 'D' || testLetter == 'F';
    }
    
   /**
    * Computer generates random values within the field and 
    * fires missile using fireMissile method
    * 
    * @return success of missile fire
    */
    public boolean computerFiresMissile()
    {
    	int xPos;
    	int yPos;
    	
    	do 
    	{
    		xPos = getRandBetween( 0, arrayWidth - 1 );
    		
    		yPos = getRandBetween( 0, arrayHeight - 1 );
    	}
    	while(isMissInArray(HUMAN, xPos, yPos)); 
       return fireMissile( COMPUTER, xPos, yPos );
    }
    
   /**
    * Displays player and computer screens
    * 
    * @param displayState integer indication as to showing fields, 
    * EXPOSED_STATE only shows exposed items, 
    * HIDDEN_STATE shows all hidden items
    */
    public void displayFields( int displayState )
    {
        int columnLocation;
        
        int rowLocation;
        
        System.out.print("\n");
        
        printCentered( "Human", arrayWidth );
        
        System.out.print("\t");
        
        printCentered( "Computer", arrayWidth );
        
        System.out.println("");
        
        for( rowLocation = 0; rowLocation < arrayHeight; rowLocation++ )
        {
            
            for( columnLocation = 0; columnLocation < arrayWidth;
                    columnLocation++ )
            {
                System.out.print( humanArray[ columnLocation ][ rowLocation ]
                                 .getLocationLetter( displayState ));
            }
            
            System.out.print("\t");
            
            for( columnLocation = 0; columnLocation < arrayWidth;
                    columnLocation++ )
            {
                System.out.print(computerArray[ columnLocation ][ rowLocation ]
                                 .getLocationLetter(displayState));
            }
            
            System.out.println();
        }
        System.out.println();
    }
    
   /**
    * Accepts player (human/computer) and fires missile at location,
    * if location had a ship, updates ShipClass object to 
    * show ship letter and returns true, 
    * otherwise returns false
    * 
    * @param player integer value identifying human or computer 
    * who is firing the missile 
    * the missile will be fired at the other player's array
    * 
    * @param xPos integer x position missile landing location
    * 
    * @param yPos integer y position missile landing location
    * 
    * @return Boolean result of ship hit
    */
    public boolean fireMissile( int player, int xPos, int yPos )
    {
        boolean hitsShip = false;
        
        if( player == HUMAN )
        {
            hitsShip = isMissInArray( COMPUTER, xPos, yPos );
            if( hitsShip )
            {
               computerArray[xPos][yPos].updateLocationState(
                       ShipClass.EXPOSED_STATE, ShipClass.MISSED_LETTER );
            }
            
            else
            {
                computerArray[xPos][yPos].updateLocationState(
                        ShipClass.EXPOSED_STATE );
            }
            
        }
        
        else if( player == COMPUTER )
        {
            hitsShip = isMissInArray( HUMAN, xPos, yPos );
            if( hitsShip )
            {
               humanArray[xPos][yPos].updateLocationState(
                       ShipClass.EXPOSED_STATE, ShipClass.MISSED_LETTER );
            }
            
            else
            {
                humanArray[xPos][yPos].updateLocationState(
                        ShipClass.EXPOSED_STATE );
            }
            
        }
        
        return !hitsShip;
        
    }
    
   /**
    * Creates random value between a low and high value inclusive
    * 
    * @param low integer low limit of random value
    * 
    * @param high integer high limit of random value
    * 
    * @return integer random value as specified
    */
    private int getRandBetween( int low, int high )
    {
    	int result, range, randNumber;
    	
    	range = (high- low) + 1;
    	
    	randNumber = (int)(Math.random() * 100000);
    	
    	result = randNumber % range + low;
    	
        return result;
    }
    
   /**
    * Finds score of requested player (human or computer)
    * 
    * @param player constant HUMAN or COMPUTER
    * 
    * @return score found from player
    */
    public int getScore( int player )
    {
        int score = 0;
        
        int rowLocation = 0;
        
        int columnLocation = 0;
        
        ShipClass workingArray[][];
        
        if(player == HUMAN)
        {
            workingArray = computerArray;
        }
        
        else
        {
            workingArray = humanArray; 
        }
        
            for( rowLocation = 0; rowLocation < arrayHeight; rowLocation++)
            {
                for( columnLocation = 0; columnLocation < arrayWidth;
                        columnLocation++)
                {
                    if( (workingArray[columnLocation]
                            [rowLocation].locationState 
                            == ShipClass.EXPOSED_STATE) && 
                            (checkShipLetter(workingArray[columnLocation]
                                    [rowLocation].shipLetter)))
                    {
                        score++;
                        
                    }
                    
                }
                
            }
            
        return score;
    }
    
   /**
    * Finds ship length from letter given
    * 
    * @param typeOfShip character letter used to find ship length
    * 
    * @return integer length of ship
    */
    private int getShipLength( char typeOfShip )
    {
    	int shipLength = 0;
    	
        switch( typeOfShip )
        {
            case AIRCRAFT_CARRIER:
            	shipLength = AIRCRAFT_CARRIER_LENGTH;
                break;
               
            case BATTLESHIP:
            	shipLength = BATTLESHIP_LENGTH;
                break;
                
            case CRUISER:
            	shipLength = CRUISER_LENGTH;
                break;
                
            case DESTROYER:
            	shipLength = DESTROYER_LENGTH;
                break;
                
            case FRIGATE:
            	shipLength = FRIGATE_LENGTH;
                break;    
        }
        
        return shipLength;
         
    }
    
   /**
    * Finds ship name from letter given
    * 
    * @param typeOfShip character letter used to find ship name
    * 
    * @return String type of ship
    */
    public String getShipName( char typeOfShip )
    {
        String shipName = " ";
        
        switch( typeOfShip )
        {
            case AIRCRAFT_CARRIER:
                shipName = "AIRCRAFT_CARRIER";
                break;
               
            case BATTLESHIP:
                shipName = "BATTLESHIP";
                break;
                
            case CRUISER:
                shipName = "CRUISER";
                break;
                
            case DESTROYER:
                shipName = "DESTROYER";
                break;
                
            case FRIGATE:
                shipName = "FRIGATE";
                break;    
        }
        
        return shipName;
    }
    
   /**
    * Reports true if a miss is found in the given array
    * 
    * @param player integer value identifying which array to look in
    * 
    * @param xPos integer x position to test for miss character
    * 
    * @param yPos integer y position to test for miss character
    * 
    * @return Boolean result of specified test
    */
    private boolean isMissInArray( int player, 
                                   int xPos, 
                                   int yPos )
    {
    	char testChar;
       if( player == HUMAN )
       {
    	   testChar = humanArray[xPos][yPos].getLocationLetter(ShipClass.EXPOSED_STATE);
           return testChar == ShipClass.MISSED_LETTER;
       }
       
       else
       {
    	   testChar = computerArray[xPos][yPos].getLocationLetter(ShipClass.EXPOSED_STATE);
           return testChar == ShipClass.MISSED_LETTER;
       }
    }
    
   /**
    * Verifies that a ship will fit on the board and that no other ship 
    * is occupying the proposed space,then places ship and returns true,
    * otherwise returns false
    * 
    * @param shipType character value representing ship; 
    * letter must be verified as upper case 
    * and/or converted to upper case
    * 
    * @param player integer constant indicating which player
    * ship to place
    * 
    * @param xPos integer x position to place bow of ship
    * 
    * @param yPos integer y position to place bow of ship
    * 
    * @param orientation character 'H' for horizontal or 'V' 
    * for vertical;
    * letter must be verified as upper case and/or converted 
    * to upper case
    * 
    * @return Boolean result of attempt to place ship
    */
    public boolean placeShip( char shipType, 
                              int player, 
                              int xPos, 
                              int yPos, 
                              char orientation )
    {
        char shipLetter = toUpper(shipType);
        ShipClass[][] cloneArray;
        char shipOrient = toUpper(orientation);
        int row;
        int column;
        
        if(checkShipLetter(shipType) && xPos >= 0 && yPos >= 0)
        {
        	 if ( player == HUMAN )
             {
                 cloneArray = humanArray;
             }
             else
             {
                 cloneArray = computerArray;
             }
             if ( shipOrient == VERTICAL && yPos <= arrayHeight - getShipLength( shipLetter ) )
             {
                 for ( row = yPos; row < getShipLength( shipLetter ) + yPos; row++ )
                 {
                     if ( cloneArray[ row ][ xPos ].getLocationLetter( ShipClass.HIDDEN_STATE ) != ShipClass.BACKGROUND_LETTER ) // ship exists= false

                     {
                         return false;
                     }
                 }
                 for ( row = yPos; row < getShipLength( shipLetter ) + yPos; row++ )

                 {
                     cloneArray[ row ][ xPos ].updateLocationState( ShipClass.HIDDEN_STATE, shipLetter );
                 }
                 return true;
             }
             if ( shipOrient == HORIZONTAL && xPos <= arrayWidth - getShipLength( shipLetter ) )
             {
                 for ( column = xPos; column < getShipLength( shipLetter ) + xPos; column++ )
                 {
                     if ( cloneArray[ yPos ][ column ].getLocationLetter
                    ( ShipClass.HIDDEN_STATE ) != ShipClass.BACKGROUND_LETTER )
                     {
                         return false;
                     }
                 }
                 for ( column = xPos; column < getShipLength( shipLetter ) + xPos; column++ )
                 {
                     cloneArray[ xPos ][ column ].updateLocationState
                     ( ShipClass.HIDDEN_STATE, shipLetter );
                 }
                 return true;
             }
         }
         return false;
        
    }
    
   /**
    * Prints given string centered within the block size it is given
    * 
    * @param toPrint String object to be printed
    * 
    * @param blockSize integer size within which the string is 
    * to be centered
    */
    private void printCentered( String toPrint,
                                int blockSize )
    {
        int blocksize = arrayWidth;
        
        int stringInput = toPrint.length();
        
        int theSpacesNeeded = ( blocksize - stringInput )/2;
        
        printChars( theSpacesNeeded, SPACE );
        
        System.out.print( toPrint );
        
        printChars( theSpacesNeeded, SPACE );
        
        
    }
    
   /**
    * Recursively prints a given number of a specified character;
    * used in printCentered for printing spaces
    * 
    * @param numChars integer number of characters to be printed
    * 
    * @param outChars character value to be repeatedly printed
    */
    private void printChars( int numChars,
                             char outChar )
    {
        if( numChars > 0 )
        {
            System.out.print( outChar );
            
            numChars--;
            
            printChars( numChars, outChar );
        }
        
    }
    
   /**
    * Sets the computer ships randomly given the number of ships to set
    * 
    * @param numShips integer number of ships to set
    */
    public void setComputerShips( int numShips )
    {
        char[] shipLetters = { 'A', 'B', 'C', 'D', 'F' };
        
        char[] shipOrientations = { 'V', 'H' };
        
        int shipCount = 0;
        
        for( shipCount = 0; shipCount < numShips; shipCount++)
        {
            
            int shipLetterLocation = getRandBetween( 0, 4 );
            
            char shipLetter = shipLetters[ shipLetterLocation ];
            
            int shipOrientationLocation = getRandBetween( 0, 1 );
            
            char shipOrientation = shipOrientations[ shipOrientationLocation];
            
            int xPos = getRandBetween( 0, arrayWidth );
            
            int yPos = getRandBetween( 0, arrayHeight );
            
            placeShip( shipLetter, COMPUTER,
                    xPos, yPos, shipOrientation );
            
        }
        
    }
    
   /**
    * If lower case letter is provided to method it is 
    * converted to upper case otherwise no action is taken
    * 
    * @param testLetter character letter to be converted as specified
    * 
    * @return character letter, converted as specified
    */
    private char toUpper( char testLetter )
    {
        
       if( testLetter >= 'a' && testLetter <= 'z' )
       {
           testLetter = ( char )( testLetter - 'a' + 'A' );
       }
       
       return testLetter;
    }
  
}