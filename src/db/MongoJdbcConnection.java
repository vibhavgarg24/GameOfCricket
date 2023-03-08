package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoJdbcConnection {

    private static MongoDatabase database;

    public static MongoDatabase getInstance() {
        if (database == null) {
            String uri = "mongodb://localhost:27017";
            MongoClient mongoClient = MongoClients.create(uri);
            try {
                database = mongoClient.getDatabase("cricket");
            } catch (Exception e) {
                System.out.println("exception");
            }
        }
        return database;
    }
}
