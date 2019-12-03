package dao;

import model.User;
import model.UserLogin;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(UserLogin loginCredentials) {
		//check if user with those credentials is in database
		//if yes, return user
		//if no, log error
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createUser(User user, UserLogin loginCredentials) {
		// TODO Auto-generated method stub
		return false;
	}

}
