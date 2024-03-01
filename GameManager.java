public class GameManager {
    private Room[] rooms;
    private Player player;

    /**
     * creating the rooms in an array
     */
    public GameManager() {
        this.rooms = new Room[5];
        this.player = null;
    }

    /**
     * Adds a player to the game.
     *
     * @param p The player to be added.
     */
    public void addPlayer(Player p) {
        if (this.player == null) {
            this.player = p;
            System.out.println(player.getName() + " was added to the game.");
        } else {
            System.out.println("Could not add " + p.getName() + " to the game.");
        }
    }

    /**
     * adding a room
     * @param r The room to add.
     */
    public void addRoom(Room r) {
        int counter = 0;
        boolean isAdded = false;
        while (isAdded == false && counter < rooms.length) {
            if (rooms[counter] == null) {
                rooms[counter] = r;
                System.out.println(rooms[counter].getRoomName() + " was added to the game.");
                isAdded = true;
            } else {
                counter += 1;
            }
        }
        if (isAdded == false) {
            System.out.println("Could not add " + r.getRoomName() + " to the game.");
        }

    }

    /**
     * Adds an item to a room in the game.
     *
     * @param r The room to add the item to.
     * @param it The item to be added.
     */
    public void addItem(Room r, Item it) {
        boolean isThere=false;
        for(int i=0;i<rooms.length;i++){
            if(rooms[i]==r) {
                r.addItemToRoom(it);
                isThere = true;
            }
        }
        if(!isThere){ System.out.println("Could not add " + it.getName() + " to the game.");}

    }

    /**
     * Removes a player from the game.
     *
     * @param p The player to be removed.
     */
    public void removePlayer(Player p) {
        if (this.player == p) {
            this.player.removePlayerItems();
            this.player.setCurrentRoom(null);
            this.player = null;
            System.out.println(p.getName() + " was removed from the game.");
        } else {
            System.out.println(p.getName() + " does not exist.");
        }
    }

    /**
     * Removes a room from the game.
     *
     * @param r The room to be removed.
     */
    public void removeRoom(Room r) {
        boolean isthere = false; // if the room is exists in the room array
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == r) {
                isthere = true;
                if (this.player != null) {
                    if (this.player.getCurrentRoom() == r) {//if the player's current room is what we want to remove
                        System.out.println(r.getRoomName() + " could not be removed.");
                    } else {
                        for (int j=0;j<rooms.length;j++) {
                            rooms[j].removeFromRoomDirection(r);
                        }
                        rooms[i].removeItemFromRoom();
                        rooms[i] = null;
                        System.out.println(r.getRoomName() + " was removed from the game.");
                    }
                }
                else if(isthere){// we want to remove room r from the array, and disconnect the others rooms to him
                    for (int k=0;k<rooms.length;k++) {
                        if(rooms[k]!=null){
                            rooms[k].removeFromRoomDirection(r);
                        }
                    }
                    rooms[i].removeItemFromRoom();
                    rooms[i] = null;
                    System.out.println(r.getRoomName() + " was removed from the game.");
                }
            }

        }
        if (isthere == false) {
            System.out.println(r.getRoomName() + " does not exist.");
        }
    }

    /**
     * Connects two rooms in the game.
     *
     * @param room1 The first room to connect.
     * @param room2 The second room to connect.
     * @param d     The direction of the connection.
     */
    public void connectRooms(Room room1, Room room2, Direction d) {
        room1.roomToRoomConnection(room2, d);
    }

    /**
     * Starts the player in a specified room.
     *
     * @param startRoom The room where the player starts.
     */
    public void startPlayer(Room startRoom) {
        if (this.player.getCurrentRoom() == null) {
            this.player.initializationCurrentRoom(startRoom);
            System.out.println(this.player.getName() + " starts in "  + startRoom.getRoomName()+".");
        } else {
            System.out.println(this.player.getName() + " has already started.");
        }

    }

    /**
     * Moves the player in a specified direction.
     *
     * @param dMove The direction in which to move the player.
     */
    public void movePlayer(Direction dMove) {
        Room current=this.player.getCurrentRoom();
        int d = -1;

        String directionName = "";
        switch (dMove) {
            case NORTH:
                d = 0;
                directionName = "north";
                break;
            case SOUTH:
                d = 1;
                directionName = "south";
                break;
            case EAST:
                d = 2;
                directionName = "east";
                break;
            case WEST:
                d = 3;
                directionName = "west";
                break;
        }
        if (d != -1 && current.getRoomsDirections()[d] != null && !current.getPuzzleStatus()) {
            System.out.println(this.player.getName() + " moved from " + current.getRoomName() + " to " + current.getRoomsDirections()[d].getRoomName() + " via the " + directionName + " exit.");
            this.player.setCurrentRoom(current.getRoomsDirections()[d]);
        } else {
            System.out.println(this.player.getName() + " could not move via the " + directionName + " exit.");
        }
    }

    /**
     * Picks up an item from the current room.
     *
     * @param it The item to pick up.
     */
    public void pickUpItem(Item it) {
        if(! (it instanceof Key)) {
            Room current = this.player.getCurrentRoom();
            boolean isInRoom = false;
            for (int j = 0; j < current.getListItems().length; j++) {
                if (current.getListItems()[j] == it) {
                    isInRoom = true;
                    boolean isTherePlace = false;
                    int count = 0;
                    while (!isTherePlace && count < this.player.getInventory().length) {
                        if (this.player.getInventory()[count] == null) {
                            isTherePlace = true;
                        } else {
                            count += 1;
                        }
                    }
                    if (isTherePlace) {
                        this.player.addItemToBag(it, count);
                    } else {
                        System.out.println(this.player.getName() + "'s inventory is full.");
                    }
                }
            }
            if (!isInRoom) {
                System.out.println(it.getName() + " is not in " + current.getRoomName() + ".");
            }
        }
    }

    /**
     * Drops an item in the current room.
     *
     * @param it The item to drop.
     */
    public void dropItem(Item it) {
        boolean isInBag = false;
        for (int i = 0; i < this.player.getInventory().length; i++) {
            if (this.player.getInventory()[i] == it) {
                isInBag = true;
            }
        }
        if (isInBag) {
            Room current=this.player.getCurrentRoom();
            boolean isTherePlaceInRoom = false;
            int counter = 0;
            while (!isTherePlaceInRoom && counter < current.getListItems().length) {
                if (current.getListItems()[counter] == null) {
                    isTherePlaceInRoom = true;
                }else{
                    counter += 1;
                }
            }
            if (isTherePlaceInRoom) {
                this.player.dropItem(it,counter);
            }
            else {
                System.out.println(current.getRoomName() + " is full.");
            }


        } else {
            System.out.println(it.getName() + " is not in "+player.getName()+"'s inventory.");
        }
    }

    /**
     * Disassembles an item.
     *
     * @param it The item to disassemble.
     */
    public void disassembleItem(Item it) {
        boolean isDestroyed = false;
        for (int i = 0; i < this.player.getInventory().length; i++) {
            if (this.player.getInventory()[i] == it) {
                this.player.destroyItemFromInventory(it,i);
                isDestroyed = true;
            }
        }
        for (int k = 0; k < this.player.getCurrentRoom().getListItems().length; k++) {
            if (this.player.getCurrentRoom().getListItems()[k] == it) {
                this.player.destroyItemFromCurrentRoom(it,k);
                isDestroyed = true;
            }
        }
        if (isDestroyed) {
            System.out.println(this.player.getName() + " disassembled " + it.getName() + ".");
        } else {
            System.out.println(this.player.getName() + " could not disassemble " + it.getName() + ".");
        }
    }

    /**
     * Solves the puzzle in the current room.
     */
    public void solvePuzzle() {
        Room current=this.player.getCurrentRoom();
        if (current.getPuzzleStatus()) { //if the puzzle exists
            System.out.println(this.player.getName() + " is solving the puzzle in " + current.getRoomName()+".");
            current.setPuzzleStatus(false);
        } else {
            System.out.println("There is no active puzzle in " + current.getRoomName() + ".");
        }
    }

    /**
     * Activates a puzzle in a specified room.
     *
     * @param currentRoom The room where the puzzle is activated.
     */
    public void activatePuzzle(Room currentRoom) {
        if (! currentRoom.getKeyRoomStatus()){
            currentRoom.setPuzzleStatus(true);
        }
        else {
            System.out.println(currentRoom.getRoomName()+" was unlocked with "+ currentRoom.getRoomKey().getName()+".");
        }
    }

    /**
     * Deactivates a puzzle in a specified room.
     *
     * @param currentRoom The room where the puzzle is deactivated.
     */
    public void deactivatePuzzle(Room currentRoom) {
        if (! currentRoom.getKeyRoomStatus()){
            currentRoom.setPuzzleStatus(false);
        }
        else {
            System.out.println(currentRoom.getRoomName()+" was unlocked with "+ currentRoom.getRoomKey().getName()+".");
        }
    }
}






