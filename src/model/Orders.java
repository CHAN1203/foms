package model;

public class Orders {
	private int orderID;
	private String status;

	
	// constructors
	
	public Orders(int orderID, String status){
		this.orderID = orderID;
		this.status = status;
	}
	
	// set methods

	public boolean setOrderID(int orderID){
		this.orderID = orderID;
		return true;
	}

	public boolean setStatus(String status){
		this.status = status;
		return true;
	}
	
	// get methods
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public String getStatus() {
		return this.status;
	}
}