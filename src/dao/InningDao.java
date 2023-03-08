package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import db.MongoJdbcConnection;
import model.Inning;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class InningDao {

    private static MongoCollection<Document> inningCollection;
    private static ObjectMapper objectMapper;

    public InningDao() {
        MongoDatabase db = MongoJdbcConnection.getInstance();
        inningCollection = db.getCollection("inning");
        objectMapper = new ObjectMapper();
    }

    public Inning findById(String id) {
        Document document = inningCollection.find(eq("_id", id)).first();
        Inning inning;
        try {
            inning = objectMapper.readValue(document.toJson(), Inning.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inning;
    }

    public String addInning(String battingTeamId, String bowlingTeamId) {
        String id = UUID.randomUUID().toString();
        Document document = new Document("_id", id);
        document.append("battingTeamId", battingTeamId);
        document.append("bowlingTeamId", bowlingTeamId);
        document.append("totalRuns", 0);
        document.append("totalWickets", 0);
        document.append("scoreLine", new ArrayList<String>());
        inningCollection.insertOne(document);
        return id;
    }

    public void updateInning(String id, Inning inning) {
        inningCollection.updateMany(eq("_id", id),
                Updates.combine(
                        Updates.set("battingTeamId", inning.getBattingTeamId()),
                        Updates.set("bowlingTeamId", inning.getBowlingTeamId()),
                        Updates.set("totalRuns", inning.getTotalRuns()),
                        Updates.set("totalWickets", inning.getTotalWickets()),
                        Updates.set("scoreLine", inning.getScoreLine())
                ));
    }
}
