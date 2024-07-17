import java.util.*;

public class Deck {
    private int numDeck;
    private List<String> gameDeck;
    private List<String> orderedDeck;

    public Deck(int num){
        numDeck = num;
        orderedDeck = new ArrayList<>();
        String[] nums = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Clubs", "Spades", "Hearts", "Diamonds"};

        for (int i = 1; i <= numDeck; i++){
            for (String cardNum : nums){
                for (String suit : suits){
                    orderedDeck.add(cardNum + " of " + suit);
                }
            }
        }
        gameDeck = orderedDeck;
    }

    public List<String> getDeck(){
        return gameDeck;
    }

    public String deal(){
        String card = gameDeck.get(0);
        gameDeck.remove(0);

        return card;
    }

    public void shuffle(){
        Random rand = new Random();
        int deckSize = gameDeck.size();

        for(int i = 0; i <= 200; i++){
            int one = rand.nextInt(deckSize);
            int two = rand.nextInt(deckSize);
            String cardOne = gameDeck.get(one);
            String cardTwo = gameDeck.get(two);

            gameDeck.set(one, cardTwo);
            gameDeck.set(two, cardOne);
        }
    }

    public void reset(){
        gameDeck = orderedDeck;
    }


}