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
        bearCardArray.add(new ScoreCards("BearA", "Score points shown for total number of pairs of Bears.\nA pair of Bears is exactly two Bears adjacent to each other with no other adjacent Bears.\n1 pair = 4 points, 2 pairs = 11 points, 3 pairs = 19 points, 4 pairs = 27 points (Max)."));
        bearCardArray.add(new ScoreCards("BearB", "Score 10 points per group of three Bears. Each group must be exactly three adjacent Bears,\nand may not have any other Bears adjacent to it. 3 Bears = 10 points (Max)."));
        bearCardArray.add(new ScoreCards("BearC", "Score points shown for each group of one, two, or three Bears, depending on size.\nEach group of Bears may not have any other Bears adjacent to it.\nScores a bonus 3 points for having one of each of the three group sizes.\n group size 1 = 2 points, group size 2 = 5 points, group size 3 = 8 points (Max). Bonus 3 points for having all 3 groups."));
        Collections.shuffle(bearCardArray);

        ArrayList<ScoreCards> foxCardArray = new ArrayList();
        foxCardArray.add(new ScoreCards("FoxA", "Score points shown for each Fox, depending on the number of unique wildlife type adjacent to it.\nOther adjacent Foxes may be scored as unique when scoring each Fox.\n1 unique wildlife type = 1 point, 2 unique wildlife types = 2 points, 3 unique wildlife types = 3 points, 4 unique wildlife types = 4 points, 5 unique wildlife types = 5 points (Max)."));
        foxCardArray.add(new ScoreCards("FoxB", "Score points shown for each Fox, depending on the number of unique pairs of other wildlife types adjacent to it.\nOther adjacent Fox pairs do not score. Each pair of other wildlife must be unique - the same wildlife may not count for more than one pair,\neven if four or six of that wildlife are adjacent. Pairs of wildlife do not need to be adjacent to each other.\n1 pair = 3 points, 2 pairs = 5 points, 3 pairs = 7 points (Max)."));
        foxCardArray.add(new ScoreCards("FoxC", "Score points shown for each Fox depending on the number of a single wildlife type adjacent to it.\nOther adjacent Foxes may not be scored.\n1 single wildlife type tile = 1 point, 2 single wildlife type tiles = 2 points, 3 single wildlife type tiles = 3 points, 4 single wildlife type tiles = 4 points, 5 single wildlife type tiles = 5 points, 6 single wildlife type tiles = 6 points (Max)"));
        Collections.shuffle(foxCardArray);

        ArrayList<ScoreCards> elkCardArray = new ArrayList();
        elkCardArray.add(new ScoreCards("ElkA", "Score points shown for each straight line of adjacent Elk,\n depending on the length of the line. A straight line is defined as orthogonally adjacent.\nTwo lines of Elk may be adjacent to one another, however, each Elk may only count for a single line.\nLines do not need to be horizontal. 1 Elk = 2 points, 2 Elk = 5 points, 3 Elk = 9 points, 4 Elk = 13 points (Max)"));
        elkCardArray.add(new ScoreCards("ElkB", "Score points shown for each contiguous group of adjacent Elk in any shape, depending on size of group.\n1 Elk = 2 points, 2 Elk = 4 points, 3 Elk = 7 points, 4 Elk = 10 points, 5 Elk = 14 points, 6 Elk = 18 points, 7 Elk = 23 points, 8+ Elk = 28 points (Max)."));
        elkCardArray.add(new ScoreCards("ElkC", "Score points shown for each group of Elk in each exact shape.\nTwo groups of Elk may be adjacent to one another, however, each Elk may only count for a single group. Groups may be rotated.\n1 Elk = 2 points, 2 Elks adjacent = 5 points, 3 Elk in triangle = 9 points, 4 Elk in a diamond = 13 points (Largest Group Size)."));
        Collections.shuffle(elkCardArray);


        ArrayList<ScoreCards> hawkCardArray = new ArrayList();
        hawkCardArray.add(new ScoreCards("HawkA", "Score points shown for total number of Hawks that are not adjacent to any other Hawks.\n1 Hawk = 2 points, 2 Hawks = 5 points, 3 Hawks = 8 points, 4 Hawks = 11 points, 5 Hawks = 14 points, 6 Hawks = 18 Points, 7 Hawks = 22 points , 8+ Hawks = 26 points (Max)"));
        hawkCardArray.add(new ScoreCards("HawkB", "Score points shown for each Hawk, based on it's line of sight to any other Hawk that are not directly adjacent.\nLine of sight is not interrupted by a gap in Habitat Tiles, or by other wildlife.\n1 Hawk = 2 points, 2 Hawks = 5 points, 3 Hawks = 9 points, 4 Hawks = 12 points, 5 Hawks = 16 points, 6 Hawks = 20 points, 7 Hawks = 24 points, 8 Hawks = 28 points (Max)."));
        hawkCardArray.add(new ScoreCards("HawkC", "Score 3 points for each pair of Hawks that share a direct line of sight between them.\nEach Hawk may be counted as part of multiple pairs.\nLine of sight is not interrupted by a gap in Habitat Tiles, or by other wildlife.\npair of Hawks = 3 points (No Limit)"));
        Collections.shuffle(hawkCardArray);

        ArrayList<ScoreCards> salmonCardArray = new ArrayList();
        salmonCardArray.add(new ScoreCards("SalmonA", "Score points shown for each run of Salmon, depending on length of run.\nA run is defined as a group od adjacent Salmon where each Salmon is adjacent to no more than two other Salmon.\nA group of three Salmon in a 'triangle' shape may count as a run, but no other Salmon may be attached to this run.\nEach run of Salmon may not have any other Salmon adjacent to it.\n1 Salmon = 2 points, 2 Salmon = 4 points, 3 Salmon = 7 points, 4 Salmon = 11 points, 5 Salmon = 15 points, 6 Salmon = 20 points, 7+  Salmon = 26 points (Max)."));
        salmonCardArray.add(new ScoreCards("SalmonB", "Score points shown for each run of Salmon, depending on length of run.\nA run is defined as a group of adjacent Salmon where each Salmon is adjacent to no more than two other Salmon.\nA group of three Salmon in a 'triangle' shape may count as a run, but no other Salmon may be attached to this run.\nEach run of Salmon may not have any other Salmon adjacent to it.\n1 Salmon = 2 points, 2 Salmon = 4 points, 3 Salmon = 8 points, 4+ Salmon = 12 points (Max)."));
        salmonCardArray.add(new ScoreCards("SalmonC", "Score points shown for each run of Salmon, depending on length of run.\nA run is defined as a group of adjacent Salmon where each Salmon is adjacent to no more than two other Salmon.\nA group of three Salmon in a 'triangle' shape may count as a run, but no other Salmon may be attached to this run.\nEach run of Salmon may not have any other Salmon adjacent to it.\n1 Salmon = 2 points, 2 Salmon = 4 points, 3 Salmon = 9 points, 4 Salmon = 11 points, 5+ Salmon = 17 points (Max)."));
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
