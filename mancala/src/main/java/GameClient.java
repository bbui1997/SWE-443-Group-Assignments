/**
 * Created by Jess on 3/19/2017.
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
public class GameClient {

    public static Scanner scan;
    public static Socket sock;
    public static boolean isHost;
    public static MancalaGame game;
    public static Player p1;
    public static Player p2;
    public static PrintWriter out;
    public static BufferedReader in;
    public static int port = 1337;
    public static String address = "";


    public static void main(String[] args){
        //prepare to receive user input
        scan = new Scanner(System.in);
        if (args.length > 0) {
            address = args[0];
            System.out.println("ADDRESS: " + address);
        }

        //Game Splash, Player Selection, Host/Client selection
        boolean done = false;
        isHost = false;
        while(!done) {
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
        }catch (Exception e){
            System.out.println("Network issue. Cannot resolve." + e.toString());
            return;
        }

        //no network issues. Make the game board
        game = new MancalaGame(6);
        //make the players and add to game
        p1 = new Player();
        p2 = new Player();
        p1.setName("Host");
        p1.setGame(game);
        p2.setName("Client");
        p2.setGame(game);
        game.addPlayer(p1);
        game.addPlayer(p2);
        System.out.println(isHost ? "You are Player 1":"You are Player 2");

        //Game loop
        while(!gameOver()){
            printBoard();
            if((game.getPlayerTurn() == 0 && isHost) || (game.getPlayerTurn() == 1 && !isHost)){
                takeTurn();
            }
            else{
                System.out.println("The opponent is taking a turn");
                receiveTurn();
            }
        }

    }

    public static Socket setupHost() throws Exception{
        //setup host logic
        int backlog = 3;
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("HOST: opening socket on addr:port " + addr + ":" + port);
        ServerSocket serv = new ServerSocket(port, backlog, addr);
        return serv.accept();
    }

    public static Socket setupClient() throws Exception{
        //setup client logic
        if(!address.equals("")){
            System.out.println("CLIENT: connecting to addr:port " + address + ":" + port);
            return new Socket(address,port);
        }
        else {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("CLIENT: connecting to addr:port " + addr + ":" + port);
            return new Socket(addr, port);
        }

    }

    //Print the state of the board to the use (Command Line GUI)
    public static void printBoard(){
        //Top half of the board
        StringBuilder s = new StringBuilder("[P1] | " );
        s.append(flipP1(game.getP1Houses()));
        s.append("| \n ");
        s.append(String.format("%2d",game.getP1Store().getPebbles())); //accounting for 2 digits
        s.append("  |             |  ");
        s.append(String.format("%2d",game.getP2Store().getPebbles())); //accounting for 2 digits
        s.append("\n     | ");
        s.append(printP2(game.getP2Houses()));
        s.append("| [P2]");
        System.out.println(s.toString());
    }

    public static String flipP1(List<House> l){
        String s = "";
        for (House h:l){
            s = h.getPebbles() + " " + s;
        }
        return s;
    }
    public static String printP2(List<House> l){
        String s = "";
        for (House h:l){
            s += h.getPebbles() + " ";
        }
        return s;
    }

    public static void takeTurn(){
        System.out.println("Your Turn");
        //handles invalid moves
       boolean done = false;
        int i = 0;
       while(!done) {
           //Player picks a house
           System.out.println("Choose a house 0-5 to redistribute:");
           i = isHost ? 5-scan.nextInt():scan.nextInt();
           if (i > 5 || i < 0){
               System.out.println("CHOOSE A HOUSE 0-5!!!!!");
               continue;
           }
           if (isHost) {
               done = game.getP1Houses().get(i).redistributeCounterClockwise(); //take your turn
           } else {
               done = game.getP2Houses().get(i).redistributeCounterClockwise(); //take your turn

           }
       }
      out.println(i);
    }
    public static void receiveTurn(){
        int i = 0;
        try {
            i = Integer.parseInt(in.readLine());
        }catch(Exception e){
            System.out.println("Error Receiving Move " + e.toString());
        }
        if (isHost) {
            game.getP2Houses().get(i).redistributeCounterClockwise(); //take your turn
        } else {
            game.getP1Houses().get(i).redistributeCounterClockwise(); //take your turn

        }
    }

    //check if game is over and who won
    public static boolean gameOver(){
        if(p1.hasWon()){
            System.out.println(isHost ? "You Won!":"You Lost!");
            return true;
        }
        if(p2.hasWon()){
            System.out.println(isHost ? "You Won!":"You Lost!");
            return true;
        }
        if(p1.hasTie()){
            System.out.println("Tie!");
            return true;
        }
        return false;
    }

    public static void showInstructions(){
        System.out.println("Welcome to our Mancala Game");
        System.out.println("We are using a text-based GUI");
        System.out.println("You will see a board that looks like this:");
        System.out.println("  | x x x x x x |  ");
        System.out.println("  | 0 1 2 3 4 5 | <-- These numbers won't be here, but no matter what player you are");
        System.out.println("  | x x x x x x |     that number corresponds to that house, keep in mind that the ");
        System.out.println("                      game is played counter clockwise. ");

    }


}
