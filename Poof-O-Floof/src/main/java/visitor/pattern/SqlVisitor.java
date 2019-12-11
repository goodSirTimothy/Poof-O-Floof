package visitor.pattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Photo;
import model.User;
import model.UserCreation;
import model.UserLogin;

public interface SqlVisitor {

	/**
	 * 
	 * @param conn
	 * @param userId
	 * @return a {@link PreparedStatement}
	 * @throws SQLException
	 */
	PreparedStatement selectPhotoByUserId(Connection conn, int userId) throws SQLException;
	
	PreparedStatement selectUserByUsername(Connection conn, String username) throws SQLException;
	
	PreparedStatement login(Connection conn, UserLogin loginCredentials) throws SQLException;

	PreparedStatement saveUsers(Connection conn, User user) throws SQLException;

	PreparedStatement savePhoto(Connection conn, int userId, Photo photo) throws SQLException;

}
