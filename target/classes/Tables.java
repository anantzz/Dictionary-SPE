public class Tables {
    int table_no;
    int customer_id;
    int capacity;
    public Tables() {}
    public Tables(int t_no, int cap) {
        table_no = t_no;
        capacity = cap;
        customer_id = 0;
    }
    public int get_table_no() { return table_no; }
    public void set_table_no(int t_no) { table_no = t_no; }
    public int get_customer_id() { return customer_id; }
    public void set_customer_id(int c_id) { customer_id = c_id; }
    public int get_capacity() { return capacity; }
    public void set_capacity(int c) { capacity = c; }
}
