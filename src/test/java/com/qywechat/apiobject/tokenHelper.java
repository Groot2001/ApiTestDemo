package com.qywechat.apiobject;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class tokenHelper {
    public static Response getAccessToken(String corpid, String corpsecret){
        Response accessResponse = given()
                .param("corpid", corpid)
                .param("corpsecret", corpsecret)
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response();
        return accessResponse;
    }
}
