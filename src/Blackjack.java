import java.util.*;

public class Blackjack {
    private int numPlayers;
    private Map<String, List<String>> playerHands;
    private List<String> dealer;
    private Deck gameDeck;
    private Scanner console;


    // method: BlackJack (no return type)
    // purpose: 
    public Blackjack(int numPlayers, List<String> playerNames){
        Map<String, List<String>> playersEmpty = new HashMap<>();
        for(String name : playerNames){
            playersEmpty.put(name, new ArrayList<>());
        }
        
        this.numPlayers = numPlayers;
        playerHands = playersEmpty;
        dealer = new ArrayList<>();
        console = new Scanner(System.in);
        gameDeck = new Deck(1);
    }


    public void blackjackRound(){
        handsReset();
        gameDeck.reset();
        gameDeck.shuffle();
        startingHands();
        showHands(0);
        if(total(dealer) == 21){
            System.out.println(dealer + printTotal(total(dealer)));
            System.out.println("The dealer has blackjack. You lose");
        } else {
            for(String player : playerHands.keySet()){
                turn(player);
                System.out.println();
            }
        }
        showHands(1);
        dealerTurn();
        winOrLose();
    }


    public void startingHands(){
        for(String player : playerHands.keySet()){
            List<String> playerHand = playerHands.get(player);
            playerHand.add(gameDeck.deal());
            playerHand.add(gameDeck.deal());
            playerHands.put(player, playerHand);
        }

        dealer.add(gameDeck.deal());
        dealer.add(gameDeck.deal());
    }

    public void showHands(int turns){
        if(turns == 0){
            for(String player : playerHands.keySet()){
                List<String> hand = playerHands.get(player);
                System.out.println(player + ": " + hand + " " + printTotal(total(hand)));
                System.out.println();
            }
            if(total(dealer) == 21){
                System.out.println("Dealer: " + dealer + total(dealer));
            } else {
                System.out.println("Dealer: " + dealer.get(0) + ", CARD 2");
            }

        } else {
            for(String player : playerHands.keySet()){
                List<String> hand = playerHands.get(player);
                System.out.println(player + ": " + hand + " " + total(hand));
                System.out.println();
            }
                System.out.println("Dealer: " + dealer);
        }
    }

    public void turn(String player){
        List<String> currentHand = playerHands.get(player);
        int handTotal = total(currentHand);
        
        System.out.println();
        if(handTotal == 21){
            System.out.println("You already have blackjack. Sit the rest of this round out winner!");
        } else {
            System.out.println("What Would You Like To Do " + player + "?");
            System.out.println("Hand: " + currentHand + " " + printTotal(handTotal));
            System.out.println("Hit (H)");
            System.out.println("Stay (S)");
            System.out.print("Choice: ");
            String choice = console.nextLine();

            while(!choice.equals("S")){
                if(choice.equals("H")){
                    String card = gameDeck.deal();
                    currentHand.add(card);
                    aceCheck(currentHand);
                    playerHands.put(player, currentHand);
                    handTotal = total(currentHand);
                    System.out.println();
                    System.out.println("Card: " + card);
                    System.out.println("Hand: " + currentHand + printTotal(handTotal));
                    System.out.println();
                }

                if(handTotal == 21){
                    System.out.println("You have blackjack. Congrats!");
                    break;
                } else if (handTotal > 21){
                    System.out.println("You have gone bust. Better luck next round!");
                    break;
                }

                System.out.println("What Would You Like To Do " + player + "?");
                System.out.println("Hand: " + currentHand + " " + printTotal(handTotal));
                System.out.println("Hit (H)");
                System.out.println("Stay (S)");
                System.out.print("Choice: ");
                choice = console.nextLine();
            }
        }
    }

    public void dealerTurn(){
        System.out.println();
        while(total(dealer) < 18){
            String card = gameDeck.deal();
            dealer.add(card);
            System.out.println("Card: " + card);
            System.out.println("Dealer's Hand: " + dealer + printTotal(total(dealer)));
            System.out.println();

            if(total(dealer) >= 17){
                if(total(dealer) == 21){
                    System.out.println("The dealer has blackjack");
                } else {
                    System.out.println("The dealer has " + total(dealer));
                    if(total(dealer) > 21){
                        System.out.println("The dealer has gone bust");
                    }
                }
                break;
            }
        }
    }

    public int total(List<String> hand){
        int total = 0;
        
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
        }
            return total;
    }

    public String printTotal(int total){
        String totalString = "";
        if(total == 21){
            totalString = "(" + total + ") [BLACKJACK]";
        } else if(total > 21){
            totalString = "(" + total + ") [BUST]";
        } else {
            totalString = "(" + total + ")";
        }
        return totalString;
    }

    public void winOrLose(){
        List<String> winners = new ArrayList<>();
        List<String> losers = new ArrayList<>();
        List<String> push = new ArrayList<>();
        int dealerTotal = total(dealer);

        if(dealerTotal > 21){
            for(String player : playerHands.keySet()){
                int playerTotal = total(playerHands.get(player));
                if(playerTotal <= 21){
                    winners.add(player);
                } else {
                    losers.add(player);
                }
            }
        } else {
            for(String player : playerHands.keySet()){
                int playerTotal = total(playerHands.get(player));
                if(playerTotal > 21){
                    losers.add(player);
                } else if(playerTotal > dealerTotal){
                    winners.add(player);
                } else if(playerTotal == dealerTotal){
                    push.add(player);
                } else if(playerTotal < dealerTotal){
                    losers.add(player);
                }
            }
        }
        System.out.println("Winners: " + winners);
        System.out.println("Losers: " + losers);
        System.out.println("Push: " + push);

    }


    public void aceCheck(List<String> hand){
        for(int i = 0; i < hand.size() -1; i++){
            String card = hand.get(i);
            if(card.equals("Ace of Spades") || card.equals("Ace of Clubs") || card.equals("Ace of Hearts") || card.equals("Ace of Hearts")){
                hand.add(card);
                hand.remove(i);
                i--;
            }
        }
        
    }

    public void handsReset(){
        for(String player : playerHands.keySet()){
            List<String> playerHand = playerHands.get(player);
            playerHand.clear();
            playerHands.put(player, playerHand);
            dealer.clear();
        }
    }
}