package application.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import application.UserProfile;

public class UserProfileManager {

	private final Connection connection;

	public UserProfileManager(Connection connection) throws SQLException {
		this.connection = connection;
		createTableIfNotExists();
	}

	public boolean addUser(UserProfile userProfile) {

		String query = "INSERT INTO Users (FirstName, LastName, Age, Height, Weight, UserName, Password )  "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, userProfile.getFirstName());
			preparedStatement.setString(2, userProfile.getLastName());
			preparedStatement.setString(3, userProfile.getAge());
			preparedStatement.setString(4, userProfile.getHeightInInches());
			preparedStatement.setString(5, userProfile.getWeightInLbs());
			preparedStatement.setString(6, userProfile.getUserName());
			preparedStatement.setString(7, userProfile.getPassword());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException exception) {
			return false;
		}

	}
	
	public UserProfile findUserByUserName(String userName) {

		UserProfile foundProfile = null;

		try (PreparedStatement prepareStatement = connection
				.prepareStatement("SELECT * FROM Users WHERE userName = ?")) {
			prepareStatement.setString(1, userName);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				foundProfile = mapResultToProfile(resultSet);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return foundProfile;
	}
		

	public void removeUser() {
	}

	public UserProfile updateUser(UserProfile profile) {
		UserProfile foundProfile = null;

		String contactUpdateQuery = "UPDATE Users SET " + "FirstName = ?, " + "LastName = ?, " + "Age = ?, "
				+ "Height = ?, " + "Weight = ?, " + "UserName = ?, " + "Password = ? "
				+ "WHERE UserID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(contactUpdateQuery)) {
			preparedStatement.setString(1, profile.getFirstName());
			preparedStatement.setString(2, profile.getLastName());
			preparedStatement.setString(3, profile.getAge());
			preparedStatement.setString(4, profile.getHeightInInches());
			preparedStatement.setString(5, profile.getWeightInLbs());
			preparedStatement.setString(6, profile.getUserName());
			preparedStatement.setString(7, profile.getPassword());
			preparedStatement.setLong(8, profile.getId());
			
			boolean updated = preparedStatement.executeUpdate() > 0;
			if (updated) {
				foundProfile = findUserByUserId(profile.getId());
			}
		} catch (SQLException exception) {
		}
		return foundProfile;
	}

	public UserProfile findUserByUserId(int userId) {

		UserProfile foundProfile = null;

		try (PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM Users WHERE userid = ?")) {
			prepareStatement.setLong(1, userId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				foundProfile = mapResultToProfile(resultSet);
			}
		} catch (SQLException exception) {
		}
		return foundProfile;
	}

	private UserProfile mapResultToProfile(ResultSet resultSet) throws SQLException {
		UserProfile foundProfile = new UserProfile();
		foundProfile.setFirstName(resultSet.getString("FirstName"));
		foundProfile.setLastName(resultSet.getString("LastName"));
		foundProfile.setAge(resultSet.getString("Age"));
		foundProfile.setHeightInInches(resultSet.getString("Height"));
		foundProfile.setWeightInLbs(resultSet.getString("Weight"));
		foundProfile.setUserName(resultSet.getString("UserName"));
		foundProfile.setPassword(resultSet.getString("Password"));
		foundProfile.setId(resultSet.getInt("UserID"));
		return foundProfile;
	}

	private void createTableIfNotExists() {
		try (Statement statement = connection.createStatement()) {
			statement.execute("SELECT * FROM Users");
		} catch (SQLException ex) {
			createTable();
		}
	}

	private void createTable() {
		String sql = "CREATE TABLE Users (" + "UserID" + " integer primary key," + "FirstName" + " VARCHAR,"
				+ "LastName" + " VARCHAR," + "Age" + " VARCHAR," + "Height" + " VARCHAR," + "Weight" + " VARCHAR," 
				+ "UserName" + " VARCHAR," + "Password"	+ "  VARCHAR,";
		try (Statement statement = connection.createStatement()) {
			statement.execute(sql);
		} catch (SQLException exception) {
			System.out.println("Error occured when creating table");
		}
	}

}
