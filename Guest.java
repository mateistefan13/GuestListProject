
public class Guest {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;

	Guest(String firstName, String lastName, String email, String phoneNo) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	
	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getName() {
		return (this.firstName + " " + this.lastName); 
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getPhoneNo() {
		return this.phoneNo;
	}
	
	public boolean isUsed(Guest ob) {
		if (this.getName().equalsIgnoreCase(ob.getName()) || this.getEmail().equalsIgnoreCase(ob.getEmail())
		|| this.getPhoneNo().equalsIgnoreCase(ob.getPhoneNo())) {
			return true;
		}
		return false;
	}
	
	public boolean isNameUsed(String fullName) {
		if (this.getName().equalsIgnoreCase(fullName)) {
			return true;
		}
		return false;
	}
	
	public boolean isEmailUsed(String email) {
		if (this.getEmail().equalsIgnoreCase(email)) {
			return true;
		}
		return false;
	}
	
	public boolean isPhoneNoUsed(String phoneNo) {
		if (this.getPhoneNo().equalsIgnoreCase(phoneNo)) {
			return true;
		}
		return false;
	}
	
	
	
}
