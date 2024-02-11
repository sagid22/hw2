public class Main {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();

        Room room1 = new Room("Room 1");
        Room room2 = new Room("Room 2");

        // Set room exits
        gameManager.connectRooms(room1, room2, Direction.NORTH);

        // Create items
        Item item = new Item("Item 1");

        // Add items to rooms
        gameManager.addItem(room1, item);

        // Create player and start the game
        Player player = new Player("Player 1", 5);
        gameManager.addPlayer(player);
        gameManager.startPlayer(room2);


        // Sample gameplay
        gameManager.movePlayer(Direction.SOUTH); // Move to room 2
        gameManager.pickUpItem(item); // Pick up the key
    }
}
