package test_service.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class StubTest {
    private static WireMockServer wireMockServer;

    @BeforeAll
    static void beforeAll(){
        wireMockServer = new WireMockServer(wireMockConfig().port(8089));
        wireMockServer.start();
        configureFor("localhost", 8089);
    }

    @Test
    void stub(){
        stubFor(get(urlEqualTo("/user/d"))
                .withHeader("Accept", equalTo("text/pain"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>d info</response>")));

        //todo: use
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reset();
        stubFor(get(urlEqualTo("/user/d"))
                .withHeader("Accept", equalTo("text/pain"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>exception</response>")));
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void mockOnProxy() throws InterruptedException, IOException {
        stubFor(
                get(urlMatching(".*")).
                        atPriority(10).
                        willReturn(aResponse().proxiedFrom("https://ceshiren.com")));
        Thread.sleep(10000);
        stubFor(
                get(urlMatching("/categories_and_latest")).
                        atPriority(10).
                        willReturn(aResponse().withBody(Files.readAllBytes(Paths.get("G:\\code\\hogwarts_study\\ApiTestDemo\\src\\main\\resources\\mock_local.json")))));
        Thread.sleep(100000);
    }

    @AfterAll
    static void afterAll(){
        wireMockServer.stop();
    }
}
