package org.yuanxing.springsessionboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.yuanxing.springsessionboot.bean.ReturnData;
import org.yuanxing.springsessionboot.bean.StatusCode;
import org.yuanxing.springsessionboot.bean.User;
import org.yuanxing.springsessionboot.bean.UserErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanxing
 * @create 2019-08-07 9:15
 * @see
 */

@RestController
@RequestMapping("/api/user")
@Log4j2
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,Object> test() {
        Map<String,Object> map = new HashMap<>(16);
        return map;
    }

    @RequestMapping("/login")
    @ResponseBody
    public ReturnData login(HttpServletRequest request, String username, String password)
    {
        log.debug("登陆用户名" + username);
        log.debug("登陆密码  " + password);

        User user = new User("password",
                "username",
                "1",
                "test",
                18,
                "ljs");

        if (user != null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("loginUserId", user.getUserid());
            //session 交由sepringsession进行管理  无需手动将session存入redis中
            //redisTemplate.opsForValue().set("loginUser:" + user.getUserId(), session.getId());

            return new ReturnData(StatusCode.REQUEST_SUCCESS, user, "登录成功！");
        }
        else
        {
            throw new UserErrorException(StatusCode.ACCOUNT_OR_PASSWORD_ERROR, "用户名或密码错误！");
        }
    }


    /**
     *
     * @return
     * @see （1）返回登陆页面
     */
    @RequestMapping("/loginPage")
    public String loginPage(HttpServletRequest request) {
        return "loginPage";
    }


    /**
     * @see （1） 退出登陆
     */
    public ReturnData quitLogin() {
        return null;
    }





}
