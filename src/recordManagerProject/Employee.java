////////////////////////////////////////////////////////
//                                                    //
// Title:          Employee.java                      //
// Author:         O. Guzelyte (160421859)            //
// Last modified:  29/11/2017                         //
//                                                    //
////////////////////////////////////////////////////////
 /* CHANGES
 * AFTER TASK 2
 * Included a setID method upon realisation that record manager will generate the ID when it adds the employee.
 * Included a failed qualification string in the case of adding a qualification to a non existing employee.
 * AFTER TASK 3
 * Realised I'll need to get the name in order to search for names in RecordManager class, so included a getName method.
 * Created getters for department, startWork, phoneNr and address so I could use JUnit efficiently.
 * */
package recordManagerProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Employee {

	private UUID ID; // Unique employee ID.
	private String name = null; // Name of employee.
	private String homeAddress = null; // Home address of employee.
	private Department department = null; // Department.
	private String phoneNr; // Phone nr of employee.
	private String startWork; // Work start time of employee.
	private String failedQualification = ""; // If qualification failed to be added.
	private List<Qualification> qualifications = new ArrayList<Qualification>(); // List of all qualifications of employee.

	// Set name of employee.
	public void setName(String name) {
		this.name = name;
	}
	
	// Get name of employee.
	public String getName() {
		return name;
	}
	
	// Set ID of employee.
	public void setID(UUID ID) {
		this.ID = ID;
	}

	// Get ID of employee.
	public UUID getID() {
		return ID;		
	}
		
	// Set home Address.
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	// Get home Address.
	public String getHomeAddress() {
		return homeAddress;
	}

	// Set department employee works in.
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	// Get department employee works in.
	public Department getDepartment() {
		return department;
	}

	// Set start work.
	public void setStartWork(String startWork) {
		this.startWork = startWork;
	}
	
	// Set start work.
	public String getStartWork() {
		return startWork;
	}

	// Add qualification with specified full name, level and date achieved.
	public void addQualification(String fullName, String level, String dateAchieved) throws ParseException {
		if(Employee.class != null) { // If employee exists.
			Qualification qual = new Qualification(); // Create qualification object.
			qual.setFullName(fullName); // Set name of qualification.
			qual.setLevel(level); // Set level of qualification
			if(isValidDate(dateAchieved) == true) // Check if achieved date is valid.
	    		qual.setDateAchieved(dateAchieved); // If so, add it.
	    	else qual.setDateAchieved("Invalid date inserted"); // If not, date is invalid.
			qualifications.add(qual); // Add qualification to the list.
		}
		failedQualification = "\nError: can't add a qualification for a non-existent employee.\n"; // If employee does not exist, add a failure string.s
	}

	 // Is date valid.
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
	 
	// Set the phone number.
	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}
	
	// Set the phone number.
	public String getPhoneNr() {
		return phoneNr;
	}

	@Override
	public String toString() {
		// Put all information about employee into variable result.
		String result = "\nEmployee ID = " + ID + "\nName = " + name + "\nHome Address = " + homeAddress + "\nDepartment = " + department
				+ "\nPhone Nr = " + phoneNr + "\nStarted working = " + startWork + "\nQualifications = " + qualifications.size();
		// If no qualifications exist for this particular employee.
		if(qualifications.size()==0) {
			result += " - No qualifications added."; // Add that no qualifications were added.
			result += failedQualification; // If there was an error adding qualification, add an error string.
		} else { // Else from 0 to size of qualifications array, invoke the qualification toString method.
			for(int i=0; i<qualifications.size(); i++) {
				result += "\n\n" + (i+1) + ".";
				result += qualifications.get(i).toString();
		  }
		}
		return result;
	}
}
