package driver;


import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.AnimalDao;
import dao.AnimalDaoImpl;
import model.Photo;
import petfinder.PetFinderConnectionUtil;

/**
 * 
 * @author Tim
 *
 */
public class MainDriver {
	private static Logger logger = LogManager.getRootLogger();
<<<<<<< HEAD
	
	public static void main(String[] args) {
		//testPetFinder();
=======
	private static PetFinderConnectionUtil petFinderConnInst = PetFinderConnectionUtil.getInstance();
	private static final AnimalDao animalDao = AnimalDaoImpl.getInstance();
	
	public static void main(String[] args) {
		//testPetFinder();
		//testSaveDao();
		testGetFavoriteList();
>>>>>>> 70eb80283e5bac4a88b5f9fa466d7749a0589e85
	}
	
	private static void testPetFinder() {
		PetFinderConnectionUtil petFinderConnInst = PetFinderConnectionUtil.getInstance();
		try {
			petFinderConnInst.requestNewToken();
		    logger.debug(petFinderConnInst.getCurrentToken());
		    petFinderConnInst.requestAnimalsByLocation("38.953524,-77.350450", 20);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private static void testGetFavoriteList() {
		logger.trace(animalDao.getFavoriteList(1));
	}
	
	private static void testSaveDao() {
		Photo p = new Photo("111", "111", "javadog.com");
		logger.trace(animalDao.savePhoto(p));
		logger.trace(animalDao.saveFavorite(2, "111"));
		
	}
}
