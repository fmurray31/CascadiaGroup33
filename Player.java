public class Player {
    private String userName;
    private int score;

    public Player(String userName) {
        this.userName = userName;
        score = 0;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return userName + ", score: " + score;
    }
}
