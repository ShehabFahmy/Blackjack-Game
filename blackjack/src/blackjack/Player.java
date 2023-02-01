package blackjack;

public class Player {
    private String name;
    private int score;
    protected int nCards;

    public Player (String n) {
        this.name = n;
        this.score = 0;
    }

    Card pCards[] = new Card[11];

    public void countScore() {
        score = 0;
        for (int i = 0; i < nCards; i++) {
            score += pCards[i].getValue();
        }
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
}