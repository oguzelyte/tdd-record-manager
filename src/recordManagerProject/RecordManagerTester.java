////////////////////////////////////////////////////////
//                                                    //
// Title:          RecordManagerTester.java           //
// Author:         O. Guzelyte (160421859)            //
// Last modified:  29/11/2017                         //
//                                                    //
////////////////////////////////////////////////////////
package recordManagerProject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class RecordManagerTester {
    /* List of cities to create record manager objects with */
	public static final String[] CITY_LIST = { "London", "Newcastle", "Durham" };
	
	/* Employee names for 3 corresponding cities */
	public static String[][] employeeNames = { 
/*London*/   { "Mark Newton", "Mark Newton", "Aurora Minerva" },
/*Newcastle*/{ "Chloe Dnepra", "Jade Sierra" }, 
/*Durham*/	 { "Ian Ball", "Jessica Knicks", "Rachel Tain", "Ian Gully" } };
	
	/* Employee addresses for 3 corresponding cities */
	public static String[][] employeeAddresses = {
			{ "1 Small Cres, London WA2 9EX, UK", "11 Waggoners Dr, Copmanthorpe, London YO23 3XJ, UK", "4 Dairy Cl, Sutton at Hone, London DA4 9ES, UK" },
			{ "36 Victoria St, Newcastle CT5 1HZ, UK", "53 Basingfield Rd, Newcastle KT7 0PD, UK" },
			{ "13 Ffordd Llechi, Durham LL64 5JY, UK", "5 Ashway, Northleach, Durham GL54 3PB, UK", "9 Pinfold Rd, Tideswell, Durham SK17 8PN, UK", "4A Pellatt Rd, East Dulwich, Durham SE22 9JA, UK" } };
	
	/* Employee phone numbers for 3 corresponding cities */
	public static String[][] employeeNrs = { 
			{ "01925576714", "01904254707", "01322881341" },
			{ "01227783075", "01372147767" }, 
			{ "01407610570", "01451452382", "01298817415", "02037780711" } };
	
	/* Departments employees work in for 3 corresponding cities */
	public static Department[][] employeeDepartments = {
			{ Department.FINANCE, Department.INFORMATION_TECHNOLOGY, Department.SERVICE },
			{ Department.QUALITY_ASSURANCE, Department.SUPPLY_CHAIN_MANAGEMENT }, 
			{ Department.FINANCE, Department.SERVICE, Department.INFORMATION_TECHNOLOGY, Department.QUALITY_ASSURANCE } };
	
	/* Employee start to work dates */
	public static String[][] employeeStartDates = { 
			{ "27/11/2019", "09/0393", "09/05/2012" },
			{ "02/01/2016", "24/05/2015" }, 
			{ "05/06/2012", "08/05/2017", "02/06/2014", "20/12/2016" } };

	public static void main(String[] args) throws ParseException {

		// Create a record manager list for different cities.
		List<RecordManager> managerList = new ArrayList<RecordManager>();

		// Add different record managers to a list.
		for (int i = 0; i < CITY_LIST.length; i++) {
			managerList.add(new RecordManager(CITY_LIST[i]));
		}

		// Add employees with their corresponding details to the record managers.
		for (int i = 0; i < CITY_LIST.length - 1; i++) { // INTENTIONALLY go until CITY_LIST.length-1 not to add employees to Durham record manager.
			for (int j = 0; j < employeeNames[i].length; j++) { // Until the end of the ith element of employeeNames array.
				managerList.get(i).addEmployee(employeeNames[i][j], employeeAddresses[i][j], employeeNrs[i][j],
						employeeDepartments[i][j], employeeStartDates[i][j]); // Add information about the employee using the record manager.
			}
		}

		// Add employee qualifications with their corresponding details to the record managers.
		for (int i = 0; i < CITY_LIST.length; i++) {

			List<Employee> employeesOfACity = managerList.get(i).getEmployees(); // Get all employees from city (London, Newcastle, Durham).
			RecordManager currentManager = managerList.get(i); // Get the current record manager.

			if (employeesOfACity.size() != 0) { // If there are any employees for a given record manager, add qualification.
				for (int j = 0; j < employeeNames[i].length; j++) {
					if (i != 0) // INTENTIONALLY add this qualification only to Newcastle's and Durham's employees.
						currentManager.addQualification(employeesOfACity.get(j).getID(), "Computer Science degree",
								"1st degree", "2016-05-29");
					if (i == CITY_LIST.length - 2) { // INTENTIONALLY add this qualification only to Newcastle's employees.
						currentManager.addQualification(employeesOfACity.get(j).getID(), "Microsoft Word certificate",
								"Pass", "2013-05-29");
					}
				} /* Note that London employees have no qualifications, Newcastle's have 2 per 
				     person and Durham's don't get added because no employees for that company
				     exist */
			}
		}

		// Prints out information about all employees in all record managers.
		for (int i = 0; i < CITY_LIST.length; i++) {
			managerList.get(i).employeesToString();
		}

	}

}
