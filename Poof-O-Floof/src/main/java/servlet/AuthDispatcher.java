package servlet;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import model.UserLogin;
import util.Json;

public class AuthDispatcher implements Dispatcher{

	private final UserDao userDao = UserDaoImpl.getInstance();
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public boolean supports(HttpServletRequest req) {
		return isLogin(req) || isCurrentUser(req);
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			UserLogin cred = (UserLogin) Json.read(req.getInputStream(), UserLogin.class);
			logger.trace(cred);
			User user = userDao.login(cred);
			
			if(user != null) {
				resp.setContentType(Json.CONTENT_TYPE);
				//storing user id in cookie because i dont want to store a name
				Cookie cookie = new Cookie("currentUser", Integer.toString(user.getUserId()));
				cookie.setPath("/Poof-O-Floof/api");
				resp.addCookie(cookie);
								
				resp.getOutputStream().write(Json.write(user));
				return;
			}
			else {
				resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

	private boolean isLogin(HttpServletRequest req) {
		return req.getMethod().equals("POST") && req.getRequestURI().equals("/Poof-O-Floof/api/login");
	}
	
	private boolean isCurrentUser(HttpServletRequest req) {
		return req.getMethod().equals("GET") && req.getRequestURI().equals("/Poof-O-Floof/api/info");
	}
}
