import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreCards {
    // Tile description should be in the format "Animal Scorenumber", ie "Bear2"
    private String cardTitle;
    private String cardDescription;

    public ScoreCards(String cardTitle, String cardDescription) {
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
    }

    // method to add every score card to an arraylist, then shuffle and return a list of the first 5 cards
    public List<ScoreCards> generateScore() {
        ArrayList<ScoreCards> scoreCardArray = new ArrayList();

        for (int i=0; i<10; i++) {
            ScoreCards bear1 = new ScoreCards("Bear1", "desc");
        }

        Collections.shuffle(scoreCardArray);

        return scoreCardArray.subList(0, 4);
    }

    public String getCardTitle() {
        return cardTitle;
    }
}
