package io.bnn.mongodbspringboot0211.controller;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;

@RestController
@RequestMapping("/do")
public class MongodbController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/findAll")
    public void findAll(){

        MongoDatabase db = mongoTemplate.getDb();
        logger.info("current db is:{}",db.getName());

        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        logger.info("collections are {}", collectionNames);

        MongoCollection<Document> collection = mongoTemplate.getCollection("student");
        FindIterable<Document> documents = collection.find();
        for (Document d :
                documents) {
            logger.info(d.toJson());
        }
    }
    @GetMapping("/aa")
    public void aa(){
        MongoCollection<Document> collection = mongoTemplate.getCollection("student");
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);
    }

    @GetMapping("/bb")
    public void bb(){
        MongoCollection<Document> collection = mongoTemplate.getCollection("customer");
        Document doc = new Document("firstName", "aaaa")
                .append("lastName", "bbbb");
        collection.insertOne(doc);
    }
}
