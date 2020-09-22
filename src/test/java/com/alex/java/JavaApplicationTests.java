package com.alex.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class JavaApplicationTests {

    // private final Logger logger = LoggerFactory.getLogger(JavaApplicationTests.class);

    @Test
    void logging() {
        
        log.warn("this is a warning ...");
        log.info("info level ...");
        log.error("this is an error");
        log.debug("debug level...");
        log.trace("this is a trace");

    }

}
