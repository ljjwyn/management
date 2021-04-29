package com.instructional.system.management;

import com.instructional.system.management.server.ScoresServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManagementApplicationTests {

    @Autowired
    private ScoresServer scoresServer;

    @Test
    void contextLoads() {
        System.out.println(scoresServer.getAllScoresCount("12"));
    }

    @Test
    void contextLoadsScocres() {
        System.out.println(scoresServer.getTopTailStudents("12","totalRank"));
    }

}
