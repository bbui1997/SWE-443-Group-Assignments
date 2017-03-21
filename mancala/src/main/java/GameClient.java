/**
 * Created by Jess on 3/19/2017.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class GameClient {

    //Class variables for the network setup
    public static Scanner scan;
    public static Socket sock;
    public static boolean isHost;
    public static MancalaGame game;
    public static Player p1;
    public static Player p2;
    public static PrintWriter out;
    public static BufferedReader in;
    public static int port = 1337;  //hard coded port at 1337
    public static String address = "";


    public static void main(String[] args) {
        //prepare to receive user input
        scan = new Scanner(System.in);
        //This code enables actual network play
        //passing an IP address argument allows two players to play over a network
        //Note this will not be possible on the Mason Network due to security blocks
        if (args.length > 0) {
            address = args[0];
            System.out.println("ADDRESS: " + address);
        }

        //Game Splash, Player Selection, Host/Client selection
        boolean done = false;
        isHost = false;
        while (!done) {
            System.out.println("Welcome to Mancala!");
            System.out.println("Player 1 (Host) or Player 2 (Guest)?");
            String s = scan.next();
            //set host
            if (s.contains("1") && !s.contains("2")) {
                isHost = true;
                done = true;
            }
            //set client
            else if (s.contains("2") && !s.contains("1")) {
                done = true;

            }
            //invalid choice
            else {
                System.out.println("Invalid selection. Usage: 1 for Player 1. 2 for Player 2.");
            }

        }
        showInstructions();
        //host/client setup logic
        try {
            sock = isHost ? setupHost() : setupClient();
            //I.O setup
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            //network issues
        } catch (Exception e) {
            System.out.println("Network issue. Cannot resolve. " + e.toString());
            return;
        }

        //no network issues. Make the game board
        game = new MancalaGame(6);  //can be played with more than 6 but the graphics are set up for 6
        //make the players and add to game
        p1 = new Player();
        p2 = new Player();
        p1.setName("Host");
        p1.setGame(game);
        p2.setName("Client");
        p2.setGame(game);
        System.out.println(isHost ? "You are Player 1" : "You are Player 2");

        //Game loop
        //Print the board, get the turn, take a turn or receive a turn
        while (!gameOver()) {
            printBoard();
            if ((game.getPlayerTurn() == 0 && isHost) || (game.getPlayerTurn() == 1 && !isHost)) {
                takeTurn();
            } else {
                System.out.println("The opponent is taking a turn");
                receiveTurn();
            }
        }
        scan.close();
    }

    public static Socket setupHost() throws Exception {
        //setup host logic
        int backlog = 3;
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("HOST: opening socket on addr:port " + addr + ":" + port);
        ServerSocket serv = new ServerSocket(port, backlog, addr);
        return serv.accept();
    }

    public static Socket setupClient() throws Exception {
        //setup client logic
        if (!address.equals("")) {
            System.out.println("CLIENT: connecting to addr:port " + address + ":" + port);
            return new Socket(address, port);
        } else {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("CLIENT: connecting to addr:port " + addr + ":" + port);
            return new Socket(addr, port);
        }

    }

    //Print the state of the board to the use (Command Line GUI)
    public static void printBoard() {
        //Use Stringbuilder to make it pretty
        StringBuilder s = new StringBuilder("[P1] | ");
        s.append(flipP1(game.getP1Houses()));
        s.append("| \n ");
        s.append(String.format("%2d", game.getP1Store().getPebbles())); //accounting for 2 digits
        s.append("  |             |  ");
        s.append(String.format("%2d", game.getP2Store().getPebbles())); //accounting for 2 digits
        s.append("\n     | ");
        s.append(printP2(game.getP2Houses()));
        s.append("| [P2]");
        System.out.println(s.toString());
    }

    //Preserves the counter clockwise design
    public static String flipP1(List<House> l) {
        String s = "";
        for (House h : l) {
            s = h.getPebbles() + " " + s;
        }
        return s;
    }
    //doesn't need to be flipped
    public static String printP2(List<House> l) {
        String s = "";
        for (House h : l) {
            s += h.getPebbles() + " ";
        }
        return s;
    }
    //Handles the turn system
    //most of the logic is in redistribute function
    public static void takeTurn() {
        System.out.println("Your Turn");
        //handles invalid moves
        boolean done = false;
        int i = 0;
        while (!done) {
            //Player picks a house to redistribute
            //Note that no matter what side of the board the user is on, they will select 0-5
            //this was done on purpose to preserve the user mental model
            System.out.println("Choose a house 0-5 to redistribute:");
            try {
                i = isHost ? 5 - scan.nextInt() : scan.nextInt();
                if (i > 5 || i < 0) {
                    System.out.println("CHOOSE A HOUSE 0-5!!!!!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                // gets rid of spam inputs
                scan.nextLine();
                printBoard();
                takeTurn();
            }
            //actual turn logic here
            if (isHost) {
                done = game.getP1Houses().get(i).redistributeCounterClockwise(); //take your turn
            } else {
                done = game.getP2Houses().get(i).redistributeCounterClockwise(); //take your turn

            }
        }
        out.println(i);
    }

    //receive turn logic
    public static void receiveTurn() {
        int i = 0;
        try {
            i = Integer.parseInt(in.readLine());
        } catch (Exception e) {
            System.out.println("Error Receiving Move " + e.toString());
        }
        if (isHost) {
            game.getP2Houses().get(i).redistributeCounterClockwise(); //take your turn
        } else {
            game.getP1Houses().get(i).redistributeCounterClockwise(); //take your turn

        }
    }

    //check if game is over and who won
    public static boolean gameOver() {
       //no plays means game is over
        //check who won
        if (!game.canPlay()) {
            System.out.println("\nGame Over!");
            //P1 wins
            if (p1.hasWon()) {
                printBoard();
                System.out.println(isHost ? "You Won!" : "You Lost!");
                return true;
            }
            //P2 wins
            if (p2.hasWon()) {
                printBoard();
                System.out.println(isHost ? "You Lost!" : "You Won!");
                return true;
            }
            //Tie
            if (p1.hasTie()) {
                printBoard();
                System.out.println("Tie!");
                return true;
            }
        }
        //Game isn't actually over
        return false;
    }

    //Instruction menu for the user
    //Printed before the game starts (after player selection)
    public static void showInstructions() {
        System.out.println("Welcome to our Mancala Game");
        System.out.println("We are using a text-based GUI");
        System.out.println("You will see a board that looks like this:");
        System.out.println("  | x x x x x x |  ");
        System.out.println("  | 0 1 2 3 4 5 | <-- These numbers won't be here, but no matter what player you are");
        System.out.println("  | x x x x x x |     that number corresponds to that house, keep in mind that the ");
        System.out.println("                      game is played counter clockwise. ");

    }


}
