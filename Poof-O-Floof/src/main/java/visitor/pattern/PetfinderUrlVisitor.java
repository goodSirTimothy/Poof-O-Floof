package visitor.pattern;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PetfinderUrlVisitor {
	private static Logger logger = LogManager.getRootLogger();
	private static Properties props = getProperties();
	private static final String PET_FINDER_API_KEY = props.getProperty("petfinder.key");
	private static final String PET_FINDER_SECRET = props.getProperty("petfinder.secret");
	
	private static final String TOKEN_URL = "https://api.petfinder.com/v2/oauth2/token";
	private static final String PET_FINDER_API_ROOT = "https://api.petfinder.com/v2/";
	
	public String getAuth() {
		return PET_FINDER_API_KEY + ":" + PET_FINDER_SECRET;
	}

	public URL makePetfinderURL(String str) throws MalformedURLException {
		return new URL(str);
	}
	
	public String getTokenString() {
		return TOKEN_URL;
	}

	public URL getTokenURL() throws MalformedURLException {
		return new URL(TOKEN_URL);
	}

	public String getPetFinderApiRootString() {
		return PET_FINDER_API_ROOT;
	}

	public URL getPetFinderApiRootURL() throws MalformedURLException {
		return new URL(PET_FINDER_API_ROOT);
	}

	public String getPetFinderAnimalsString() {
		return PET_FINDER_API_ROOT + "animals";
	}
	
	public String getLocationString(String location, int radius) {
		return String.format("?location=%s&radius=%d", location, radius);
	}
	


	/**
	 * load properties from application.properties
	 * 
	 * @return
	 */
	private static Properties getProperties() {
		try {
			Properties props = new Properties();
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
			return props;
		} catch (IOException | NullPointerException e) {
			logger.error("Unable to locate Properties at src/main/resources/application.properties");
			logger.error("Stack Trace: ", e);
			throw new RuntimeException("Check Logs; Failed to get properties");
		}
	}
}
