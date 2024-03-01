public class Key extends Item{
    protected String name;
    protected int value;
    protected boolean keyStatus;
    public  Key(String name,int value){
        super(name,value);
        keyStatus= false;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    public Boolean getkeyStatus(){
        return this.keyStatus;
    }
    @Override
    public void useItem(Player player){
        player.getCurrentRoom().setKeyRoomStatus(true);
        player.getCurrentRoom().setRoomKey(this);
        if(player.getCurrentRoom().getPuzzleStatus()){
            player.getCurrentRoom().setPuzzleStatus(false);
        }
    }

    @Override
    public boolean equals(Object otherKey1) {
        if (!(otherKey1 instanceof Key)) {
            return false;
        }
        Key otherKey2 = (Key) otherKey1;
        return (this.getValue() == otherKey2.getValue());
    }

    @Override
    public int hashCode() {
        int result = 41;
        result = 31 * result + name.hashCode();
        result = 31 * result + value;
        result = 31 * result + (keyStatus ? 1 : 0);
        return result;
    }

}

