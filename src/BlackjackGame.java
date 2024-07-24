import java.util.*;

public class BlackjackGame {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        List<String> players = new ArrayList<>();
        players.add("Player1");
        players.add("Player2");
        players.add("Player3");
        players.add("Player4");

        Blackjack blackjack = new Blackjack(4, players);

        System.out.println("Welcome to Blackjack!");
        String playing = "";
        while(!playing.equals("No")){
            System.out.println();
            blackjack.blackjackRound();
            System.out.println();
            System.out.println("Would you like to play again? Type yes to play or no to exit");
            System.out.print("Play again?: ");
            playing = console.nextLine();
        }
        
    }
}
