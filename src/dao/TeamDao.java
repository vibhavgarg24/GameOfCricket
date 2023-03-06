package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import db.MongoJdbcConnection;
import model.Team;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class TeamDao {

    private static MongoCollection<Document> teamCollection;
    private static ObjectMapper objectMapper;

    public TeamDao() {
        MongoDatabase db = MongoJdbcConnection.getInstance();
        teamCollection = db.getCollection("team");
        objectMapper = new ObjectMapper();
    }

    public Team findById(String id) {
        Document document = teamCollection.find(eq("_id", id)).first();
        Team team;
        try {
            team = objectMapper.readValue(document.toJson(), Team.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return team;
    }

    public Team findByName(String teamName) {
        Document document = teamCollection.find(eq("name", teamName)).first();
        Team team;
        try {
            team = objectMapper.readValue(document.toJson(), Team.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return team;
    }

    public String addTeam(String teamName) {
        String id = UUID.randomUUID().toString();
        Document document = new Document("_id", id);
        document.append("name", teamName);
        document.append("playerIds", new ArrayList<String>());
        teamCollection.insertOne(document);
        return id;
    }

    public void updateTeam(String id, Team team) {
        teamCollection.updateMany(eq("_id", id),
                Updates.combine(
                        Updates.set("name", team.getName()),
                        Updates.set("playerIds", team.getPlayerIds())
                ));
    }
}
