package project;

/**
 * Exception thrown when an invalid team is provided for a player.
 * Used in the player creation process to signal incorrect input.
 */
public class UnknownTeamException extends Exception {
    public UnknownTeamException(String teamAbbr, String playerName) {
        super("Unknown team: " + teamAbbr + " at " + playerName + ".");
    }
}
