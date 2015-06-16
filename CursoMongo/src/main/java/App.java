import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by pablo on 05/06/15.
 */
public class App {
    public static void main(String[] args) {
        MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
        MongoDatabase db = client.getDatabase("test");
        MongoCollection<Document> coll = db.getCollection("test");
    }
}
