package project;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The Main is the entry point of the application. It initializes necessary components.
 *
 * Functionality:
 * - Loading team data and creating a mapping of team abbreviations to team objects.
 * - Reading player information from a JSON file and associating it with the teams.
 * - Initializing a menu management system to provide options and handle user interactions.
 */
public class Main {
    /**
     * The main entry point of the program.
     * It loads teams and players, then starts the stats menu.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Loads all the teams
        Map<String, Team> teamMap = Team.createAllTeams();

        // Loads every player from players.json
        List<Player> players = PlayerDataReader.loadPlayersFromJson("players.json", teamMap);
        // Initializes the menu manager
        MenuManager menuManager = new StatsMenuManager(scanner, players, teamMap);
        // Starts the menu
        menuManager.showMenu();
    }
}