package model;

public class Orders {
	private int orderID;
	private String status;

	public void vOrders(int orderID, String status){
		this.orderID = orderID;
		this.status = status;
	}

	public bool setOrderID(int orderID){
		
		if (orderID > 0) {
			this.orderID = orderID;
			return True;
		}

		else {
			return False;
		}
	}

	public bool setStatus(String status){
		this.status = status;
		return True;
	}
}
