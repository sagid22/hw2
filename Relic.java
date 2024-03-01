public class Relic extends Item{
    protected String name;
    protected int value;
   // protected boolean isRelicUsed;

    public  Relic(String name,int value){
        super(name,value);
        //isRelicUsed=false;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue(){
        return value;
    }

    @Override
    public void useItem(Player player){
        boolean isRelicNearby= false;
        Room currentRoom= player.getCurrentRoom();
        for (int i=0; i<currentRoom.getListItems().length; i++){ // Chcking if the Relic is in the Room
            if(currentRoom.getListItems()[i].equals(this)){
                isRelicNearby= true;
            }
        }
        for(int j=0;j<player.getInventory().length;j++){  // Checking if the Relic is in the player's bag
            if(player.getInventory()[j].equals(this)) {
                isRelicNearby=true;
            }
        }
        if(isRelicNearby){
            System.out.println(player.getName()+" is inspecting "+this.getName()+".");
        }
        else {
            System.out.println(this.getName()+" is not near "+player.getName()+".");
        }
    }

    @Override
    public boolean equals(Object otherRelic1) {
        if (!(otherRelic1 instanceof Relic)) {
            return false;
        }
        Relic otherRelic2 = (Relic) otherRelic1;
        return (this.getValue() == otherRelic2.getValue());
    }
    @Override
    public int hashCode() {
        int result = 19; // Different initial prime number
        int multiplier = 43; // Different multiplier

        result = multiplier * result + name.hashCode();
        result = multiplier * result + value;
        return result;
    }


}
