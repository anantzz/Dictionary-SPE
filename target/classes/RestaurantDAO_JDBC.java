import java.util.ArrayList;
// import java.util.List;
import java.sql.*;

public class RestaurantDAO_JDBC implements RestaurantDAO {
    Connection dbConnection;

    public RestaurantDAO_JDBC(Connection dbconn){
		// JDBC driver name and database URL
 		//  Database credentials
		dbConnection = dbconn;
	}

	public Item getItemByitem_id(int item_id)
    {
        Item item = new Item();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select * from Item where item_id = " + item_id;
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				String name = rs.getString("item_name");
				int id = rs.getInt("item_id");
				int prc = rs.getInt("price");
				float rt = rs.getFloat("rating");
				int nv = rs.getInt("number_of_votes");
				item.set_item_id(id);
				item.set_item_name(name);
                item.set_price(prc);
                item.set_rating(rt);
                item.set_number_of_votes(nv);

				if(rs.next())
				{
					// This statement will never be executed since there will be no repeated roll numbers since it a primary key.
					System.out.println("There are more than one items with this item_id");
				}
				break;
				// Add exception handling here if more than one row is returned
				// It is not possible that more than one row is returned since roll number is the primary key, no duplicate roll numbers will exist in the database.
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		// If no matching record is found, the default value of student object will be returned, i.e. s.rollno = 0, s.name = null will be returned. 
		return item;
    }

    // Menu
	public void insertItem(Item item)
    {
        PreparedStatement preparedStatement = null;
		String sql;
		sql = "insert into Item values (?, ?, ?, ?, ?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setString(1, item.get_item_name());
            System.out.println(item.item_name);
			// preparedStatement.setString(1, "check");
			preparedStatement.setInt(2, item.get_item_id());
			preparedStatement.setInt(3, item.get_price());
			preparedStatement.setFloat(4, item.get_rating());
			preparedStatement.setInt(5, item.get_number_of_votes());
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
 
			System.out.println("Item: item_id " + item.get_item_id() + ", " + "item_name " + item.get_item_name() + " added to the database");
		}
        catch (SQLException e)
        {
 			System.out.println(e.getMessage());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    }
	public void updateItem(Item item)
    {
        if(getItemByitem_id(item.get_item_id()).get_item_id() == 0)
		{
			System.out.println("No such item exists in the database.");
			return;
		}
		Statement stmt = null;
		String sql;
		sql = "update Item set item_name = " + "'" + item.get_item_name() + "'" + 
        ", price = " + item.get_price() + 
        ", rating = " + item.get_rating() + 
        ", number_of_votes = " + item.get_number_of_votes() + " where item_id = " + item.get_item_id();
		
		try {
			stmt = dbConnection.createStatement();

			// execute update SQL stetement
			stmt.executeUpdate(sql);
 
			// System.out.println("Item: item_id " + item.get_item_id() + ", " + "item_name " + item.get_item_name() + " updated in the database.");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    }
	public void deleteItem(int item_id)
    {
        if(getItemByitem_id(item_id).get_item_id() == 0)
		{
            System.out.println();
			System.out.println("No such item exists in the database.");
			return;
		}
		Statement stmt = null;
		String sql;
		sql = "delete from Item where item_id = " + item_id;

		try {
			stmt = dbConnection.createStatement();
 
			// execute delete SQL stetement
			stmt.executeUpdate(sql);
 
			// System.out.println("Item: Item_id " + item_id + ", deleted from the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    }

    // Cancel Order
    public void insertOrder(Orders order)
    {
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "insert into Orders values (?, ?, ?, ?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setInt(1, order.get_customer_id());
			preparedStatement.setInt(2, order.get_item_id());
			preparedStatement.setInt(3, order.get_quantity());
			preparedStatement.setString(4, order.get_status());
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
 
			System.out.println("Order: Customer Id " + order.get_customer_id() + ", " + "Item Id " + order.get_item_id()+", " + "Quantity " + order.get_quantity() + " added to the database");
		}
        catch (SQLException e)
        {
 			System.out.println(e.getMessage());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    }
	public void updateOrder(Orders order)
    {
		if(getOrders(order.get_customer_id(), order.get_item_id()).get_item_id() == 0)
		{
			System.out.println("No such item exists in the database.");
			return;
		}
		Statement stmt = null;
		String sql;
		sql = "update Orders set quantity = " + order.get_quantity() + 
        ", order_status = " + "'" + order.get_status() + "'" + " where item_id = " + order.get_item_id() + " and customer_id = " + order.get_customer_id();
		
		try {
			stmt = dbConnection.createStatement();

			// execute update SQL stetement
			stmt.executeUpdate(sql);
 
			// System.out.println("Order: Customer Id " + order.get_customer_id() + "and Item id" + order.get_item_id() + ", updated in the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    }
	public void deleteOrder(int c_id, int i_id)
    {
		if(getOrders(c_id, i_id).get_item_id() == 0)
		{
            System.out.println();
			System.out.println("No such order exists in the database.");
			return;
		}
		Statement stmt = null;
		String sql;
		sql = "delete from Orders where customer_id = " +c_id + " and item_id = " + i_id;

		try {
			stmt = dbConnection.createStatement();
 
			// execute delete SQL stetement
			stmt.executeUpdate(sql);
 
			// System.out.println("Order: Customer Id " + c_id + "and Item id" + i_id + ", deleted from the database");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
    }
    // Fetch order status
	public ArrayList<Orders> fetchOrders(int c_id)
	{
		ArrayList<Orders> orders = new ArrayList<>();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select * from Orders where customer_id = " + c_id;
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				Orders order = new Orders();
				int customer_id = rs.getInt("customer_id");
				int item_id = rs.getInt("item_id");
				int quantity = rs.getInt("quantity");
				String order_status = rs.getString("order_status");
				order.set_customer_id(customer_id);
				order.set_item_id(item_id);
				order.set_quantity(quantity);
				order.set_status(order_status);
				orders.add(order);
				// Add exception handling here if more than one row is returned
				// It is not possible that more than one row is returned since roll number is the primary key, no duplicate roll numbers will exist in the database.
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return orders;
	}
	public Orders getOrders(int c_id, int i_id)
	{
		Orders order = new Orders();
		order.set_status("NO SUCH ORDER FOUND");
		ArrayList<Orders> orders = fetchOrders(c_id);
		for(Orders o : orders) {
			if(o.get_item_id() == i_id) return o;
		}
		return order;
	}
	public void decreaseQuantity(int c_id, int i_id, int q)
	{
		Orders order = getOrders(c_id, i_id);
		if(order.get_item_id() == 0){
			System.out.println("No such order!");
			return;
		}
		int initialQ = order.get_quantity();
		initialQ -= q;
		if(initialQ <= 0) {
			deleteOrder(c_id, i_id);
			return;
		}
		order.set_quantity(initialQ);
		updateOrder(order);
	}
	public void increaseQuantity(int c_id, int i_id, int q)
	{
		Orders order = getOrders(c_id, i_id);
		if(order.get_item_id() == 0){
			// order.set_customer_id(c_id);
			// order.set_item_id(i_id);
			// order.set_quantity(q);
			// order.set_status("BEING PREPARED");
			System.out.println("No such order!");
			return;
		}
		int initialQ = order.get_quantity();
		initialQ += q;
		order.set_quantity(initialQ);
		updateOrder(order);
	}
	public void insertCustomer(Customer customer)
	{
		PreparedStatement preparedStatement = null;
		String sql;
		sql = "insert into Customer values (?, ?, ?, ?, ?, ?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setInt(1, customer.get_customer_id());
			preparedStatement.setString(2, customer.get_name());
			preparedStatement.setString(3, customer.get_contact_no());
			if(customer.get_table_no() != 0)
			preparedStatement.setInt(4, customer.get_table_no());
			else
			preparedStatement.setNull(4, Types.NULL);
			preparedStatement.setString(5, customer.get_address());
			if(customer.get_delivery_id() != 0 )
			preparedStatement.setInt(6, customer.get_delivery_id());
			else
			preparedStatement.setNull(6, Types.NULL);
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
 
			System.out.println("Customer: Customer Id " + customer.get_customer_id() + ", " + "Customer Name " + customer.get_name() + " added to the database");
		}
        catch (SQLException e)
        {
 			System.out.println(e.getMessage());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}

	public void updateCustomer(Customer customer)
	{
		if(getCustomerBycustomer_id(customer.get_customer_id()).get_customer_id() == 0)
		{
			System.out.println("No such item exists in the database.");
			return;
		}
		Statement stmt = null;
		String sql;
		String d_id = Integer.toString(customer.get_delivery_id());
		String t_no = Integer.toString(customer.get_table_no());
		if(customer.get_delivery_id() == 0) d_id = "null";
		if(customer.get_table_no() == 0) t_no = "null";
		sql = "update Customer set customer_name = " + "'" + customer.get_name() + "'" + 
        ", contact_no = " + "'" + customer.get_contact_no() + "'" + 
        ", table_no = " + t_no + 
        ", delivery_id = " + d_id + 
        ", customer_address = " + "'" + customer.get_address() + "'" + " where customer_id = " + customer.get_customer_id();
		try {
			stmt = dbConnection.createStatement();

			// execute update SQL stetement
			stmt.executeUpdate(sql);
 
			System.out.println("Customer: customer_id " + customer.get_customer_id() + " updated in the database.");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}

	public Customer getCustomerBycustomer_id(int customer_id)
	{
		Customer c = new Customer();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select * from Customer where customer_id = " + customer_id;
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				int id = rs.getInt("customer_id");
				String name = rs.getString("customer_name");
				String contact = rs.getString("contact_no");
				int tableno = rs.getInt("table_no");
				String address = rs.getString("address");
				int delivery = rs.getInt("delivery_id");
				c.set_customer_id(id);
				c.set_name(name);
				c.set_contact_no(contact);
				c.set_table_no(tableno);
				c.set_address(address);
				c.set_delivery_id(delivery);
				if(rs.next())
				{
					// This statement will never be executed since there will be no repeated roll numbers since it a primary key.
					System.out.println("There are more than one customers with this customer_id");
				}
				break;
				// Add exception handling here if more than one row is returned
				// It is not possible that more than one row is returned since roll number is the primary key, no duplicate roll numbers will exist in the database.
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		// If no matching record is found, the default value of student object will be returned, i.e. s.rollno = 0, s.name = null will be returned. 
		return c;
	}
	public int countOfCustomer()
	{
		int c = 0;
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select count(*) c from Customer";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				c = rs.getInt("c");
				if(rs.next())
				{
					// This statement will never be executed since there will be no repeated roll numbers since it a primary key.
					System.out.println("There are more than one customers with this customer_id");
				}
				break;
				// Add exception handling here if more than one row is returned
				// It is not possible that more than one row is returned since roll number is the primary key, no duplicate roll numbers will exist in the database.
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		// If no matching record is found, the default value of student object will be returned, i.e. s.rollno = 0, s.name = null will be returned. 
		return c;
	}

	// Tables
	public Tables getTableByno(int number)
	{
		Tables t = new Tables();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select * from Tables where table_no = " + number;
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				int table_no = rs.getInt("table_no");
				int id = rs.getInt("customer_id");
				int capacity = rs.getInt("capacity");
				t.set_table_no(table_no);
				t.set_customer_id(id);
				t.set_capacity(capacity);
				if(rs.next())
				{
					// This statement will never be executed since there will be no repeated roll numbers since it a primary key.
					System.out.println("There are more than one tables with this table no");
				}
				break;
				// Add exception handling here if more than one row is returned
				// It is not possible that more than one row is returned since roll number is the primary key, no duplicate roll numbers will exist in the database.
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		// If no matching record is found, the default value of student object will be returned, i.e. s.rollno = 0, s.name = null will be returned. 
		return t;
	}

	public void updateTable(Tables table)
	{
		if(getTableByno(table.get_table_no()).get_table_no() == 0)
		{
			System.out.println("No such item exists in the database.");
			return;
		}
		Statement stmt = null;
		String sql;
		sql = "update Tables set" + 
        " customer_id = " + table.get_customer_id() + 
        ", capacity = " + table.get_capacity() + " where table_no = " + table.get_table_no();
		
		try {
			stmt = dbConnection.createStatement();

			// execute update SQL stetement
			stmt.executeUpdate(sql);
 
			// System.out.println("Table: table_no " + table.get_table_no() + ", " + "customer_id " + " updated in the database.");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}

	// Delivery
	public void updateDelivery(Delivery delivery)
	{
		if(getDeliveryBydeliveryid(delivery.get_delivery_id()).get_delivery_id() == 0)
		{
			System.out.println("No such item exists in the database.");
			return;
		}
		Statement stmt = null;
		String sql;
		sql = "update Delivery set " +
		" deliverer_name = " + "'" + delivery.get_name() + "'" +
        ", contact_no = " + "'" + delivery.get_contact_no() + "'" + 
        ", rating = " + delivery.get_rating() + 
        ", number_of_votes = " + delivery.get_number_of_votes() + 
        ", customer_id = " + delivery.get_customer_id() +
		" where delivery_id = " + delivery.get_delivery_id();
		
		try {
			stmt = dbConnection.createStatement();

			// execute update SQL stetement
			stmt.executeUpdate(sql);
 
			// System.out.println("Delivery: delivery_id " + delivery.get_delivery_id() + " updated in the database.");
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}

		try{
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
 			System.out.println(e.getMessage());
 		}
	}
	
	public Delivery getDeliveryBydeliveryid(int delivery_id)
	{
		Delivery d = new Delivery();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select * from Delivery where delivery_id = " + delivery_id;
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				int id = rs.getInt("delivery_id");
				String name = rs.getString("deliverer_name");
				String contact = rs.getString("contact_no");
				float rating = rs.getFloat("rating");
				int votes = rs.getInt("number_of_votes");
				int c_id = rs.getInt("customer_id");
				d.set_delivery_id(id);
				d.set_name(name);
				d.set_contact_no(contact);				
				d.set_number_of_votes(votes);
				d.set_rating(rating);
				d.set_customer_id(c_id);
				if(rs.next())
				{
					// This statement will never be executed since there will be no repeated roll numbers since it a primary key.
					System.out.println("There are more than one tables with this table no");
				}
				break;
				// Add exception handling here if more than one row is returned
				// It is not possible that more than one row is returned since roll number is the primary key, no duplicate roll numbers will exist in the database.
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		// If no matching record is found, the default value of student object will be returned, i.e. s.rollno = 0, s.name = null will be returned. 
		return d;
	}

	public ArrayList<Item> getMenu()
	{
		ArrayList<Item> menu = new ArrayList<Item>();
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select * from Item";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while(rs.next()) {
				//Retrieve by column name
				Item it = new Item();
				String name = rs.getString("item_name");
				int id = rs.getInt("item_id");
				int prc = rs.getInt("price");
				float rating = rs.getFloat("rating");
				int votes = rs.getInt("number_of_votes");
				it.set_item_name(name);
				it.set_item_id(id);				
				it.set_price(prc);
				it.set_rating(rating);
				it.set_number_of_votes(votes);
				menu.add(it);
			}
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		// Add exception handling when there is no matching record
		// If no matching record is found, the default value of student object will be returned, i.e. s.rollno = 0, s.name = null will be returned. 
		return menu;
	}
	public void showBill(int c_id)
	{
		// select * from Orders o inner join Item i on o.item_id = i.item_id where o.customer_id = c_id;
		// select o.customer_id as customer_id, o.item_id as item_id, i.item_name as item_name, o.quantity as quantity, i.price as price, i.price * o.quantity as total from Orders o inner join Item i on o.item_id = i.item_id where o.customer_id = c_id;
		String sql;
		Statement stmt = null;
		
		try{
			stmt = dbConnection.createStatement();
			sql = "select o.customer_id as customer_id, o.item_id as item_id, i.item_name as item_name, o.quantity as quantity, i.price as price, i.price * o.quantity as total from Orders o inner join Item i on o.item_id = i.item_id where o.customer_id = " + c_id;
			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
			System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s\n", "Customer ID", "Item ID", "Item Name", "Quantity", "Price", "Total");
			int amount = 0;
			while(rs.next()) {
				//Retrieve by column name
				System.out.format("%-20d%-20d%-20s%-20d%-20d%-20d\n", rs.getInt("customer_id") , rs.getInt("item_id") , rs.getString("item_name") , rs.getInt("quantity") , rs.getInt("price") , rs.getInt("total"));
				amount += rs.getInt("total");
				// Add exception handling here if more than one row is returned
				// It is not possible that more than one row is returned since roll number is the primary key, no duplicate roll numbers will exist in the database.
			}
			System.out.println("The total bill amount is " + amount);
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}