import java.util.*;

public class JackBot {
    public static void main(String[] args){
        List<String> names = new ArrayList<>();
        names.add("Andrew");
        names.add("Tyler");
        names.add("Jack");
        names.add("Dorian");

        Blackjack blackJackGame = new Blackjack(4, names);
        blackJackGame.startingHands();
        blackJackGame.showHands();
    }

}
