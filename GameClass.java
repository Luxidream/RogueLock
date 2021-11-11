
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.util.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



import javax.swing.JFrame;
public class GameClass{
	 public static int player1 = 1;
	 public static int player2 = 2;
	 
	 public int[][] gameBoard = new int[5][5];
	 public int[][] trapBoard = new int[5][5];
	 public int[] collected = new int[10];
	 private int[] password = new int[10];
	 

	 
	 public int playerX;
	 public int playerY;
	 
	 public static int emptyVal = 0;
	 public static int playerVal = 1;
	 public static int swordVal = 2;
	 public static int shieldVal = 3;
	 
	 public static int goldVal = 4;
	 public static int potionVal = 5;
	 public static int ringVal = 6;
	 public static int scrollValue = 7;
	 public ArrayList<Integer> realPassword = new ArrayList<Integer>();
	 public ArrayList<Integer> fakePassword = new ArrayList<Integer>();
	 
	 public GameClass() {
		 this.gameBoard = new int[5][5];
		 this.collected = new int[10];
		 this.password[0] = 2;
		 this.password[0] = 3;
		 this.password[0] = 4;
		 
		
	 }
	
	 public ArrayList<Integer> getFakePassword() {
		 return this.fakePassword;
	 }
	 
	 public ArrayList<Integer> getRealPassword() {
		 return this.realPassword;
	 }
	 
	 public void setPassword(int[] password) {
		 this.password = password;
	 }
	 
	 public void addToRealPassword(int a) {
		 realPassword.add(a);
	 }
	 
	 
	 public void setPlayerLoc(int x, int y, int val) {
		 if (this.gameBoard[x][y] != 0) {
			 fakePassword.add(this.gameBoard[x][y]);
		 }
		 
		 this.gameBoard[x][y] = val;
	 }
	 
	 public void setLoc(int x, int y, int val) {
		 this.gameBoard[x][y] = val;
	 }
	 
	 public void setPlayerX (int x) {
		 playerX = x;
	 }
	 public void setPlayerY (int x) {
		 playerX = x;
	 }
	 
	 public int getPlayerX() {
		 return playerX;
	 }
	 
	 public int getPlayerY() {
		 return playerY;
	 }
	 
	 public int[][] getBoard() {
		 return this.gameBoard;
	 }
	 
	 public boolean isSurrounded(int x, int y) {
		 
		 if(gameBoard[x][y] != 0) {
			 return true;
		 }
		 
		 int failcounters = 0;
		 for (int i = x-1; i<x+2; i++) {
			 for (int j = y-1; j<y+2; j++) {
				 if (i<0 || i>4 || j<0 || j>4) {
					 failcounters++;
				 }
				 else if(gameBoard[i][j] != emptyVal && i!=x && j != y) {
					 failcounters++;
				 }
		 }
		}
		 
		 if(failcounters >= 8) {
			 return true;
		 }
		 else {
			 return false;
		 }

	 }
		 
	 public Location aStarSearch(GameClass gb, Location startLoc, Location endLoc) {
		 Location finalLoc;
		 ArrayList<Location> finalPath = new ArrayList<Location>();
		 ArrayList<Location> fringe = new ArrayList<Location>();
		 ArrayList<Location> explored = new ArrayList<Location>();
		 int[][] board = gb.getBoard();
		 int goalVal = board[endLoc.getX()][endLoc.getY()];
		 
		 int tries = 0;
		 boolean goal = false;

		 int goalX = endLoc.getX();
		 int goalY = endLoc.getY();

		 Location tempGoal = endLoc;
		 
		
		 Location tempStartLoc = startLoc;
		 while(!goal && tries<40) {
			 int startX = tempStartLoc.getX();
			 int startY = tempStartLoc.getY();
			 int minX = startX-1;
			 int maxX = startX+1;
			 int minY = startY-1;
			 int maxY = startY+1;
			 
			 if (minX < 0) {
				 minX = 0;
			 }
			 if (maxX > 4) {
				 minX = 4;
			 }
			 if (minX < 0) {
				 minX = 0;
			 }
			 if (minX < 0) {
				 minX = 0;
			 }
			 
			 
			 for(int a = minX; a < maxX+1; a++) {
				 for(int b = minY; b < maxY+1; b++) {
					 if(board[a][b] == 0 && a!=startX && b!=startY || board[a][b] == goalVal) {
						 Location temp = new Location(a,b,0);
						 int cost = 1;
						 temp.setHeuristic(temp.getHeuristic(temp,endLoc)+cost);
						 temp.setParent(tempStartLoc);
						 fringe.add(temp);
					 }
					 
				 }
				 int minfringe = 999999;
				 Location minLocation = null;
				 for (int c = 0; c<fringe.size(); c++) {
					 if(fringe.get(c).getHeuristic() < minfringe) {
						 minfringe = fringe.get(c).getHeuristic();
						 minLocation = fringe.get(c);
					 }
				 }
				 if (minLocation.getX() == endLoc.getX() && minLocation.getY() == endLoc.getY()) {
					 finalPath.add(minLocation);
					 goal = true;
				 }
				 
				 tempStartLoc = minLocation;
				 
				 
				 
			 }
		 }
		 return null;
	 }
	 
	 public static void main(String[] args) {
		 
		 boolean gameActive = true;// keeps track of if game is active when program needs to stop
         Scanner in = new Scanner(System.in);
		 GameClass gamer = new GameClass();
		 //Initialize the location of the player
		 //This random is probably vulnerable to attack
		 Random rand = new Random();
		 int randPlayerX = rand.nextInt(5);
		 int randPlayerY = rand.nextInt(5);
		 gamer.setLoc(randPlayerX, randPlayerY, 1);
		 int playerX = randPlayerX;
		 int playerY = randPlayerY;
		 
		 int itemcount = 2;
		 int attemptcount = 0;
		 
		 while(itemcount <= 7 && attemptcount <= 100) {
			 
			 randPlayerX = rand.nextInt(5);
			 randPlayerY = rand.nextInt(5);
			 if(!gamer.isSurrounded(randPlayerX, randPlayerY)) {
				 gamer.setLoc(randPlayerX, randPlayerY, itemcount);
				 itemcount++;
				 attemptcount = 0;
			 }
			 attemptcount++;

		 }
		 
		 //int[][] board = gamer.getBoard();
		 int[][] tempBoard = gamer.getBoard();
	      GridExample mainPanel = new GridExample(tempBoard);

	      JFrame frame = new JFrame("GridExample");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.getContentPane().add(mainPanel);
	      frame.pack();
	      frame.setLocationByPlatform(true);
	      frame.setVisible(true);
	      
	      String input;
	      System.out.println("2 - coin");
	      System.out.println("3 - chest");
	      System.out.println("4 - flag");
	      System.out.println("5 - pennant");
	      System.out.println("6 - star");
	      System.out.println("7 - sword");
	      System.out.print("Enter the first character of your password (2-7): ");
	      input = in.next();
	      gamer.addToRealPassword(Integer.parseInt(input));
	      System.out.print("Enter the second character of the password (2-7): ");
	      input = in.next();
	      gamer.addToRealPassword(Integer.parseInt(input));
	      System.out.print("Enter the third character of the password (2-7): ");
	      input = in.next();
	      gamer.addToRealPassword(Integer.parseInt(input));
	      
		 while(gameActive) {
			 int[][] board = gamer.getBoard();
			 
			 System.out.println("|---|---|---|---|---|");
		        System.out.println("| " + board[0][0] + " | "
		                           + board[1][0] + " | " + board[2][0]
		                           + " | " + board[3][0] + " | " + board[4][0] + " |");
		        System.out.println("|-------------------|");
		        System.out.println("| " + board[0][1] + " | "
	                    + board[1][1] + " | " + board[2][1]
	                    + " | " + board[3][1] + " | " + board[4][1] + " |");
		        System.out.println("|-------------------|");
		        System.out.println("| " + board[0][2] + " | "
	                    + board[1][2] + " | " + board[2][2]
	                    + " | " + board[3][2] + " | " + board[4][2] + " |");
		        System.out.println("|-------------------|");
		        System.out.println("| " + board[0][3] + " | "
	                    + board[1][3] + " | " + board[2][3]
	                    + " | " + board[3][3] + " | " + board[4][3] + " |");
		        System.out.println("|-------------------|");
		        System.out.println("| " + board[0][4] + " | "
	                    + board[1][4] + " | " + board[2][4]
	                    + " | " + board[3][4] + " | " + board[4][4] + " |");
		        System.out.println("|---|---|---|---|---|");
		        
		        System.out.println("Current player position: " + playerX + ", " + playerY);
		        System.out.print("Current password: ");
		        for(int i = 0; i<gamer.getFakePassword().size(); i++) {
		        	System.out.print(gamer.getFakePassword().get(i));
		        }
		        System.out.println("");
		        System.out.print("Correct password: ");
		        for(int i = 0; i<gamer.getRealPassword().size(); i++) {
		        	System.out.print(gamer.getRealPassword().get(i));
		        }
		        System.out.println("");
		        System.out.println("Make your move with HJKL then press ENTER to confirm.");
                String move = in.next();
                if (move.equals("1")) { //quits 
                        gameActive = false;
                }
                if (move.equals("k")) { //movement commands
                		gamer.setLoc(playerX, playerY, 0);
                        playerX = playerX;
                        playerY = playerY - 1;
                        if(playerY < 0) {
                    		playerY = 4;
                    	}
                        gamer.setPlayerLoc(playerX, playerY, 1);
                        
                }
                if (move.equals("j")) {
                	gamer.setLoc(playerX, playerY, 0);
                	playerX = playerX;
                    playerY = playerY + 1;
                    if(playerY > 4) {
                		playerY = 0;
                	}
                    gamer.setPlayerLoc(playerX, playerY, 1);
                }
                if (move.equals("h")) {
                	gamer.setLoc(playerX, playerY, 0);
                	playerX = playerX - 1;
                	if(playerX < 0) {
                		playerX = 4;
                	}
                    playerY = playerY;
                    gamer.setPlayerLoc(playerX, playerY, 1);
                }
                if (move.equals("l")) {
                	gamer.setLoc(playerX, playerY, 0);
                	playerX = playerX + 1;
                	if(playerX > 4) {
                		playerX = 0;
                	}
                    playerY = playerY;
                    gamer.setPlayerLoc(playerX, playerY, 1);
                }
                frame.getContentPane().removeAll();
                GridExample tempMainPanel = new GridExample(gamer.getBoard());
                frame.getContentPane().add(tempMainPanel);
      	        frame.pack();
      	        frame.setVisible(false);
      	        frame.setVisible(true);
                SwingUtilities.updateComponentTreeUI(frame);
                
                //potentially vulnerable maybe fix this
                if(gamer.getFakePassword().size() == gamer.getRealPassword().size()) {
                	if(gamer.getFakePassword().equals(gamer.getRealPassword())) {
                		System.out.println("You won!");
                		break;
                	}
                	else{
                		System.out.println("Wrong password! You lose.");
                		break;
                	}
                }
               


                

		 }

		 
		 
		 

		 
		 
		 
		 
	 }
	 
	
	
}
