package dao;

import model.AnimalBasic;

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
	boolean saveFavoritePicture(int animalId, int userId);

	/************************************************
	 * 					Update Logic				*
	 ***********************************************/
	boolean updateDailyLikes(AnimalBasic animalBasic);
	
	boolean updateTotalLikes(AnimalBasic animalBasic);

	/************************************************
	 * 					Get Logic					*
	 ***********************************************/
}
