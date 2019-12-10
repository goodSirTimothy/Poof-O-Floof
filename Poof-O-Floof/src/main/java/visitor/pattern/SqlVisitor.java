package visitor.pattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Photo;

public class SqlVisitor {
	private static SqlVisitor instance = new SqlVisitor();

	private SqlVisitor() {
	}

	public static SqlVisitor getInstance() {
		return instance;
	}

	private static final String SELECT_ALL = "SELECT * FROM ";
	private static final String SELECT_PHOTO_BY_USERID = SELECT_ALL + "photo WHERE user_id = ?";
	private static final String SAVE_USERS = "INSERT INTO users (user_id, current_ip, current_ip_location, display_name, email, secure_key, salt) "
			+ "VALUES(user_id_seq.nextval, ?, ?, ?, ?, ?, ?)";
	private static final String SAVE_PHOTO = "INSERT INTO photo (id, photo_id, animal_id, animal_type, full_url, url) "
			+ "VALUES (photo_id_seq.nextval, ?, ?, ?, ?, ?)";

	/**
	 * Likes
	 */
	private static final String GET_DAILY_LIKES = "SELECT likes FROM daily_likes WHERE species = ?";
	private static final String UPDATE_DAILY_LIKES = "UPDATE daily_likes SET likes = ? WHERE species = ?";
	private static final String INSERT_DAILY_LIKES = "INSERT INTO daily_likes (daily_likes_id, species, likes, current_date) "
			+ "VALUES (daily_likes_id.next, ?, 1, CURRENT_TIMESTAMP)";

	/*************************************************************************************
	 * get Logic *
	 ************************************************************************************/
	/**
	 * 
	 * @param conn
	 * @param userId
	 * @return a {@link PreparedStatement}
	 * @throws SQLException
	 */
	public PreparedStatement selectPhotoByUserId(Connection conn, int userId) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SELECT_PHOTO_BY_USERID);
		ps.setInt(1, userId);
		return ps;
	}

	/*************************************************************************************
	 * Save Logic *
	 ************************************************************************************/

	public PreparedStatement saveUsers(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SAVE_USERS);
		return ps;
	}

	public PreparedStatement savePhoto(Connection conn, int userId, Photo photo) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(SAVE_PHOTO);
		int stIndex = 0;
		ps.setInt(++stIndex, photo.getAnimalId());
		ps.setInt(++stIndex, photo.getPhotoId());
		ps.setString(++stIndex, photo.getType());
		ps.setString(++stIndex, photo.getFullUrl());
		ps.setString(++stIndex, photo.getUrl());
		return ps;
	}
}
