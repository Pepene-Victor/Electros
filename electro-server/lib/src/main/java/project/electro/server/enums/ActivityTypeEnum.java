package project.electro.server.enums;

public enum ActivityTypeEnum {

	CREATE(0),
	
	DELETE(1),
		
	UPDATE(2),
	
	REQUEST(3);
		
	private final int value;
		
	ActivityTypeEnum(int value) {
			this.value = value;
	}
		
	public int getValue() {
			return value;
	}
}
