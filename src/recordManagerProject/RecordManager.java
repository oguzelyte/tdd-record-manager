////////////////////////////////////////////////////////
//                                                    //
// Title:          RecordManager.java  //
// Author:         O. Guzelyte (160421859)            //
// Last modified:  29/11/2017                         //
//                                                    //
////////////////////////////////////////////////////////
/* CHANGES
 * AFTER TASK 2
 * Decided to include city specification for record manager.
 * Decided to make home address, phone nr, department as single inputs, concerned by the difficulty aspect.
 * Decided to make phone nr a String, as wouldn't go out of bounds for int values.
 * Decided to make an enum class Department for department values.
 * Decided to use Java's UUID to generate employee's unique ID.
 * Decided to add employeesToString method in order to display all info about employees on
 * screen efficiently.
 * AFTER TASK 3
 * Renamed searchByName to findMatchesForName on assumption the result would be clearer.
 * Decided to change name and address of an employee by a specified ID so included UUID parameter to changeName method and changeAddress method.
 * */
package recordManagerProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class RecordManager {
	
		
	private List<Employee> employees = new ArrayList<Employee>(); // List of all employees of a company.
	private String city; // City where company is based in.
	
	public RecordManager(String city) { // Constructor to specify the city of company.
		this.city = city;		
	}

    // Specify name, home address, phone nr, department, start work date and add an employee to a company.
    public void addEmployee(String name, String homeAddress, String phoneNr, Department department, String startWork) throws ParseException {
    	Employee empl = new Employee(); // Create an employee object.
    	empl.setName(name); // Add information about him.
    	empl.setHomeAddress(homeAddress);
    	empl.setPhoneNr(phoneNr);
    	empl.setDepartment(department);
    	if(isValidDate(startWork) == true) // Check if date specified is in valid format and not after current date.
    		empl.setStartWork(startWork); // If so, set start work date.
    	else empl.setStartWork("Invalid date inserted"); // Else specify date is invalid.
    	empl.setID(java.util.UUID.randomUUID()); // Set unique ID for employee.
    	employees.add(empl); // Add employee to the list.
    }
    
    // Check if entered date is valid format and less than today's date.
    private static boolean isValidDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy"); // Create a date format.
        try {            
        	format.parse(date.trim()); // Try to parse the date and see if it fits in specified format.
        } catch (ParseException pex) { // If doesn't, date is invalid.
        	return false;
        }
        // If date format is okay, get instance of today's date.
        String today = format.format(Calendar.getInstance().getTime());
        Date checkDate = format.parse(date); // Make date being checked into type Date.
        Date todayDate = format.parse(today); // Make today's date into type Date.
        if(checkDate.after(todayDate)) { // Check if date being checked is after today's date.
        	return false; // If so return false.
        }
        return true; // Else return true.
    }
      
    // Get list of all employees.
    public List<Employee> getEmployees() {
		return employees;
    }
    
    // Get information about all employees.
    public void employeesToString() {
    	if(employees.size()==0) { // If no employees.
    		System.out.println("There are no employees for company based in " + city + "."); // Write that there are no employees.
    	} else { 
    		System.out.println("Employees of record manager for city: " + city + "\n-----------------------------------------------\nFound " + employees.size() + " employees\n");
    		for(int j=0; j<employees.size(); j++) { // Else go through the list of all employees and invoke overridden toString() methods.
    			if(j==0)
    				System.out.print(employees.get(j).toString());
    			else
    				System.out.print("\n" + employees.get(j).toString());
    		}
    		System.out.println("\n");
    	}    	
    }
    
    /*
     * Adds a qualification to an employee with a given ID. Adds qualification's
     * full name, level and achieved date. If a specified employee does not exist
     * throws an error.
     *  
     * */
    public String addQualification(UUID ID, String fullName, String level, String dateAchieved) throws ParseException {
    	Boolean found = false;
    	
    	String result = "";
    	
    	for(int i=0; i<employees.size(); i++) {
    		if(employees.get(i).getID() == ID) {
    			employees.get(i).addQualification(fullName, level, dateAchieved);
    			found = true;
    			break;
    		}
    	}
    	if(found==false) {
    		result += "Employee with given ID does not exist.";
    	}
    	return result;
    }
     
    // Finds all matches for a given name and returns a list of employees
    public List <Employee> findMatchesForName(String name) {
    
    	int matchCounter = 0; // Set counter to zero
    	List <Employee> employeeMatches = new ArrayList<Employee>(); // List of possible matches
    	
    	for(int i=0; i<employees.size(); i++) { // From 0 to size of employees
    			if(employees.get(i).getName().equals(name)) { // See if name equals to given name
    				matchCounter++; // If so - increment the counter
    				employeeMatches.add(employees.get(i)); // Add a match to the list
    			}
    	}
    	
    	if(matchCounter == 0) { // If match counter equals to zero
    		System.out.println("NO MATCHES FOUND FOR NAME: " + name); // Print there are no matches as such
    	}
    	return employeeMatches; // Return matches
    
    }
    
    // Change name of a given employee ID
    public String changeName(UUID ID, String name) {
    	
    	// Result string is empty
    	String result = "";
    	
    	Boolean found = false; // Found no names so far
    	for(int i=0; i<employees.size(); i++) { // From zero until size of employees
    		if(employees.get(i).getID() == ID) { // See if there is employee with given ID
    			employees.get(i).setName(name); // If so then set the name of him/her to new name
    			found = true; // Employee is found
    			break; // Break out of cycle, employee found
    		}
    	}
    	
    	// If employee not found, write that such employee does not exist.
    	if(found == false) result += "Employee of ID " + ID + " does not exist. Name was not changed.";
    	
		return result; // Return result.
    }
    
    // Change address of a given employee ID
    public String changeAddress(UUID ID, String address) {
    	
    	// Result string is empty
    	String result = "";
    	
    	Boolean foundAddress = false; // Found no addresses so far
    	for(int i=0; i<employees.size(); i++) { // From zero until size of employees
    		if(employees.get(i).getID() == ID) {  // See if there is employee with given ID
    			employees.get(i).setHomeAddress(address); // If so then set the name of him/her to new address
    			foundAddress = true; // Employee is found
    			break; // Break out of cycle, employee found
    		}
    	}
    	
    	// If employee not found, write that such employee does not exist.
    	if(foundAddress == false) result = "Employee of ID " + ID + " does not exist. Address was not changed.";
		
    	return result; // Return result.
    }
    
    // Change address and name at the same time, for convenient use
    public void changeAddressAndName(UUID ID, String address, String name) {    	
    	changeAddress(ID, address);
    	changeName(ID, name);
    }

}
