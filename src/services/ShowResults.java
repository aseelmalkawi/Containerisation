package services;

import com.mongodb.client.*;
import org.bson.Document;

import java.sql.SQLException;

public class ShowResults {
    static Analyst analyst;
    MongoClient mc;
    MongoDatabase db;

    public ShowResults() throws SQLException {
        analyst = new Analyst();
        analyst.analyse();

        mc = MongoClients.create();
        db = mc.getDatabase("Analytics");
        MongoCollection<Document> collection = db.getCollection("results");

        FindIterable<Document> result = collection.find();
        for (Document doc : result){
            System.out.println(doc.toJson());
        }
    }
}