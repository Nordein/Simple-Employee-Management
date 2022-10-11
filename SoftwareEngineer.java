
public class SoftwareEngineer extends Employee {

	public SoftwareEngineer(String name, double salary) {
		empName = name;
		empSalary = salary;
	}

	@Override
	public void giveRaise(double raise) {
		empSalary = empSalary * ((raise + 100) / 100);
	}
}
