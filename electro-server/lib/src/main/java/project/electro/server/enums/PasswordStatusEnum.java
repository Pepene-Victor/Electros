package project.electro.server.enums;

public enum PasswordStatusEnum {

	NEW(0),
	
	OLD(1),
		
	OK(2);
		
	private final int value;
		
	PasswordStatusEnum(int value) {
			this.value = value;
	}
		
	public int getValue() {
			return value;
	}
	
}
