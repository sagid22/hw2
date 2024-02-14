public class GameManager {
    private Room[] rooms;
    private Player player;
    private String playeName;
    private int bagCap;

    public GameManager() {
        this.rooms = new Room[5];
        this.player = null;
    }

    public void addPlayer(Player p) {
        if (this.player == null) {
            this.player = p;//לחשוב האם זה בסדר שאני שם p או לעשות חדש
            System.out.println(player.getName() + "was added to the game.");
        } else {
            System.out.println("Could not add" + p.getName() + "to the game.");
        }
    }

    public void addRoom(Room r) {
        int counter = 0;
        boolean isAdded = false;
        while (isAdded == false && counter < rooms.length) {
            if (rooms[counter] == null) {
                rooms[counter] = r;//לחשוב האם זה בסדר שאני שם r או לעשות חדש
                System.out.println(rooms[counter].getRoomName() + "was added to the game.");
                isAdded = true;
            } else {
                counter += 1;
            }
        }
        if (isAdded == false) {
            System.out.println("Could not add" + r.getRoomName() + "to the game.");
        }

    }

    public void addItem(Room r, Item i) {
        r.addItemToRoom(i);
    }

    public void removePlayer(Player p) {
        if (this.player == p) {//לבדוק האם ההשוואה נכונה פה וגם בשאר הREMOVE
            this.player = null;
            System.out.println(p.getName() + "was removed from the game");
        } else {
            System.out.println(p.getName() + "does not exist.");
        }
    }

    public void removeRoom(Room r) {
        boolean isthere = false;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == r) {//לבדוק שוב תקינות של השוואה
                isthere = true;
                if (rooms[i].getPlayer() == null) {
                    rooms[i] = null;
                    System.out.println(r.getRoomName() + "was removed from the game");
                } else {
                    System.out.println(r.getRoomName() + "could not be removed.");
                }
            }

        }
        if (isthere == false) {
            System.out.println(r.getRoomName() + "does not exist.");
        }
    }

    public void removeItem(Room r, Item i) {
        r.removeItemFromRoom(i);
    }

    public void connectRooms(Room room1, Room room2, Direction d) {
        room1.roomToRoomConnection(room2, d);
    }


    public void startPlayer(Room startRoom) {
        boolean isAlreadyThere = false;
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getPlayer() == this.player) {
                isAlreadyThere = true;
            }
        }
        if (isAlreadyThere == true) {
            System.out.println(this.player.getName() + "has already started.");
        } else {
            startRoom.playerInitialization(this.player);
            System.out.println(this.player.getName() + "starts in" + startRoom.getRoomName());
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
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getPlayer() == this.player) {
                if (rooms[i].getRoomsDirections()[d] != null && rooms[i].getPuzzleStatus() == false) {//לבדוק אם להוסיף "וגם" עם בדיקת סטטוס הפאזל בחדר
                    (rooms[i].getRoomsDirections()[d]).setPlayer(this.player);
                    System.out.println(this.player.getName() + "moved from" + rooms[i].getRoomName() + "to" + rooms[i].getRoomsDirections()[d].getRoomName() + "via the" + dMove + "exit.");
                    rooms[i].setPlayer(null);
                } else {
                    System.out.println(this.player.getName() + "could not move via the" + dMove + "exit.");
                }
            }
        }
    }

    public void pickUpItem(Item it) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getPlayer() == this.player) {//בדיקה אם זה החדר שבו השחקן
                for (int j = 0; j < rooms[i].getListItems().length; j++) {
                    boolean isInRoom = false;
                    if (rooms[i].getListItems()[j] == it) {//אם יש את החפץ בחדר
                        isInRoom = true;
                        boolean isTherePlace = false;
                        int count = 0;
                        while (isTherePlace == false && count < this.player.getItems().length) {//בודקת אם יש מקום בתיק של השחקן
                            if (this.player.getItems()[count] == null) {
                                isTherePlace = true;
                            }
                            count += 1;

                        }
                        if (isTherePlace == true) {
                            this.player.getItems()[count] = it;
                            rooms[i].getListItems()[j] = null;
                            System.out.println(this.player.getName() + "picked up" + it.getName() + "from" + rooms[i].getRoomName() + ".");
                        } else {
                            System.out.println("player's inventory is full.");
                        }
                    }
                    if (isInRoom == false) {
                        System.out.println(it.getName() + "is not in" + rooms[i].getRoomName() + ".");
                    }
                }
            }
        }

    }

    public void dropItem(Item it) {
        boolean isInBag = false;
        int index = 1;
        for (int i = 0; i < this.player.getItems().length; i++) {//בדיקה האם האייטם בתיק
            if (this.player.getItems()[i] == it) {
                index = i;
                isInBag = true;
            }
        }
        if (isInBag == true) {
            for (int j = 0; j < this.rooms.length; j++) {
                if (rooms[j].getPlayer() == this.player) {//האם השחקן בחדר
                    boolean isTherePlaceInRoom = false;
                    int counter = 0;
                    while (isTherePlaceInRoom == false && counter < rooms[j].getListItems().length) {
                        if (rooms[j].getListItems()[counter] == null) {
                            isTherePlaceInRoom = true;
                        }
                        counter += 1;
                    }
                    if (isTherePlaceInRoom == true) {
                        rooms[j].getListItems()[counter] = it;
                        this.player.getItems()[index] = null;
                        System.out.println(this.player.getName() + "droped" + it.getName() + "in" + rooms[j].getRoomName() + ".");
                    } else {
                        System.out.println(rooms[j].getRoomName() + "is full.");
                    }
                }
            }
        } else {
            System.out.println(it.getName() + "is not in player's inventory.");
        }
    }

    public void disassembleItem(Item it) {
        boolean isDestroyed = false;
        for (int i = 0; i < this.player.getItems().length; i++) {//בדיקה האם האייטם בתיק
            if (this.player.getItems()[i] == it) {
                this.player.getItems()[i] = null;
                isDestroyed = true;
            }
        }
        for (int j = 0; j < rooms.length; j++) {
            for (int k = 0; k < rooms[j].getListItems().length; k++) {
                if (rooms[j].getListItems()[k] == it) {
                    rooms[j].getListItems()[k] = null;
                    isDestroyed = true;
                }
            }
        }
        if (isDestroyed == true) {
            System.out.println(this.player.getName() + "disassembled" + it.getName() + ".");
        } else {
            System.out.println(this.player.getName() + "coould not destroy+" + it.getName() + ".");
        }
    }

    public void solvePuzzle() {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getPlayer() == this.player) {
                if (rooms[i].getPuzzleStatus() == true) {
                    System.out.println(this.player.getName() + "is solving the puzzle in" + rooms[i].getRoomName());
                    rooms[i].setPuzzleStatus(false);//אולי אפשר לעשות deactivate.לנסות להבין האם ההגדרה של אמת-החידה לא פתורה ושקר לא פתורה זו הקונבנציה הנכונה
                } else {
                    System.out.println("There is no active puzzle in " + rooms[i].getRoomName() + ".");
                }
            }
        }
    }

    public void activatePuzzle(Room currentRoom) {
        currentRoom.setPuzzleStatus(true);
    }

    public void deactivatePuzzle(Room currentRoom) {
        currentRoom.setPuzzleStatus(false);
    }


    //public Room getExactRoom(Room r) {

      //  for (int i = 0; i < rooms.length; i++) {
        //    if (rooms[i] == r) {//לבדוק שוב תקינות של השוואה
          //      return rooms[i];
            //}
        //}

    }






