
public class AdministrativeAssistant extends Employee implements Hourly {

	public int hoursPerWk;
	
	public AdministrativeAssistant(String name, double salary, int hours) {
		empName = name;
		empSalary = salary; //How much they are paid per hour
		hoursPerWk = hours;
		
	}
	
	//Completed methods implemented from Hourly
	public int getHours() {
		return hoursPerWk;
	}
	public void setHours(int hours) {
		hoursPerWk = hours;
	}
	//Override methods
	@Override
	public void giveRaise(double raiseHourly) {
		empSalary = empSalary + raiseHourly;
	}
	@Override
	public double getPaid() {
		return empCash = empCash + (empSalary * (hoursPerWk * 2)); 
	}
}
