package org.yuanxing.springsessionboot.bean;

/**
 * @author yuanxing
 * @create 2019-08-07 9:21
 * @see
 */
public class StatusCode {

    public static String REQUEST_SUCCESS = "200";
    /**
     * 用户名或密码错误
     */
    public static String ACCOUNT_OR_PASSWORD_ERROR = "500";
    /**
     * 用户未登陆
     */
    public static String NEED_LOGIN = "501";
    /**
     * 用户不存在
     */
    public static String USER_NOT_EXIST = "502";
}
