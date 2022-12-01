package cowsbulls;

import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
    public static void main(String[] args) {
		playBullsAndCows(123);
	}

    static boolean contains(int[] arr, int toCheck) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == toCheck) {
                return true;
            }
        }

        return false;
    }

    static int[] generateSecretDigits(int seed) {
        Random generator = new Random(seed);
        int[] ret = new int[4];

        int i=0;
        while(i < ret.length) {
            int rand = generator.nextInt(10); // This generate a random number from 0 to 9 inclusive

            if(!contains(ret, rand)) {
                ret[i++] = rand;
            }
        }
        
        return ret;
    }

    static int getNumOfBulls(int[] secret, int[] guess) throws Exception {
        // Check for repeated digits in guess
        for(int i=0; i<guess.length; i++) {
            for(int j=0; j<guess.length; j++) {
                if(i != j && guess[i] == guess[j]) {
                    throw new Exception("You are not allowed to repeat the same number!");
                }
            }
        }

        if(secret.length != guess.length) {
            throw new Exception("You must enter a four-digit number! You just wasted one guess!");
        }

        int bulls = 0;
        for(int i=0; i<secret.length; i++) {
            if(secret[i] == guess[i]) {
                bulls++;
            }
        }

        return bulls;
    }

    static int getNumOfCows(int[] secret, int[] guess) throws Exception {
        // Check for repeated digits in guess
        for(int i=0; i<guess.length; i++) {
            for(int j=0; j<guess.length; j++) {
                if(i != j && guess[i] == guess[j]) {
                    throw new Exception("You are not allowed to repeat the same number!");
                }
            }
        }

        if(secret.length != guess.length) {
            throw new Exception("You must enter a four-digit number! You just wasted one guess!");
        }

        int cows = 0;
        for(int i=0; i<secret.length; i++) {
            if(contains(secret, guess[i])) {
                cows++;
            }
        }

        return cows;
    }

    static void playBullsAndCows(int seed) {
        Scanner scanner = new Scanner(System.in);
        int[] secret = generateSecretDigits(seed);
        
        System.out.println("Welcome to the game! Have fun!");

        int attempts = 0;
        while(true) {
            if(attempts >= 5) {
                System.out.print("Do you want to quit the game? Answers: y/n ");
                String input = scanner.nextLine();
                if(input.equals("Y") || input.equals("y")) {
                    System.out.println("You've decided to quit the game, after making " + attempts + " attempts.");
                    break;
                }
            }
            
            System.out.print("Guess # " + (++attempts) + ", Enter a four-digit number, each number should be unique : ");
            String guessstr = scanner.nextLine();
            
            if(!guessstr.matches("[0-9]*")) {
                System.out.println("You must enter a four-digit number! You just wasted one guess!");
                continue;
            }
            
            int[] guess = new int[guessstr.length()];

            // Convert int to array of digits
            for (int i = 0; i < guessstr.length(); i++) {
                guess[i] = Integer.parseInt(guessstr.charAt(i) + "");
            }
            
            try {
                int bulls = getNumOfBulls(secret, guess);
                int cows = getNumOfCows(secret, guess);
                
                if(bulls == secret.length) {
                    System.out.println("Congratulations, you guessed the secret number! It took you " + attempts + " attempts.");
                    break;
                }
                
                System.out.println("Bulls: " + bulls);
                System.out.println("Cows: " + cows);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}