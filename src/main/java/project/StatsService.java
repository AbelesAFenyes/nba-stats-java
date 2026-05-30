package project;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * This class contains every necessary method for handling and displaying player and team statistics.
 */
public class StatsService {

    /**
     * Prints all players with their team abbreviation and position.
     * @param players The list of players to display.
     */
    public static void printAllPlayers(List<Player> players) {
        players.forEach(p -> System.out.println(
                p.getName() + " | " + p.getTeam().getAbbreviation() + " | " + p.getPosition()
        ));
    }

    /**
     * Prompts the user to input a team name or abbreviation,
     * and prints players on matching teams along with their key stats.
     * @param players The list of players to search.
     */
    public static void printPlayersByTeam(List<Player> players) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input team name or abbreviation: ");
        String teamInput = scanner.nextLine().toLowerCase();

        players.stream()
                .filter(p -> {
                    String teamName = p.getTeam().getName().toLowerCase();
                    String teamAbbr = p.getTeam().getAbbreviation().toLowerCase();
                    return teamName.contains(teamInput) || teamAbbr.contains(teamInput);
                })
                .forEach(p -> System.out.println(
                        p.getName() + " | " + p.getPosition() +
                                " | PTS: " + p.getSeasonStats().getPointsPerGame() +
                                ", REB: " + p.getSeasonStats().getReboundsPerGame() +
                                ", AST: " + p.getSeasonStats().getAssistsPerGame()
                ));
    }

    /**
     * Returns a list of top N players sorted by SPER (Simplified Player Efficiency Rating).
     * @param players The list of players to evaluate.
     * @param topN The number of top players to return.
     * @return List of top N players based on SPER.
     */
    public static List<Player> getTopSPERPlayers(List<Player> players, int topN) {
        return players.stream()
                .sorted((p1, p2) -> Double.compare(p2.getSeasonStats().getSper(), p1.getSeasonStats().getSper()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    /**
     * Returns top N MVP candidates, ranked by SPER weighted by team win percentage.
     * This emphasizes players who contribute significantly to successful teams.
     * @param players The list of players to evaluate.
     * @param topN The number of MVP candidates to return.
     * @return List of top N MVP candidates.
     */
    public static List<Player> getMvpCandidates(List<Player> players, int topN) {
        return players.stream()
                .sorted((p1, p2) -> {
                    double sper1 = p1.getSeasonStats().getSper() * p1.getTeam().getWinPercentage() * 2;
                    double sper2 = p2.getSeasonStats().getSper() * p2.getTeam().getWinPercentage() * 2;
                    return Double.compare(sper2, sper1);
                })
                .limit(topN)
                .collect(Collectors.toList());
    }

    /**
     * Returns top N Defensive Player of the Year candidates, ranked by a weighted defensive score.
     * Score is calculated using rebounds, steals, and blocks.
     * @param players The list of players to evaluate.
     * @param topN The number of DPOY candidates to return.
     * @return List of top N DPOY candidates.
     */
    public static List<Player> getDpoyCandidates(List<Player> players, int topN) {
        return players.stream()
                .sorted((p1, p2) -> {
                    double d1 = 0.5 * p1.getSeasonStats().getReboundsPerGame()
                            + p1.getSeasonStats().getStealsPerGame()
                            + p1.getSeasonStats().getBlocksPerGame();
                    double d2 = 0.5 * p2.getSeasonStats().getReboundsPerGame()
                            + p2.getSeasonStats().getStealsPerGame()
                            + p2.getSeasonStats().getBlocksPerGame();
                    return Double.compare(d2, d1);
                })
                .limit(topN)
                .collect(Collectors.toList());
    }

    /**
     * Prompts user to input a player name and prints matching players.
     * @param players The list of players to search.
     */
    public static void getPlayerByName(List<Player> players) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input player name: ");
        String searchName = scanner.nextLine().toLowerCase();
        players.stream()
                .filter(p -> p.getName().toLowerCase().contains(searchName))
                .forEach(System.out::println);
    }


    /**
     * Interactively creates a new Player object from user input.
     * Validates position and team abbreviation, throws custom exceptions on error.
     * @param scanner Scanner instance for input.
     * @param teamMap Map of team abbreviations to Team objects.
     * @return The newly created Player.
     * @throws UnknownTeamException if team abbreviation is invalid.
     * @throws InvalidPositionException if position string is invalid.
     */
    public static Player addNewPlayer(Scanner scanner, Map<String, Team> teamMap)
            throws UnknownTeamException, InvalidPositionException {

        System.out.print("Player name: ");
        String name = scanner.nextLine();

        System.out.print("Position (C, C_F, F, F_C, F_G, G, G_F): ");
        String pos = scanner.nextLine().toUpperCase();
        Position position;
        try {
            position = Position.valueOf(pos);
        } catch (IllegalArgumentException e) {
            throw new InvalidPositionException(pos, name);
        }

        System.out.print("Team 3-letter abbreviation (e.g., PHX): ");
        String teamAbbr = scanner.nextLine().toUpperCase();

        Team playerTeam = teamMap.get(teamAbbr);
        if (playerTeam == null) {
            throw new UnknownTeamException(teamAbbr, name);
        }

        System.out.print("Games played: ");
        int gp = scanner.nextInt();

        System.out.print("Points per game: ");
        double pts = scanner.nextDouble();

        System.out.print("Rebounds per game: ");
        double reb = scanner.nextDouble();

        System.out.print("Assists per game: ");
        double ast = scanner.nextDouble();

        System.out.print("Steals per game: ");
        double stl = scanner.nextDouble();

        System.out.print("Blocks per game: ");
        double blk = scanner.nextDouble();

        System.out.print("Turnovers per game: ");
        double tov = scanner.nextDouble();
        scanner.nextLine();

        SeasonStats stats = new SeasonStats(gp, pts, ast, reb, stl, blk, tov);
        return new Player(name, position, playerTeam, stats);
    }

    /**
     * Prints playoff and play-in teams from both conferences based on win records.
     * Top 6 teams qualify for playoffs; next 4 teams qualify for play-in tournament.
     * @param teams The full list of teams to evaluate.
     */
    public static void printPlayoffAndPlayInTeams(List<Team> teams) {
        List<Team> eastern = teams.stream()
                .filter(t -> t.getConference() == Conference.EASTERN)
                .sorted(Comparator.comparingInt(Team::getWins).reversed())
                .toList();

        List<Team> western = teams.stream()
                .filter(t -> t.getConference() == Conference.WESTERN)
                .sorted(Comparator.comparingInt(Team::getWins).reversed())
                .toList();

        System.out.println("\nEastern Conference:");
        printPlayoffAndPlayInBracket(eastern);

        System.out.println("\nWestern Conference:");
        printPlayoffAndPlayInBracket(western);
    }

    /**
     * Helper method to print playoff and play-in brackets for a conference.
     * @param sortedTeams List of teams sorted by win count.
     */
    private static void printPlayoffAndPlayInBracket(List<Team> sortedTeams) {
        System.out.println("Playoff teams:");
        for (int i = 0; i < 6 && i < sortedTeams.size(); i++) {
            Team t = sortedTeams.get(i);
            System.out.println((i + 1) + ". " + t.getName() + " (" + t.getWins() + "W - " + t.getLosses() + "L)");
        }

        System.out.println("Play-in teams:");
        for (int i = 6; i < 10 && i < sortedTeams.size(); i++) {
            Team t = sortedTeams.get(i);
            System.out.println((i + 1) + ". " + t.getName() + " (" + t.getWins() + "W - " + t.getLosses() + "L)");
        }
    }
}
