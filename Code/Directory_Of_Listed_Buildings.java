
/**
 * @author 2512860
 *
 */
// Implementation for Directory of listed buildings as an array list

import java.util.*;

public class Directory_Of_Listed_Buildings {
	protected List<Entry> dolb; // new list called dolb

	public Directory_Of_Listed_Buildings() {
		dolb = new ArrayList<Entry>(); // dolb is new array list of type entry
	}

	/**
	 * Add a record to the list
	 */
	public void addEntry(Entry e) {
		dolb.add(e);
	}

	/**
	 * Display All Entries in the array list
	 * @return string of entries called result
	 */
	public String displayAllEntries() {
		String result = ""; // declare string result
		ListIterator<Entry> iter = dolb.listIterator(); // create list iterator
		while (iter.hasNext()) { // when there is a next line
			Entry current = iter.next(); // set the curent entry as the next line
			result += current.getEntry(); // add to the result the current entry and restart loop
		}
		return result; // return full string result
	}

	/**
	 * @param i is the index at which the loop is at
	 *Gets current line using integer passed from method call  
	 */
	public String getCurrentLine(int i) {
		String result = "";
		Entry current = dolb.get(i); // current entry is set to the entry at position "i" in the array list
		result = current.getEntry(); // result is set to that entry 
		return result; // result is returned

	}

	/**
	 * 
	 * @returns the size of the array
	 */
	public int getSize() {
		int i = dolb.size() - 1;
		return i;
	}

	/**
	 * 
	 * @param userInput = the address the user is searching for
	 * @return
	 */
	public String sortByAddress(String userInput) {
		String result = "No entries found"; // set initial result as no entries found
		ListIterator<Entry> iter = dolb.listIterator(); // creates iterator 
		while (iter.hasNext()) { // while there is a next line
			Entry current = iter.next(); // current entry is set to that next line.
			if (current.getAddress().contains(userInput)) { // if the current entry adress field contains the user input 
				if (result.equals("No entries found")) { // if result is equal to "no entries found" - this means basically if no entry has contained the user input yet
					result = current.getEntry(); // replace no entries found with the result
				}
				else { // else if there already are results found
					result += current.getEntry(); // add to the already found results
				}
			}
		}
		return result; // return result
	}

	/**
	 * sorts by date using the users input
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	public String sortByDate(int d, int m, int y) {
		ListIterator<Entry> iter = dolb.listIterator(); // creates list iterator
		String result = "No entries found"; // sets intial result to no entries found
		while (iter.hasNext()) { // if there is a next line
			Entry current = iter.next(); // set current entry to that line
			if (current.getDay() == d && current.getMonth() == m && current.getYear() == y) { // if the user inputs matches the current entry day month and year. (All three)
				if (result.equals("No entries found")) { // if result is equal to "no entries found" - this means basically if no entry has contained the user input yet
					result = current.getEntry(); // replace no entries found with the result
				}
				else { // else if there already are results found
					result += current.getEntry(); // add to the already found reesults
				}
			}
		}
		return result; // return result
	}

	/**
	 * Checks if reference already exists 
	 * @param ref is the user reference number
	 */
	public boolean ifReferenceExists(int ref) {
		boolean exists = false; // set the boolean exists variable to false 
		ListIterator<Entry> iter = dolb.listIterator(); // creates iterator
		while (iter.hasNext()) { // while there is a next line
			Entry current = iter.next();// current entry is set to the next line
			if (ref == current.getReference()) { // if the user reference number is equal to the current entry reference number
				exists = true; // exists is set to true
				break; // break out of the loop as no more searching is needed
			} else
				exists = false; // else exists stays as false
		}
		return exists; // return exists
	}

}
