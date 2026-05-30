package project;

/**
 * Represents the season statistics of an NBA player.
 */
public class SeasonStats  {
    private int gamesPlayed;
    private double pointsPerGame;
    private double assistsPerGame;
    private double reboundsPerGame;
    private double stealsPerGame;
    private double blocksPerGame;
    private double turnoversPerGame;

    /**
     * Constructs a new SeasonStats instance.
     *
     * @param gamesPlayed        the number of games played by the player in the season
     * @param pointsPerGame      the points scored per game
     * @param assistsPerGame     the assists made per game
     * @param reboundsPerGame    the rebounds grabbed per game
     * @param stealsPerGame      the steals made per game
     * @param blocksPerGame      the blocks made per game
     * @param turnoversPerGame   the turnovers committed per game
     */
    public SeasonStats(int gamesPlayed, double pointsPerGame, double assistsPerGame,
                       double reboundsPerGame, double stealsPerGame, double blocksPerGame,
                       double turnoversPerGame) {
        this.gamesPlayed = gamesPlayed;
        this.pointsPerGame = pointsPerGame;
        this.assistsPerGame = assistsPerGame;
        this.reboundsPerGame = reboundsPerGame;
        this.stealsPerGame = stealsPerGame;
        this.blocksPerGame = blocksPerGame;
        this.turnoversPerGame = turnoversPerGame;
    }


    public double getPointsPerGame() {
        return pointsPerGame;
    }


    public double getAssistsPerGame() {
        return assistsPerGame;
    }


    public double getReboundsPerGame() {
        return reboundsPerGame;
    }


    public double getStealsPerGame() {
        return stealsPerGame;
    }


    public double getBlocksPerGame() {
        return blocksPerGame;
    }

    /**
     * Calculates and returns the player's simplified player efficiency rating (SPER) for the season.
     * I used the following formula to calculate SPER: points + assists + rebounds + steals + and blocks - turnovers.
     *
     * @return the simplified player efficiency rating (SPER) rounded to two decimal places
     */
    public double getSper() {
        double sper = pointsPerGame + assistsPerGame + reboundsPerGame + stealsPerGame + blocksPerGame - turnoversPerGame;
        return Math.round(sper * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Games: " + gamesPlayed +
                ", PPG: " + pointsPerGame +
                ", APG: " + assistsPerGame +
                ", REB: " + reboundsPerGame +
                ", STL: " + stealsPerGame +
                ", BLK: " + blocksPerGame +
                ", TO: " + turnoversPerGame +
                ", SPER: " + getSper();
    }
}
