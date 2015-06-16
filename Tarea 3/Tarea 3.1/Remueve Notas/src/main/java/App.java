import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 16/06/15.
 */
public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> collection = db.getCollection("students");

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()){
            Document cur = cursor.next();
            int id = cur.getInteger("_id");
            List<Document> scores = (List<Document>) cur.get("scores");
            double scorcito = 101;
            for(Document score : scores){
                if("homework".equals(score.getString("type"))) {
                    System.out.println("hola");
                    if(scorcito > score.getDouble("score"))
                        scorcito = score.getDouble("score");
                }
            }
        collection.updateOne(new Document("_id",id),new Document("$pull",new Document("scores",new Document("type","homework").append("score",scorcito))));
        }
    }
}
