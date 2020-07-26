package com.qywechat.apiobject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class departmentApiObject {
    public static Response createDepartment(String name, String accessToken) {
        String createBody = "{" +
                "   \"name\": \""+name+"\"," +
                "   \"parentid\": 1" +
                "}";
        Response createResponse = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response();
        return createResponse;
    }

    public static Response updateDepartment(String id, String name, String accessToken){
        String updateBody = "{" +
                "   \"id\": "+id+",\n" +
                "   \"name\": "+name+",\n" +
                "}";
        Response updateResponse = given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + accessToken)
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response();
        return updateResponse;
    }

    public static Response listDepartment(String departId, String accessToken){
        Response listResponse = given()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+accessToken+"&id="+departId)
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response();
        return listResponse;
    }

    public static Response deleteDepartment(String departId, String accessToken){
        Response deleteResponse = given()
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token=" + accessToken + "&id=" + departId)
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response();
        return deleteResponse;
    }
}
