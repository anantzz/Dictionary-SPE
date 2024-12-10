public class Item {
    String item_name = "";
    int item_id = 0;
    int price = 0;
    float rating = 0;
    int number_of_votes = 0;
    public Item(){}
    public Item(String i_name, int i_id, int p, float r, int n) {
        item_name = i_name;
        item_id = i_id;
        price = p;
        rating = r;
        number_of_votes = n;
    }
    public String get_item_name() { return item_name; }
    public void set_item_name(String n) { item_name = n; }
    public int get_item_id() { return item_id; }
    public void set_item_id(int id) { item_id = id; }
    public int get_price() { return price; }
    public void set_price(int p) { price = p; }
    public float get_rating() { return rating; }
    public void set_rating(float rtg) { rating = rtg; }
    public void give_vote(float stars) { 
        float initial = rating * number_of_votes;
        number_of_votes++;
        rating = (initial + stars)/number_of_votes;
    }
    public int get_number_of_votes() { return number_of_votes; }
    public void set_number_of_votes(int nv) { number_of_votes = nv; }
}
