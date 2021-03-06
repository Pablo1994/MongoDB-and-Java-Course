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

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.net.UnknownHostException;

public class FindAndModifyTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoCollection collection = createCollection();
        collection.drop();

        final String counterId = "abc";
        int first;
        int numNeeded;


        System.out.println();
    }

    private static int getRange(String id, int range, DBCollection collection) {
        DBObject doc = collection.findAndModify(
                new BasicDBObject("_id", id), null, null, false,
                new BasicDBObject("$inc", new BasicDBObject("counter", range)),
                true, true);
        return (Integer) doc.get("counter") - range + 1;
   }

    private static MongoCollection createCollection() throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        return db.getCollection("FindModifyTest");
    }

    private static void printCollection(final DBCollection collection) {
        try (DBCursor cursor = collection.find().sort(new BasicDBObject("_id", 1))) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        }

    }
}
