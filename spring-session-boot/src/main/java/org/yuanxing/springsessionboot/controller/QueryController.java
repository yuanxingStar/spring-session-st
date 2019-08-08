package org.yuanxing.springsessionboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yuanxing.springsessionboot.bean.ReturnData;
import org.yuanxing.springsessionboot.bean.StatusCode;
import org.yuanxing.springsessionboot.bean.User;
import org.yuanxing.springsessionboot.bean.UserErrorException;

/**
 * @author yuanxing
 * @create 2019-08-07 14:23
 * @see （1） 一个查询Controller 用于测试 用户session信息
 */

@RestController
@RequestMapping("/api/query")
public class QueryController {

    @RequestMapping(value = "/getUserInfo")
    public ReturnData get(String userid)
    {
        User user = new User("password",
                             "username",
                             "1",
                             "test",
                             18,
                             "ljs");
        if ("1".equals(userid))
        {
            return new ReturnData(StatusCode.REQUEST_SUCCESS, user, "查询成功！");
        }
        else
        {
            throw new UserErrorException(StatusCode.USER_NOT_EXIST, "用户不存在！");
        }
    }
}
