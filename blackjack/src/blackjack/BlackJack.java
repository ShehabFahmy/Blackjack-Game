package blackjack;
import java.util.*;
public class BlackJack {
    public static Game g = new Game();
    public static GUI gui = new GUI();
    public static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter First player name: ");
        String x = sc.next();
        Player p1 = new Player(x);
        g.insertPlayer(p1, 0);

        System.out.print("Enter Second player name: ");
        x = sc.next();
        Player p2 = new Player(x);
        g.insertPlayer(p2, 1);

        System.out.print("Enter Third player name: ");
        x = sc.next();
        Player p3 = new Player(x);
        g.insertPlayer(p3, 2);

        Player D = new Player("Dealer");
        g.insertPlayer(D, 3);

    }
    public static void main (String[] args) {
        //GUI gui = new GUI();
        g.generateDeck();
        start();
        gui.runGUI( g.card, g.player[0].pCards, g.player[1].pCards, g.player[2].pCards, g.player[3].pCards );
        g.play(0);
        g.play(1);
        g.play(2);
        g.updateScore();
        g.dealerTurn();
        g.checkWinner();
    }
}