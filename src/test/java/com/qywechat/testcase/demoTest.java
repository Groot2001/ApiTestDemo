package com.qywechat.testcase;

import com.qywechat.apiobject.tokenHelper;
import com.qywechat.task.envTask;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.qywechat.apiobject.departmentApiObject;

import java.util.ArrayList;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class demoTest {
    static String accessToken;
    private static final Logger logger = LoggerFactory.getLogger(demoTest.class);
    @BeforeAll
    static void beforeAll() {
        accessToken = tokenHelper
                .getAccessToken("ww256535a80314e468","at3_GXEzi1rTd2g8n1Hy8KfkynY8l4cBXpLVbgKLsPA")
                .path("access_token");

    }

    @BeforeEach
    @AfterEach
    void envClear(){
        envTask.departmentClear(accessToken);
    }

    @Test
    @Description("创建部门")
    //@RepeatedTest(20)
    //@Execution(ExecutionMode.CONCURRENT)
    void createDepart(){
        String name = "111111";
        Response createResp = departmentApiObject.createDepartment(name, accessToken);
        assertEquals("0", createResp.path("errcode").toString());
    }

    @Test
    @Description("更新部门")
    void updateDepart(){
        String name = "gz";
        String departId = departmentApiObject.createDepartment(name, accessToken).path("id").toString();
        Response updateDepartment = departmentApiObject.updateDepartment(departId, name, accessToken);
        assertEquals("0", updateDepartment.path("errcode").toString());
    }

    @Test
    @Description("查询部门")
    void listDepart(){
        Response ListResp = departmentApiObject.listDepartment("", accessToken);
        assertEquals("0", ListResp.path("errcode").toString() );

    }

    @Test
    @Description("删除部门")
    void deleDepart(){
        String name = "sz";
        String departId = departmentApiObject.createDepartment(name, accessToken).path("id").toString();
        Response deleteResponse = departmentApiObject.deleteDepartment(departId + "", accessToken);
        assertEquals("0", deleteResponse.path("errcode").toString());
    }
}
