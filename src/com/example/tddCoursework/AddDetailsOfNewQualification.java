////////////////////////////////////////////////////////
//                                                    //
// Title:          AddDetailsOfNewQualification.java  //
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
import recordManagerProject.RecordManager;

public class AddDetailsOfNewQualification extends AbstractLoggingJUnitTest{

	// Create a record manager type object for city London
	private RecordManager manager1 = new RecordManager("London");

	// Helper method that adds 2 employees to the record manager object
	private void addEmployees() throws ParseException {
		manager1.addEmployee("Klein Handstand", "33 Brighton Grove", "0335232346", Department.SUPPLY_CHAIN_MANAGEMENT,"23/04/2011");
		manager1.addQualification(manager1.getEmployees().get(0).getID(),"Sociology degree", "1st degree", "24/03/2014");
		manager1.addEmployee("Mark Zuckerberg", "35 Brighton Grove", "0845648345", Department.FINANCE, "23/11/2013");
	}

	// Check if can add qualifications to a person that already has a qualification, and if it gets updated
	@Test
	public void addQualificationForPersonThatHasQualification() throws ParseException {
		
		// Add the employees
		addEmployees();
		
		// Add second qualification to Klein Handstand
		manager1.addQualification(manager1.getEmployees().get(0).getID(),"Computer Science", "1st degree", "24/02/2015");
		
		// String to check if after qualification add, Klein's information gets updated correctly
		String afterAddQualification = "\nEmployee ID = " + manager1.getEmployees().get(0).getID() + "\nName = Klein Handstand\nHome Address = 33 Brighton Grove\nDepartment = SUPPLY_CHAIN_MANAGEMENT"
				+ "\nPhone Nr = 0335232346\nStarted working = 23/04/2011\nQualifications = 2\n\n1.Full Name = Sociology degree\nLevel = 1st degree\nDate Achieved = 24/03/2014\n"
				+ "\n2.Full Name = Computer Science\nLevel = 1st degree\nDate Achieved = 24/02/2015";
		
		// Checking method to compare Klein's details from before and after qualification add
		assertEquals(afterAddQualification, manager1.getEmployees().get(0).toString());
		
	}
	
	// Check if can add qualifications to a person that already has no qualifications, and if it gets updated
	@Test
	public void addQualificationForPersonWithNoQualification() throws ParseException {
		
		// Add the employees
		addEmployees();
		
		// Add first qualification to Mark Zuckerberg
		manager1.addQualification(manager1.getEmployees().get(1).getID(),"Computer Science", "1st degree", "24/02/2015");
		
		// String to check if after qualification add, Mark's information gets updated correctly
		String afterAddQualification = "\nEmployee ID = " + manager1.getEmployees().get(1).getID() + "\nName = Mark Zuckerberg\nHome Address = 35 Brighton Grove\nDepartment = FINANCE"
				+ "\nPhone Nr = 0845648345\nStarted working = 23/11/2013\nQualifications = 1\n\n1.Full Name = Computer Science\nLevel = 1st degree\nDate Achieved = 24/02/2015";
		
		// Checking method to compare Mark's details from before and after qualification add
		assertEquals(afterAddQualification, manager1.getEmployees().get(1).toString());
		
	}
	
	// Try to add a qualification to a person with a wrong ID and see if it returns the correct information
	@Test
	public void addQualificationForPersonWithWrongID() throws ParseException {
		
		// Add the employees
		addEmployees();
		
		// Generate a random UUID that does not belong to any employee
		UUID testFakeID = new UUID(12345678, 87654321);
		
		// Checking method to compare fake employee's details from before and after qualification add
		assertEquals("Employee with given ID does not exist.", manager1.addQualification(testFakeID,"Computer Science", "1st degree", "24/02/2015"));
		
	}

}
