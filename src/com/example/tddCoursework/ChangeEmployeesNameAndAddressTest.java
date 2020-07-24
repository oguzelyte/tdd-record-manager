////////////////////////////////////////////////////////
//                                                    //
// Title:          ChangeEmployeeNameAndAddress.java  //
// Author:         O. Guzelyte (160421859)            //
// Last modified:  29/11/2017                         //
//                                                    //
////////////////////////////////////////////////////////
package com.example.tddCoursework;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.UUID;

import org.junit.Test;

import recordManagerProject.Department;
import recordManagerProject.Employee;
import recordManagerProject.RecordManager;

public class ChangeEmployeesNameAndAddressTest extends AbstractLoggingJUnitTest {

	// Create a record manager type object for city London
	private RecordManager manager1 = new RecordManager("London");

	// Helper method that adds 5 employees to the record manager object
	private void addEmployees() throws ParseException {
		manager1.addEmployee("Klein Handstand", "33 Brighton Grove", "0335232346", Department.SUPPLY_CHAIN_MANAGEMENT,"23/04/2011");
		manager1.addEmployee("Mark Zuckerberg", "35 Brighton Grove", "0845648345", Department.FINANCE, "23/11/2013");
		manager1.addEmployee("Daniel Sampos", "32 Brighton Grove", "0335254246", Department.INFORMATION_TECHNOLOGY,"23/08/2015");
		manager1.addEmployee("Mark Zuckerberg", "34 Brighton Grove", "0235262346", Department.SERVICE, "23/10/2015");
		manager1.addEmployee("Cian Downey", "38 Brighton Grove", "0335254346", Department.INFORMATION_TECHNOLOGY,"23/08/2012");
	}

	// Test if change of name was successful
	@Test
	public void changeNameOfEmployeeSuccessful() throws ParseException {

		// Add employees using helper method
		addEmployees();

		// Get all employees from record manager for easier iteration
		java.util.List<Employee> employees = manager1.getEmployees();

		// From zero to size of employees list do
		for (int i = 0; i < employees.size(); i++) {

			// Prepare the "expected" string to be inserted into assertEquals method
			// The only altered employee detail is the name, everything else needs to be as was set initially
			String afterNameChange = "\nEmployee ID = " + employees.get(i).getID() + "\nName = Chloe Apache"
					+ "\nHome Address = " + employees.get(i).getHomeAddress() + "\nDepartment = "
					+ employees.get(i).getDepartment() + "\nPhone Nr = " + employees.get(i).getPhoneNr()
					+ "\nStarted working = " + employees.get(i).getStartWork()
					+ "\nQualifications = 0 - No qualifications added.";

			// Invoke the changeName method by user ID and change it for all employees to "Chloe Apache"
			manager1.changeName(employees.get(i).getID(), "Chloe Apache");
			
			// Check if name after changeName actually changed
			assertEquals(afterNameChange, employees.get(i).toString());
		}
	}

	// Test if change of name failed
	@Test
	public void changeNameOfEmployeeFailed() throws ParseException {

		// Add employees using helper method
		addEmployees();

		// Generate a random UUID that does not belong to any employee
		UUID testFakeID = new UUID(12345678, 87654321);

		// Check if the return string is that employee of such ID does not exist so change cannot happen.
		assertEquals("Employee of ID " + testFakeID + " does not exist. Name was not changed.",
				manager1.changeName(testFakeID, "Chloe Apache"));

	}

	// Test if change of address was successful
	@Test
	public void changeAddressOfEmployeeSuccessful() throws ParseException {

		// Add employees using helper method
		addEmployees();

		// Get all employees from record manager for easier iteration
		java.util.List<Employee> employees = manager1.getEmployees();

		// From zero to size of employees list do
		for (int i = 0; i < employees.size(); i++) {

			// Prepare the "expected" string to be inserted into assertEquals method
			// The only altered employee detail is the address, everything else needs to be as was set initially
			String afterAddressChange = "\nEmployee ID = " + employees.get(i).getID() + "\nName = "
					+ employees.get(i).getName() + "\nHome Address = 37 Dunston Street" + "\nDepartment = "
					+ employees.get(i).getDepartment() + "\nPhone Nr = " + employees.get(i).getPhoneNr()
					+ "\nStarted working = " + employees.get(i).getStartWork()
					+ "\nQualifications = 0 - No qualifications added.";

			// Invoke the changeAddress method by user ID and change it for all employees to "37 Dunston Street"
			manager1.changeAddress(employees.get(i).getID(), "37 Dunston Street");
			
			// Check if address after changeAddress actually changed
			assertEquals(afterAddressChange, employees.get(i).toString());
		}
	}

	// Test if change of address failed
	@Test
	public void changeAddressOfEmployeeFailed() throws ParseException {

		// Add employees using helper method
		addEmployees();

		// Generate a random UUID that does not belong to any employee
		UUID testFakeID = new UUID(12345678, 87654321);

		// Check if the return string is that employee of such ID does not exist so change cannot happen.
		assertEquals("Employee of ID " + testFakeID + " does not exist. Address was not changed.",
				manager1.changeAddress(testFakeID, "37 Dunston Street"));

	}

}
