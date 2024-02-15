public class Room {
    private String name;
    private Item[] listItems;
    private Room[] roomsDirections;
    private boolean puzzleStatus;

    public Room(String name) {
        this.name = name;
        this.listItems = new Item[2];
        this.roomsDirections = new Room[4];//north-0,south-1,east-2,west-3
        this.puzzleStatus = false;
    }

    public boolean getPuzzleStatus() {
        return this.puzzleStatus;
    }

    public void setPuzzleStatus(boolean status) {
        this.puzzleStatus = status;
    }

    public void addItemToRoom(Item i) {
        if (listItems[0] == null) {
            listItems[0] = i;
            System.out.println(listItems[0].getName() + " was added to the game.");
        } else if (listItems[1] == null) {
            listItems[1] = i;
            System.out.println(listItems[1].getName() + " was added to the game.");
        } else {
            System.out.println("Could not add " + i.getName() + " to the game.");
            ;
        }

    }

    public void removeItemFromRoom(Item it) {
        boolean isthere = false;
        for (int i = 0; i < listItems.length; i++) {
            if (listItems[i] == it) {//לבדוק אם ההשוואה טובה, אם לא לעשות equals או ככה או get.name
                isthere = true;
                listItems[i] = null;
                System.out.println(it.getName() + " was removed from the game.");
            }

        }
        if (isthere == false) {
            System.out.println(it.getName() + " does not exist.");
        }
    }

    public String getRoomName() {
        return this.name;
    }
    public Item[] getListItems(){return this.listItems;}
    public void roomToRoomConnection(Room other,Direction d){
        int d1=-1,d2=-1;
        switch(d){
            case NORTH:
                d1=0;
                d2=1;
            case SOUTH:
                d1=1;
                d2=0;
            case EAST:
                d1=2;
                d2=3;
            case WEST:
                d1=3;
                d2=2;
        }
        if(this.getRoomsDirections()[d1]==null&& other.getRoomsDirections()[d2]==null){
            this.getRoomsDirections()[d1]=other;
            other.getRoomsDirections()[d2]=this;
            System.out.println(this.getRoomName()+" and "+other.getRoomName()+" are connected");
        }
        else {System.out.println("Could not connect "+this.getRoomName()+" and "+other.getRoomName()+".");}
    }


    public Room[] getRoomsDirections() {
        return roomsDirections;
    }

    }


