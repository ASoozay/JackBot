import java.util.*;

public class Blackjack {
    private int numPlayers;
    private Map<String, List<String>> playerHands;
    private Deck gameDeck;

    public Blackjack(int numPlayers, List<String> playerNames){
        Map<String, List<String>> playersEmpty = new HashMap<>();
        for(String name : playerNames){
            playersEmpty.put(name, new ArrayList<>());
        }
        playersEmpty.put("Dealer", new ArrayList<>());
        

        playerHands = playersEmpty;
        this.numPlayers = numPlayers;
        gameDeck = new Deck(1);
    }

    public void startingHands(){
        gameDeck.shuffle();
        for(String player : playerHands.keySet()){
            List<String> playerHand = playerHands.get(player);
            playerHand.add(gameDeck.deal());
            playerHand.add(gameDeck.deal());
            playerHands.put(player, playerHand);
        }
    }

    public void showHands(){
        for(String player : playerHands.keySet()){
            List<String> hand = playerHands.get(player);
            System.out.println(player + ": " + hand + " " + total(hand));
            System.out.println();
        }
    }

    public String total(List<String> hand){
        int total = 0;
        String totalString = "";
        
        for (String card : hand){
            int index = card.indexOf(" ");
            String cardValue = card.substring(0, index);
            if(cardValue.equals("Jack") || cardValue.equals("Queen") || cardValue.equals("King")){
                total = total + 10;
            } else if (cardValue.equals("Ace")){
                int aceMax = 11;
                int aceMin = 1;
                if(total + aceMax > 21){
                    total = total + aceMin;
                } else {
                    total = total + aceMax;
                }
            } else {
                int cardInt = Integer.parseInt(cardValue);
                total = total + cardInt;
            }

            if (total == 21){
                totalString = "(" + total + ")" + " [BLACKJACK]";
            } else if (total > 21){
                totalString = "(" + total + ")" + " [BUST]";
            } else {
                totalString = "(" + total + ")";
            }
        }

        return totalString;
    }
}