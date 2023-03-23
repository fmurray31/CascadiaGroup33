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
    // The score cards for each animal are added to a separate arraylist, which is then shuffled and the first card in each arraylist is added to the final arraylist
    public List<ScoreCards> generateScore() {
        ArrayList<ScoreCards> scoreCardArray = new ArrayList();

        ArrayList<ScoreCards> bearCardArray = new ArrayList();
        bearCardArray.add(new ScoreCards("BearA", "Score points for total number of pairs of bears.\nA pair of bears is exactly two adjacent bears, with no others adjacent."));
        bearCardArray.add(new ScoreCards("BearB", "desc"));
        bearCardArray.add(new ScoreCards("BearC", "desc"));
        Collections.shuffle(bearCardArray);

        ArrayList<ScoreCards> foxCardArray = new ArrayList();
        foxCardArray.add(new ScoreCards("FoxA", "desc"));
        foxCardArray.add(new ScoreCards("FoxB", "desc"));
        foxCardArray.add(new ScoreCards("FoxC", "desc"));
        Collections.shuffle(foxCardArray);

        ArrayList<ScoreCards> elkCardArray = new ArrayList();
        elkCardArray.add(new ScoreCards("ElkA", "desc"));
        elkCardArray.add(new ScoreCards("ElkB", "desc"));
        elkCardArray.add(new ScoreCards("ElkC", "desc"));
        Collections.shuffle(elkCardArray);


        ArrayList<ScoreCards> hawkCardArray = new ArrayList();
        hawkCardArray.add(new ScoreCards("HawkA", "desc"));
        hawkCardArray.add(new ScoreCards("HawkB", "desc"));
        hawkCardArray.add(new ScoreCards("HawkC", "desc"));
        Collections.shuffle(hawkCardArray);

        ArrayList<ScoreCards> salmonCardArray = new ArrayList();
        salmonCardArray.add(new ScoreCards("SalmonA", "desc"));
        salmonCardArray.add(new ScoreCards("SalmonB", "desc"));
        salmonCardArray.add(new ScoreCards("SalmonC", "desc"));
        Collections.shuffle(salmonCardArray);

        scoreCardArray.add(bearCardArray.get(0));
        scoreCardArray.add(foxCardArray.get(0));
        scoreCardArray.add(elkCardArray.get(0));
        scoreCardArray.add(hawkCardArray.get(0));
        scoreCardArray.add(salmonCardArray.get(0));

        return scoreCardArray;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    // method to print a list of score cards, with formatting
    public void displayScoreCards (List<ScoreCards> sc) {
        for (ScoreCards card : sc) {
            System.out.println(card.getCardTitle() + "\n");
            System.out.println(card.cardDescription + "\n");
            System.out.println("*+*+*+*+*+*+*+*+*+*+*");
        }
    }
}
