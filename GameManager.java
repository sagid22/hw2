public class GameManager {
    private Room[]rooms;
    private Player player;
    private String playeName;
    private int bagCap;
    public GameManager(){
    this.rooms=new Room[5];
    this.player=null;
    }
    public void addPlayer(Player p) {
        if (this.player == null) {
            this.player = p;//לחשוב האם זה בסדר שאני שם p או לעשות חדש
            System.out.println(player.getName() + "was added to the game.");
        }
        else {System.out.println("Could not add"+p.getName()+"to the game.");}
    }
    public void addRoom(Room r){
        int counter=0;
        boolean isAdded=false;
        while(isAdded==false&&counter<rooms.length){
            if(rooms[counter]==null){
                rooms[counter]=r;//לחשוב האם זה בסדר שאני שם r או לעשות חדש
                System.out.println(rooms[counter].getName() + "was added to the game.");
                isAdded=true;
            }
            else {counter+=1;}
        }
        if(isAdded==false){
            System.out.println("Could not add"+r.getName()+"to the game.");
        }

    }
    public void addItem(Room r,Item i){
        r.addItemToRoom(i);
    }

    public void removePlayer(Player p){
        if(this.player==p){//לבדוק האם ההשוואה נכונה פה וגם בשאר הREMOVE
            this.player=null;
            System.out.println(p.getName()+"was removed from the game");
        }
        else{System.out.println(p.getName()+"does not exist.");}
    }
    public void removeRoom(Room r){
        boolean isthere=false;
        for (int i=0;i<rooms.length;i++){
            if(rooms[i]==r){
                isthere=true;
                rooms[i]=null;
                System.out.println(r.getName()+"was removed from the game");
            }

        }
        if (isthere==false){System.out.println(r.getName()+"does not exist.");}
    }
    public void removeItem(Room r,Item i){
        r.addItemToRoom(i);
    }
    public void connectRooms(Room room1,Room room2,Direction d){
        if (room1.isDirectionAlreadyConnected(d)==true)
        {
            System.out.println("Could not connect"+room1.getName()+"and"+room2.getName()+".");
        }
        else
            {

                if(d==Direction.NORTH){
                if(room2.isDirectionAlreadyConnected(Direction.SOUTH)==true){
                    System.out.println("Could not connect"+room1.getName()+"and"+room2.getName()+".");

                }
            }
            if(d==Direction.SOUTH){
                if(room2.isDirectionAlreadyConnected(Direction.NORTH)==true){
                    System.out.println("Could not connect"+room1.getName()+"and"+room2.getName()+".");

                }
            }
            if(d==Direction.EAST){
                if(room2.isDirectionAlreadyConnected(Direction.WEST)==true){
                    System.out.println("Could not connect"+room1.getName()+"and"+room2.getName()+".");

                }
            }
            if(d==Direction.WEST){
                if(room2.isDirectionAlreadyConnected(Direction.EAST)==true){
                    System.out.println("Could not connect"+room1.getName()+"and"+room2.getName()+".");

                }
            }
            else {
                room1.connect(d);
                room2.connectOpp(d);
            }


        }



    }


}
