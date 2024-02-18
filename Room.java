public class Room {
    private String name;
    private Item[] listItems;
    private Room[] roomsDirections;
    private boolean puzzleStatus;

    /**
     * Constructs a new Room object with the specified name.
     *
     * @param name The name of the room.
     */
    public Room(String name) {
        this.name = name;
        this.listItems = new Item[2];
        this.roomsDirections = new Room[4];//north-0,south-1,east-2,west-3
        this.puzzleStatus = false;
    }

    /**
     * Gets the puzzle status of the room.
     *
     * @return The puzzle status of the room.
     */
    public boolean getPuzzleStatus() {
        return this.puzzleStatus;
    }

    /**
     * Sets the puzzle status of the room.
     *
     * @param status The puzzle status to set.
     */
    public void setPuzzleStatus(boolean status) {
        this.puzzleStatus = status;
    }

    /**
     * Adds an item to the room.
     *
     * @param i The item to add to the room.
     */
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
    public void removeFromRoomDirection(Room r){//מקבלת חדר ובמידה והוא מחובר במערך הכיוונים- שמים במקומו NULL
        for (int i=0;i<roomsDirections.length;i++){
            if(roomsDirections[i]==r){
                roomsDirections[i]=null;
            }
        }
    }

    /**
     * Removes an item from the room.
     *
     * @param it The item to remove from the room.
     */
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

    /**
     * Gets the name of the room.
     *
     * @return The name of the room.
     */
    public String getRoomName() {
        return this.name;
    }

    /**
     * Gets the list of items in the room.
     *
     * @return The list of items in the room.
     */
    public Item[] getListItems(){return this.listItems;}

    /**
     * Establishes a connection between two rooms.
     *
     * @param other The other room to connect to.
     * @param d     The direction of the connection.
     */
    public void roomToRoomConnection(Room other,Direction d){
        int d1=-1,d2=-1;
        switch(d){
            case NORTH:
                d1=0;
                d2=1;
                break;
            case SOUTH:
                d1=1;
                d2=0;
                break;
            case EAST:
                d1=2;
                d2=3;
                break;
            case WEST:
                d1=3;
                d2=2;
                break;
        }
        if(this.getRoomsDirections()[d1]==null&& other.getRoomsDirections()[d2]==null){
            this.getRoomsDirections()[d1]=other;
            other.getRoomsDirections()[d2]=this;
            System.out.println(this.getRoomName()+" and "+other.getRoomName()+" are connected"+".");
        }
        else {System.out.println("Could not connect "+this.getRoomName()+" and "+other.getRoomName()+".");}
    }

    /**
     * Gets the directions of connected rooms.
     *
     * @return The array of connected rooms in different directions.
     */
    public Room[] getRoomsDirections() {
        return roomsDirections;
    }


    }


