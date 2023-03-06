package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import db.MongoJdbcConnection;
import enums.PlayerType;
import model.Player;
import org.bson.Document;

import java.io.IOException;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class PlayerDao {

    private static MongoCollection<Document> playerCollection;
    private static ObjectMapper objectMapper;

    public PlayerDao() {
        MongoDatabase db = MongoJdbcConnection.getInstance();
        playerCollection = db.getCollection("player");
        objectMapper = new ObjectMapper();
    }

    public Player findById(String id) {
        Document document = playerCollection.find(eq("_id", id)).first();
        Player player;
        try {
            player = objectMapper.readValue(document.toJson(), Player.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return player;
    }

    public Player getDefaultPlayer(String id) {
        Player player = findById(id);
        player.setRunsScored(0);
        player.setRunsGiven(0);
        player.setWickets(0);
        player.setBallsPlayed(0);
        player.setBallsBowled(0);
        return player;
    }

    public String addPlayer(String playerName, PlayerType playerType) {
        String id = UUID.randomUUID().toString();
        Document document = new Document("_id", id);
        document.append("name", playerName);
        document.append("runsScored", 0);
        document.append("runsGiven", 0);
        document.append("wickets", 0);
        document.append("ballsPlayed", 0);
        document.append("ballsBowled", 0);
        document.append("playerType", playerType);
        playerCollection.insertOne(document);
        return id;
    }

    public void updatePlayer(String id, Player player) {
        playerCollection.updateMany(eq("_id", id),
                Updates.combine(
                        Updates.set("name", player.getName()),
                        Updates.set("runsScored", player.getRunsScored()),
                        Updates.set("runsGiven", player.getRunsGiven()),
                        Updates.set("wickets", player.getWickets()),
                        Updates.set("ballsPlayed", player.getBallsPlayed()),
                        Updates.set("ballsBowled", player.getBallsBowled()),
                        Updates.set("playerType", player.getPlayerType())

                ));
    }
}
