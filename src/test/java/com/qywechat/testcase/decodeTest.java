package com.qywechat.testcase;

import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class decodeTest {
    @Test
    void decode(){
        given()
                .filter((req, resp, ctx)->{
                    Response originResponse = ctx.next(req, resp);
                    byte[] decode = Base64.getDecoder().decode(originResponse.getBody().asString().replace("\n", ""));
                    byte[] decode2 = Base64.getDecoder().decode(new String(decode).replace("\n", ""));
                    Response newResponse = new ResponseBuilder().clone(originResponse).setBody(decode2).build();
                    return newResponse;
                })
                .when()
                .get("http://shell.ceshiren.com:8000/encode2.json")
                .then()
                .log().body().body("category_list.categories[0].description", equalTo("霍格沃兹测试学院官方文章"));
    }
}
