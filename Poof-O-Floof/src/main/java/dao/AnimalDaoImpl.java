package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.AnimalBasic;
import model.AnimalFull;
import model.Photo;
import util.ConnectionUtil;
import util.Exceptions;

public class AnimalDaoImpl implements AnimalDao {

	private static final AnimalDao instance = new AnimalDaoImpl();
	
	private AnimalDaoImpl() {}
	
	public static AnimalDao getInstance() {
		return instance;
	}
	
	public static final String SAVE_ANIMAL = "INSERT INTO animal (animal_id, animal_type, species, age, gender, animal_size) " + 
			"VALUES (?, ?, ?, ?, ?, ?)";
	
	public static final String CHECK_PHOTO = "SELECT photo_id FROM photo WHERE photo_id = ?";
	public static final String SAVE_PHOTO = "INSERT INTO photo (animal_id, photo_id, full_url, type) " +
			"VALUES (?, ?, ?, ?)";
	public static final String SAVE_FAVORITE = "INSERT INTO favorite (favorite_id, user_id, photo_id) " +
		    "VALUES (favorite_id_seq.nextval, ?, ?)";
	
	public static final String GET_DAILY_LIKES = "SELECT likes FROM daily_likes WHERE species = ?";
	public static final String UPDATE_DAILY_LIKES = "UPDATE daily_likes SET likes = ? WHERE species = ?";
	public static final String INSERT_DAILY_LIKES = "INSERT INTO daily_likes (daily_likes_id, species, likes, current_date) " +
			"VALUES (daily_likes_id.next, ?, 1, CURRENT_TIMESTAMP)";
	public static final String GET_FAVORITE_PHOTO_LIST = "SELECT * FROM favorite f INNER JOIN photo p "
			+ "ON (f.photo_id = p.photo_id) WHERE user_id = ?";

	private final Logger logger = LogManager.getLogger(getClass());
	
	//unused
	public boolean saveFullAnimal(AnimalFull animalFull) {
		// TODO Auto-generated method stub
		return false;
	}

	//unused, probably broken
	public boolean saveBasicAnimal(AnimalBasic animalBasic) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = SAVE_ANIMAL;
			
			int stIndex = 0;
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(++stIndex, animalBasic.getId());
			ps.setString(++stIndex, animalBasic.getType());
			ps.setString(++stIndex, animalBasic.getSpecies());
			ps.setString(++stIndex, animalBasic.getAge());
			ps.setString(++stIndex, animalBasic.getGender());
			ps.setString(++stIndex, animalBasic.getSize());
			
			return ps.executeUpdate() == 1;
			
		}
		catch(SQLException e) {
			Exceptions.logSQLException(e);
			return false;
		}
	}
	
	@Override
	public boolean savePhoto(Photo photo) {
		try(Connection conn = ConnectionUtil.getConnection()){
			//save photo if photo is not already in database
			//check if photo is in database
			PreparedStatement ps1 = conn.prepareStatement(CHECK_PHOTO);
			int stIndex = 0;
			ps1.setInt(++stIndex, photo.getPhotoId());
			
			ResultSet rs = ps1.executeQuery();
			
			//if result set is empty, then add photo
			if(rs.next()) {
				logger.info("photo already in database");
				return true;
			}
			else {
				PreparedStatement ps2 = conn.prepareStatement(SAVE_PHOTO);
				stIndex = 0;
				ps2.setInt(++stIndex, photo.getAnimalId());
				ps2.setInt(++stIndex, photo.getPhotoId());
				ps2.setString(++stIndex, photo.getFullUrl());
				ps2.setString(++stIndex, photo.getType());
				
				return ps2.executeUpdate() == 1;
			}
		}
		catch(SQLException e) {
			Exceptions.logSQLException(e);
			return false;
		}
	}

	@Override
	public boolean saveFavorite(int userId, int photoId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			//save favorite
			PreparedStatement ps = conn.prepareStatement(SAVE_FAVORITE);
			
			int stIndex = 0;
			ps.setInt(++stIndex, userId);
			ps.setInt(++stIndex, photoId);
			
			return ps.executeUpdate() == 1;
		}
		catch(SQLException e) {
			Exceptions.logSQLException(e);
			return false;
		}
	}

	
	@Override
	public List<Photo> getFavoriteList(int userId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement(GET_FAVORITE_PHOTO_LIST);
			
			int stIndex = 0;
			ps.setInt(++stIndex, userId);
			
			ResultSet rs = ps.executeQuery();
			
			List<Photo> photoList = new ArrayList<Photo>();
			
			while(rs.next()) {
				int animalId = rs.getInt("animal_id");
				int photoId = rs.getInt("photo_id");
				String fullUrl = rs.getString("full_url");
				String type = rs.getString("type");
				photoList.add(new Photo(animalId, photoId, fullUrl, type));
			}
			
			return photoList;
		}
		catch(SQLException e) {
			Exceptions.logSQLException(e);
			return null;
		}
	}
	
	@Override
	public boolean updateDailyLikes(AnimalBasic animalBasic) {
		//check if species is already in table
		//if not, insert
		//if yes, get number of likes and increment
		try(Connection conn = ConnectionUtil.getConnection()){
			//gets 1 row containing only the number of likes
			PreparedStatement getStmt = conn.prepareStatement(GET_DAILY_LIKES);
			int stIndex = 0;
			getStmt.setString(1, animalBasic.getSpecies());
			
			ResultSet rs = getStmt.executeQuery();
			
			//species is already in table
			if(rs.next()) {
				int numLikes = rs.getInt("likes");
				numLikes++;
				PreparedStatement incrStmt = conn.prepareStatement(UPDATE_DAILY_LIKES);
				stIndex = 0;
				incrStmt.setInt(++stIndex, numLikes);
				incrStmt.setString(++stIndex, animalBasic.getSpecies());
				
				return incrStmt.executeUpdate() == 1;
			}
			//species is not in table
			else {
				PreparedStatement insrStmt = conn.prepareStatement(INSERT_DAILY_LIKES);
				stIndex = 0;
				insrStmt.setString(++stIndex, animalBasic.getSpecies());
				
				return insrStmt.executeUpdate() == 1;
				
			}
			
		}
		catch(SQLException e) {
			Exceptions.logSQLException(e);
			return false;
		}
	}

	@Override
	public boolean updateTotalLikes(AnimalBasic animalBasic) {
		// TODO Auto-generated method stub
		return false;
	}






}
