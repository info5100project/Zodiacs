package application;

import java.sql.SQLException;

import application.model.UserProfile;
import application.repository.ConnectionManager;
import application.repository.UserProfileManager;

public class UserTest {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		// Insert User Profile
		UserProfile up1 = new UserProfile();
		up1.setFirstName("John1");
		up1.setLastName("Jay1");
		up1.setAge("20");
		up1.setHeightInInches("65");
		up1.setWeightInLbs("120");
		up1.setUserName("JohnJay1");
		up1.setPassword("JohnJayPassword1");
//		
		UserProfile up2 = new UserProfile();
		up2.setFirstName("John2");
		up2.setLastName("Jay2");
		up2.setAge("25");
		up2.setHeightInInches("75");
		up2.setWeightInLbs("160");
		up2.setUserName("JohnJay2");
		up2.setPassword("JohnJayPassword2");
//		
		UserProfileManager manager = new UserProfileManager(ConnectionManager.getConnection());
		manager.addUser(up1);
		manager.addUser(up2);
		System.out.println("+++++++++++++++++++++ Completed");
		
		
		// Update User Profile
		up1.setFirstName("John11");
		up1.setLastName("Jay11");
		up1.setAge("33");
		up1.setHeightInInches("66");
		up1.setWeightInLbs("121");
		up1.setUserName("JohnJay11");
		up1.setPassword("JohnJayPassword11");
		manager.updateUser(up1);
		
		
		// Search User Profile
		UserProfile userProfile = manager.findUserByUserId(2);
		System.out.println("UserName: " + userProfile.getUserName());
		System.out.println("Password: " + userProfile.getPassword());
		System.out.println("FirstName: " + userProfile.getFirstName());
		System.out.println("LastName: " + userProfile.getLastName());
		System.out.println("Age: " + userProfile.getAge());
		System.out.println("Height: " + userProfile.getHeightInInches());
		System.out.println("Weight: " + userProfile.getWeightInLbs());
		System.out.println("UserId: " + userProfile.getId());
		
	}

}
