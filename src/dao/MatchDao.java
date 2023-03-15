package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import db.MongoJdbcConnection;
import org.bson.Document;

import java.util.UUID;

public class MatchDao {

    private static MongoCollection<Document> matchCollection;

    public MatchDao() {
        MongoDatabase db = MongoJdbcConnection.getInstance();
        matchCollection = db.getCollection("match");
    }

    public String addMatch(int overs, String firstInningId, String secondInningId, String winnerId) {
        String id = UUID.randomUUID().toString();
        Document document = new Document("_id", id);
        document.append("overs", overs);
        document.append("firstInningId", firstInningId);
        document.append("secondInningId", secondInningId);
        document.append("winnerId", winnerId);
        matchCollection.insertOne(document);
        return id;
    }
}
