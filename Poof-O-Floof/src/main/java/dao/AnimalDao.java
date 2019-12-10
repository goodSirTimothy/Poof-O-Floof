package dao;

import java.util.List;

import model.AnimalBasic;
import model.Photo;

public interface AnimalDao {

	/************************************************
	 * 					Save Logic					*
	 ***********************************************/
	/**
	 * save the information of a favorite picture
	 * @param animalId = the animalID (with this, when can query the DB and retrieve all information
	 * @param userId = the user that wanted to save 
	 * @return
	 */
	
	boolean savePhoto(Photo photo);
	
	boolean saveFavorite(int userId, int photoId);
	
	List<Photo> getFavoriteList(int userId);

	/************************************************
	 * 					Update Logic				*
	 ***********************************************/
	//may need a function to add new column to likes unless it should be handled here
	
	boolean updateDailyLikes(AnimalBasic animalBasic);
	
	//maybe have a function to reset daily likes
	
	boolean updateTotalLikes(AnimalBasic animalBasic);

	/************************************************
	 * 					Get Logic					*
	 ***********************************************/
}
