package com.zk.mybatisplus.config.interceptor;

import com.zk.mybatisplus.common.utils.TokenUtil;
import com.zk.mybatisplus.mapper.TTenantUserMapper;
import com.zk.mybatisplus.model.TTenantUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 * preHandler：  业务处理器处理请求之前被调用，对用户的request进行处理，若返回值为true，则继续调用后续的拦截器和目标方法；
                 若返回值为false，则终止请求；这里可以加上登录校验，权限拦截等。
 * postHandler： Controller执行后但未返回视图前调用该方法，这里可以对返回用户前的模型数据进行加工处理。
 * afterCompletion：Controller执行后且返回视图后调用，可以得到Controller时的异常信息，这里可以记录操作日志，资源清理等
 */
//拦截器无需增加@Component注解，在WebConfiguration类中使用@Bean注解将拦截器注成bean。
public class AuthInterceptor implements HandlerInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    private TTenantUserMapper userMapper;

    public AuthInterceptor(TTenantUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("拦截器-已进入拦截器！！ preHandle业务执行前");

        //已经登录的用户信息已经存储到session中 此处做登录拦截
        HttpSession session = request.getSession();
        String userName = session.getAttribute("userName") + "";
        String password = session.getAttribute("password") + "";
        TTenantUser user = userMapper.selectUserByNameAndPwd(userName, password);
        if (null == user) {
            logger.info("未登录，跳转到登录页面");
        }

        //已经登录的用户已经把token传到前端 从请求中获取token实现登录认证
        String token=request.getHeader("token");
        boolean verify = TokenUtil.verify(token);
        if(!verify){
            logger.info("token认证失败！跳转到登录页面");
        }
        String url = request.getRequestURI();
        logger.info("拦截url的值：" + url);
        return true;//放行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        int status = response.getStatus();
        logger.info(" postHandle业务执行后 status--" + status);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.info(" afterCompletion请求完成后");
    }

}
