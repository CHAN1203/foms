import repository. *;
import helper.Helper;
import model. *;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.OrderStatus;


/**
 * OrderManager is a controller class that acts as a "middleman"
 * between the view classes -  {@link OrderView} and the model classes - {@link Order}. <p>
 *
 * @author Hill Seah, Max
 * @version 1.0
 * @since 2022-04-06
 *
 */
public class OrderController {
    /**
     * Creates a new order for the customer of the room Id specified as the argument. <p>
     * Calls {@link RoomManager} to update room orders
     * @param roomId Room Id of customer ordering
     * @return Order Id of the new order
     */
    public static String createOrder(String branch) {
//    	Branch branchObject = Repository.BRANCH.get(branch);
//    	String orderId = "O0001";
//    	HashMap <String, Order> orders = branchObject.getOrders();
//        int oid = Helper.generateUniqueId(orders);
//        String orderId = String.format("O%04d", oid);
//        Order newOrder = new Order(orderId, Helper.getTimeNow(), branch);
//        
//        // update room's order
////        OrderController.updateOrders(orderId, newOrder);
//
//        HashMap<String, Order> orderObject = branchObject.getOrders();
//        orderObject.put(orderId, newOrder);
        return "O0001";
    }

    /**
     * Adds menu item of the specified quantity to the customer's order
     * @param name Name of the menu item to be added
     * @param orderId Id of the order
     * @param amount Quantity of menu item to be added
     * @return {@code true} if menu item is successfully added to the order. Otherwise, {@code false} if menu item fails to be added (order Id is invalid/ menu item name entered does not exist in menu database)
     */
    public static boolean addOrderItem(String name, String orderId, int quantity, String branch) {
        if (orderId.equals("")) {
            return false;
        }
        String formattedName = name.toUpperCase();
        String menuIdOfOrder = MenuController.getMenuIdFromName(formattedName);
        if (menuIdOfOrder.equals("")) {
            // no menu item found
            return false;
        }
        MenuItems menuItemToAdd = Repository.BRANCH.get(branch).getMenuItems().get(menuIdOfOrder); 
        Order currentOrder = Repository.BRANCH.get(branch).getOrders().get(orderId);
        currentOrder.addOrderItem(menuItemToAdd, quantity);
        return true;
    }

    /**
     * Removes menu item of the specified quantity from the customer's order
     * @param name Name of the menu item to be removed
     * @param orderId Id of the order
     * @param amount Quantity of menu item to be removed
     * @return {@code true} if menu item is successfully removed from the order. Otherwise, {@code false} if menu item fails to be removed (order Id is invalid/ menu item name entered does not exist in order or menu database)
     */
    public static boolean removeOrderItem(String name, String orderId, int quantity, String branch) {
        if (orderId.equals("")) {
            return false;
        }
        String formattedName = name.toUpperCase();
        String menuIdOfOrder = MenuController.getMenuIdFromName(branch, formattedName);
        if (menuIdOfOrder.equals("")) {
            // no menu item found
            return false;
        }
        MenuItems menuItemToDelete = Repository.BRANCH.get(branch).getMenuItems().get(menuIdOfOrder); 
        Order currentOrder = Repository.BRANCH.get(branch).getOrders().get(orderId);
        return currentOrder.removeOrderItem(menuItemToDelete, quantity);
    }

    /**
     * Prints the order of the specified order Id
     * @param orderId Order Id of the order
     */
    public static void printOrderDetails(String orderId, String branch) {
        Order currentOrder = Repository.BRANCH.get(branch).getOrders().get(orderId);
        System.out.println(String.format("%-58s", "").replace(" ", "-"));
        System.out.printf("Order Id: %s Date/Time: %s\n", currentOrder.getOrderId(), currentOrder.getDateTime());
        System.out.println();
        System.out.println(String.format("%-4s %-26s %10s %18s", "Index", "Item", "Qty", "Price"));
        System.out.println(String.format("%-66s", "").replace(" ", "─"));

        int index = 1;
        for (Map.Entry<MenuItems, Integer> entry : currentOrder.getCurrentOrders().entrySet()) {
            MenuItems key = entry.getKey();
            Integer value = entry.getValue();
            double totalPrice = value * key.getPrice();
            System.out.printf("%-4d %-26s %10d %8s$%.2f\n", index++, key.getName(), value, "", totalPrice);
        }

        System.out.println();
        System.out.println(String.format("%-11s: %s", "Remarks", currentOrder.getRemarks()));
        System.out.println(String.format("%-11s: $%.2f", "Total bill", currentOrder.getTotalBill()));
        System.out.println(String.format("%-58s", "").replace(" ", "-"));
    }
    
    /**
     * Sets the remarks for the order of the specified order Id
     * @param remarks Remarks for the order
     * @param orderId Order Id of the order
     */
    public static boolean setRemarks(String remarks, String orderId, String branch){
    	if (orderId.equals("")) {
            return false;
        }
        Order currentOrder = Repository.BRANCH.get(branch).getOrders().get(orderId);
        currentOrder.setRemarks(remarks);
        return true;
    }

    /**
     * Update the status of the order of the specified order Id
     * @param newOrderStatus New order status to be used to update the order status
     * @param orderId Order Id of the order
     * @return {@code true} if updating of order is successfull. Otherwise, {@code false} if id updating of order fails (Order Id is not in database).
     */
    public static boolean updateStatus(OrderStatus newOrderStatus, String orderId, String branch) {
        if (!validateOrderId(orderId, branch)) {
            System.out.println("Order id not found");
            return false;
        }
        Order currentOrder = Repository.BRANCH.get(branch).getOrders().get(orderId);
        currentOrder.setStatus(newOrderStatus);
        Repository.persistData(FileType.BRANCH);
        return true;
    }
    
    /**
     * Checks if order Id is in the database for validation
     * @param orderId Order Id of the order
     * @return {@code true} if order Id is found in the database. Otherwise, {@code false} if order Id is not found in database
     */
    public static boolean validateOrderId(String orderId, String branch) {
        return Repository.BRANCH.get(branch).getOrders().containsKey(orderId);
    }

    /**
     * Prints all the orders in the database
     */
    public static void printAllOrders(String branch) {
        ArrayList<Order> sortedList = new ArrayList<Order>();

        // copy
        for (Order order : Repository.BRANCH.get(branch).getOrders().values()) {
            sortedList.add(order);
        }
        Collections.sort(sortedList);
        for (Order order : sortedList) {
            System.out.println(order);
        }
    }
    
    public static OrderStatus checkOrderStatus(String orderId, String branch) {
    	Order currentOrder = Repository.BRANCH.get(branch).getOrders().get(orderId);
        return currentOrder.getStatus();
    }
    
//  public static boolean updateOrders(String orderId, Orders order) {
//  Orders targetOrder = searchOrder(orderId);
//  if (targetOrder == null) {
//      return false;
//  } else {
//      targetOrder.getOrders().add(order);
//  }
//  Database.saveFileIntoDatabase(FileType.ROOMS);
//  return true;
//}

    /**
     * Update all room order to delivered after the user has checked out <p>
     * Calls {@link RoomManager} to validate room id.
     * @param roomId Id of the room
     * @return {@code true} if update of room orders is successful. Otherwise, {@code false} if room id not found.
     */
//    public static boolean updateAllRoomOrderToDelivered(String roomId) { //do we need this method at all?
//        if (!RoomManager.validateRoomId(roomId)) {
//            return false;
//        }
//        ArrayList<Order> orderToUpdate = searchOrderByRoom(roomId);
//        for (Order order : orderToUpdate) {
//            if (order.getStatus() == OrderStatus.CONFIRMED || order.getStatus() == OrderStatus.PREPARING){
//                updateStatus(OrderStatus.DELIVERED, order.getOrderId());    
//            }
//        }
//        return true;
//    }

    /**
     * Searches the orders made by customer residing in room of the specified room Id
     * 
     * @param roomId Room Id of the customer's room
     * @return ArrayList of {@link Order} object of all the orders made by that room.
     */
//    public static ArrayList<Order> searchOrderByRoom(String roomId) { //do we need this method?
//        ArrayList<Order> orders = new ArrayList<Order>();
//        for (Order order : Database.ORDERS.values()) {
//            if (order.getRoomId().equals(roomId)){
//                orders.add(order);
//            }
//        }
//        return orders;
//    }

    /**
     * Method to calculate the total order price of a room
     * @param roomId Id of the room to calculate
     * @return total order price of the room.
     */
//    public static double calculateTotalOrderPrice(String orderId, String branch) { // we dont need a totalOrderPrice right? 
//        ArrayList<Orders> orders = RoomManager.searchRoom(roomId).getOrders(); // since we can already .getTotalBill();
//        if (orders == null) {
//            return 0;
//        }
//        double total = 0;
//        for (Orders order : orders) {
//            total += order.getTotalBill();
//        }
//        return total;
//    }
        
}