package project;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for reading the JSON file.
 */
public class PlayerDataReader {

    /**
     * Loads players from a JSON file and creates a list of {@link Player} objects.
     * It maps each player to their respective {@link Team} using the provided team map.
     *
     * @param filePath the path to the JSON file containing player data.
     * @param teamMap a map of team abbreviations to {@link Team} objects.
     * @return a list of valid {@link Player} instances that were successfully parsed and mapped.
     */
    public static List<Player> loadPlayersFromJson(String filePath, Map<String, Team> teamMap) {
        List<Player> players = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement elem : jsonArray) {
                JsonObject obj = elem.getAsJsonObject();

                String name = obj.get("NAME").getAsString();
                String teamAbbr = obj.get("TEAM").getAsString().toUpperCase();
                String pos = obj.get("POS").getAsString().replace("-","_");
                int gamesPlayed = obj.get("GP").getAsInt();

                double pts = obj.get("PpG").getAsDouble();
                double reb = obj.get("RpG").getAsDouble();
                double ast = obj.get("ApG").getAsDouble();
                double stl = obj.get("SpG").getAsDouble();
                double blk = obj.get("BpG").getAsDouble();
                double tov = obj.get("TOpG").getAsDouble();

                SeasonStats stats = new SeasonStats(gamesPlayed, pts, ast, reb, stl, blk, tov);

                Team playerTeam = teamMap.get(teamAbbr);

                if (playerTeam != null) {
                    Position positionEnum = Position.valueOf(pos);
                    Player player = new Player(name, positionEnum, playerTeam, stats);
                    players.add(player);
                } else {
                    System.err.println("Unknown Team: " + teamAbbr + " player: " + name);
                }
            }

        } catch (Exception e) {
            System.err.println("Error while reading the JSON file: " + e.getMessage());
        }

        return players;
    }
}
