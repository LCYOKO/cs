package com.xiaomi.cs.common;

/**
 * @author l
 */
public class ResponseConstants {

    /**
     *
     *  BASE_CODE  例如100-500
     *
     *
     */

    public static final int SUCCESS_CODE=200;
    public static final String SUCCESS_MSG="成功";
    public static final int FAIL_CODE=500;
    public static final String FAIL_MSG="失败";

    /**
     *
     *
     *
     *   业务状态码， 后续添加补充！！！
     *
     */
    public static final int LOGIN_SUCCESS=2000;
    public static final String LOGIN_SUCCESS_MGS="登录成功";
    public static final int LOGIN_FAIL=4001;
    public static final String LOGIN_FIAL="账号或密码不正确";
    public static final int USER_NOT_IN=4002;
    public static final String UUSER_NOT_IN_MSG="用户不存在";
    public static final int ADD_TPYE_FAIL=4003;
    public static final String ADD_TPYE_FAIL_MSG="该问题分类已存在";

}
