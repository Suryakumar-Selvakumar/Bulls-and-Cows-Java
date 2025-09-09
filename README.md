# Bulls and Cows

This project is an implementation of the classic logical deduction game Bulls and Cows. The game challenges a player to
guess a secret code composed of unique symbols. After each guess, the program provides feedback in the form of "bulls"
(correct symbols in the correct position) and "cows" (correct symbols in the wrong position).

The project was built in incremental stages, gradually adding features to create a fully playable, robust game.

1. **Stage 1 - Game log:**  
   Output a static example of a game log with multiple turns, showing how guesses and grades are displayed.

2. **Stage 2 - Grader:**  
   Implement the grading logic that counts bulls and cows for a predefined secret code based on a player's single guess.

3. **Stage 3 - Secret code:**  
   Add the ability to generate a pseudo-random secret code of any valid length, ensuring uniqueness of digits.

4. **Stage 4 - Game time:**  
   Combine code generation and grading into a playable version of the game where the player makes guesses until they
   succeed.

5. **Stage 5 - Improve the code generator:**  
   Replace the custom pseudo-random approach with Java’s built-in random utilities to generate codes more cleanly and
   reliably.

6. **Stage 6 - New level:**  
   Expand the game’s difficulty by supporting up to 36 symbols (digits 0-9 and lowercase letters a-z). Allow players to
   choose both the code length and the size of the symbol set.

7. **Stage 7 - Error handling:**  
   Add input validation and error messages for invalid lengths, invalid symbols, and impossible configurations, ensuring
   the game runs smoothly without crashing.

## Demo

<video width="1920" height="1080" align="center" src=""></video>

## Takeaway

This project deepened my understanding of algorithm design and validation in Java. I practiced handling user input,
random generation, and string-based logic while also learning to scale a program from a simple static output to a
flexible, error-resilient game. By expanding the symbol range and integrating thorough error handling, I bolstered my
ability to write programs that are both user-friendly and robust. This step-by-step progression strengthened my
confidence in tackling more complex problem-solving challenges.