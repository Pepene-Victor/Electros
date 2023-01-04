package project.electro.server.enums;

public enum GenderEnum {

	MASCULINE(0),
		
	FEMININE(1),
		
	OTHER(2);
		
	private final int value;
		
	GenderEnum(int value) {
			this.value = value;
	}
		
	public int getValue() {
			return value;
	}
}
