package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import enums.*;
import java.util.Timer;



public class Order implements Serializable, Comparable<Order>{
	
	private static final long serialVersionUID = 1L;

	private String orderId;
	
	private String dateTime;
	
	private double totalBill;
	
	private transient Timer timer = new Timer();
	
	private HashMap<MenuItem, Integer> currentOrders;
	
	private DineInOption option;

	private List<String> remarks = new ArrayList<String>();
	
	private String branchName;
	
	private OrderStatus status;
	 
	public Order(String orderId, String dateTime, String branchName) {
		this.orderId = orderId;
		this.dateTime = dateTime;
		this.branchName = branchName;
		this.totalBill = 0;
		this.status = OrderStatus.PROCESSING;
		remarks.add("No Remarks");

		this.currentOrders = new HashMap<MenuItem, Integer>();
	}
	
	public void addOrderItem(MenuItem menuItem, int amount){
        if (currentOrders.containsKey(menuItem)){
            int currAmount = currentOrders.get(menuItem);
            currentOrders.put(menuItem, currAmount + amount);
        }
        else{
            currentOrders.put(menuItem, amount);
        }
        totalBill += (menuItem.getPrice() * amount);
    }
	
	public boolean removeOrderItem(MenuItem toBeRemoved, int amount) {

        int currAmount = currentOrders.get(toBeRemoved);
        if (amount <= currAmount) {
            if (amount == currAmount) {
                currentOrders.remove(toBeRemoved);
            } else {
                currentOrders.put(toBeRemoved, currAmount - amount);
            }
            totalBill -= (toBeRemoved.getPrice() * amount);
            return true;
            
        } else {
            System.out.println("");
            return false;
        }
    }
	
	public MenuItem findOrderItem(String name) {

        for (MenuItem menuItem : currentOrders.keySet()) {
        	
        
            if (menuItem.getName().equalsIgnoreCase(name)){
                return menuItem;
            }
        }
        return null;
    }
	

	public HashMap<MenuItem, Integer> getCurrentOrders() {
        return currentOrders;

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

	public List<String> getRemarks() {
		return remarks;
	}

	public String getBranchName() {
		return branchName;
	}
	
	public Timer getTimer() {
		return timer;
	}
	
	public DineInOption getOption() {
		return option;
	}

	public void setOption(DineInOption option) {
		this.option = option;
	}

	public void setRemarks(String customerRemarks) {
		remarks.clear();
		remarks.add(customerRemarks);
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
