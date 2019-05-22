package com.xrr.springboot_mybatis.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: xue.rongrong
 * @Date: 2019/5/21 15:56
 */
public class ServiceResult implements Serializable {

    /**
     * 返回的信息
     */
    private String message;
    /**
     * 执行是否成功
     */
    private Boolean isSuccess;
    /**
     * 返回数据
     */
    private Map<String,Object> data = new HashMap<String,Object>();
    public ServiceResult(){}
    public ServiceResult(Boolean isSuccess){
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void addData(String key, Object value){
        data.put(key, value);
    }

    public String toJSON(ServiceResult serviceResult){
        Gson gson=new Gson();
        return gson.toJson(serviceResult);
    }

    public static void main(String[] args){
        ServiceResult res = new ServiceResult(false);
        res.setMessage("对不起，您没有操作权限");
        res.addData("1",1);
        System.out.println(res.toJSON(res));
    }
}
