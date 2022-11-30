package project.electro.server.enums;

public enum StatusEnum {

	FREE(0),
	
	BLOCKED(1),
	
	PENDING(2);
	
	private final int value;
	
	StatusEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
