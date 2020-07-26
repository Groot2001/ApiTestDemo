package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Util {
    public static void JsonMock(Map<String, Object> map, String jsonText) {
        try{
            JSONObject jsonObj = new JSONObject(jsonText);
            Iterator<String> keys=jsonObj.keys();
            while(keys.hasNext()){
                String key = keys.next();
                map.put(key, jsonObj.opt(key));
//                System.out.println(key+": " + jsonObj.get(key).toString());
                if (jsonObj.optJSONArray(key) instanceof JSONArray) {
                    JSONArray jsonArray = jsonObj.getJSONArray(key);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonArrayObj = jsonArray.getJSONObject(i);
                        String jsonArrayObjString = jsonArrayObj.toString();
                        System.out.println("数组"+jsonArrayObjString);
                        JsonMock(map,jsonArrayObjString);
                    }
                }
                if (jsonObj.optJSONObject(key) instanceof JSONObject) {
                    System.out.println("对象"+jsonObj.getJSONObject(key).toString());
                    JsonMock(map,jsonObj.getJSONObject(key).toString());
                }
                if (jsonObj.optNumber(key) instanceof Number){
                    System.out.println("数字"+jsonObj.getNumber(key));
                }
                if (jsonObj.optString(key) instanceof String){
                    System.out.println("字符串"+jsonObj.getString(key));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("set66666666666666666666666"+map);
    }
}
