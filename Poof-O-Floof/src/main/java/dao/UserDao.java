package dao;

import model.User;
import model.UserLogin;

public interface UserDao {
	User login(UserLogin loginCredentials);
	
	boolean createUser(User user, UserLogin loginCredentials);
}
