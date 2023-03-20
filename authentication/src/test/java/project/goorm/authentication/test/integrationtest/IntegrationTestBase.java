package project.goorm.authentication.test.integrationtest;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import project.goorm.authentication.common.configuration.annotation.IntegrationTest;
import project.goorm.authentication.common.configuration.nosql.MongoInitialization;
import project.goorm.authentication.common.configuration.rdb.RDBInitialization;

@IntegrationTest
public class IntegrationTestBase {

    @Autowired
    private RDBInitialization RDBInitialization;

    @Autowired
    private MongoInitialization mongoInitialization;

    @AfterEach
    void setUP() {
        RDBInitialization.truncateAllEntity();
        mongoInitialization.clearCollections();
    }
}
