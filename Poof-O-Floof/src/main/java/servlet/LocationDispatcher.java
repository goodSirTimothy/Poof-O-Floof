package servlet;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.LocationRequest;
import petfinder.PetFinderConnectionUtil;
import util.Json;

public class LocationDispatcher implements Dispatcher {

  private static final Logger logger = LogManager.getLogger();

  private static final int RADIUS = 50;

  @Override
  public boolean supports(HttpServletRequest request) {
    logger.info(isPostLocation(request));
    return isPostLocation(request);
  }

  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) {
    // to do:
    // take in json object instead of string - done
    // write list of animals in way thats easier to work with
    // pagination or something
    try {
      // get coordinates
      LocationRequest locReq = (LocationRequest) Json.read(request.getInputStream(),
          LocationRequest.class);
      String location = locReq.getCoords();
      logger.debug("location is: " + location);

      // put coordinates into thing
      try {
        PetFinderConnectionUtil pfcu = PetFinderConnectionUtil.getInstance();
        pfcu.requestNewToken();
        String animalList = pfcu.requestAnimalsByLocation(location, RADIUS);

        // pass only photos to front end
        logger.debug("A Photo Bundle is sent to the requester.");
        logger.trace("Animal sent:" + animalList);
        response.setHeader("Content-type", "application/json");
        response.getWriter().write(animalList);
        // response.getOutputStream().write(Json.write(animalList));
        return;

      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean isPostLocation(HttpServletRequest req) {
    return req.getMethod().equals("POST")
        && req.getRequestURI().contentEquals("/Poof-O-Floof/api/location");
  }

}
