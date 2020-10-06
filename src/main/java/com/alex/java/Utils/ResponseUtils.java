package com.alex.java.Utils;

import org.apache.coyote.Response;

import java.util.List;

public class ResponseUtils {

    public static ResponseObj<Object> successResponse(Object objectList){
        ResponseObj<Object> response = new ResponseObj<>();
        response.setCode(1);
        response.setMessage("已经成功获取了数据");
        response.setData(objectList);
        return response;
    }

    public static ResponseObj<Object> errorResponse(Object error){
        ResponseObj<Object> response = new ResponseObj<>();

        response.setMessage(error.toString());
        response.setCode(-1);
        response.setData(null);

        return response;
    }
}
