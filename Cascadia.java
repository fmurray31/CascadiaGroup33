public class Cascadia {

    public static void main(String[] args) {
        SetupInput setupInput = new SetupInput();
        Score score = new Score();
        Tiles tiles = new Tiles();

        // testing ui
        HabitatTiles habitatTiles = new HabitatTiles("forest","forest","forest","forest","forest","forest","elk","salmon","fox");
        System.out.println(habitatTiles);

        int numUsers = setupInput.numPlayer();
        Player[] playerArray;

        playerArray = setupInput.userNameRequest(numUsers);

        score.setOrder(playerArray);
        score.printOrder(playerArray);

        System.out.println("\n\n-----------------------------------------------------------\n\n");

        tiles.setupTiles();
    }
}
