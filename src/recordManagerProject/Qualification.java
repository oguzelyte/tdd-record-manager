//////////////////////////////////////////////////////////
//                                                      //
// Title:          Qualification.java                   //
// Author:         O. Guzelyte (160421859)              //
// Last modified:  29/11/2017                           //
//                                                      //
//////////////////////////////////////////////////////////
/* CHANGES
 * AFTER TASK 2
 * Decided to override toString in order to get all information
 * about a qualification to efficiently display in RecordManagerTester
 * class.
 * 
 * */
package recordManagerProject;


public class Qualification {
	
	private String fullName; // Full name of qualification.
	private String level; // Qualification level.
	private String dateAchieved; // Date achieved.

	// Set full name.
	public void setFullName(String fullName) {
		this.fullName = fullName;		
	}
	
	// Set level.
	public void setLevel(String level) {
		this.level = level;		
	}
	
	// Set date achieved.
	public void setDateAchieved(String dateAchieved) {
		this.dateAchieved = dateAchieved;		
	}
	

	@Override
	public String toString() {
		// Return information about the qualification.
		return "Full Name = " + fullName + "\nLevel = " + level + "\nDate Achieved = " + dateAchieved;
	}
	

}
