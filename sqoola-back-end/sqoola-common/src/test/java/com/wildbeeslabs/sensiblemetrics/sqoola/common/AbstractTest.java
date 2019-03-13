package com.wildbeeslabs.sensiblemetrics.sqoola.common;

import de.pearl.pem.frontend.mocks.StubSegmentService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static de.pearl.pem.frontend.service.EntryCacheServiceImpl.ENTRY_CACHE;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = TestPemFrontendApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private StubSegmentService segmentService;

    @Autowired
    private CacheManager cacheManager;

    protected MockMvc mockMvc;

    @Before
    public void initAbstractTest() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        RestAssuredMockMvc.mockMvc(mockMvc);
        clearEntryCache();
        segmentService.setEntries(null);
    }

    protected void clearEntryCache() {
        cacheManager.getCache(ENTRY_CACHE).clear();
    }
}
