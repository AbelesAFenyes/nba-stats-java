package project;

import java.util.HashMap;
import java.util.Map;
/**
 * Represents an NBA team with their regular season record, which conference it belongs to
 * and the team's 3 letter abbreviation.
 */
public class Team {
    private String name;
    private int wins;
    private int losses;
    private Conference conference;
    private String abbreviation;

    /**
     * Constructs a Team with the specified name, win/loss record, conference and their abbreviation.
     *
     * @param name       	the full name of the team
     * @param wins       	the number of wins
     * @param losses     	the number of losses
     * @param conference 	the conference (Eastern or Western)
     * @param abbreviation	the 3 letter abbreviation
     */
    public Team(String name, int wins, int losses, Conference conference, String abbreviation) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.conference = conference;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public Conference getConference() {
        return conference;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Calculates and returns the team's win percentage.
     *
     * @return win percentage as a double (0.0–100.0)
     */
    public double getWinPercentage() {
        int totalGames = wins + losses;
        if (totalGames == 0) return 0.0;
        return (wins * 100.0) / totalGames;
    }

    /**
     * Creates and returns a map of all NBA teams keyed by their abbreviation.
     *
     * @return a map containing team abbreviation → Team instance
     */
    public static Map<String, Team> createAllTeams() {
        Map<String, Team> teamMap = new HashMap<>();

        // East
        teamMap.put("CLE", new Team("Cleveland Cavaliers", 64, 18, Conference.EASTERN, "CLE"));
        teamMap.put("BOS", new Team("Boston Celtics", 61, 21, Conference.EASTERN, "BOS"));
        teamMap.put("NYK", new Team("New York Knicks", 51, 31, Conference.EASTERN, "NYK"));
        teamMap.put("IND", new Team("Indiana Pacers", 50, 32, Conference.EASTERN, "IND"));
        teamMap.put("MIL", new Team("Milwaukee Bucks", 48, 34, Conference.EASTERN, "MIL"));
        teamMap.put("DET", new Team("Detroit Pistons", 44, 38, Conference.EASTERN, "DET"));
        teamMap.put("ATL", new Team("Atlanta Hawks", 40, 42, Conference.EASTERN, "ATL"));
        teamMap.put("ORL", new Team("Orlando Magic", 41, 41, Conference.EASTERN, "ORL"));
        teamMap.put("CHI", new Team("Chicago Bulls", 39, 43, Conference.EASTERN, "CHI"));
        teamMap.put("MIA", new Team("Miami Heat", 37, 45, Conference.EASTERN, "MIA"));
        teamMap.put("TOR", new Team("Toronto Raptors", 30, 52, Conference.EASTERN, "TOR"));
        teamMap.put("PHI", new Team("Philadelphia 76ers", 24, 58, Conference.EASTERN, "PHI"));
        teamMap.put("BKN", new Team("Brooklyn Nets", 26, 56, Conference.EASTERN, "BKN"));
        teamMap.put("CHA", new Team("Charlotte Hornets", 19, 63, Conference.EASTERN, "CHA"));
        teamMap.put("WAS", new Team("Washington Wizards", 18, 64, Conference.EASTERN, "WAS"));

        //West
        teamMap.put("OKC", new Team("Oklahoma City Thunder", 68, 14, Conference.WESTERN, "OKC"));
        teamMap.put("MEM", new Team("Memphis Grizzlies", 48, 34, Conference.WESTERN, "MEM"));
        teamMap.put("HOU", new Team("Houston Rockets", 52, 30, Conference.WESTERN, "HOU"));
        teamMap.put("DEN", new Team("Denver Nuggets", 50, 32, Conference.WESTERN, "DEN"));
        teamMap.put("LAL", new Team("Los Angeles Lakers", 50, 32, Conference.WESTERN, "LAL"));
        teamMap.put("LAC", new Team("Los Angeles Clippers", 50, 32, Conference.WESTERN, "LAC"));
        teamMap.put("GSW", new Team("Golden State Warriors", 48, 34, Conference.WESTERN, "GSW"));
        teamMap.put("MIN", new Team("Minnesota Timberwolves", 49, 33, Conference.WESTERN, "MIN"));
        teamMap.put("SAC", new Team("Sacramento Kings", 40, 42, Conference.WESTERN, "SAC"));
        teamMap.put("DAL", new Team("Dallas Mavericks", 39, 43, Conference.WESTERN, "DAL"));
        teamMap.put("PHX", new Team("Phoenix Suns", 36, 46, Conference.WESTERN, "PHX"));
        teamMap.put("SAS", new Team("San Antonio Spurs", 34, 48, Conference.WESTERN, "SAS"));
        teamMap.put("POR", new Team("Portland Trail Blazers", 36, 46, Conference.WESTERN, "POR"));
        teamMap.put("NOP", new Team("New Orleans Pelicans", 21, 61, Conference.WESTERN, "NOP"));
        teamMap.put("UTA", new Team("Utah Jazz", 17, 65, Conference.WESTERN, "UTA"));

        return teamMap;
    }


    @Override
    public String toString() {
        return name + " (" + conference + ") - " + wins + "/" + losses + " | " + String.format("%.1f", getWinPercentage()) + "% win";
    }
}