import java.util.Scanner;

public class Company {
	/* 	CSC 205: <14709> / <Online>
	Minilab: <Minilab 2>
	Author(s): <Joel Wells> & <35934052>
	Description: <This program models a tracking system for employees at a company. We will use it to hire new employees,
	change their salaries, track hours worked per week, then send out paychecks.> 
*/
	
	public static void main(String[] args) {
		
		//Variable Data Declaration
		String userChoice;
		String employeeName;
		int Cnt = 0; //Cnt is used for Option C and Option E to keep track of the name the user inputs with whats in the database, if matching, Cnt increments by 1 and does not display an error message
		double giveRaise;
		int employeeWeeklyHours = 0;
		double employeeInitialPay = 0;

		
		Scanner scnr = new Scanner(System.in);
		
		//Array set up to store all employees, maximum of 5
		SoftwareEngineer[] employeeArray = new SoftwareEngineer[5];
		
		//Array set up to store hourly Administrative Assistant employees maximum of 5
		AdministrativeAssistant[] hourlyArray = new AdministrativeAssistant[5];
		
		//Menu select to navigate Admin commands for employees
		do  {
			
			System.out.println("What do you want to do?");
			System.out.println("A. Add an Employee");
			System.out.println("B. List all Employees");
			System.out.println("C. Give an Employee a raise");
			System.out.println("D. Give Paychecks");
			System.out.println("E. Change someone’s hours");
			System.out.println("F. Quit");
			
			userChoice = scnr.next();
			//Option A, to add an employees to the database
			if ((userChoice.equals("a")) || (userChoice.equals("A"))) {
				if (employeeArray[4] != null) {
					System.out.println("We have maximum employees.");
				} else 
					for (int i = 0; i < employeeArray.length; ++i) {
						if (employeeArray[i] == null) {
							System.out.println("What is their name?");
							scnr.nextLine(); // This line you have to add (It consumes the \n character)
							employeeName = scnr.nextLine();
							//This while loop is checks to make sure the user puts in a name and does not accidentally put a blank 
							while (employeeName.equals("")) { 
								System.out.println("What is their name?");
								employeeName = scnr.nextLine();
							}
							//Asks for salary/hourly input for the new employee and adds it to the array object
							System.out.println("What is their salary (yearly or hourly)?");
							employeeInitialPay = scnr.nextDouble();
							while(employeeInitialPay < 0.0) {
								System.out.println("What is their salary (yearly or hourly)?");
								employeeInitialPay = scnr.nextDouble();
							}
							//Creats an Employee object then typecasts it as a Salary SoftwareEngineer to insert into the array at position [i]
							SoftwareEngineer allEmpObject =  new SoftwareEngineer(employeeName, employeeInitialPay);
							employeeArray[i] = allEmpObject; 
							//Asks to see if the the worker is hourly or not. If they are hourly, then we get their weekly hours and copy the the 
							//employeeArray object to hourlyEmpObject in the same position				
							System.out.println("Are they an hourly worker? (Y/N)");
							userChoice = scnr.next();
							while ((!userChoice.equals("n")) && (!userChoice.equals("N")) && (!userChoice.equals("y")) && (!userChoice.equals("Y"))) {
								System.out.println("Are they an hourly worker? (Y/N)");
								userChoice = scnr.next();
							}	//If branch to determine if the employee is salary or hourly
								if ((userChoice.equals("n")) || (userChoice.equals("N"))) {
									System.out.println(employeeArray[i].getName() + " was hired!");
								}
								if ((userChoice.equals("y")) || (userChoice.equals("Y"))) {
									System.out.println("How many hours per week do they work?");						
									employeeWeeklyHours = scnr.nextInt();
									//Create an AdministrativeAssistant object then inserts into the hourlyArray at position [i]
									AdministrativeAssistant hourlyEmpObject = new AdministrativeAssistant(employeeName, employeeInitialPay, employeeWeeklyHours);
									hourlyArray[i] =  hourlyEmpObject;
									System.out.println(hourlyEmpObject.getName() + " was hired!");	
								}
								break;
						}	 
				}
			}
			//Option B, displays and lists all current employees in the database/employeeArray
			else if ((userChoice.equals("b")) || (userChoice.equals("B"))) {
				if (employeeArray[0] == null) {
					System.out.println("Nobody works here!");
				} else
				for (int i = 0; i < employeeArray.length; ++i) {
					//Checks to see if there are employees and determines if they are an hourly or salary employee then displays them
					if ((employeeArray[i] != null) && (hourlyArray[i] != null)) {
						System.out.println(hourlyArray[i].getName() + " Hourly Wage: " + hourlyArray[i].getSalary() + " Cash: " + hourlyArray[i].getCash() + " " + "Administrative Assistant");	
					} else if((employeeArray[i] != null) && (hourlyArray[i] == null)) {
						System.out.println(employeeArray[i].getName() + " Salary: " + employeeArray[i].getSalary() + " Cash: " + employeeArray[i].getCash() + " " + "Software Engineer");
					}					
				}
			}						
			//Option C, allows the user to pick an employee to give them a raise, gives 
			else if ((userChoice.equals("c")) || (userChoice.equals("C"))) {
				if (employeeArray[0] == null) {
					System.out.println("No one works here so we cannot give anyone a raise!");				
				} else {
				System.out.println("Who do you want to give a raise to?");		
				scnr.nextLine();
				employeeName = scnr.nextLine();
				Cnt = 0;	
				//For loop to find the name match of employeeName and if succesful, give them a raise
				for (int i = 0; i < employeeArray.length; ++i) {
					//Applies a raise to the employee based on user input to hourly workers			
					if (employeeArray[i] != null ) {
						if (employeeArray[i].getName().equals(employeeName)) {
							//Applies a raise to the employee based on user input to salary workers
							System.out.println("What raise do you want to give them?");
							giveRaise = scnr.nextDouble();
							//Ensures an employee gets a positive raise.
							while (giveRaise < 0.0) {
								System.out.println("A raise cannot be negative, try again.");
								giveRaise = scnr.nextDouble();
							}
						
							//If the Administrative Assitantant employee is not empty(null), then we trigger the raise for Administrative Assitantant
							//If the Administrative Assistant employee array is empty(null), then we trigger the raise for Software Engineer
							if (hourlyArray[i] != (null)) {
								hourlyArray[i].giveRaise(giveRaise);
								System.out.println(hourlyArray[i].getName() + " is happy!");
								Cnt++;
								break;
							} else if (hourlyArray[i] == null) {
							employeeArray[i].giveRaise(giveRaise);
							System.out.println(employeeArray[i].getName() + " is happy!");
							Cnt++;
							break;
							}
						} 
					}	
				}	
					if (Cnt == 0) {
						System.out.println("Name does not match.");
					}
				}			
			}
			//Option D, sends out paychecks to employees
			else if ((userChoice.equals("d")) || (userChoice.equals("D"))) {
				if (employeeArray[0] == null) {
					System.out.println("No one works here so we cannot send out paychecks.");
				} else if (employeeArray[0] != null) {
				for (int i = 0; i < employeeArray.length; ++i) {
						if ((employeeArray[i] != null) && (hourlyArray[i] != null)) {
							hourlyArray[i].getPaid();
						} else if ((employeeArray[i] != null) && (hourlyArray[i] == null)) {
							employeeArray[i].getPaid();
						}
					}
				System.out.println("Hooray for money!");
				}		
			}
			//Option E, change's the hours for a selected employee
			else if ((userChoice.equals("e")) || userChoice.equals(("E"))) {
					System.out.println("Change hours for who?");
					scnr.nextLine();
					employeeName = scnr.nextLine();
					Cnt = 0;
					for (int i = 0; i < hourlyArray.length; ++i) {
						//Checks to see if we have an hourly employee then checks to see if we have matching names from user input 
						//Updates hours for hourly employees, naturally they cannot work negative hours.
						if (hourlyArray[i] != null) {
							if ((hourlyArray[i].getName().equals(employeeName))) {
								System.out.println(hourlyArray[i].getName() + " currently works " + hourlyArray[i].getHours() + " hours per week. What would you like to change it to?");
								employeeWeeklyHours = scnr.nextInt();
								while (employeeWeeklyHours < 0) {
									System.out.println("Employees cannot work less than 0 hours per week. Try again.");
									employeeWeeklyHours = scnr.nextInt();
								}
								hourlyArray[i].setHours(employeeWeeklyHours);
								System.out.println(hourlyArray[i].getName() + " will now work " + hourlyArray[i].getHours() + " per week");
								Cnt++;
								break;
							}
							
						}
					}
					if (Cnt == 0) {
						System.out.println("Name does not match or they are not an hourly employee.");
					}
				}	
			else  if ((!userChoice.equals("f")) && (!userChoice.equals("F"))) {
				System.out.println("Invalid option");
			}
			//Option F, breaks the loop and ends the program. No further instructions required.

		} while ((!userChoice.equals("f")) && (!userChoice.equals("F")));
		
		System.out.println("Bye!");
		scnr.close();	
	}
}
