public class GameManager {
    private Room[] rooms;
    private Player player;
    private int bagCap;

    public GameManager() {
        this.rooms = new Room[5];
        this.player = null;
    }

    public void addPlayer(Player p) {
        if (this.player == null) {
            this.player = p;
            System.out.println(player.getName() + " was added to the game.");
        } else {
            System.out.println("Could not add " + p.getName() + " to the game.");
        }
    }

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

    public void addItem(Room r, Item i) {
        r.addItemToRoom(i);
    }

    public void removePlayer(Player p) {
        if (this.player == p) {
            this.player.removePlayerItems();
            this.player = null;
            System.out.println(p.getName() + " was removed from the game.");
        } else {
            System.out.println(p.getName() + " does not exist.");
        }
    }

    public void removeRoom(Room r) {
        boolean isthere = false; // if the room is exists in the room array
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == r) {
                isthere = true;
                if (this.player.getCurrentRoom() == r) {//if the player's current room is what we want to remove
                    System.out.println(r.getRoomName() + " could not be removed.");
                } else {
                    rooms[i] = null;
                    System.out.println(r.getRoomName() + " was removed from the game.");
                }
            }

        }
        if (isthere == false) {
            System.out.println(r.getRoomName() + " does not exist.");
        }
    }


    public void connectRooms(Room room1, Room room2, Direction d) {
        room1.roomToRoomConnection(room2, d);
    }


    public void startPlayer(Room startRoom) {
        if (this.player.getCurrentRoom() == null) {
            this.player.initializationCurrentRoom(startRoom);
            System.out.println(this.player.getName() + " starts in "  + startRoom.getRoomName()+".");
        } else {
            System.out.println(this.player.getName() + " has already started.");
        }

    }


    public void movePlayer(Direction dMove) {
        int d = -1;
        switch (dMove) {
            case NORTH:
                d = 0;
            case SOUTH:
                d = 1;
            case EAST:
                d = 2;
            case WEST:
                d = 3;
        }
        if (this.player.getCurrentRoom().getRoomsDirections()[d] != null && this.player.getCurrentRoom().getPuzzleStatus() == false) {//לבדוק אם להוסיף "וגם" עם בדיקת סטטוס הפאזל בחדר
            System.out.println(this.player.getName() + " moved from " + this.player.getCurrentRoom() + " to " + this.player.getCurrentRoom().getRoomsDirections()[d] + " via the " + dMove + " exit.");
            this.player.setCurrentRoom(this.player.getCurrentRoom().getRoomsDirections()[d]);
        } else {
            System.out.println(this.player.getName() + " could not move via the " + dMove + " exit.");
        }
    }

    public void pickUpItem(Item it) {
        Room current=this.player.getCurrentRoom();
        boolean isInRoom = false;
        for (int j = 0; j < current.getListItems().length; j++) {
            if (current.getListItems()[j] == it) {//אם יש את החפץ בחדר
                isInRoom = true;
                boolean isTherePlace = false;
                int count = 0;
                while (!isTherePlace && count < this.player.getInventory().length) {//בודקת אם יש מקום בתיק של השחקן
                    if (this.player.getInventory()[count] == null) {
                        isTherePlace = true;
                    } else {
                        count += 1;
                    }
                }
                if (isTherePlace) {
                    this.player.addItemToBag(it,count);
                } else {
                    System.out.println("player's inventory is full.");
                }
            }
            if (!isInRoom ) {
                System.out.println(it.getName() + " is not in " + current.getRoomName() + ".");
            }
        }
    }

    public void dropItem(Item it) {
        boolean isInBag = false;
        for (int i = 0; i < this.player.getInventory().length; i++) {//בדיקה האם הפריט בתיק
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
                this.player.DropItem(it,counter);
            }
            else {
                System.out.println(current.getRoomName() + " is full.");
            }


        } else {
            System.out.println(it.getName() + "is not in player's inventory.");
        }
    }

    public void disassembleItem(Item it) {
        boolean isDestroyed = false;
        for (int i = 0; i < this.player.getInventory().length; i++) {//בדיקה האם הפריט בתיק
            if (this.player.getInventory()[i] == it) {
                this.player.destroyItemFromInventory(it,i);
                isDestroyed = true;
            }
        }
        for (int k = 0; k < this.player.getCurrentRoom().getListItems().length; k++) {// בידקה האם הפריט בחדר
            if (this.player.getCurrentRoom().getListItems()[k] == it) {
                this.player.destroyItemFromCurrentRoom(it,k);
                isDestroyed = true;
            }
        }
        if (isDestroyed) {
            System.out.println(this.player.getName() + " disassembled " + it.getName() + ".");
        } else {
            System.out.println(this.player.getName() + " could not destroy " + it.getName() + ".");
        }
    }

    public void solvePuzzle() {
        Room current=this.player.getCurrentRoom();
        if (current.getPuzzleStatus()) { //if the puzzle exists
            System.out.println(this.player.getName() + " is solving the puzzle in " + current.getRoomName());
            current.setPuzzleStatus(false);//אולי אפשר לעשות deactivate.לנסות להבין האם ההגדרה של אמת-החידה לא פתורה ושקר לא פתורה זו הקונבנציה הנכונה
        } else {
            System.out.println("There is no active puzzle in " + current.getRoomName() + ".");
        }
    }

    public void activatePuzzle(Room currentRoom) {
        currentRoom.setPuzzleStatus(true);
    }

    public void deactivatePuzzle(Room currentRoom) {
        currentRoom.setPuzzleStatus(false);
    }

    }






