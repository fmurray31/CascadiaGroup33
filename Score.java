import java.nio.file.StandardWatchEventKinds;
import java.util.List;
import java.util.Random;

public class Score {
    private Player player;
    private List<ScoreCards> scoreCards;
    public void scorePlayer (Player p, List<ScoreCards> sc) {
        player = p;
        scoreCards = sc;

        for (int i=0; i<4; i++) {
            switch (scoreCards.get(i).getCardTitle()) {
                case "bear1": bear1Score(); break;
                case "bear2": bear2Score(); break;
                case "bear3":
            }
        }
    }

    private void bear1Score () {}
    private void bear2Score () {}
    private  void bear3Score() {}
}
