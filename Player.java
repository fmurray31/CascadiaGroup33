public class Player {
    public String userName;
    public int score;

    //Map
    public int[][] map;

    public Player(String userName, int[][] map) {
        this.userName = userName;
        score = 0;
        this.map = map;
    }

    public int[][] getMap() {
        return map;
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
