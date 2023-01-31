public class Cascadia {

    public static void main(String[] args) {
        SetupInput setupInput = new SetupInput();
        Score score = new Score();

        int numUsers = setupInput.numPlayer();
        Player[] playerArray = new Player[numUsers];

        playerArray = setupInput.userNameRequest(numUsers);

        score.printPlayerArray(playerArray);
    }
}
