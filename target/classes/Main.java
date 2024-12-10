//STEP 1. Import required packages
// import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static DAO_Factory daoFactory;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		try {
			daoFactory = new DAO_Factory();
			System.out.println("Welcome to Our Restaurant.");
			String pass = "password";
			String inp_pass = "";
			while(!pass.equals(inp_pass))
			{
				System.out.println("Press 1 if you are a customer and 2 if you are an admin.");
				int choice = sc.nextInt();
				if(choice == 1){
					inp_pass = pass;
					int id = newId();
					System.out.println("Please let us know some details about you");
					System.out.println("What is your name?");
					String name = sc.nextLine();
					name = sc.nextLine();
					System.out.println("What is your contact number?");
					String number = sc.next();
					System.out.println("What is your address?");
					String address = sc.nextLine();
					address = sc.nextLine();
					System.out.println("Press 1 if you want to dine-in and 2 if you want to get your order delivered");
					
					int choice1 = sc.nextInt();
					int table_no = rd.nextInt(5) + 1;
					int delivery_id = rd.nextInt(3) + 1;
					if(choice1 == 1) {
						Customer c = new Customer();
						c.set_customer_id(id);
						c.set_name(name);
						c.set_contact_no(number);
						c.set_table_no(table_no);
						c.set_address(address);
						c.set_delivery_id(0);
						addNewCustomer(c);
						Tables table = getTablesbyID(table_no);
						table.set_customer_id(id);
						allocateTable(table);
						System.out.println("\nHere are your table details :");
						getTableDetails(table_no);
						System.out.println();
					} else {
						Customer c = new Customer();
						c.set_customer_id(id);
						c.set_name(name);
						c.set_contact_no(number);
						c.set_table_no(0);
						c.set_address(address);
						c.set_delivery_id(delivery_id);
						addNewCustomer(c);
						allocateDeliveryman(id, delivery_id);
						System.out.println("\nHere are your Delivery-man details : ");
						getDeliveryDetails(delivery_id);
						System.out.println();
					}


					System.out.println("Here is the menu.");
					DisplayMenu();
					System.out.println("Enter 1 to order the item following the Item Id and Quantity");
					System.out.println("Enter 2 to increse the item quantity following the Item Id and the quantity you want to increase");
					System.out.println("Enter 3 to decrease the item quantity following the Item Id and the quantity you want to decrease");
					System.out.println("Enter 4 to get the status of your order following the Item_id");
					System.out.println("Enter 0 to end the interaction");

					int op=-1;
					
					while(op!=0){
						op = sc.nextInt();
						if(op==0)break;
						int item_id=sc.nextInt();

						if(op==1){
							int quantity=sc.nextInt(); 
							Orders order=new Orders(id,item_id, quantity);
							addOrder(order);
						}

						else if(op==2){
							int quantity=sc.nextInt(); 
							changeQuantity(id,item_id,quantity,1);
						}
						
						else if(op==3){
							int quantity=sc.nextInt(); 
							changeQuantity(id,item_id,quantity,0);
						}

						else if(op==4){
							System.out.println(usecase_getOrderStatus(id,item_id));
						}
					}

					System.out.println("Here is your bill");
					ShowBill(id);
					ArrayList<Orders> myOrders = fetchMyOrders(id);
					for(Orders o:myOrders){
						Item item=getItem(o.get_item_id());
						System.out.println("Give your feedback for the item "+ item.get_item_name()+" between 0 to 5");
						Float fb=sc.nextFloat();
						item.give_vote(fb);
						updateMyRating(item,id,o.get_item_id());
					}
					if(choice1!=1){
						Delivery deliveryman = getDeliveryMan(delivery_id);
						System.out.println("Please give us some feedback about your delivery man " + deliveryman.get_name()+ " between 0 to 5");
						Float fb=sc.nextFloat();
						deliveryman.give_vote(fb);
						updateMyDeliveryRating(deliveryman);
					}
					
				} 
                
                
                
                
                else {
                    System.out.println("Enter password: ");
					inp_pass = sc.next();
					if(!pass.equals(inp_pass))
						continue;
                    else
                    {
                        System.out.println("You are authorized to alter the menu.");
                        System.out.println("Here is the current menu.");
                        DisplayMenu();
                        System.out.println("Press 1 insert an item to menu and 2 to update an existing item.");
                        int c = sc.nextInt();
                        Item item = new Item();
                        
                        if(c == 1)
                        {

                            System.out.println("Enter the details of the new item");
                            
                            System.out.print("item_name: ");
                            sc.nextLine();
                            String nm = sc.nextLine();
                            item.set_item_name(nm);
                            
                            System.out.print("Distinct item_id: ");
                            int id = sc.nextInt();
                            item.set_item_id(id);
                            
                            System.out.print("price: ");
                            int prc = sc.nextInt();
                            item.set_price(prc);
                            
                            System.out.print("rating: ");
                            float rtg = sc.nextFloat();
                            item.set_rating(rtg);
                            
                            System.out.print("number of votes: ");
                            int nov = sc.nextInt();
                            item.set_number_of_votes(nov);
                            
                            usecase_alter_menu(c, item);
                            
                            System.out.println("Here is the updated menu.");
                            DisplayMenu();
                        }
                        else
                        {
                            System.out.println("Enter the item_id of the item that you want to update");
                            
                            System.out.print("item_id: ");
                            int id = sc.nextInt();
                            item.set_item_id(id);
                            
                            System.out.print("Updated item_name: ");
                            sc.nextLine();
                            String nm = sc.nextLine();
                            item.set_item_name(nm);
                            
                            System.out.print("Updated price: ");
                            int prc = sc.nextInt();
                            item.set_price(prc);
                            
                            System.out.print("Updated rating: ");
                            float rtg = sc.nextFloat();
                            item.set_rating(rtg);
                            
                            System.out.print("Updated number of votes: ");
                            int nov = sc.nextInt();
                            item.set_number_of_votes(nov);
                            
                            usecase_alter_menu(c, item);
                            
                            System.out.println("Here is the updated menu.");
                            DisplayMenu();
                        }
					}
					
				}
			} 
			
			
		} catch(Exception e) {
				e.printStackTrace();
		}
		sc.close();
		System.out.println("Thanks for visiting us!");
	}

	public static void DisplayMenu()
	{
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			ArrayList<Item> menu = rdao.getMenu();
			System.out.format("%-20s%-20s%-20s%-20s%-20s\n", "Item Name", "Item ID", "Price", "Rating", "Number of people voted");
			for(Item i : menu) {
				System.out.format("%-20s%-20d%-20d%-20.1f%-20d\n", i.get_item_name(), i.get_item_id(), i.get_price(), i.get_rating(), i.get_number_of_votes());
			}
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static void ShowBill(int c_id)
	{
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			rdao.showBill(c_id);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	// Kevin
	public static void usecase_alter_menu(int choice, Item item)
	{
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			switch (choice) {
				case 1:
					rdao.insertItem(item);
					break;
				case 2:
					rdao.updateItem(item);
					break;

				default:
					break;
			}	
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		} catch(Exception e) {
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
		}
	}

	public static void usecase_order_cancel(int customer_id, int item_id, int qty)
	{
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			rdao.decreaseQuantity(customer_id, item_id, qty);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		} catch(Exception e) {
				daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
				e.printStackTrace();
		}
	}

	// Anant
	public static String usecase_getOrderStatus(int c_id, int i_id)
	{
		String s = "";
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			Orders o = rdao.getOrders(c_id, i_id);
			s = o.get_status();
			if(s.equals("BEING PREPARED"))o.set_status("DELIVERED");
			rdao.updateOrder((o));
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
			
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return s;
	}

	public static void usecase_updateRating(Item item)
	{
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			rdao.updateItem(item);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
			
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static Item getItem(int item_id)
	{
		Item item = new Item();
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			item = rdao.getItemByitem_id(item_id);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return item;
	}

	public static int newId()
	{
		int rn = 0;
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			rn = rdao.countOfCustomer() + 1;
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return rn;
	}


	public static void allocateTable(Tables table){
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			rdao.updateTable(table);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );

		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static void getTableDetails(int table_id){
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			Tables table=rdao.getTableByno(table_id);
			System.out.println("Table no : " + table.get_table_no() +" Customer_ID : " + table.get_customer_id() + " Capacity : "+ table.get_capacity());
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );

		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static void allocateDeliveryman(int customer_id,int delivery_id){
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			Delivery delivery=rdao.getDeliveryBydeliveryid(delivery_id);
			delivery.customer_id=customer_id;
			rdao.updateDelivery(delivery);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );

		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static void getDeliveryDetails(int delivery_id){
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			Delivery delivery = rdao.getDeliveryBydeliveryid(delivery_id);
			System.out.println("Delivery Id : " + delivery.get_delivery_id() +" Name : " + delivery.get_name() + " Contact no : "+ delivery.get_contact_no()+" Rating : "+delivery.get_rating()+" Number of votes : "+delivery.get_number_of_votes());
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );

		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static void addNewCustomer(Customer customer){
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			rdao.insertCustomer(customer);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static void addOrder(Orders order){
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			rdao.insertOrder(order);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static void changeQuantity(int c_id, int i_id, int q,int op){
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			if(op==1){
				rdao.increaseQuantity(c_id, i_id, q);
			}else{
				rdao.decreaseQuantity(c_id, i_id, q);
			}
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static ArrayList<Orders> fetchMyOrders(int c_id){
		ArrayList<Orders> myOrders = new ArrayList<Orders>();
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			myOrders=rdao.fetchOrders(c_id);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return myOrders;
	}

	public static void updateMyRating(Item item,int id,int item_id){
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			rdao.updateItem(item);
			rdao.deleteOrder(id,item_id);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public static Delivery getDeliveryMan(int delivery_id){
		Delivery deliveryman=new Delivery();
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			deliveryman = rdao.getDeliveryBydeliveryid(delivery_id);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return deliveryman;
	}	

	public static void updateMyDeliveryRating(Delivery delivery){
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			rdao.updateDelivery(delivery);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}
	
	public static Tables getTablesbyID(int table_id){
		Tables t = new Tables();
		try{
			daoFactory.activateConnection();
			RestaurantDAO rdao = daoFactory.getRestaurantDAO();
			t = rdao.getTableByno(table_id);
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return t;
	}
}
