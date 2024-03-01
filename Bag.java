public class Bag extends Item{
    protected String name;
    protected int value;
    protected int cap;
    protected Item[] inventory;
    public Bag(String name, int value, int cap){
        super(name, value);
        this.inventory=new Item[cap];
        }
    void useItem(Player player){



    public int sumValuesOfBagItems(){
        int sum=0;
        for (int i=0; i<this.inventory.length; i++){
            sum+= inventory[i].getValue();
        }
        return sum;
    }
    @Override
    public boolean equals(Object otherBag1) {
        if (!(otherBag1 instanceof Bag)) {
            return false;
        }
        Bag otherBag2 = (Bag) otherBag1;
        return (this.sumValuesOfBagItems() == otherBag2.sumValuesOfBagItems() &&
                this.getInventory().length == otherBag2.getInventory().length);
    }
    @Override
    public int hashCode() {
        int result = 29; // Different initial prime number
        int multiplier = 47; // Different multiplier

        result = multiplier * result + name.hashCode();
        result = multiplier * result + value;
        result = multiplier * result + cap;

        for (Item item : inventory) {
            if (item != null) {
                result = multiplier * result + item.hashCode();
            }
        }

        return result;
    }

}
