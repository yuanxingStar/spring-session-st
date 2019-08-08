package org.yuanxing.springsessionboot.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.yuanxing.springsessionboot.bean.ReturnData;
import org.yuanxing.springsessionboot.bean.StatusCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yuanxing
 * @create 2019-08-07 13:51
 * @see (1) 拦截过滤请求
 */
public class RedisSessionInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @see (1) 从请求的 session中读取loginUserid  判断用户是否登陆
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //无论访问的地址是不是正确的，都进行登录验证，登录成功后的访问再进行分发，404的访问自然会进入到错误控制器中
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") != null) {
            try {
                //验证当前请求的session是否是已登录的session
                String loginSessionId = redisTemplate.opsForValue()
                                                     .get("loginUser:" + (long) session.getAttribute("loginUserId"));
                if (loginSessionId != null && loginSessionId.equals(session.getId())) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response401(response);
        return false;
    }


    /**
     *
     * @param response
     * @see (1) 未找到用户的session信息  返回用户未登陆
     *
     */
    private void response401(HttpServletResponse response)
        {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");

            try
            {
                //采用jackson 将实体类转化为 json字符串
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter()
                        .print(mapper.writeValueAsString(
                                new ReturnData(StatusCode.NEED_LOGIN, null, "用户未登录！")
                        ));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
        {

        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
        {

        }


}
