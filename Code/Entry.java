/**
 * @author 2512860
 *
 */

import java.awt.Font;
import java.util.*;

public abstract class Entry {
	
	private int reference;
	private String address;
	private char category;
	private Calendar listed;
	
	
/**
 * Constructor for entry
 * @param ref- reference
 * @param add - address
 * @param cat - category
 * @param d - day 
 * @param m - month 
 * @param y - year
 */
	public Entry(int ref, String add, char cat, int d, int m, int y) {
		reference =  ref;
		address = add;
		category = cat;
		Calendar inst = Calendar.getInstance();
		inst.set(y, m-1, d);
		listed = inst;	

	}
	
	public int getReference()
	{
		return reference;
	}
	
	public String getAddress() {
		return address;
	}
	
	public char getCat() {
		return category;
	}
	
	public int getDay () {
	    return listed.get(Calendar.DATE);
	  } //getDay
	  
	  public int getMonth () {
	    int month =  listed.get(Calendar.MONTH) + 1;
	    return month;
	  } //getMonth
	  
	  public int getYear () {
	    return listed.get(Calendar.YEAR);
	  } //getYear
	  

	public String getEntry() {
		String result = "reference: " + getReference() +"       address: " + getAddress()+"       category: " + getCat() +"       listed: " + getDay()  +"/"+ getMonth() +"/" + getYear() +"\n";
		return result;
	}

}
