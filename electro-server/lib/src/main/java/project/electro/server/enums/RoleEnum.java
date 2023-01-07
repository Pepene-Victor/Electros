package project.electro.server.enums;


public enum RoleEnum {
	
	ADMINISTRATOR(Code.ADMINISTRATOR),
	
	CUSTOMER(Code.CUSTOMER);
	
	private final String value;
	
	RoleEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public class Code {
		public static final String ADMINISTRATOR = "ROLE_ADMIN";
		
		public static final String CUSTOMER = "ROLE_CUSTOMER";
	}
}
