import java.util.Scanner;
import java.util.Random;

public class Game {
    
    private Player player1;
    private Player player2;
    Boolean playAgain;

    Game() {
        Scanner sc = new Scanner(System.in);
        System.out.println("First player, enter a name: ");
        player1 = new Player();
        player1.setName(sc.nextLine());
        System.out.println("Welcome " + player1.getName() + "!");

        System.out.println("Second player, enter a name: ");
        player2 = new Player();
        player2.setName(sc.nextLine());
        System.out.println("Welcome " + player2.getName() + "!");
        playAgain = true;
    }

    public void play() {
        //initialize variables
        Boolean win = false; //for when the player wins
        int currentPlayer = 0; // will be either player 1 or 2
        Boolean choseReplay = false; //used with if statements to make sure some lines dont get repeated
        Boolean invalidInput = false; //used for: as long as the input is invalid, do the while loop
        int userRemoves = 0; //how many pieces the player wants to remove
        while (playAgain) {
            //if statement to repopulate game if replaying
            if (choseReplay == true) {
                Board.populate(); 
                System.out.println("Reseting the game!");
                choseReplay = false;
            }
            //introduce the game
            System.out.println("There are " + Board.getNumPieces() + " pieces on the board.");
            System.out.println("Each player will take turns removing pieces by a desired amount until one piece is left.");
            System.out.println("Each turn, you must remove at least one piece, but no more than half the remaining pieces on the board.");
            System.out.println("The loser is the player that ends up with the last piece.");
            //randomize whether player 1 or two will go first
            Random r = new Random();
            int oneOrTwo = r.nextInt(2) + 1; //returns a 1 or 2 only
            if (oneOrTwo == 1) { //player one gets to go first
                System.out.println(player1.getName() + " will go first!");
                System.out.println(player1.getName() + ", choose a number between 1 and " + (Board.getNumPieces() / 2) + ".");
                currentPlayer = 1; 
            }
            if (oneOrTwo == 2) { //player 2 gets to go first
                System.out.println(player2.getName() + " will go first!");
                System.out.println(player2.getName() + ", choose a number between 1 and " + (Board.getNumPieces() / 2) + ".");
                currentPlayer = 2;
            }
            Scanner s = new Scanner(System.in);
            while (win == false) {
                //get the amount the user wants to remove
                try {
                    userRemoves = Integer.valueOf(s.nextLine());
                } catch (Exception e) {
                    System.out.println("You can only enter a number!");
                }
                //make sure that it is a valid input (within bounds)
                if (!((1 <= userRemoves) && (userRemoves <= (Board.getNumPieces() / 2)))) {
                    invalidInput = true;
                    while(invalidInput) { //if it is an invalid input, keep asking for an input
                        System.out.println("Invalid input!");
                        System.out.println("Try again.");
                        try {
                        userRemoves = Integer.valueOf(s.nextLine());
                        } catch (Exception e) {
                            System.out.println("You can only enter a number!");
                        }
                        //for when the input is finally valid,
                        if ((1 <= userRemoves) && (userRemoves <= (Board.getNumPieces() / 2))) {
                            Board.removePieces(userRemoves); 
                            invalidInput = false; //exit loop
                        }
                    }
                }
                else {
                    Board.removePieces(userRemoves); //if not invalid, remove the certain amount of pieces
                }
                System.out.println("There are now " + Board.getNumPieces() + " pieces on the board.");
                //when there is one piece left, the current player is the winner because they would make the other player pick up the one piece.
                if (Board.getNumPieces() == 1) {
                    String winner;
                    if (currentPlayer == 1) { //condition for if player one is the winner
                        winner = player1.getName();
                        player1.setPoints(); //add a point to player 1's record
                        System.out.println("Congrats! " + winner + " wins!");
                        System.out.println(winner + " has " + player1.getPoints() + " point(s)!");
                        System.out.println(player2.getName() + " has " + player2.getPoints() + " point(s)!");
                        //ask if replay:
                        System.out.println("Would you like to play again?");
                        if (s.nextLine().equals("yes") || s.nextLine().equals("Yes")) {
                            choseReplay = true; //so that program can check and make sure some pieces of code don't get repeated
                            win = false; //reset so that "hasnt won yet" loop can run
                            break; 
                        }
                        else if (s.nextLine().equals("no") || s.nextLine().equals("No")) {
                            playAgain = false; //don't play
                            win = true; //ends win loop
                        }
                    }
                    else { //condition for if player two is the winner
                        winner = player2.getName();
                        player2.setPoints(); //add a point to player 2's record
                        System.out.println("Congrats! " + winner + " wins!");
                        System.out.println(winner + " has " + player2.getPoints() + " point(s)!");
                        System.out.println(player1.getName() + " has " + player1.getPoints() + " point(s)!");
                        //ask if replay:
                        System.out.println("Would you like to play again?");
                        if (s.nextLine().equals("yes") || s.nextLine().equals("Yes")) {
                            choseReplay = true; //so that program can check and make sure some pieces of code don't get repeated
                            win = false; //reset so that "hasnt won yet" loop can run
                            break;
                        }
                        else if (s.nextLine().equals("no") || s.nextLine().equals("No")) {
                            playAgain = false; //don't play
                            win = true; //ends win loop
                        }
                    }
                    
                }
                //change currentPlayer every round except if replay, skip this so it doesn't get printed out prematurely
                if (!choseReplay) {
                    if (currentPlayer == 1) {
                        currentPlayer = 2;
                        System.out.println(player2.getName() + ", choose a number between 1 and " + (Board.getNumPieces() / 2) + "."); //prompt best works here so we can ask player x directly
                    }
                    else {
                        currentPlayer = 1;
                        System.out.println(player1.getName() + ", choose a number between 1 and " + (Board.getNumPieces() / 2) + "."); //prompt best works here so we can ask player x directly
                    }
                }
                
            }
        }
        
    }

}
