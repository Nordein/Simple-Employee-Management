
public abstract class Employee {
	
	//Variable setup
	protected String empName;
	protected double empSalary;
	protected double empCash;
	
	
	
	//Normal getter methods
	public String getName() {
		return empName;
	}
	public double getSalary() {
		return empSalary;
	}
	public double getCash() {
		return empCash;
	}
	public double getPaid() {
		return empCash = empCash + (empSalary / 26.0); 
	}
	//Abstract methods
	public abstract void giveRaise(double raiseSalary);	
}
