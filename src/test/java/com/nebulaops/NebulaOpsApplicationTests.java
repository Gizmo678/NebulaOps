package com.nebulaops;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Assuming we might add a test profile later
class NebulaOpsApplicationTests {

    // Ignoring context loads test for now since DB is not running during simple build
    // @Test
    // void contextLoads() {
    // }

}
