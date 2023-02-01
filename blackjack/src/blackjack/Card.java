package blackjack;

public class Card {
    private int suit;
    private int rank;
    private int value;

    public Card(int s, int r) {
        this.suit = s;
        this.rank = r;
        if (r == 10 || r == 11 || r == 12) {
            this.value = 10;
        } else {
            this.value = r + 1;
        }
    }
    public Card(Card c) {
        this.suit = c.suit;
        this.rank = c.rank;
        this.value = c.value;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }
}