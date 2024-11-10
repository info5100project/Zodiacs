package application;


import javafx.scene.image.Image;

public class UserProfile {
	int id;
	String firstName;
	String lastName;
	String age;
	String heightInInches;
	String weightInLbs;
	String userName;
	String password;
	Image image;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getHeightInInches() {
		return heightInInches;
	}
	public void setHeightInInches(String heightInInches) {
		this.heightInInches = heightInInches;
	}
	public String getWeightInLbs() {
		return weightInLbs;
	}
	public void setWeightInLbs(String weightInLbs) {
		this.weightInLbs = weightInLbs;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
}
