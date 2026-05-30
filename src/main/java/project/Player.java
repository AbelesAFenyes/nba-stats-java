package project;

/**
 * Represents an NBA player with a name, position, team, and season statistics.
 */
public class Player {
    private String name;
    private Position position;
    private Team team;
    private SeasonStats seasonStats;

    /**
     * Constructs a new Player instance.
     *
     *
     * @param name         the player's full name
     * @param position     the player's position
     *                     (guard, forward, center, some have a primary listed first and a secondary listed after)
     * @param team         the team the player belongs to
     * @param seasonStats  the player's season statistics
     */
    public Player(String name, Position position, Team team, SeasonStats seasonStats) {
        this.name = name;
        this.position = position;
        this.team = team;
        this.seasonStats = seasonStats;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Team getTeam() {
        return team;
    }

    public SeasonStats getSeasonStats() {
        return seasonStats;
    }

    @Override
    public String toString() {
        return "Player: " + name +
                ", Position: " + position +
                ", Team: " + team.getName() +
                "\nStats: " + seasonStats.toString();
    }
}