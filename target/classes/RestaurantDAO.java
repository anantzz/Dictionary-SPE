// import java.lang.*;
import java.util.ArrayList;
// import java.util.List;

public interface RestaurantDAO {

    // Menu
	public Item getItemByitem_id(int item_id);
	public void insertItem(Item item);
	public void updateItem(Item item);
	public void deleteItem(int item_id);

    // Cancel Order
	public void insertOrder(Orders order);
	public void updateOrder(Orders order);
	public void deleteOrder(int c_id, int i_id);
	public void decreaseQuantity(int c_id, int i_id, int q);
	public void increaseQuantity(int c_id, int i_id, int q);

    // Fetch order status
	public ArrayList<Orders> fetchOrders(int c_id);
	public Orders getOrders(int c_id, int i_id);

	// Show every customer, order pair (implement join)
	public void insertCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public Customer getCustomerBycustomer_id(int customer_id);
	public int countOfCustomer();

	// Tables
	public Tables getTableByno(int number);
	public void updateTable(Tables table);

	// Delivery
	public void updateDelivery(Delivery delivery);
	public Delivery getDeliveryBydeliveryid(int delivery_id);
	public ArrayList<Item> getMenu();
	public void showBill(int c_id);
}
