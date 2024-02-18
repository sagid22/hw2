public class Player {
    private  String name;
    private Item[] inventory;
    private Room currentRoom;

    /**
     * Constructs a new Player object with the specified name and bag capacity.
     *
     * @param name    The name of the player.
     * @param bagCap  The capacity of the player's inventory.
     */
    public  Player(String name,int bagCap){
        this.name=name;
        this.inventory =new Item[bagCap];
        this.currentRoom=null;
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
    public Room getCurrentRoom(){return  this.currentRoom;}

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
            inventory[i]= null;
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
        this.currentRoom.getListItems()[index]=null;
    }

    /**
     * Adds an item to the player's inventory.
     *
     * @param newItem The item to add to the inventory.
     * @param place   The index where the item should be placed in the inventory.
     */
    public void addItemToBag(Item newItem,int place){
        for(int i=0;i<currentRoom.getListItems().length;i++){
            if(currentRoom.getListItems()[i]==newItem){
                currentRoom.getListItems()[i]=null;
            }
        }
        this.inventory[place]=newItem;//הכנסת הפריט לתיק
        System.out.println(this.name + " picked up" + newItem.getName() + " from " + currentRoom.getRoomName() + ".");
    }

    /**
     * Drops an item from the player's inventory into the current room.
     *
     * @param itemToDrop The item to drop.
     * @param place      The index of the item in the inventory.
     */
    public void DropItem(Item itemToDrop,int place){
        for(int i = 0; i<this.inventory.length; i++){
            if(this.inventory[i]==itemToDrop){
                this.inventory[i]=null;
            }
        }
        this.currentRoom.getListItems()[place]=itemToDrop;
        System.out.println(this.name + " dropped " + itemToDrop.getName() + " in " + currentRoom.getRoomName() + ".");
    }
}
