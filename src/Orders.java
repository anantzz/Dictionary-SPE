public class Orders {
    int customer_id;
    int item_id;
    int quantity;
    String order_status;
    public Orders() {}
    public Orders(int c_id, int i_id, int q) {
        customer_id = c_id;
        item_id = i_id;
        quantity = q;
        order_status = "BEING PREPARED";
    }
    public int get_customer_id() { return customer_id; }
    public void set_customer_id(int c_id) { customer_id = c_id; }
    public int get_item_id() { return item_id; }
    public void set_item_id(int i_id) { item_id = i_id; }
    public int get_quantity() { return quantity; }
    public void set_quantity(int q) { quantity = q; }
    public String get_status() { return order_status; }
    public void set_status(String s) { order_status = s; }
}
