public class Delivery {
    int delivery_id;
    String deliverer_name;
    String contact_no;
    float rating;
    int number_of_votes;
    int customer_id;
    public Delivery() {}
    public Delivery(int d_id, String n, String c_no, float f, int v, int c_id) {
        delivery_id = d_id;
        deliverer_name = n;
        contact_no = c_no;
        rating = f;
        number_of_votes = v;
        c_id = customer_id;
    }
    public int get_delivery_id() { return delivery_id; }
    public void set_delivery_id(int d_id) { delivery_id = d_id; }
    public String get_name() { return deliverer_name; }
    public void set_name(String s) { deliverer_name = s; }
    public String get_contact_no() { return contact_no; }
    public void set_contact_no(String s) { contact_no = s; }
    public float get_rating() { return rating; }
    public void set_rating(float rtg) { rating = rtg; }
    public void give_vote(float stars) { 
        float initial = rating * number_of_votes;
        number_of_votes++;
        rating = (initial + stars)/number_of_votes;
    }
    public int get_number_of_votes() { return number_of_votes; }
    public void set_number_of_votes(int nv) { number_of_votes = nv; }
    public int get_customer_id() { return customer_id; }
    public void set_customer_id(int cid) { customer_id = cid; }
}
