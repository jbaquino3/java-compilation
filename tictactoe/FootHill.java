package tictactoe;

import java.util.Scanner;

public class FootHill {
    static Player[][] board;
    static Player p0;
    static Player p1;
    static Player p2;
    static int game; // Current game
    
    public static void main(String[] args) {
		// Initialize
		board = new Player[3][3];
		p0 = new Player(); // Default player
		p1 = new Player();
		p2 = new Player();
		game = 1;
		
		// Ask user symbols
		p1.setSymbol(askUserForSymbol());
		p2.setSymbol(askUserForSymbol());
		
		while(game <= 5) {
		    System.out.println("\nGame " + game + " starting:");
		    resetBoard(board, p0);
		    displayBoard(board);
		    
		    while(true) {
		    	makeAMove(board, p1);
		    	if(isWin(board, p1) == true) {
		    		p1.addWin();
		    		System.out.println(p1.getSymbol() + " player won!");
		    		System.out.println("Number of wins for player " + p1.getSymbol() + ": " + p1.getWins());
		    		System.out.println("Number of wins for player " + p2.getSymbol() + ": " + p2.getWins());
		    		game++;
		    		break;
		    	}
		    	if(isDraw(board) == false) {
		    		System.out.println("Board is full! It's a draw!");
		    		System.out.println("Number of wins for player " + p1.getSymbol() + ": " + p1.getWins());
		    		System.out.println("Number of wins for player " + p2.getSymbol() + ": " + p2.getWins());
		    		game++;
		    		break;
		    	}
		    	
		    	makeAMove(board, p2);
		    	if(isWin(board, p2) == true) {
		    		p2.addWin();
		    		System.out.println(p2.getSymbol() + " player won!");
		    		System.out.println("Number of wins for player " + p1.getSymbol() + ": " + p1.getWins());
		    		System.out.println("Number of wins for player " + p2.getSymbol() + ": " + p2.getWins());
		    		game++;
		    		break;
		    	}
		    	if(isDraw(board) == false) {
		    		System.out.println("Board is full! It's a draw!");
		    		System.out.println("Number of wins for player " + p1.getSymbol() + ": " + p1.getWins());
		    		System.out.println("Number of wins for player " + p2.getSymbol() + ": " + p2.getWins());
		    		game++;
		    		break;
		    	}
		    }
		}
	}

    public static char askUserForSymbol() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is the symbol for a player? ");
        
        while(true) {
            char symbol = scanner.next().charAt(0);
            
            if(symbol < p0.MIN_SYMBOL || symbol > p0.MAX_SYMBOL) {
                System.out.print("Sorry that is no good, choose a capital letter. Try again: ");
            } else if (p1.getSymbol() == symbol || p2.getSymbol() == symbol) {
                System.out.print("Sorry, letter is already used by other player. Try again: ");
            } else {
                return symbol;
            }
        }
    }

    public static void resetBoard(Player[][] b, Player p) {
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                board[i][j] = p;
            }
        }
        
        board = b;
    }

    public static void makeAMove(Player[][] b, Player p) {
    	Scanner scanner = new Scanner(System.in);
    	int row = -1;
    	int col = -1;
    	
    	while(row == -1) {
    		System.out.print("Enter a row (");
            for(int i=0; i<b.length-1; i++) {
            	System.out.print(i + ", ");
            }
            System.out.print("or " + (b.length-1) + ") for player " + p.getSymbol() + ": ");
            
            row = scanner.nextInt();
            if(row < 0 || row >= b.length) {
            	row = -1;
            	System.out.println("Invalid row!");
            } else {
            	break;
            }
    	}
        
    	while(col == -1) {
    		System.out.print("Enter a column (");
            for(int i=0; i<b.length-1; i++) {
            	System.out.print(i + ", ");
            }
            System.out.print("or " + (b.length-1) + ") for player " + p.getSymbol() + ": ");
            
            col = scanner.nextInt();
            if(col < 0 || col >= b.length) {
            	col = -1;
            	System.out.println("Invalid column!");
            } else {
            	break;
            }
    	}
        
    	if(b[row][col].getSymbol() == p.DEFAULT_SYMBOL) {
    		board[row][col] = p;
	        board = b;
	    	displayBoard(b);
    	} else {
    		System.out.println("Invalid coordinates!");
    		makeAMove(b, p);
    	}
    }

    public static void displayBoard(Player[][] b) {
        System.out.println("-------------");
        for(int i=0; i<b.length; i++) {
            System.out.print("| ");
            for(int j=0; j<b[i].length; j++) {
                System.out.print(b[i][j].getSymbol() + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static boolean isWin(Player[][] b, Player p) {
        boolean diag1Win = true;
        boolean diag2Win = true;
        boolean rowWin = true;
        boolean colWin = true;
        
        for(int i=0; i<b.length; i++) {
            rowWin = true;
            colWin = true;
            
            for(int j=0; j<b[i].length; j++) {
                if(b[i][j].getSymbol() != p.getSymbol()) {
                    rowWin = false;
                }
                
                if(b[j][i].getSymbol() != p.getSymbol()) {
                    colWin = false;
                }
                
                if(i == j && b[i][j].getSymbol() != p.getSymbol()) {
                    diag1Win = false;
                }
                
                if(j == b.length - i -1 && b[i][j].getSymbol() != p.getSymbol()) {
                    diag2Win = false;
                }
            }
            
            if(rowWin == true || colWin == true) {
                return true;
            }
        }
        
        if(diag1Win == true || diag2Win == true) {
            return true;
        }
        
        return false;
    }

    public static boolean isDraw(Player[][] b) {
        for(int i=0; i<b.length; i++) {
        	for(int j=0; j<b[i].length; j++) {
        		if(b[i][j].getSymbol() == p0.DEFAULT_SYMBOL) {
        			return true;
        		}
        	}
        }
        
        return false;
    }
}

