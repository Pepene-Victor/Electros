package project.electro.server.exceptions;



public class UserExistsByUsernameException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserExistsByUsernameException(String username) {
		super("User already exists with username:  "+ username +"!!!");
		
	}
	
}
