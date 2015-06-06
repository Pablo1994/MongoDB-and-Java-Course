import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.orderBy;

/**
 * Created by pablo on 06/06/15.
 */
public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> collection = db.getCollection("grades");

        Bson projection = fields(include("student_id", "score"), excludeId());
        Bson sort = orderBy(ascending("student_id"), ascending("score"));
        MongoCursor<Document> cursor = collection.find(new Document("type", "homework")).sort(sort).projection(projection).iterator();

        int idDummy = -1;
        while (cursor.hasNext()) {
            Document cur = cursor.next();
            int id = cur.getInteger("student_id");
            double score = cur.getDouble("score");
            if (id > idDummy) {
                System.out.println("Entra al if con id: " + id);
                collection.deleteOne(new Document("student_id", id).append("score",score));
                idDummy = id;
            }
        }
    }
}
