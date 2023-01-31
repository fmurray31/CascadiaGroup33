public class Cascadia {

    public static void main(String[] args) {
        SetupInput setupInput = new SetupInput();
        Score score = new Score();
        Tiles tiles = new Tiles();

        int numUsers = setupInput.numPlayer();
        Player[] playerArray;

        playerArray = setupInput.userNameRequest(numUsers);

        score.setOrder(playerArray);
        score.printOrder(playerArray);

        System.out.println("\n\n-----------------------------------------------------------\n\n");

        tiles.setupTiles();
        System.out.println(tiles.getAnimalAL());
    }
    
}
