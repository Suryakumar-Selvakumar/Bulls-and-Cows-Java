package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String[] codeChars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length:");

        String codeLengthInput = "";
        int codeLength;
        try {
            codeLengthInput = sc.nextLine();
            codeLength = Integer.parseInt(codeLengthInput);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", codeLengthInput);
            return;
        }

        if (codeLength == 0) {
            System.out.print("Error: can't generate a secret number with a length of 0.");
            return;
        }

        if (codeLength > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique symbols.", codeLength);
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        String noOfSymbolsInput = "";
        int noOfSymbols;
        try {
            noOfSymbolsInput = sc.nextLine();
            noOfSymbols = Integer.parseInt(noOfSymbolsInput);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", noOfSymbolsInput);
            return;
        }

        if (noOfSymbols > 36) {
            System.out.print("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        if (codeLength > noOfSymbols) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", codeLength, noOfSymbols);
            return;
        }
        String secretCode = generateSecretCode(codeLength, noOfSymbols);
        String symbolsRange = printSymbolsRange(noOfSymbols);
        System.out.printf("The secret is prepared: %s %s.\n", scrambleSecretCode(secretCode), symbolsRange);
        System.out.println("Okay, let's start a game!");

        int[] grades = new int[]{0, 0};
        int i = 1;
        do {
            System.out.printf("Turn %d:\n", i);
            String guess = sc.next();
            boolean isGuessValid = validateGuess(guess, codeLength, noOfSymbols, symbolsRange);
            if (!isGuessValid) {
                continue;
            }
            grades = gradeGuess(guess, secretCode);
            printGrade(grades[0], grades[1]);
            i++;
        } while (grades[0] < secretCode.length());

        if (grades[0] == secretCode.length()) {
            System.out.println("Congratulations! You guessed the secret code.");
        }
    }

    static boolean validateGuess(String guess, int codeLength, int noOfSymbols, String symbolsRange) {
        if (guess.length() < codeLength) {
            System.out.printf("Error: Length of guess must be %d symbols.\n", codeLength);
            return false;
        }

        for (char ch : guess.toCharArray()) {
            if (Arrays.stream(Arrays.copyOf(codeChars, noOfSymbols)).noneMatch(String.valueOf(ch)::equals)) {
                System.out.printf("Error: Guess can only consist of symbols in the range %s.\n", symbolsRange);
                return false;
            }
        }

        return true;
    }

    static int[] gradeGuess(String guess, String secretCode) {
        int bullsCount = 0;
        int cowsCount = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                bullsCount++;
            } else if (secretCode.contains(String.valueOf(guess.charAt(i)))) {
                cowsCount++;
            }
        }

        return new int[]{bullsCount, cowsCount};
    }

    static void printGrade(int bullsCount, int cowsCount) {
        if (cowsCount > 0 && bullsCount > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s). %n", bullsCount, cowsCount);
        } else if (cowsCount > 0 && bullsCount == 0) {
            System.out.printf("Grade: %d cow(s). %n", cowsCount);
        } else if (cowsCount == 0 && bullsCount > 0) {
            System.out.printf("Grade: %d bull(s). %n", bullsCount);
        } else {
            System.out.println("None. ");
        }
    }

    static String generateSecretCode(int codeLength, int noOfSymbols) {
        StringBuilder secretCode = new StringBuilder();
        Random random = new Random();

        while (secretCode.length() < codeLength) {
            int randomInt = random.nextInt(noOfSymbols);

            if (!secretCode.toString().contains(codeChars[randomInt])) {
                secretCode.append(codeChars[randomInt]);
            }
        }

        return secretCode.toString();
    }

    static StringBuilder scrambleSecretCode(String secretCode) {
        StringBuilder scrambledSecretCode = new StringBuilder();
        for (char ignored : secretCode.toCharArray()) {
            scrambledSecretCode.append("*");
        }

        return scrambledSecretCode;
    }

    static String printSymbolsRange(int noOfSymbols) {
        int digitUpperBound = 0;
        int letterUpperBound = 0;

        for (int i = 0; i < noOfSymbols; i++) {
            if (Character.isDigit(codeChars[i].charAt(0))) {
                digitUpperBound = i;
            } else {
                letterUpperBound = i;
            }
        }

        if (noOfSymbols == 1) {
            return "(0)";
        } else if (noOfSymbols <= 10) {
            return "(0-%s)".formatted(codeChars[digitUpperBound]);
        } else if (noOfSymbols == 11) {
            return "(0-9, a)";
        } else {
            return "(0-9, a-%s)".formatted(codeChars[letterUpperBound]);
        }
    }
}
