package project.electro.server.controllers.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import project.electro.server.exceptions.ProductExistsByIdException;
import project.electro.server.exceptions.UserExistsByUsernameException;


@ControllerAdvice
public class ControllerExceptionsHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> dataIntegrityException(DataIntegrityViolationException e) {
		
		return new ResponseEntity<>("Data integrity violation", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e){
		
		StringBuilder sb = new StringBuilder();
		
		e.getAllErrors().stream().forEach(exception -> {
			sb.append(exception.getDefaultMessage() + "\n");
		});
		
		return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserExistsByUsernameException.class)
	public ResponseEntity<String> userExistsByEmailException( UserExistsByUsernameException e){
		
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductExistsByIdException.class)
	public ResponseEntity<String> handle(ProductExistsByIdException e, 
            HttpServletRequest request, HttpServletResponse response) {
	    
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}

