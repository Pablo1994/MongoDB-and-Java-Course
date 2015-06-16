/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;

public class UpdateRemoveTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("fieldSelectionTest");
        collection.drop();
        Random rand = new Random();

        // insert 10 documents with two random integers
        for (int i = 0; i < 10; i++) {
            collection.insertOne(
                    new Document()
                            .append("_id", i)
                            .append("x", i));
        }

        //collection.replaceOne(eq("x",5), new Document("_id",5).append("x",20).append("update",true));
        //collection.updateOne(eq("_id", 9), new Document("$set", new Document("x", 20)),new UpdateOptions().upsert(true));
        //collection.updateMany(gte("_id", 5), new Document("$inc", new Document("x", 1)));
        collection.deleteMany(gt("_id",4));

        List<Document> all = collection.find().into(new ArrayList<>());
        for (Document cur : all) {
            System.out.println(cur);
        }
    }
}
