package project;

/**
 * Exception thrown when an invalid position is provided for a player.
 * Used in the player creation process to signal incorrect input.
 */
public class InvalidPositionException extends Exception {
    public InvalidPositionException(String position, String playerName) {
        super("Unknown position: " + position + " at " + playerName + ".");
    }
}