Unbeatable TicTacToe - Command line game
=========

Technologies used:

1. Java 8

This uses a basic Minimax algorithm to simulate an AI player, similar to the algorithm shown on http://en.wikipedia.org/wiki/Minimax

function minimax(node, depth, maximizingPlayer)
    if depth = 0 or node is a terminal node
        return the heuristic value of node
    if maximizingPlayer
        bestValue := -∞
        for each child of node
            val := minimax(child, depth - 1, FALSE)
            bestValue := max(bestValue, val)
        return bestValue
    else
        bestValue := +∞
        for each child of node
            val := minimax(child, depth - 1, TRUE)
            bestValue := min(bestValue, val)
        return bestValue

(* Initial call for maximizing player *)
minimax(origin, depth, TRUE)


Note: The Minimax algorithm (with no modifications) only guarantees that the computer cannot be beaten. However, it does always assume that it's playing the perfect opponent that'll be impossible to beat, and when a human deliberately plays badly, it can behave unusually by not going for obvious wins.

To deploy the application to your system, download TicTacToe-1.0-SNAPSHOT-jar-with-dependencies.jar and  Ensure your JAVA_HOME is version 8.

Then execute the following;

java -jar TicTacToe-1.0-SNAPSHOT-jar-with-dependencies.jar