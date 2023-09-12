package com.task_02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCls {

	// ** First Part - a **
	public long cntMail(List<Employee> employeeList) {
		Stream<Employee> stream = employeeList.stream();
		long cnt = stream.filter(Employee -> "Male".equals(Employee.getGender())).count();

		return cnt;
	}

	// ** First Part - b **
	public long cntFemail(List<Employee> employeeList) {
		Stream<Employee> stream = employeeList.stream();
		long cnt = stream.filter(Employee -> "Female".equals(Employee.getGender())).count();

		return cnt;
	}

	// ** Second **
	public void printOrganizationName(List<Employee> employeeList) {
		Stream<Employee> stream = employeeList.stream();

		System.out.println("Following department names are :- ");
		// ** Way - 1 **
		stream.map(Employee::getDepartment).distinct().forEach(System.out::println);

		// ** Way - 2 **
		// stream.map(Emp -> Emp.getDepartment()).distinct().forEach(em ->
		// System.out.println(em));

		// ** Way - 3 **
		// List<String> depName = stream.map(Emp ->
		// Emp.getDepartment()).distinct().collect(Collectors.toList());

	}

	// ** Third **
	public Map<String, Double> averageAgeOfMale(List<Employee> employeeList) {
		Stream<Employee> stream = employeeList.stream();

		Map<String, Double> averageAgeByGender = stream
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));

		return averageAgeByGender;
	}

	// ** Fourth **
	public Optional<Employee> highestPaidEmployee(List<Employee> employeeList) {
		Stream<Employee> stream = employeeList.stream();

		Optional<Employee> highestPaidEmployee = stream.max(Comparator.comparingDouble(Employee::getSalary));

		// Optional<Employee> highestPaidEmployee =
		// stream.max(Comparator.comparingDouble(emp -> emp.getSalary()));

		/*
		 * compare method of the Comparator interface expects two object references as
		 * arguments, not primitive data types like int. To use this int value with a
		 * Comparator, you need to wrap it in an Integer object because Comparator works
		 * with objects, not primitive types.
		 */

		// Optional<Employee> highestPaidEmployee = stream.max((i1,i2) ->
		// Integer.compare((int)i1.getSalary(), (int)i2.getSalary()));
		return highestPaidEmployee;
	}

	// ** Fifth **
	public List<String> employee2015JoinName(List<Employee> employeeList) {
		Stream<Employee> stream = employeeList.stream();

		List<String> nameList = stream.filter(emp -> emp.getYearOfJoining() > 2015).map(Employee::getName).sorted()
				.collect(Collectors.toList());

		return nameList;
	}

	// ** Six **
	public Map<String, Long> departmentCnt(List<Employee> employeeList) {
		Stream<Employee> stream = employeeList.stream();
		Map<String, Long> depCnt = stream
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		return depCnt;
	}

	// ** Seven **
	public Map<String, Double> getAverageSalofEachDep(List<Employee> employeeList) {
		Stream<Employee> stream = employeeList.stream();

		Map<String, Double> averageSalEachDep = stream.collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

		return averageSalEachDep;
	}

	// ** Eight **
	public Optional<Employee> FindYoungeEmpInPro(List<Employee> employeeList) {
		Optional<Employee> youngestMale = employeeList.stream()
				.filter(employee -> "Product Development".equals(employee.getDepartment()))
				.filter(employee -> "Male".equals(employee.getGender())).min(Comparator.comparingInt(Employee::getAge));

		/*
		 * Optional<Employee> youngestMale = employeeList.stream() .filter(employee ->
		 * "Product Development".equals(employee.getDepartment())) .filter(employee ->
		 * "Male".equals(employee.getGender())) .min((employee1, employee2) ->
		 * Integer.compare(employee1.getAge(), employee2.getAge()));
		 */

		return youngestMale;
	}

	// ** Nine **
	public Map<String, Long> getCntInSalesAndMarketing(List<Employee> employeeList) {

		Map<String, Long> cntInSalesAndMarketing = employeeList.stream()
				.filter(employee -> "Sales And Marketing".equals(employee.getDepartment()))
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

		return cntInSalesAndMarketing;
	}

	// ** Ten Part - A**
	public OptionalDouble getAvgSalOfMale(List<Employee> employeeList) {
		OptionalDouble avgSalOfMale = employeeList.stream().filter(emp -> "Male".equals(emp.getGender()))
				.mapToDouble(Employee::getSalary).average();

		// avgSaleOfMale = employeeList.stream().filter(emp ->
		// "Male".equals(emp.getGender())).collect(Collectors.averagingDouble(Employee::getSalary));

		return avgSalOfMale;
	}

	// ** Ten Par - B **
	public OptionalDouble getAvgSalOfFemale(List<Employee> employeeList) {
		OptionalDouble avgSalOfFemale = employeeList.stream().filter(emp -> "Female".equals(emp.getGender()))
				.mapToDouble(Employee::getSalary).average();
		return avgSalOfFemale;
	}

	// ** Eleven **
	public Map<String, List<String>> printAllEmployee(List<Employee> employeeList) {
		Map<String, List<String>> empDetail = employeeList.stream().collect(Collectors
				.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));
		return empDetail;
	}

	// ** Fourteen **
	public Employee FindOldestEmpInOrg(List<Employee> employeeList) {
		// Employee OldestEmp = employeeList.stream().max((employee1,employee2) ->
		// Integer.compare(employee1.getAge(),employee2.getAge())).orElse(null);

		Employee OldestEmp = employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).orElse(null);

		return OldestEmp;
	}

	public static void main(String[] args) {

		List<Employee> employeeList = new ArrayList<Employee>();

		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

		MainCls obj = new MainCls();

		// ** First **
		// int maleCnt = obj.cntMail(employeeList);
		// int femaleCnt = obj.cntFemail(employeeList);
		// System.out.println("Number of male employee are :- " + maleCnt);
		// System.out.println("Number of female employee are :- " + femaleCnt);

		// ** Second **
		// obj.printOrganizationName(employeeList);

		// ** Third **
		// Map<String,Double> averageAge = obj.averageAgeOfMale(employeeList);

		// averageAge.forEach((gender, age) -> {
		// System.out.println("Average age for " + gender + ": " + age);
		// });

		// ** Fourth **
		// Optional<Employee> highestPaidEmployee =
		// obj.highestPaidEmployee(employeeList);

		// if(highestPaidEmployee.isPresent()) {
		// Employee employee = highestPaidEmployee.get();
		// System.out.println("Employee with the highest paid salary:");
		// System.out.println("Name: " + employee.getName());
		// System.out.println("Salary: " + employee.getSalary());
		// }
		// else {
		// System.out.println("No employee found.");
		// }

		// ** Fifth **
		// List<String> empName = obj.employee2015JoinName(employeeList);
		// System.out.println("Names of employees who joined after 2015:");
		// empName.forEach(System.out::println);

		// ** Six **
		/*
		 * Map<String,Long> eachDepCnt = obj.departmentCnt(employeeList);
		 * System.out.println("Employee counts in each department:");
		 * eachDepCnt.forEach((department, count) -> { System.out.println(department +
		 * ": " + count); });
		 * 
		 * // ** Seven ** Map<String,Double> avgSalEach =
		 * obj.getAverageSalofEachDep(employeeList); avgSalEach.forEach((department,
		 * salary) -> { System.out.println("Average age for " + department + ": " +
		 * salary); });
		 */
		// ** Eight **
		Optional<Employee> youngestMaleInProductDevelopment = obj.FindYoungeEmpInPro(employeeList);
		if (youngestMaleInProductDevelopment.isPresent()) {
			Employee youngestMale = youngestMaleInProductDevelopment.get();
			System.out.println("Youngest Male Employee in Product Development:");
			System.out.println("Name: " + youngestMale.getName());
			System.out.println("Age: " + youngestMale.getAge());
			// Print other details if needed
		} else {
			System.out.println("No youngest male employee found in the Product Development department.");
		}

		// ** Nine **
		Map<String, Long> genderCountsInSalesAndMarketing = obj.getCntInSalesAndMarketing(employeeList);
		System.out.println("Number of male and female employees in Sales and Marketing:");
		genderCountsInSalesAndMarketing.forEach((gender, count) -> {
			System.out.println(gender + ": " + count);
		});

		// ** Ten **
		OptionalDouble averageSalaryOfMaleEmployees = obj.getAvgSalOfMale(employeeList);

		if (averageSalaryOfMaleEmployees.isPresent()) {
			double averageSalary = averageSalaryOfMaleEmployees.getAsDouble();
			System.out.println("Average salary of male employees: " + averageSalary);
		} else {
			System.out.println("No male employees found.");
		}

		OptionalDouble averageSalaryOfFemaleEmployees = obj.getAvgSalOfFemale(employeeList);

		if (averageSalaryOfFemaleEmployees.isPresent()) {
			double averageSalary = averageSalaryOfFemaleEmployees.getAsDouble();
			System.out.println("Average salary of female employees: " + averageSalary);
		} else {
			System.out.println("No male employees found.");
		}

		// ** Ele **
		Map<String, List<String>> empDetail = obj.printAllEmployee(employeeList);

		empDetail.forEach((department, name) -> {
			System.out.println("Department :- " + department);

			name.forEach(nameEmp -> System.out.println(nameEmp));
		});

		// ** Twel **
		Double totalSal = employeeList.stream().mapToDouble(Employee::getSalary).sum();

		Double avgSal = totalSal / employeeList.size();

		System.out.println("Total salary :- " + totalSal);
		System.out.println("Average salary :- " + avgSal);

		// ** Thirteen **
		List<Employee> youngerOrEqual25 = employeeList.stream().filter(employee -> employee.getAge() <= 25)
				.collect(Collectors.toList());

		List<Employee> olderThan25 = employeeList.stream().filter(employee -> employee.getAge() > 25)
				.collect(Collectors.toList());

		// Display the separated employees
		System.out.println("Employees 25 Years Old or Younger:");

		youngerOrEqual25.forEach(e -> System.out
				.println("Employee ID: " + e.getId() + ", Name: " + e.getName() + ", Age: " + e.getAge()));

		System.out.println("\nEmployees Older Than 25 Years:");
		olderThan25.forEach(e -> System.out
				.println("Employee ID: " + e.getId() + ", Name: " + e.getName() + ", Age: " + e.getAge()));
		System.out.println("\n");

		// ** Fourtein **
		Employee oldestEmp = obj.FindOldestEmpInOrg(employeeList);
		if (!oldestEmp.equals(null)) {
			System.out.println("Name :- " + oldestEmp.getName());
			System.out.println("Age :- " + oldestEmp.getAge());
			System.out.println("Department :- " + oldestEmp.getDepartment());
		} else
			System.out.println("Not oldest employee is present");

	}
}
