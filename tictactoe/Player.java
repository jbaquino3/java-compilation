package tictactoe;

class Player {
    private char symbol;
    private int wins;
    public final int MAX_WINS = 5;
    public final char MIN_SYMBOL = 'A';
    public final char MAX_SYMBOL = 'Z';
    public final char DEFAULT_SYMBOL = ' ';

    // Constructors
    public Player() {
        setSymbol(DEFAULT_SYMBOL);
        resetWins();
    }
    
    public Player(char symbol) throws IllegalArgumentException {
        if(symbol < MIN_SYMBOL || symbol > MAX_SYMBOL) {
            throw new IllegalArgumentException("Invalid symbol.");
        }

        setSymbol(symbol);
        resetWins();
    }

    // Accessors
    public char getSymbol() {
        return symbol;
    }

    public int getWins() {
        return wins;
    }

    // Mutators
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    // Other methods
    public boolean addWin() {
        if(getWins() == MAX_WINS) {
            return false;
        } else {
            wins++;
            return true;
        }
    }

    public void resetWins() {
        wins = 0;
    }
}



