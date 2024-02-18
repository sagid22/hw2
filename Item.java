
public class Item {
    private  String name;


    /**
     * Constructs a new Item object with the specified name.
     *
     * @param name The name of the item.
     */
    public  Item (String name){
        this.name=name;
    }


    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }
}