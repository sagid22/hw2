public class Bag extends Item{
    protected String name;
    protected int value;
    protected int cap;
    protected Item[] inventory;
    public Bag(String name, int value, int cap){
        super(name, value);
        this.inventory=new Item[cap];
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getValue() {
        return value;
    }

    public Item[] getInventory() {
        return this.inventory;
    }

    public int freeSpacesInBag(){
        int counter= 0;
        for(int i=0; i<this.getInventory().length; i++){
            if(this.getInventory()[i] == null){
                counter+=1;
            }
        }
        return counter;
    }
    public int noSpacesInBag(){
        int counter= 0;
        for(int i=0; i<this.getInventory().length; i++){
            if(this.getInventory()[i] != null){
                counter+=1;
            }
        }
        return counter;
    }

    @Override
    public void useItem(Player player) { //Changings Bags
        if (player.getInventory().length > this.getInventory().length) {
            System.out.println(this.getName() + " is too small.");
        } else {
            boolean isBagNearby = false;
            Room currentRoom = player.getCurrentRoom();
            for (int i = 0; i < currentRoom.getListItems().length; i++) { // Chcking if the Bag is in the Room
                if (currentRoom.getListItems()[i].equals(this)) {
                    isBagNearby = true;
                }
            }
            for (int j = 0; j < player.getInventory().length; j++) {  // Checking if the bag is in the player's bag
                if (player.getInventory()[j].equals(this)) {
                    isBagNearby = true;
                }
            }
            if(!isBagNearby){
                System.out.println(this.getName()+" is not near player.");
            }
            else {
                if(player.getPlayerBag()==null){  // if bag has not initialized
                    int index=0;
                    for (int i=0; i<player.getInventory().length; i++) {
                        if(player.getInventory()[i]!=null)
                        this.inventory[index]=player.getInventory()[i];
                        index+=1;

                    }
                }
                for (int i=0; i<player.getInventory().length; i++){

                }
            }
        }
    }
        //שימוש בתיקים או בתיק גדולים יאפשר לשחקן להחליף את תיקו הנוכחי בתיק המדובר, על ידי העברת כלל החפצים
        //בתיק הנוכחי לחדש כאשר תכולת התיק הנוכחי תתאפס, כולל העברת התיק הנוכחי לתוך התיק החדש – אם ניתן,
        //אחרת התיק הנוכחי ייהרס ותודפס הודעה מתאימה )ראו תרגיל בית 2(. שימו לב, רק תיק גדול יכול להכיל בתוכו
        //תיק וכן תיק גדול אינו יכול להכיל בתוכו תיק גדול אחר.

        //בניסיון החלפת התיק של שחקן player לתיק bag יש להדפיס .bag carrying now is player אם הניסיון
        //צלח. אם בעת הניסיון השחקן היה צריך להרוס את התיק הקודם שלו, תודפס ההודעה על הריסת חפץ
        //כאשר item הינו שם התיק הקודם, ולפני ההודעה על הצלחת הניסיון. אחרת, אם bag לא נמצא בקרבת
        //player יש להדפיס את ההודעה player near not is bag. אם bag קטן מדי ולא ניתן להעביר לתוכו את
        //כלל החפצים שעל player תודפס ההודעה .small too is bag. במקרה של מספר כשלים, תודפס הודעת
        //כשל יחידה על פי סדר העדיפויות שנקבע על פי סדר ההופעה בפסקה זו.





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
