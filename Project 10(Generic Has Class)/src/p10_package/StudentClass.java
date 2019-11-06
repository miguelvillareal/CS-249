package p10_package;

/**
 * Class for managing student data
 * 
 * CS 249 
 * 
 * DUE: 4-16-19
 * 
 * @author Miguel Villareal
 *
 */
public class StudentClass

implements Comparable<StudentClass>
{
	/**
     * Constant for identifying comma
     */
	 private final char COMMA = 44;
       
    /**
     * Gender data for class
     */
	 public char gender;
       
    /**
     * GPA data for class
     */
	 public double gpa;
	 
	/**
	 * Name data for class
	 */
     public String name;
       
    /**
     * StudentID data for class
     */
	 public int studentID;
     
    /**
     * Initialization constructor for data
     * <p>
     * Note: Class does not require a default constructor
     * 
     * @param inName name of student to be input into object
     * 
     * @param inStudentID ID number of student to be input into object
     * 
     * @param inGender gender of student to be input into object
     * 
     * @param inGPA gpa of student to be input into object
     */
	 public StudentClass( String inName,
             int inStudentID,
             char inGender,
             double inGPA )
     {
    	 //initializes all not final fields
     	 gender = inGender;
     	 
     	 name = inName;
     	 
     	 studentID = inStudentID;
     	 
     	 gpa = inGPA;
     }

    /**
     * Copy constructor
     * 
     * Calls other constructor with copied data
     * 
     * @param copied StudentClass object to be copied
     */
     public StudentClass( StudentClass copied )
     {
    	  // copys the fields
     	  gender = copied.gender;
     	  
     	  name = copied.name;
     	  
     	  studentID = copied.studentID;
     	  
     	  gpa = copied.gpa;
     }
       
    /**
     * Compares student objects
     * <p>
     * Note: Compares name as class key; returns integer result such that 
     * if this name is less than other name, a negative number is returned; 
     * if this name is greater than other name, a positive number is returned; 
     * if this name is equal to, and the same length as other name, 
     * zero is returned
     * 
     * @specified compareTo in interface java.lang.Comparable<StudentClass>
     * 
     * @param other StudentClass object to be compared with this object
     * 
     * @return integer difference between the names
     */
     public int compareTo( StudentClass other )
     {
    	 // my index
    	 int index = 0;
 		 // string one
         char str0neLetter;
         // string two
         char strTwoLetter;
         // difference between them
         int alphabetDifference;
         // while index is less than both strings 
         while( index < this.name.length() && index < other.name.length() )
         {
        	 // characters within string
         	 str0neLetter = this.name.charAt( index );
             
             strTwoLetter = other.name.charAt( index );
             // difference between them
             alphabetDifference = str0neLetter - strTwoLetter;
         	
         	if( alphabetDifference != 0 )
            {
                 	
                  return alphabetDifference;
                      
            }
                 
             index++;
                 
         }
         // get difference
         return this.name.length() - other.name.length();
             
     }
     
    /**
     * Creates hash value from local data
     * 
     * Algorithm: Accesses the integer values of the characters in 
     * the name string up to but not including the first comma; 
     * then returns the sum
     * 
     * Uses .charAt
     * 
     * @overrides hashCode in class java.lang.Object
     * 
     * @return integer hash value representing data
     */
     public int hashCode()
     {
    	 // index
    	 int myIndex = 0;
    	 // total number
    	 int total = 0;
    	 // while the index value is not a comma
    	 while( name.charAt( myIndex ) != COMMA ) 
    	 {
    		 // increment total by the index value
    		 total += ( int ) name.charAt( myIndex );
    		 // increment index
    		 myIndex = myIndex + 1;
    	 }
    	 // return total
    	 return total;
     }
     
    /**
     * Overrides Object toString with local
     * 
     * @overrides toString in class java.lang.Object
     */
     public String toString()
     {
    	 // puts the information into a string
    	 return name + "/" + studentID + "/" + gender + "/" + gpa + " ";
     }
}