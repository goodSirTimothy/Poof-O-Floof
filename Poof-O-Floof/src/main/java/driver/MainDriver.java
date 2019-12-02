package driver;

import dao.AnimalDao;
import dao.AnimalDaoImpl;
import model.AnimalBasic;

public class MainDriver {
	
	private static final AnimalDao animalDao = AnimalDaoImpl.getInstance();
	
	public static void main(String[] args) {
		
		AnimalBasic saveTest = new AnimalBasic(667, "tarantula", "brazillian black", "older than god", "m", "even bigger", "none");
		
		animalDao.saveBasicAnimal(saveTest);
	}
}
