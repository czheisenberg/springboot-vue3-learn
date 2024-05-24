package com.ctfer.wiki.resp;

public class CommonResp<T> {
//    通用的返回类型 CommonResp, 以后所有后端接口的返回值全部都是 CommonResp 类型
    /**
    *
    * 业务上的成功或失败
    * */
    private boolean success = true;

    /**
    * 返回信息
    * */
    private String message;

    /**
    * 返回泛型数据，自定义类型
    * */
    private T content;
    // ex
//    {
//        success:true,
//        message: "登录成功",
//        content:{"登录用户信息}
//
//    }

    public boolean getSuccess(){return success;}

    public void setSuccess(boolean success){this.success = success;}

    public String getMessage(){return message;}

    public void setMessage(String message){this.message = message;}

    public T getContent(){return content;}

    public void setContent(T content){this.content = content;}

}
