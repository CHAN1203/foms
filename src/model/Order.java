package model;

import java.io.Serializable;
import java.util.HashMap;
import enums. *;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// attributes 
	private int orderID;
	private OrderStatus status;
	private HashMap<String, Integer> itemsInOrder; // <Item Name, Quantity>
	
	// constructor
	
	public Order(int orderID, OrderStatus status){
		this.orderID = orderID;
		this.status = status;
		this.itemsInOrder = null;
	}
	
	// set methods

	public boolean setOrderID(int orderID){
		this.orderID = orderID;
		return true;
	}

	public boolean setStatus(OrderStatus status){
		this.status = status;
		return true;
	}
	
	public boolean addItem(String item, Integer quantity) {
		
		// if item is already present, add to quantity only
		
		if (itemsInOrder.containsKey(item)) {
			int currentItemQuantity = itemsInOrder.get(item);
			itemsInOrder.put(item, currentItemQuantity + quantity);
			return true;
		}
		
		// item is absent, add new item to order
		itemsInOrder.put(item, quantity);
		return true;
	}
	
	// get methods
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public OrderStatus getStatus() {
		return this.status;
	}
	
	public HashMap<String, Integer> getItemsInOrder(){
		return this.itemsInOrder;
	}
}