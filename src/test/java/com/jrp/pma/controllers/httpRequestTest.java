package com.jrp.pma.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // The test will run with this annotation ONLY if the name of the test package will start with 'com.jrp.pma' like here, this test will run on a random Port as we defined in the arguments
public class httpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; // we can mimic everything we can do in a browser with this method

    @Test
    public void homePageReturnsVersionNumberCorrectly_thenSuccess() {
        String renderHtml =
                this.restTemplate.getForObject("http://localhost:" + port +"/", String.class); // restTemplate will have the all webpage, so it's good for testing webpages and such
        assertEquals(renderHtml.contains("3.3.3"), true);
    }
}
