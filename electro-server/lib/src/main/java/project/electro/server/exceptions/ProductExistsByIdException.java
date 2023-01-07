package project.electro.server.exceptions;



public class ProductExistsByIdException extends Exception{

	private static final long serialVersionUID = 1L;

	public ProductExistsByIdException(Long long1) { 
		super("Product with pzn: " + long1 + " already exist");
	}
}
