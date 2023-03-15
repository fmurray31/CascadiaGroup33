import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreCards {
    // Tile description should be in the format "Animal Letter", ie "BearC"
    private String cardTitle;
    private String cardDescription;

    public ScoreCards(String cardTitle, String cardDescription) {
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
    }

    // method to add every score card to an arraylist, then shuffle and return a list of the first 5 cards
    public List<ScoreCards> generateScore() {
        ArrayList<ScoreCards> scoreCardArray = new ArrayList();
        //scoreCardArray.add(new ScoreCards("BearA", "desc"));
        //scoreCardArray.add(new ScoreCards("BearB", "desc"));
        scoreCardArray.add(new ScoreCards("BearC", "desc"));

        scoreCardArray.add(new ScoreCards("FoxA", "desc"));
        scoreCardArray.add(new ScoreCards("FoxB", "desc"));
        scoreCardArray.add(new ScoreCards("FoxC", "desc"));

        //scoreCardArray.add(new ScoreCards("ElkA", "desc"));
        scoreCardArray.add(new ScoreCards("ElkB", "desc"));
        Collections.shuffle(scoreCardArray);

        return scoreCardArray.subList(0, 5);
    }

    public String getCardTitle() {
        return cardTitle;
    }

    // TODO: 15/03/2023 add card description to print, with formatting
    public void displayScoreCards (List<ScoreCards> sc) {
        for (ScoreCards card : sc) {
            System.out.println(card.getCardTitle());
        }
    }
}
