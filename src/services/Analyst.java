package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Analyst {
    Connection connection;
    PreparedStatement st;
    ResultSet rs;
    MongoClient mc;
    Document commandResult;
    MongoDatabase db;
    List<Document> doc = new ArrayList<>();

    public Analyst() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/testdb","root", "aseel2000");
        st = connection.prepareStatement("select max(grade), avg(grade), min(grade) from Class");
        rs = st.executeQuery();
    }

    public void analyse() throws SQLException {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = mongoClient.getDatabase("Analytics");
            try {
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                commandResult = database.runCommand(command);
                System.out.println("Connected successfully to server.");

                mc = MongoClients.create();
                db = mc.getDatabase("Analytics");
                MongoCollection<Document> collection = db.getCollection("results");

                while (rs.next()) {
                    String max = rs.getString("max(grade)");
                    doc.add(new Document("Max", max));

                    String min = rs.getString("min(grade)");
                    doc.add(new Document("Min", min));

                    String avg = rs.getString("avg(grade)");
                    doc.add(new Document("Average", avg));
                }
                collection.insertMany(doc);
            } catch (MongoException me) {
                System.err.println("An error occurred while attempting to run a command: " + me);
            }
        }
    }
}