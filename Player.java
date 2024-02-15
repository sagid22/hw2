public class Player {
    private  String name;
    private Item[] items;
    private Room currentRoom;


    public  Player(String name,int bagCap){
        this.name=name;
        this.items=new Item[bagCap];
        this.currentRoom=null;
    }
    public String getName(){
        return this.name;
    }

    public Item[] getItems() {
        return items;
    }
    public Room getCurrentRoom(){return  this.currentRoom;}
    public void setCurrentRoom(Room newCurrentRoom){this.currentRoom=newCurrentRoom;}

    public void initializationCurrentRoom(Room newCurrentRoom){
        this.currentRoom= newCurrentRoom;
    }

    public void removePlayerItems(){
        for (int i=0; i<items.length; i++){
            items[i]= null;
        }
    }
    public void destroyItemFromInventory(Item it, int index) {
        this.items[index] = null;
        }
    public void destroyItemFromCurrentRoom(Item it, int index) {
        this.currentRoom.getListItems()[index]=null;
    }

    public void addItemToBag(Item newItem,int place){
        for(int i=0;i<currentRoom.getListItems().length;i++){
            if(currentRoom.getListItems()[i]==newItem){
                currentRoom.getListItems()[i]=null;
            }
        }
        this.items[place]=newItem;
        System.out.println(this.name + "picked up" + newItem.getName() + "from" + currentRoom.getRoomName() + ".");
    }
    public void DropItem(Item itemToDrop,int place){
        for(int i=0;i<this.items.length;i++){
            if(this.items[i]==itemToDrop){
                this.items[i]=null;
            }
        }
        this.items[place]=itemToDrop;
        System.out.println(this.name + "droped" + itemToDrop.getName() + "in" + currentRoom.getRoomName() + ".");
    }


//public void pickItem(Item item)
//{
  //  int counter=0;
    //boolean isPlaced=false;
    //while(isPlaced=false&&counter<items.length){
      //  if(items[counter]==null){
        //    items[counter]=item;
          //  isPlaced=true;
        //}
       // counter+=1;
   // }
   // if (isPlaced==true){System.out.println("");}
//}



}
