public class Customer {
    int customer_id;
    String customer_name;
    String contact_no;
    int table_no;
    String customer_address;
    int delivery_id;
    public Customer() {}
    public Customer(int c_id, String n, String c_no, int t_no, String add, int d_id) {
        customer_id = c_id;
        customer_name = n;
        contact_no = c_no;
        table_no = t_no;
        customer_address = add;
        delivery_id = d_id;
    }
    public int get_customer_id() { return customer_id; }
    public void set_customer_id(int c_id) { customer_id = c_id; }
    public String get_name() { return customer_name; }
    public void set_name(String s) { customer_name = s; }
    public String get_contact_no() { return contact_no; }
    public void set_contact_no(String s) { contact_no = s; }
    public int get_table_no() { return table_no; }
    public void set_table_no(int t) { table_no = t; }
    public String get_address() { return customer_address; }
    public void set_address(String add) { customer_address = add; }
    public int get_delivery_id() { return delivery_id; }
    public void set_delivery_id(int d_id) { delivery_id = d_id; }
}
