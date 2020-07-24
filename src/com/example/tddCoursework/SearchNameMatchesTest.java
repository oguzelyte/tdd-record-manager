/////////////////////////////////////////////////
//                                             //
// Title:          SearchNameMatchesTest.java  //
// Author:         O. Guzelyte (160421859)     //
// Last modified:  29/11/2017                  //
//                                             //
/////////////////////////////////////////////////
package com.example.tddCoursework;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import recordManagerProject.Department;
import recordManagerProject.RecordManager;

@RunWith(Parameterized.class)
public class SearchNameMatchesTest extends AbstractLoggingJUnitTest {

	private static RecordManager manager1;
	
	@Before
	public void initialiseRecordManagerAndEmployees() {
		
		// Create static object of record manager (as the array used for parametrisation
		// requires so)
		SearchNameMatchesTest.manager1 = new RecordManager("London");
		
		// Adds the employees to a manager. This is needed as otherwise the ID would not
		// be retrieved correctly in the upcoming statements.
		try {
			addEmployees();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// First match string, containing details retrieved for match of Cian Downey.
	// Note that the ID is created dynamically, therefore employees needed to be
	// added above.
	private static String cianDowneyMatch1 = "[\nEmployee ID = "
			+ manager1.getEmployees().get(4).getID() + "\n" + "Name = Cian Downey\n"
			+ "Home Address = 38 Brighton Grove\n" + "Department = INFORMATION_TECHNOLOGY\n" + "Phone Nr = 0335254346\n"
			+ "Started working = 23/08/2012\n" + "Qualifications = 0 - No qualifications added.]";

	// First match string, containing details retrieved for match of Mark
	// Zuckerberg. Note that the ID is created dynamically, therefore employees
	// needed to be added above.
	private static String markZuckerbergMatch1 = "[\nEmployee ID = "
			+ manager1.getEmployees().get(1).getID() + "\n" + "Name = Mark Zuckerberg\n"
			+ "Home Address = 35 Brighton Grove\n" + "Department = FINANCE\n" + "Phone Nr = 0845648345\n"
			+ "Started working = 23/11/2013\n" + "Qualifications = 0 - No qualifications added., \n";

	// Second match string, containing details retrieved for match of Cian Downey.
	// Note that the ID is created dynamically, therefore employees needed to be
	// added above.
	private static String markZuckerbergMatch2 = "Employee ID = " + manager1.getEmployees().get(3).getID()
			+ "\n" + "Name = Mark Zuckerberg\n" + "Home Address = 34 Brighton Grove\n" + "Department = SERVICE\n"
			+ "Phone Nr = 0235262346\n" + "Started working = 23/10/2015\n"
			+ "Qualifications = 0 - No qualifications added.]";

	// Parameters for 3 tests: multiple (>1) name match, singular name match and no
	// name matches. Mark Zuckerberg - 2 matches, Daniel Santos - 0 matches, Cian
	// Downey - 1 match.
	@Parameters
	public static Collection<Object[]> data() {

		return Arrays.asList(new Object[][] { { "Mark Zuckerberg", markZuckerbergMatch1 + markZuckerbergMatch2 },
				{ "Daniel Santos", "[]" }, { "Cian Downey", cianDowneyMatch1 } });
	}

	// Input (the 3 test cases) and expected output.
	private String input;
	private String expected;

	// Constructor to set the input and expected parameters throughout the 3 cases.
	public SearchNameMatchesTest(String input, String expected) {
		this.input = input;
		this.expected = expected;
	}

	// Helper method that adds 5 employees to the record manager object. Out of them,
	// 3 are used for tests, while 2 serve as tests to check if the name search
	// algorithm works.
	private static void addEmployees() throws ParseException {
		manager1.addEmployee("Klein Handstand", "33 Brighton Grove", "0335232346", Department.SUPPLY_CHAIN_MANAGEMENT,"23/04/2011");
		manager1.addEmployee("Mark Zuckerberg", "35 Brighton Grove", "0845648345", Department.FINANCE, "23/11/2013");
		manager1.addEmployee("Daniel Sampos", "32 Brighton Grove", "0335254246", Department.INFORMATION_TECHNOLOGY,"23/08/2015");
		manager1.addEmployee("Mark Zuckerberg", "34 Brighton Grove", "0235262346", Department.SERVICE, "23/10/2015");
		manager1.addEmployee("Cian Downey", "38 Brighton Grove", "0335254346", Department.INFORMATION_TECHNOLOGY,"23/08/2012");
	}

	// Test to see if the expected value is equal to the output of
	// findMatchesForName.
	@Test
	public void findNameMatchesOrMismatches() throws ParseException {
		assertEquals(expected, manager1.findMatchesForName(input).toString());
	}

}
