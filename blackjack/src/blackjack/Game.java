package blackjack;
import java.util.Random;
import java.util.Scanner;

public class Game {
    Player player[] = new Player[4];
    Card card[] = new Card[52];
    private int highscore;
    private int nBJ = 0;
    private int nWinners = 0;

    public void generateDeck() {
        for (int i = 0; i < 52; i++) {
            if (i <= 12) {
                card[i] = new Card(0, i);
            }
            else if (i <= 25) {
                card[i] = new Card(1, i-13);
            }
            else if (i <= 38) {
                card[i] = new Card(2, i-26);
            }
            else if (i <= 51) {
                card[i] = new Card(3, i-39);
            }
        }
    }
    private Card generateCard() {
        Random rand = new Random();
        Card drawnCard;
        int randomChoice;
        while (true) {
            randomChoice = rand.nextInt(52);
            if (card[randomChoice] != null) {
                drawnCard = card[randomChoice];
                card[randomChoice] = null;
                break;
            }
        }
        return drawnCard;
    }
    public void insertPlayer(Player p, int index) {
        player[index] = p;
        player[index].pCards[0] = generateCard();
        player[index].nCards += 1;
        System.out.println(player[index].pCards[0].getSuit() + " " + player[index].pCards[0].getRank() + " " + player[index].pCards[0].getValue());
        //if (index != 3) {
            player[index].pCards[1] = generateCard();
            player[index].nCards += 1;
            System.out.println(player[index].pCards[1].getSuit() + " " + player[index].pCards[1].getRank() + " " + player[index].pCards[1].getValue());
        //}
    }
    public void addCard(int index) {
        player[index].pCards[player[index].nCards] = generateCard();
        //System.out.println(player[index].pCards[player[index].nCards].getSuit() + " " + player[index].pCards[player[index].nCards].getRank() + " " + player[index].pCards[player[index].nCards].getValue());
        if (index == 3) {
            BlackJack.gui.updateDealerHand(player[index].pCards[player[index].nCards], card);
        }
        else {
            BlackJack.gui.updatePlayerHand(player[index].pCards[player[index].nCards], index);
        }
        player[index].nCards++;
        updateScore();
    }
    public void dealerTurn() {
        updateScore();
        System.out.println(player[3].getScore());
        while (player[3].getScore() <= highscore) {
            addCard(3);
            System.out.println(player[3].getScore());
        }
        System.out.println("Dealer's Score: " + player[3].getScore());
        if (player[3].getScore() > 21) {
            System.out.println("Dealer BUSTED");
        }
    }
    public void updateScore() {
        highscore = 0;
        for (int i = 0; i < 4; i++) {
            //player[i].BJ = false;
            //player[i].lost = false;
            player[i].countScore();
            if (player[i].getScore() > highscore && player[i].getScore() <= 21 && i != 3) {
                highscore = player[i].getScore();
            }
        }
    }
    public void checkWinner() {
        updateScore();
        System.out.println("HighScore: " + highscore);
        nBJ = 0;
        nWinners = 0;
        for (int i = 0; i < 4; i++) {
            if (player[i].getScore() == 21) {
                nBJ++;
            }
            else if (player[i].getScore() == highscore) {
                nWinners++;
            }
        }
        System.out.println("nBJ: " + nBJ);
        System.out.println("nWinners: " + nWinners);
        if (player[3].getScore() > highscore && player[3].getScore() <= 21) {
            System.out.println("Dealer BLACKJACK");
        }
        else if (nBJ == 0 && nWinners == 1) {
            for (int i = 0; i < 4; i++) {
                if (player[i].getScore() == highscore) {
                    System.out.println(player[i].getName() + " Wins");
                    break;
                }
            }
        }
        else if (nBJ == 1) {
            for (int i = 0; i < 4; i++) {
                if (player[i].getScore() == 21) {
                    System.out.println(player[i].getName() + " BLACKJACK");
                    break;
                }
            }
        }
        else {
            System.out.println("PUSH");
        }
    }
    public void play(int pIndex) {
        updateScore();
        Scanner sc = new Scanner(System.in);
        System.out.println(player[pIndex].getName() + "'s turn");
        boolean contnu;
        do {
            int c;
            System.out.println(player[pIndex].getName() + "'s Score: " + player[pIndex].getScore());
            if (player[pIndex].getScore() <= 21) {
                System.out.println("1) Hit");
                System.out.println("2) Stand");
                c = sc.nextInt();
                if (c == 1) {
                    contnu = true;
                    addCard(pIndex);
                } else {
                    contnu = false;
                }
            }
            else {
                System.out.println(player[pIndex].getName() + " BUSTED");
                contnu = false;
            }
            updateScore();
        }while(contnu);
    }
}