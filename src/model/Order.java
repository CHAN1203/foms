package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import helper.Helper;
import repository.Repository;
import enums.*;

public class Order implements Serializable, Comparable<Orders>{
	
	private String orderId;
	
	private String dateTime;
	
	private double totalBill;
	
	private HashMap<MenuItem, Integer> itemsInOrder;
	
	private String remarks;
	
	private String branchName;
	
	private OrderStatus status;
	
	public Order(String orderId, String dateTime, String branchName) {
		this.orderId = orderId;
		this.dateTime = dateTime;
		this.branchName = branchName;
		this.totalBill = 0;
		this.remarks = "No Remarks";
		this.itemsInOrder = new HashMap<MenuItems, Integer>();
	}
	
	public void addOrderItem(MenuItems menuItem, int amount){
        if (itemsInOrder.containsKey(menuItem)){
            int currAmount = currentOrders.get(menuItem);
            itemsInOrder.put(menuItem, currAmount + amount);
        }
        else{
        	itemsInOrder.put(menuItem, amount);
        }
        totalBill += (menuItem.getPrice() * amount);
    }
	
	public boolean removeOrderItem(MenuItems toBeRemoved, int amount) {

        int currAmount = itemsInOrder.get(toBeRemoved);
        if (amount <= currAmount) {
            if (amount == currAmount) {
            	itemsInOrder.remove(toBeRemoved);
            } else {
            	itemsInOrder.put(toBeRemoved, currAmount - amount);
            }
            totalBill -= (toBeRemoved.getPrice() * amount);
            return true;
        } else {
            System.out.println("");
            return false;
        }
    }
	
	public MenuItem findOrderItem(String name) {
        for (MenuItem menuItem : itemsInOrder.keySet()) {
            if (menuItem.getName().equalsIgnoreCase(name)){
                return menuItem;
            }
        }
        return null;
    }
	
	public HashMap<MenuItem, Integer> getItemsInOrder() {
        return itemsInOrder;
    }
	
	public String getOrderId() {
		return orderId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public double getTotalBill() {
		return totalBill;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public String getRemarks() {
		return remarks;
	}

	public String getBranchName() {
		return branchName;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public boolean setTotalBill(double totalBill) {
		if(totalBill<0) {
			return false;
		}
		this.totalBill = totalBill;
		return true;
	}
	
	@Override
    public int compareTo(Order order) {
        if (this == order) {
            return 0;
        }
        int thisOrderId = Integer.parseInt(this.getOrderId().substring(1));
        int thatOrderId = Integer.parseInt(order.getOrderId().substring(1));

        return thisOrderId - thatOrderId;
    }
	
	
}
