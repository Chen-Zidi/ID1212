# Task 2, HTTPServer / HTTPClient
## HTTPServer / HTTPClient

Your task is to write a guessing game with sockets where the dialogue will be according to the following (when you connect with your webbrowser):

Welcome to the number guess game.
I'm thinking of a number between 1 and 100. Whats your guess? 50
That's too low. Please guess higher: 75
That' too high. Please guess lower: 67
You made it in 4 guess(es).

Requirements of the program: It should consist of at least two classes, a serverclass and a guessclass where the former handles the requests from and the responses to the server and the latter handles the gamelogic.

**Note: Each new client connecting should lead to a new instance of the game by adding a "Set-Cookie" field in the http-response.**

## Extra assignment
Use the java.net.HttpURLConnection class to simulate a browser and play the game 100 times and present the average number of guesses.