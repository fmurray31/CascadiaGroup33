public class Player {
    public String userName;
    public int score;
    public String[][] map;

    public Player(String userName, String[][] map) {
        this.userName = userName;
        score = 0;
        this.map = map;
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

    public String[][] getMap () {
        return map;
    }
}
