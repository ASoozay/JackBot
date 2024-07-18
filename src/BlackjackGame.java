import java.util.*;

public class BlackjackGame {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        List<String> players = new ArrayList<>();
        players.add("Andrew");
        players.add("Jack");
        players.add("Tyler");
        players.add("Dorian");

        Blackjack blackjack = new Blackjack(4, players);

        blackjack.blackjackRound();
    }
}
