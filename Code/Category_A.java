/**
 * @author 2512860
 *
 */

public class Category_A extends Entry {

	protected String council = "";
	protected String parish = "";

	public Category_A(int ref, String add, char cat, int d, int m, int y, String council, String parish) {
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
		String result = "REFERENCE: " + getReference() + "       ADDRESS: " + getAddress() + "       CATEGORY: "
				+ getCat() + "       LISTED: " + getDay() + "/" + getMonth() + "/" + getYear() + "       COUNCIL: "
				+ getCouncil() + "       PARISH: " + getParish() + "\n";
		return result;
	}
}
