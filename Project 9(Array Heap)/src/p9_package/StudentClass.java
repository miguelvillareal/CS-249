package p9_package;
/**
 * Class for managing student data
 * 
 * CS 249 
 * 
 * DUE: 4-09-19
 * 
 * @author Miguel Villareal
 *
 */
public class StudentClass
{
	/**
     * Blank
     */
	 public char gender;
       
    /**
     * Blank
     */
	 public double gpa;
       
    /**
     * Blank
     */
	 public String name;
       
    /**
     * Blank
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
     public StudentClass(String inName,
    		 int inStudentID,
             char inGender,
             double inGPA)
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
     	  this.gender = copied.gender;
     	  
     	  this.name = copied.name;
     	  
     	  this.studentID = copied.studentID;
     	  
     	  this.gpa = copied.gpa;
     }
       
    /**
     * Compares student objects
     * <p>
     * Note: Compares gpa as class key; returns integer result such that if 
     * this gpa is less than other gpa, a negative number is returned; 
     * if this gpa is greater than other gpa, a positive number is returned; 
     * if this gpa is equal to other gpa, zero is returned
     * <p>
     * Note: difference precision is 0.00001
     * 
     * @param other StudentClass object to be compared with this object
     * 
     * @return integer difference between gpa grades
     */
     public int compareTo( StudentClass other )
     {
         // compares the gpa's of two students
         int thisGPA = ( int )( this.gpa * 100000 );
         
         int otherGPA = ( int )( other.gpa * 100000 );
         // returns the difference
         return thisGPA - otherGPA ;
             
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