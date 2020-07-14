package test_service.mock;

import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.filters.ResponseFilter;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static common.Util.JsonMock;

public class MockTest {
    private static BrowserMobProxy proxy;

    @BeforeAll
    static void beforeAll() {
        proxy = new BrowserMobProxyServer();
        proxy.start(8082);
    }

    @BeforeEach
    void before(){
        proxy.newHar("dump");
    }

    @Test
    void mockOnProxy() throws InterruptedException {
        Map<String, Object> map = new HashMap<String, Object>();
        proxy.addResponseFilter(new ResponseFilter() {
            @Override
            public void filterResponse(HttpResponse httpResponse, HttpMessageContents httpMessageContents, HttpMessageInfo httpMessageInfo) {
                System.out.println(httpMessageContents.getTextContents());

                JsonMock(map, httpMessageContents.getTextContents());
                httpMessageContents.setTextContents(httpMessageContents.getTextContents().replace("霍格沃兹", "mock"));
            }
        });
        Thread.sleep(10000);
    }

    @AfterEach
    void after(){
        Har har = proxy.endHar();
    }

    @AfterAll
    static void afterAll(){
        proxy.stop();
    }
}
