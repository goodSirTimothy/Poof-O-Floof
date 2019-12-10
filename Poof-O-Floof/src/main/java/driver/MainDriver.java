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
	private static PetFinderConnectionUtil petFinderConnInst = PetFinderConnectionUtil.getInstance();
	private static final AnimalDao animalDao = AnimalDaoImpl.getInstance();
	
	public static void main(String[] args) {
		//testPetFinder();
		//testSaveDao();
		testGetFavoriteList();
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
		Photo p = new Photo(113, 121, "javadog.com", "spider");
		logger.trace(animalDao.savePhoto(p));
		logger.trace(animalDao.saveFavorite(2, 121));
		
	}
}
