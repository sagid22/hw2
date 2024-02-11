public class Room {
    private String name;
    private Item[] listItems;
    private Boolean[] directions;
    private Player player;
    private boolean isPuzzleSolved;

    public Room(String name) {
        this.name = name;
        this.listItems = new Item[2];
        this.directions = new Boolean[4];//north-0,south-1,east-2,west-3
        this.player = null;
        this.isPuzzleSolved = false;
    }

    public boolean getPuzzleStatus() {
        return this.isPuzzleSolved;
    }

    public void setPuzzleStatus(boolean status) {
        this.isPuzzleSolved = status;
    }

    public void addItemToRoom(Item i) {
        if (listItems[0] == null) {
            listItems[0] = i;
            System.out.println(listItems[0].getName() + "was added to the game.");
        } else if (listItems[1] == null) {
            listItems[1] = i;
            System.out.println(listItems[1].getName() + "was added to the game.");
        } else {
            System.out.println("Could not add" + i.getName() + "to the game.");
            ;
        }

    }

    public void removeItemFromRoom(Item it) {
        boolean isthere = false;
        for (int i = 0; i < listItems.length; i++) {
            if (listItems[i] == it) {
                isthere = true;
                listItems[i] = null;
                System.out.println(it.getName() + "was removed from the game");
            }

        }
        if (isthere == false) {
            System.out.println(it.getName() + "does not exist.");
        }
    }

    public String getName() {
        return this.name;
    }

    public boolean isDirectionAlreadyConnected(Direction d) {
        if (d == Direction.NORTH) {
            return (directions[0] == true);
        }
        else if(d==Direction.SOUTH){
            return(directions[1]==true);
        }
        else if(d==Direction.EAST){
            return(directions[2]==true);
        }
        else if(d==Direction.WEST){
            return(directions[3]==true);
        }
        return  false;//לוודא
    }

}
