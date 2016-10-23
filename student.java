/**
 * This is a class to define the necessary attributes and methods to conceptualize a "Student"
 * The spepcific tasks are:
 * 1. Instiate 
 * 
 * @author Mithila Kundu Barsha
 * @version 23/10/2016
 */

import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.io.*;


public class Student {
	
	Scanner input = new Scanner(System.in);
	
	// Declare the important attributes of a student. For example:
    //1.Id
	int id;
    //2. Name
	String name;
    //3. Department
	String Department ;
    //4. University
	String University ;
    //5. GPAs in various terms (Multidimensional arrays)
	double[] GPA = new double[8];
    //6. subjects for Current terms
    //7. Credits and grades of Current Terms (Multidimmentional Array)
	 
	double[][][] CnG = new double[8][5][5];//first index for number of terms,second for credits and third for grades 
    
     double CGPA;
    
    /**
     * Define a constructor that initilize the default properties of the Student
     */
    public Student(int Id,String Name,String department,String versity)
    {
        id = Id;
        name = Name;
        Department = department;
        University = versity ;
    }
    
    /**
     * Define a method to print the students details.
     */
    public void studentDetailsById(int id)
    {
        //write your code here
    	System.out.println("ID          : " + id);
    	System.out.println("Name        : " +name);
    	System.out.println("Department  : " + Department);
    	System.out.println("University  : " + University); 	
    }
    
    
    /**
     * Define a method to update information of students by ID
     * Use as many arguments as required.
     */
    public void updateStudentById(int id)
    {
        //Write your code here
    	System.out.println("Do you want to update (write true/false) :");
    	boolean choice = input.nextBoolean();
    	if(choice == true )
    	{
	    	name = input.nextLine();
    		System.out.println("Enter Name : ");
	    	name = input.nextLine();
	    	System.out.println("Enter Department : ");
	    	Department = input.nextLine();
	    	System.out.println("Enter University : ");
	    	University = input.nextLine();
	    	System.out.println("Updated Name   :  " +name+ "\nDepartment   :     " +Department+ "\nUniversity   :     " +University);
    	}
    	
    } 
    
    /**
     * Define a method to compute the CGPA from the Given term GPA for a particular student.
     * se as many arguments as required.
     */
    public double computeCGPAByID()
    {
        // Write your code here
    	double cgpa = 0;
    	int count = 0; 	
    	
    	for(int i=0;i<8;i++)
    	{
    		if(GPA[i] == 0)	continue;
    		count++;
    		cgpa+=GPA[i];
    	}
    	cgpa/=count;
    	CGPA = cgpa;
    	
    	return cgpa;
    }
    
    
    /**
     * Define a method to compute the GPA from the given Credits and Grades of all the subjects
     */
    public double computeGPAById()
    {
    	double gpa =0;
    	double total = 0;
    	for(int i=0;i<8;i++)    //i for per Semester
    	{
    		System.out.println("Enter Credit And GRADE (Per Subject)  Of Semester: "+(i+1));
    		for(int j=0,k=0;j<=4;j++,k++)    // j for per Credit & k for per Grade
    		{
    			CnG[i][j][0]=input.nextDouble();
    			CnG[i][0][k]=input.nextDouble();	
    		}
    	} 
    	
    	for(int i=0;i<=7;i++)
    	{
    		gpa=0;
    		total=0;
    		for(int j=0,k=0;j<=4;j++,k++)
    		{
    			gpa+=CnG[i][j][0]*CnG[i][0][k];
    			total += CnG[i][j][0];	
    		}
    		
    		gpa = gpa / total;
    		GPA[i]=gpa;
    		System.out.printf("Result of the  trem  :    %d    %.3f\n\n",(i+1),gpa);
    	}
    	return gpa;
    } 
    
    
    /**
     * After performing required operations on each student, save the information to a file. Use File and PrintWriter Class. 
     * use as many arguments as required.
     * 
     */
    public void saveStudent() throws Exception
    {
    	FileWriter outputFile = new FileWriter("output.txt",true);
    	
    	BufferedWriter bw =new BufferedWriter(outputFile);
    	
    	PrintWriter writer = new PrintWriter(bw);
    	
    	writer.println("  ");
        writer.println("Name  : "+name);
        writer.println("ID  : " + id);
        writer.println("Department  : " + Department);
        writer.println("University  : " + University);
        writer.printf("Current CGPA  : %.3f\n\n",CGPA); 
        writer.println("  ");
        
        writer.close();
    }
    
    
    /**
     * The tasks to be carried out here:
     * 1. Create an Arrays of students using Student Class. Initlize them and perfom all the above defined operation to evualuate your code.
     * use as many arguments as required.
     */
    public static void main(String[] args) throws Exception
    {   
    	Student[] students = new Student[15];
    	
    	File inputFile = new File("input.txt");
		Scanner inp = new Scanner(inputFile);
		
		for(int i=0; inp.hasNextLine(); i++)
		{
			int roll=inp.nextInt();
			String nam=inp.next();
			String dep=inp.next();
			String uni=inp.next();
			students[i] = new Student(roll,nam,dep,uni);
			students[i].studentDetailsById(roll);
			students[i].updateStudentById(roll);
			students[i].computeGPAById();
			System.out.println(roll + " got cgpa : " +students[i].computeCGPAByID());
			System.out.printf("\n");
			students[i].saveStudent();
		} 
		inp.close();
    }
}