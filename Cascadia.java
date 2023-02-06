public class Cascadia {

    public static void main(String[] args) {
        SetupInput setupInput = new SetupInput();
        Score score = new Score();
        Tiles tiles = new Tiles();

        // testing ui
//        HabitatTiles testHab = new HabitatTiles("mountain","mountain","mountain","forest","forest","forest","elk","salmon","fox");
//        System.out.println(testHab);
//        testHab.rotateTile(3);
//        System.out.println(testHab);

        int numUsers = setupInput.numPlayer();
        Player[] playerArray;

        playerArray = setupInput.userNameRequest(numUsers);

        score.setOrder(playerArray);
        score.printOrder(playerArray);

        System.out.println("\n\n-----------------------------------------------------------\n\n");

        tiles.setupTiles();

        tiles.drawCentralTiles();
        tiles.displayCentralTiles();
    }
}
