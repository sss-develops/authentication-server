package project.goorm.authentication.common.configuration.nosql;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class MongoInitialization {

    private Set<String> collections = new HashSet<>();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void clearCollections() {
        afterEach();
    }

    void afterEach() {
        extractCollections();

        for (String collection : collections) {
            mongoTemplate.dropCollection(collection);
        }
        log.info("Initialization All Collection");
    }

    void extractCollections() {
        this.collections = new HashSet<>();
        collections.addAll(mongoTemplate.getCollectionNames());
    }
}
