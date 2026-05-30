package project;

import java.util.*;

/**
 * Implementation of the MenuManager interface that provides an interactive console menu
 * for managing and displaying NBA player and team statistics.
 */
public class StatsMenuManager implements MenuManager {
    private Scanner scanner;
    private List<Player> players;
    private Map<String, Team> teamMap;

    /**
     * Constructs a StatsMenuManager with the necessary dependencies.
     *
     * @param scanner the Scanner for user input
     * @param players the list of players
     * @param teamMap a map of team abbreviations to Team objects
     */
    public StatsMenuManager(Scanner scanner, List<Player> players, Map<String, Team> teamMap) {
        this.scanner = scanner;
        this.players = players;
        this.teamMap = teamMap;
    }

    /**
     * Displays the main menu and handles user input for various NBA statistics operations.
     */
    @Override
    public void showMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- NBA Stats Menu ---");
            System.out.println("1. List every player");
            System.out.println("2. List every player of a team");
            System.out.println("3. List top players by SPER");
            System.out.println("4. list possible MVP candidates");
            System.out.println("5. list possible DPOY candidates");
            System.out.println("6. Search for a player by name");
            System.out.println("7. Add a new player manually");
            System.out.println("8. List playoff and play-in teams");
            System.out.println("9. Help");
            System.out.println("0. Exit");
            System.out.print("Chosen option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 ->
                        StatsService.printAllPlayers(players);

                case 2 ->
                        StatsService.printPlayersByTeam(players);

                case 3 -> {
                    System.out.println("Top players by SPER:");
                    StatsService.getTopSPERPlayers(players, 10).forEach(p -> {
                        System.out.println(p.getName() +
                                " | SPER: " + String.format("%.2f", p.getSeasonStats().getSper()));
                    });
                }

                case 4 -> {
                    System.out.println("Possible MVP candidates:");
                    StatsService.getMvpCandidates(players, 5).forEach(p -> {
                        System.out.println(p.getName() +
                                " - PTS: " + p.getSeasonStats().getPointsPerGame() +
                                ", AST: " + p.getSeasonStats().getAssistsPerGame() +
                                ", REB: " + p.getSeasonStats().getReboundsPerGame() +
                                ", SPER: " + String.format("%.2f", p.getSeasonStats().getSper()));
                    });
                }

                case 5 -> {
                    System.out.println("Possible DPOY candidates:");
                    StatsService.getDpoyCandidates(players, 5).forEach(p -> {
                        System.out.println(p.getName() +
                                " - STL: " + p.getSeasonStats().getStealsPerGame() +
                                ", BLK: " + p.getSeasonStats().getBlocksPerGame() +
                                ", REB: " + p.getSeasonStats().getReboundsPerGame());
                    });
                }

                case 6 ->
                        StatsService.getPlayerByName(players);

                case 7 -> {
                    try {
                        Player newPlayer = StatsService.addNewPlayer(scanner, teamMap);
                        players.add(newPlayer);
                        System.out.println("Player successfully added: " + newPlayer.getName());
                    } catch (UnknownTeamException | InvalidPositionException e) {
                        System.err.println("Error: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.err.println("Expected a number. Please try again.");
                        scanner.nextLine();
                    }
                }


                case 8 ->
                        StatsService.printPlayoffAndPlayInTeams(new ArrayList<>(teamMap.values()));

                case 9 -> {
                    System.out.println("Help Menu:");
                    System.out.println("1 - List all players and their basic info");
                    System.out.println("2 - List players from a specific team");
                    System.out.println("3 - Show MVP candidates including their SPER");
                    System.out.println("4 - Show DPOY candidates");
                    System.out.println("5 - Search for a player by name");
                    System.out.println("6 - Add a new player manually with stats");
                    System.out.println("7 - Show top 10 players based on SPER (simplified player efficiency rating)");
                    System.out.println("8 - Show playoff and play-in teams");
                    System.out.println("0 - Exit the program");
                    System.out.println("PPG - Points per game, the average number of points a player scores per game");
                    System.out.println("APG - Assists per game, the average number of assists a player makes per game");
                    System.out.println("RPG - Rebounds per game, the average number of rebounds a player grabs per game");
                    System.out.println("SPG - Steals per game, the average number of steals a player gets per game");
                    System.out.println("BPG - Blocks per game, the average number of shots a player blocks per game");
                    System.out.println("TPG - Turnovers per game, the average number of times a player loses the ball per game");
                    System.out.println("SPER - Simplified Player Efficiency Rating. It is calculated with the formula:");
                    System.out.println("SPER = PPG + APG + RPG + SPG + BPG - TPG");
                    System.out.println("It is inspired by the official NBA PER stat but uses a simplified formula for easier use in this project.");
                }

                case 0 ->
                        running = false;

                default ->
                        System.out.println("Invalid choice. Try again.");
            }
        }
        System.out.println("You quit the program.");
        scanner.close();
    }
}
