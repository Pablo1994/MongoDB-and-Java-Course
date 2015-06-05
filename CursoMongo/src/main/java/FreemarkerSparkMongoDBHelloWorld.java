import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bson.Document;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by pablo on 05/06/15.
 */
public class FreemarkerSparkMongoDBHelloWorld {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(FreemarkerHelloWorld.class, "/");
        MongoClient client = new MongoClient();

        MongoDatabase database = client.getDatabase("course");
        final MongoCollection<Document> collection = database.getCollection("hello");

        collection.drop();
        collection.insertOne(new Document("name","MongoDB"));

        get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");

                Document document = collection.find().first();

                helloTemplate.process(document, writer);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
            return writer;
        });
    }
}
