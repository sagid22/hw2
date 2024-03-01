public class Player {
    private  String name;
    private Item[] inventory;
    private Room currentRoom;
    private Bag playerBag;

    /**
     * Constructs a new Player object with the specified name and bag capacity.
     *
     * @param name    The name of the player.
     * @param bagCap  The capacity of the player's inventory.
     */
    public  Player(String name, int bagCap){
        this.name= name;
        this.inventory = new Item[bagCap];
        this.currentRoom= null;
        this.playerBag=null;
    }


    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the inventory of the player.
     *
     * @return The inventory of the player.
     */
    public Item[] getInventory() {
        return inventory;
    }

    /**
     * Gets the current room of the player.
     *
     * @return The current room of the player.
     */
    public Room getCurrentRoom(){return this.currentRoom;}

    /**
     * Sets the current room of the player.
     *
     * @param newCurrentRoom The new current room of the player.
     */
    public void setCurrentRoom(Room newCurrentRoom){this.currentRoom=newCurrentRoom;}

    /**
     * Initializes the current room of the player.
     *
     * @param newCurrentRoom The new current room of the player.
     */
    public void initializationCurrentRoom(Room newCurrentRoom){
        this.currentRoom= newCurrentRoom;
    }

    /**
     * Removes all items from the player's inventory.
     */
    public void removePlayerItems(){
        for (int i = 0; i< inventory.length; i++){
            this.inventory[i]= null;
        }
    }

    /**
     * Destroys an item from the player's inventory.
     *
     * @param it    The item to destroy.
     * @param index The index of the item in the inventory.
     */
    public void destroyItemFromInventory(Item it, int index) {
        this.inventory[index] = null;
        }

    /**
     * Destroys an item from the current room.
     *
     * @param it    The item to destroy.
     * @param index The index of the item in the current room's item list.
     */
    public void destroyItemFromCurrentRoom(Item it, int index) {
        this.currentRoom.getListItems()[index]= null;
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param newItem The item to add to the inventory.
     * @param place   The index where the item should be placed in the inventory.
     */
    public void addItemToBag(Item newItem, int place){
        for(int i=0; i<currentRoom.getListItems().length; i++){
            if(currentRoom.getListItems()[i]== newItem){
                currentRoom.getListItems()[i]= null;
            }
        }
        this.inventory[place]=newItem;//הכנסת הפריט לתיק
        System.out.println(this.name + " picked up " + newItem.getName() + " from " + currentRoom.getRoomName() + ".");
    }

    /**
     * drops an item from the player's inventory into the current room.
     *
     * @param itemToDrop The item to drop.
     * @param place      The index of the item in the inventory.
     */
    public void dropItem(Item itemToDrop, int place){
        for(int i = 0; i<this.inventory.length; i++){
            if(this.inventory[i]== itemToDrop){
                this.inventory[i]= null;
            }
        }
        this.currentRoom.getListItems()[place]= itemToDrop;
        System.out.println(this.name + " dropped " + itemToDrop.getName() + " in " + currentRoom.getRoomName() + ".");
    }

    public int sumValuesOfplayerBag(){
        int sum=0;
        for (int i=0; i<this.inventory.length; i++){
            sum+= inventory[i].getValue();
        }
        return sum;
    }

    public Bag getPlayerBag(){
        return playerBag;
    }
    public void setPlayerBag(Bag newBag){
        this.playerBag=newBag;
    }

    public int countItemsInInventory(){
        int counter= 0;
        for(int i=0; i<this.getInventory().length; i++){
            if(this.getInventory()[i] != null){
                counter+=1;
            }
        }
        return counter;
    }
    @Override
    public boolean equals(Object otherPlayer1) {
        if (!(otherPlayer1 instanceof Player)) {
            return false;
        }
        Player otherPlayer2 = (Player) otherPlayer1;
        return (this.sumValuesOfplayerBag() == otherPlayer2.sumValuesOfplayerBag());
    }
    @Override
    public int hashCode() {
        int result = 23; // Different initial prime number
        int multiplier = 37; // Different multiplier

        result = multiplier * result + name.hashCode();
        result = multiplier * result + sumValuesOfplayerBag();
        result = multiplier * result + (currentRoom != null ? currentRoom.hashCode() : 0);

        for (Item item : inventory) {
            if (item != null) {
                result = multiplier * result + item.hashCode();
            }
        }

        return result;
    }


}
