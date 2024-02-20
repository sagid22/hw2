public class Player {
    private  String name;
    private Item[] inventory;
    private Room currentRoom;


    public  Player(String name,int bagCap){
        this.name=name;
        this.inventory =new Item[bagCap];
        this.currentRoom=null;
    }
    public String getName(){
        return this.name;
    }

    public Item[] getInventory() {
        return inventory;
    }
    public Room getCurrentRoom(){return  this.currentRoom;}
    public void setCurrentRoom(Room newCurrentRoom){this.currentRoom=newCurrentRoom;}

    public void initializationCurrentRoom(Room newCurrentRoom){
        this.currentRoom= newCurrentRoom;
    }

    public void removePlayerItems(){
        for (int i = 0; i< inventory.length; i++){
            inventory[i]= null;
        }
    }
    public void destroyItemFromInventory(Item it, int index) {
        this.inventory[index] = null;
        }
    public void destroyItemFromCurrentRoom(Item it, int index) {
        this.currentRoom.getListItems()[index]=null;
    }

    public void addItemToBag(Item newItem,int place){
        for(int i=0;i<currentRoom.getListItems().length;i++){//הרמת הפריט מהחדר
            if(currentRoom.getListItems()[i]==newItem){
                currentRoom.getListItems()[i]=null;
            }
        }
        this.inventory[place]=newItem;//הכנסת הפריט לתיק
        System.out.println(this.name + " picked up " + newItem.getName() + " from " + currentRoom.getRoomName() + ".");
    }
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
