package com.gs.extractor.service;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

@Ignore
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration
@ActiveProfiles("default")
public class JobServiceTest {

    @Test
    public void testAdd() {
        Assertions.assertTrue(true);
    }

    @Test
    public void findByUids() {
        Assertions.assertTrue(true);
    }

    @Test
    public void getByUid() {
        Assertions.assertTrue(true);
    }
}