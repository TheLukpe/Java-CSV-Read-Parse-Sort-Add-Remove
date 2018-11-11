/**
 * @author 2512860
 *
 */

public class Category_B extends Entry {

	protected String council = "";
	protected String parish = "";

	public Category_B(int ref, String add, char cat, int d, int m, int y, String council, String parish) {
		super(ref, add, cat, d, m, y);
		this.council = council;
		this.parish = parish;
	}

	public String getCouncil() {
		return council;
	}

	public String getParish() {
		return parish;
	}

	public String getEntry() {
		String result = "reference: " + getReference() + "       address: " + getAddress() + "       category: "
				+ getCat() + "       listed: " + getDay() + "/" + getMonth() + "/" + getYear() + "       council: "
				+ getCouncil() + "       parish: " + getParish() + "\n";
		return result;
	}

}
