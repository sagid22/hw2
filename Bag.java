public class Bag extends Item{
    String name;
    int value;
    int cap;
    Item[] inventory;
    public Bag(String name, int value, int cap){
        super(name, value);
        this.inventory=new Item[cap];
        }
    void useItem(Player player){
        
    }
}
