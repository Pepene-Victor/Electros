package project.electro.server.enums;

public enum OrderStatusEnum {


	CANCELLED(0),
	
	APPROVED(1),
		
	PENDING(2);
		
	private final int value;
		
	OrderStatusEnum(int value) {
			this.value = value;
	}
		
	public int getValue() {
			return value;
	}
	
}
