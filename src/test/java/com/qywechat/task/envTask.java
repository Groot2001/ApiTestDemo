package com.qywechat.task;

import com.qywechat.apiobject.departmentApiObject;
import io.restassured.response.Response;

import java.util.ArrayList;

public class envTask {
    public static void departmentClear(String accessToken){
        Response listDepartment = departmentApiObject.listDepartment("", accessToken);
        ArrayList<Integer> departIdList = listDepartment.path("department.id");
        for (int id:departIdList) {
            if (1==id){ //父节点跳过删除
                continue;
            }
            departmentApiObject.deleteDepartment(id+"", accessToken);
        }
    }
}
